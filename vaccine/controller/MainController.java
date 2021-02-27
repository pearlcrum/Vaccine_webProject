package kr.or.connect.vaccine.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.service.VaccineService;

@Controller
public class MainController {

	@Autowired
	VaccineService vaccineService;
	// 로그인 폼을 요청하면 해당 요청을 처리하는 일과 로그인 해주세요 요청하면 로그인 처리
	 @GetMapping(path="/main")
	 	public String mainFunc(HttpSession session,
								RedirectAttributes redirectAttr)
	 	{
		 	
		 	if(session.getAttribute("isAdmin")==null) {

				redirectAttr.addFlashAttribute("errorMessage","로그인이 필요합니다.");
		 		return "redirect:/login";
			}else {
				return "main";
				//암호가 틀린 경우 redirectAttr는 dispatcherServlet이 관리하는 flashMap에 값을 저장한다.
				//이런 방식을 사용하면 딱 한번만 값을 유지할 목적으로 사용할 수 있다.
				//forward는 하나의 요청이었기에 유지시키고 싶은 값을 request라는 영역에 담으나
				//redirect는 redirectAttr사용한다.
			}
	 	}
	
	 @GetMapping(path="/addPatient")
		public String mainAdd() {
			 //ModelMap model=new ModelMap();
			 return "redirect:infoInsert";
		}
	 @GetMapping(path="/selectPatient")
		public String mainSelect() {
		 	//ModelMap model=new ModelMap();
			return "redirect:selectAll";
		}
	 @GetMapping(path="/vaccinePriceAddition")
		public String mainPricePredict() {
			return "redirect:vaccinePrice";
		}
	 @GetMapping(path="/coldvaccine")
		public String mainVaccinePredict() {
			return "redirect:coldPredict";
		}
}
