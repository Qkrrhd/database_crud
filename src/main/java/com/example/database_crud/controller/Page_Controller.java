package com.example.database_crud.controller;


import com.example.database_crud.dto.UserDto;
import com.example.database_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller //Controller의 역할을 수행한다고 명시 -> 사용자의 요청을 처리한 후, 지정된 뷰에 모델 객체를 넘겨줌
public class Page_Controller {
	@Autowired // 필요한 의존 객체의 타입에 해당하는 Bean을 찾아 주입
	UserService userService;
	@RequestMapping(value = "/", method = RequestMethod.GET) // 어떤 Controller, 어떤 메소드가 처리할지 맵핑, value: url 값, method: HTTP Request 메소드 값
	public ModelAndView mian_page(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("user/user_list"); //new 기본 생성자를 통해 ModelAndView 생성

		List<UserDto> user_list = userService.select_all(); // user list 내용 모두 선택
		mv.addObject("user_list",user_list); //mv에 유저리스트 넣음
		return mv; // user list 를 가진 mv 반환
	}
}
