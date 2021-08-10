package com.example.database_crud.dao;

import com.example.database_crud.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserDao {

    //데이터베이스 유저 아이디
    String db_user = "root";
    //데이터베이스 유저 비밀번호
    String db_pw = "1340";
    //데이터베이스 url
    String db_url = "localhost:3306";
    //데이터베이스 명
    String select_db = "test";
    //테이블 명
    String table_name = "study";
    //유저 전체선택 함수
    public void insert(UserDto user){
    Connection conn = null;
    PreparedStatement pstmt = null;

        try{
        // 1. 드라이버 로딩
        Class.forName("com.mysql.jdbc.Driver");

        // 2. 연결하기
        String url = "jdbc:mysql://"+db_url+"/"+select_db;
        conn = DriverManager.getConnection(url, db_user, db_pw);

        // 3. SQL 쿼리 준비
        //쿼리를 생성하는것 엔 여러 방법이 있습니다. 텍스트로 직접입력하는것 부터 , 스트링 빌더를 이용 하거나 또는
        //prepareStatement 등을 이용하여 쿼리를 생성 하는 것 입니다. 테이블 컬럼의 순서와 유형에 주의해서 사용 해주세요.
        //스프링에선 여러 sql 접근을 위한 라이브러리 를 제공 합니다. 향후 마이바티스또는 spring jpa 등을 이용 하면 좀더 쉽게 데이터베이스를 활용할수있습니다.
        String sql = "INSERT INTO "+table_name +" VALUES (?,?,?,?)";
        pstmt = conn.prepareStatement(sql);

        // 4. 데이터 binding
        pstmt.setString(1, user.getName());
        pstmt.setInt(2, user.getAge());
        pstmt.setString(3, user.getId());
        pstmt.setString(4, user.getPasswd());

        // 5. 쿼리 실행 및 결과 처리
        // SELECT와 달리 INSERT는 반환되는 데이터들이 없으므로
        // ResultSet 객체가 필요 없고, 바로 pstmt.executeUpdate()메서드를 호출하면 됩니다.
        // INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며
        // SELECT에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
        // @return     int - 몇 개의 row가 영향을 미쳤는지를 반환
        int count = pstmt.executeUpdate();
        if( count == 0 ){
            System.out.println("데이터 입력 실패");
        }
        else{
            System.out.println("데이터 입력 성공");
        }
    }
        catch( ClassNotFoundException e){
        System.out.println("드라이버 로딩 실패");
    }
        catch( SQLException e){
        System.out.println("에러 " + e);
    }
        finally{
        try{
            if( conn != null && !conn.isClosed()){
                conn.close();
            }
            if( pstmt != null && !pstmt.isClosed()){
                pstmt.close();
            }
        }
        catch( SQLException e){
            e.printStackTrace();
        }
    }
}
    //유저 전체선택 함수
    public List<UserDto> select_all(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<UserDto> user_list = new ArrayList<>();
        try{
            // 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mysql://"+db_url+"/"+select_db;
            conn = DriverManager.getConnection(url, db_user, db_pw);

            // 3. 쿼리 수행을 위한 Statement 객체 생성
            stmt = conn.createStatement();

            // 4. SQL 쿼리 작성
            // 주의사항
            // 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
            // 2) SELECT 할 때 * 으로 모든 칼럼을 가져오는 것보다
            //   가져와야 할 칼럼을 직접 명시해주는 것이 좋다.
            // 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업 하는 것은 권하지 않음
            // 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의 !!
            String sql = "SELECT id,name,age, passwd  FROM "+table_name;

            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
            rs = stmt.executeQuery(sql);

            // 6. 실행결과 출력하기
            while(rs.next()){
                // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
                // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
                String id = rs.getString(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String passwd = rs.getString(4);

                UserDto userDto = new UserDto();

                userDto.setId(id);
                userDto.setName(name);
                userDto.setAge(age);
                userDto.setPasswd(passwd);

                System.out.println(userDto.toString());

                user_list.add(userDto);
            }
        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( stmt != null && !stmt.isClosed()){
                    stmt.close();
                }
                if( rs != null && !rs.isClosed()){
                    rs.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
        return  user_list;
    }
    //유저 단일 선택 함수
    public UserDto select_one(String user_id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        UserDto userDto = new UserDto();
        try{
            // 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mysql://"+db_url+"/"+select_db;
            conn = DriverManager.getConnection(url, db_user, db_pw);

            // 3. 쿼리 수행을 위한 Statement 객체 생성
            stmt = conn.createStatement();

            // 4. SQL 쿼리 작성
            // 주의사항
            // 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
            // 2) SELECT 할 때 * 으로 모든 칼럼을 가져오는 것보다
            //   가져와야 할 칼럼을 직접 명시해주는 것이 좋다.
            // 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업 하는 것은 권하지 않음
            // 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의 !!
            String sql = "SELECT id,name,age, passwd   FROM "+table_name +" where id= '"+user_id+"'";

            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
            rs = stmt.executeQuery(sql);

            // 6. 실행결과 출력하기
            rs.next();
                // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
                // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
                String id = rs.getString(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String passwd = rs.getString(4);

                userDto.setId(id);
                userDto.setName(name);
                userDto.setAge(age);
                userDto.setPasswd(passwd);
        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( stmt != null && !stmt.isClosed()){
                    stmt.close();
                }
                if( rs != null && !rs.isClosed()){
                    rs.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
        return  userDto;
    }
    public void update(UserDto user) {
        Connection conn = null;
        PreparedStatement  ptmt = null;
        try{
            // 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 연결하기
            String url = "jdbc:mysql://"+db_url+"/"+select_db;
            conn = DriverManager.getConnection(url, db_user, db_pw);
            // 3. SQL 쿼리 작성
            String sql = "update "+table_name+" set name =?, age=?,passwd =?  where id =?";
            // 4. 쿼리 수행을 위한 Statement 객체 생성
            ptmt = conn.prepareStatement (sql);
            ptmt.setString (1, user.getName());
            ptmt.setInt (2, user.getAge());
            ptmt.setString (3, user.getPasswd());
            ptmt.setString (4, user.getId());
            // 5. 쿼리 수행
            ptmt.executeUpdate ();
        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
        return;
    }
    //유저 삭제 함수
    public void delete(String id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<UserDto> user_list = new ArrayList<>();
        try{
            // 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mysql://"+db_url+"/"+select_db;
            conn = DriverManager.getConnection(url, db_user, db_pw);

            // 3. 쿼리 수행을 위한 Statement 객체 생성
            stmt = conn.createStatement();
            StringBuilder sb = new StringBuilder();
            //쿼리를 빌더 할때의 주의사항은 해당 쿼리에서 작은 따옴표를 넣지않으면 id 대입값을 컬럼으로 인식해버려서 오류가 난다.
            String sql = sb.append("delete from " + table_name + " where id = '")
                .append(id)
                .append("';")
                .toString();
            stmt.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}