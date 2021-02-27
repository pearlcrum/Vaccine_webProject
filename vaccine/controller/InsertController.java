package kr.or.connect.vaccine.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dao.MemberDao;
import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.service.VaccineService;

@Controller
public class InsertController {

	@Autowired
	VaccineService vaccineService;
	
	@GetMapping(path="/infoInsert")
	public String infoInsert(ModelMap model,HttpSession session,
			RedirectAttributes redirectAttr) {
		if(session.getAttribute("isAdmin")==null) {

			redirectAttr.addFlashAttribute("errorMessage","로그인이 필요합니다.");
	 		return "redirect:/login";
		}else {
		
		List<Member> list = vaccineService.getMembers(0); //최초 10개만 보여줄 것이다.
		model.addAttribute("list", list);//jsp로 list 값 보내기.
		return "infoInsert";
		}
	}
	//path의 url로 갔을때 반환할 값이다.
	
	@PostMapping(path="/insertall")
	public String selectAll(@RequestParam(name = "age", required = true) String value1,
			@RequestParam(name = "gender", required = true) String value2,
			@RequestParam(name = "Vaccine", required = true) String value3,
			ModelMap modelMap) {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//Bean Facotry를 상속받고 있는 ApplicationContext가 pre loading 해서 즉시 인스턴스를 만든다.(오브젝트 생성, 관계 설정)
		MemberDao memberDao= ac.getBean(MemberDao.class);
		
		Member member =new Member();
		member.setMemberAge(value1);
		member.setMemberSex(value2);
		member.setMemberVac(value3);
		
		//날짜 넣기
//		SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date date=new Date();
//		String s=date.toString();
//		Date now=format1.format(s);	
		member.setMemberDate(new Date());
		
		int count=memberDao.insert(member);
		//@requestParam, mapping된 메소드의 argument에 붙일 수 있는 어노테이션
		// name에는 http parameter의 name과 매핑되고, required는 필수인지 아닌지 판단한다.
		//@pathvariable의 경우 실제 url에서 ?변수명=값 같이 값을 넘겨올 때 path에서 넘겨온 값을 받기위해서 사용하는 에노테이션
		//변수명을 받기 위해 placeholder가 필요하다. 사용자가 넣어준 name과 placeholder의 이름이 일치해야지 해당 값을 채워준다.
		//@request header의 경우 요청 정보의 header를 읽어올 수 있게 된다.
		modelMap.addAttribute("value1", value1);
		modelMap.addAttribute("value2", value2);
		modelMap.addAttribute("value3", value3);
		modelMap.addAttribute("count",count);
		//modelmap이라는 객체가 requestscope
		return "redirect:infoInsert";
		//return 값은 viewname
	}
	@GetMapping(path="/delete")
	public String delete(@RequestParam(name="id", required=true) int id, 
			             @SessionAttribute("isAdmin") String isAdmin,//세션이름으로 값을 꺼내고 있다.
			             HttpServletRequest request,
			             RedirectAttributes redirectAttr) {
		if(isAdmin == null || !"true".equals(isAdmin)) { // 세션값이 true가 아닐 경우
			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
			return "redirect:/login";
		}
		String clientIp = request.getRemoteAddr(); //로그 테이블 삭제
		vaccineService.deleteMember(id, clientIp);
		return "redirect:/infoInsert";		
	}
}
