package kr.or.connect.vaccine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.service.VaccineService;

@Controller
public class ExcelController {

	@Autowired
	VaccineService vaccineService;
	
	
	@GetMapping(path="/excelTest2")
	String jspToExcel(@RequestParam(name="start", required=false, defaultValue="0") int start,
			   ModelMap model, @CookieValue(value="count",defaultValue="0",required=true) String value,
			   HttpServletResponse response, HttpServletRequest request,
			   HttpSession session,RedirectAttributes redirectAttr){
	if(session.getAttribute("isAdmin")==null) {

		redirectAttr.addFlashAttribute("errorMessage","로그인이 필요합니다.");
 		return "redirect:/login";
	}
	else {
		Map<String, ?> flashMap =RequestContextUtils.getInputFlashMap(request);
		
		try {
			int i = Integer.parseInt(value); //문자열이었으니 parseInt이용
			value = Integer.toString(++i);
		}catch(Exception ex) {
			value = "1";
		}

		Cookie cookie = new Cookie("count", value);
		cookie.setMaxAge(60 * 60 * 24 * 365); // 1년 동안 유지.
		cookie.setPath("/"); // / 경로 이하에 모두 쿠키 적용. 
		response.addCookie(cookie);
		int count=1;
		List<Member>list= new ArrayList<>(); //리스트는 항상 새로 생성한다.
		if(flashMap==null)
		{
		
			// 값 없다면 default value는 0부터이다.
			// start로 시작하는 방명록 목록 구하기
			list = vaccineService.getMembersInExcel(start); //원래 start이었음. 초기는 열개만 보여주자
			count = vaccineService.getCount();
		}
		else //초기 상태가 아닌 경우  anchor가 아닌 경우 또한 여기로
		{
			String ageOption=(String)flashMap.get("ageOption");
			String sexOption=(String)flashMap.get("sexOption");
			String vaccineOption=(String)flashMap.get("vaccineOption");
			String startDate=(String)flashMap.get("startDate");
			String endDate=(String)flashMap.get("endDate");
			
			if(ageOption.equals("전체"))
			{
				if(vaccineOption.equals("전체"))
				{
					if(sexOption.equals("전체"))
					{
						//셋다 전체인 경우
						list=vaccineService.getMembersByTermInExcel(startDate, endDate, start);
						count=vaccineService.getCountbyTerm(startDate, endDate);
						
					}
					else
					{
						//age와 vaccine만 전체인 경우 성별만 선택 가능
						list=vaccineService.getMembersWithoutSexInExcel(sexOption, startDate, endDate, start);
						count=vaccineService.getCountWithoutSex(sexOption, startDate, endDate);
					}
				}
				else if(sexOption.equals("전체"))
				{
					//나이와 성별이 전체인 경우 백신만 선택 가능
					list=vaccineService.getMembersWithoutVacInExcel(vaccineOption, startDate, endDate, start);
					count=vaccineService.getCountWithoutVac(vaccineOption, startDate, endDate);
				}
				else
				{
					//나이만 전체인 경우
					list=vaccineService.getMembersWithoutSexAndVacInExcel(sexOption, vaccineOption, startDate, endDate, start);
					count=vaccineService.getCountWithoutSexAndVac(sexOption, vaccineOption, startDate, endDate);
				}
			}
			else if(vaccineOption.equals("전체"))
			{
				if(sexOption.equals("전체"))
				{
					//백신과 sex가 전체일 경우 age만 선택 가능
					list=vaccineService.getMembersWithoutAgeInExcel(ageOption, startDate, endDate, start);
					count=vaccineService.getCountWithoutAge(ageOption, startDate, endDate);
				}
				else
				{
					//vaccine은 전체이나 성별과 나이가 전체 아닌경우 나이와 성별만 선택 가능
					list=vaccineService.getMembersWithoutAgeAndSexInExcel(ageOption, sexOption, startDate, endDate, start);
					count=vaccineService.getCountWithoutAgeAndSex(ageOption, sexOption, startDate, endDate);
					
				}
			}
			else if(sexOption.equals("전체"))
			{
				//성별만 전체인 경우 나이와 백신만 선택 가능
				list=vaccineService.getMembersWithoutAgeAndVacInExcel(ageOption, vaccineOption, startDate, endDate, start);
				count=vaccineService.getCountWithoutAgeAndVac(ageOption, vaccineOption, startDate, endDate);
			}
			else
			{
				//셋다 모두 전체가 아닌 경우
				list =vaccineService.getMembersByConditionInExcel(ageOption, sexOption, vaccineOption,startDate,endDate, start);
				count = vaccineService.getCountByCondition(ageOption, sexOption, vaccineOption,startDate,endDate);
			}
			
			int coronaPrice=(Integer)flashMap.get("corona");
			int docPrice=(Integer)flashMap.get("doc");
			int dePrice=(Integer)flashMap.get("de");
			int prePrice=(Integer)flashMap.get("pre");
			int vitPrice=(Integer)flashMap.get("vit");
			int docfreePrice=(Integer)flashMap.get("docfree");
			int specialPrice=(Integer)flashMap.get("special");
			int docdaePrice=(Integer)flashMap.get("docdae");
			int docpePrice=(Integer)flashMap.get("docpe");
			int docdaepePrice=(Integer)flashMap.get("docdaepe");
			int docvitdPrice=(Integer)flashMap.get("docvitd");
			
			int covEA=0,docEA=0,deEA=0,preEA=0,vitEA=0,docfreeEA=0,specialEA=0,docdaeEA=0,docpeEA=0,docdaepeEA=0,docvitdEA=0;
			
			// 가격과 갯수를 구해보자.
			int numOfDoc=0,numOfPre=0,numOfDae=0,numOfVit=0,numOfCov =0;
			
			for(Member mem : list)
			{
				String check=mem.getMemberVac();
				if(check.equals("코로나"))
				{
					numOfCov++;
					covEA++;
				}
				else if(check.equals("독감"))
				{
					numOfDoc++;
					docEA++;
				}
				else if(check.equals("대상포진"))
				{
					numOfDae++;
					deEA++;
				}
				else if(check.equals("프리베나"))
				{
					numOfPre++;
					preEA++;
				}
				else if(check.equals("독+대"))
				{
					numOfDoc++;
					numOfDae++;
					docdaeEA++;
				}
				else if(check.equals("독+프"))
				{
					numOfDoc++;
					numOfPre++;
					docpeEA++;
				}
				else if(check.equals("독+VIT-D"))
				{
					numOfDoc++;
					numOfVit++;
					docvitdEA++;
				}
				else if(check.equals("독+대+프"))
				{
					numOfDoc++;
					numOfDae++;
					numOfPre++;
					docdaepeEA++;
				}
				else if(check.equals("VIT-D"))
				{
					numOfVit++;
					vitEA++;
				}
				else if(check.equals("독-Free"))
				{
					numOfDoc++;
					docfreeEA++;
				}
				else if(check.equals("특별가"))
				{
					numOfDoc++;
					specialEA++;
				}	
			}
			
			int totalPrice=
					coronaPrice*covEA + docPrice*docEA + dePrice*deEA + prePrice*preEA + vitPrice*vitEA
					+ docfreePrice*docfreeEA + specialPrice*specialEA + docdaePrice*docdaeEA
					+ docpePrice*docpeEA + docdaepePrice*docdaepeEA + docvitdPrice*docvitdEA;

			
			model.addAttribute("totalPrice", totalPrice);
			
			//list에서 값 확인해서 새로운 표 만들기
			Map<String,Integer> totalEA= new HashMap<String,Integer>();
			totalEA.put("covid19", numOfCov);
			totalEA.put("dockgam", numOfDoc);
			totalEA.put("prevena", numOfPre);
			totalEA.put("Daesang", numOfDae);
			totalEA.put("vitamin", numOfVit);
			
			model.addAttribute("total",totalEA);
			
		}
		
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
	
		model.addAttribute("cookieCount",value);

		
		
		return "excelTest2";
	}
	}
}
