package kr.or.connect.vaccine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dao.VaccinePriceDao;
import kr.or.connect.vaccine.dto.VaccinePrice;
import kr.or.connect.vaccine.service.VaccineService;

@Controller
public class VaccinePriceController {

	@Autowired
	VaccineService vaccineService;
	
	@GetMapping(path="/vaccinePrice")
	public String vaccinePrice(ModelMap model,HttpSession session,
			RedirectAttributes redirectAttr) {
		
		if(session.getAttribute("isAdmin")==null) {

			redirectAttr.addFlashAttribute("errorMessage","로그인이 필요합니다.");
	 		return "redirect:/login";
		}else {
		List<VaccinePrice> list = vaccineService.getPrices(); //최초 10개만 보여줄 것이다.
		boolean a2019=true, a2020=true,a2021=true,a2022=true,a2023=true,a2024=true;
		for(VaccinePrice price:list)
		{
			int a=price.getVaccinepriceYear();
			if(a==2019)
			{
				a2019=false;
			}
			else if(a==2020)
			{
				a2020=false;
			}
			else if(a==2021)
			{
				a2021=false;
			}
			else if(a==2022)
			{
				a2022=false;
			}
			else if(a==2023)
			{
				a2023=false;
			}
			else if(a==2024)
			{
				a2024=false;
			}
		}
		model.addAttribute("a2019",a2019);
		model.addAttribute("a2020",a2020);
		model.addAttribute("a2021",a2021);
		model.addAttribute("a2022",a2022);
		model.addAttribute("a2023",a2023);
		model.addAttribute("a2024",a2024);
		model.addAttribute("list", list);//jsp로 list 값 보내기.
		return "vaccinePrice";
		}
	}
	@PostMapping(path="/insertvaccineprice")
	public String selectAllPrice(@RequestParam(name = "year", required = true) int year,
			@RequestParam(name = "corona" ,defaultValue="0") int corona,
			@RequestParam(name = "cold" ,defaultValue="0") int cold,
			@RequestParam(name = "shingles" ,defaultValue="0") int shingles,
			@RequestParam(name = "prevenar" ,defaultValue="0") int prevenar,
			@RequestParam(name = "vitd" ,defaultValue="0") int vitd,
			@RequestParam(name = "coldfree" ,defaultValue="0") int coldfree,
			@RequestParam(name = "special" ,defaultValue="0") int special,
			@RequestParam(name = "docdae" ,defaultValue="0") int docdae,
			@RequestParam(name = "docpre" ,defaultValue="0") int docpre,
			@RequestParam(name = "docdaepre" ,defaultValue="0") int docdaepre,
			@RequestParam(name = "docvitd" ,defaultValue="0") int docvitd,
			ModelMap modelMap) {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//Bean Facotry를 상속받고 있는 ApplicationContext가 pre loading 해서 즉시 인스턴스를 만든다.(오브젝트 생성, 관계 설정)
		VaccinePriceDao vaccinePriceDao = ac.getBean(VaccinePriceDao.class);
		
		VaccinePrice price= new VaccinePrice();
		
		price.setVaccinepriceYear(year);
		price.setVaccinepriceCorona(corona);
		price.setVaccinepriceCold(cold);
		price.setVaccinepriceShingles(shingles);
		price.setVaccinepricePrevenar(prevenar);
		price.setVaccinepriceVitd(vitd);
		price.setVaccinepriceColdfree(coldfree);
		price.setVaccinepriceSpecial(special);
		price.setVaccinepriceDocdae(docdae);
		price.setVaccinepriceDocpre(docpre);
		price.setVaccinepriceDocdaepre(docdaepre);
		price.setVaccinepriceDocvitd(docvitd);
		
		//날짜 넣기
//		SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date date=new Date();
//		String s=date.toString();
//		Date now=format1.format(s);	
	
		
		int count= vaccinePriceDao.insertPrice(price);
		
		//@requestParam, mapping된 메소드의 argument에 붙일 수 있는 어노테이션
		// name에는 http parameter의 name과 매핑되고, required는 필수인지 아닌지 판단한다.
		//@pathvariable의 경우 실제 url에서 ?변수명=값 같이 값을 넘겨올 때 path에서 넘겨온 값을 받기위해서 사용하는 에노테이션
		//변수명을 받기 위해 placeholder가 필요하다. 사용자가 넣어준 name과 placeholder의 이름이 일치해야지 해당 값을 채워준다.
		//@request header의 경우 요청 정보의 header를 읽어올 수 있게 된다.
		modelMap.addAttribute("year", year);
		modelMap.addAttribute("corona", corona);
		modelMap.addAttribute("cold", cold);
		modelMap.addAttribute("shingles", shingles);
		modelMap.addAttribute("prevenar", prevenar);
		modelMap.addAttribute("vitd", vitd);
		modelMap.addAttribute("coldfree", coldfree);
		modelMap.addAttribute("special", special);
		modelMap.addAttribute("docdae", docdae);
		modelMap.addAttribute("docpre", docpre);
		modelMap.addAttribute("docdaepre", docdaepre);
		modelMap.addAttribute("docvitd", docvitd);
		
		modelMap.addAttribute("count",count);
		//modelmap이라는 객체가 requestscope
		return "redirect:vaccinePrice";
		//return 값은 viewname
	}
	@GetMapping(path="/deletevaccineprice")
	public String deletePrice(@RequestParam(name="year", required=true) int year, 
			             @SessionAttribute("isAdmin") String isAdmin,//세션이름으로 값을 꺼내고 있다.
			             HttpServletRequest request,
			             RedirectAttributes redirectAttr) {
		if(isAdmin == null || !"true".equals(isAdmin)) { // 세션값이 true가 아닐 경우
			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
			return "redirect:/login";
		}
		vaccineService.deletePrice(year);
		return "redirect:/vaccinePrice";		
	}
}
