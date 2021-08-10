package com.example.database_crud.dto;

//dto는 컨트롤러와 서비스 dao 간에 데이터를 주고받을수 있게 도와주는 데이터전송객체를 말합니다.
public class UserDto {

    private String id;

    private String name;

    private int age;

    private String passwd;


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':'" + id + '\'' +
                ", 'name':'" + name + '\'' +
                ", 'age':" + age +
                ", 'passwd':'" + passwd + '\'' +
                '}';
    }
}