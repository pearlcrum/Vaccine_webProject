package kr.or.connect.vaccine.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class vaccineLogin {
	// 로그인 폼을 요청하면 해당 요청을 처리하는 일과 로그인 해주세요 요청하면 로그인 처리
		 @GetMapping(path="/login")
			public String login() {
				return "login";
			}
			
	     @PostMapping(path="/login") // loginform에서 보낸 매서드 post와 액션 login이 같아야만 해당 부분이 수행 될 것이다.
			public String login(@RequestParam(name="password", required=true) String passwd, 
					@RequestParam(name="theEmail",required=true) String id,
					HttpSession session,
					RedirectAttributes redirectAttr) {
				//암호 일치 시 세션에 로그인 정보 저장한다.
				if("원하는 비밀번호".equals(passwd)&& "원하는 아이디".equals(id)) {
					session.setAttribute("isAdmin", "true");
				}else {
					redirectAttr.addFlashAttribute("errorMessage","아이디 혹은 암호가 틀렸습니다.");
					return "redirect:/login";
					//암호가 틀린 경우 redirectAttr는 dispatcherServlet이 관리하는 flashMap에 값을 저장한다.
					//이런 방식을 사용하면 딱 한번만 값을 유지할 목적으로 사용할 수 있다.
					//forward는 하나의 요청이었기에 유지시키고 싶은 값을 request라는 영역에 담으나
					//redirect는 redirectAttr사용한다.
				}
				return "redirect:/main";
			}
			
	    @GetMapping(path="/logout") 
			public String login(HttpSession session) {
				session.removeAttribute("isAdmin"); //remove attribute에 세션 이름만 넘겨주면 삭제 가능
				return "redirect:/infoinsert";
			}
}
