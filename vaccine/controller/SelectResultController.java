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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.service.VaccineService;

@Controller
public class SelectResultController {

	//해당 객체가 controller인지 밝혀야 componentScan 시 해당 Bean을 찾아서 사용 가능하다.
			@Autowired
			VaccineService vaccineService;
			
			
			@SuppressWarnings("unused")
			@GetMapping(path="/selectResult")
			public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,
							   ModelMap model, @CookieValue(value="count",defaultValue="0",required=true) String value,
							   HttpServletResponse response, HttpServletRequest request,
							   HttpSession session,RedirectAttributes redirectAttr) {
				
				if(session.getAttribute("isAdmin")==null) {

					redirectAttr.addFlashAttribute("errorMessage","로그인이 필요합니다.");
			 		return "redirect:/login";
				}else {
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
				String ageOption,sexOption,vaccineOption,startDate,endDate;
				if(flashMap==null)
				{
					//항상 조회 버튼으로 selectResult로 넘어오기에  flashMap이 null인 경우는 anchor tag인 경우이다.
					ageOption=(String)session.getAttribute("ageOption");
					sexOption=(String)session.getAttribute("sexOption");
					vaccineOption=(String)session.getAttribute("vaccineOption");
					startDate=(String)session.getAttribute("startDate");
					endDate=(String)session.getAttribute("endDate");
					
					
				}
				else //초기 상태가 아닌 경우 anchor가 아닌 경우 또한 여기로
				{
					
						ageOption=(String)flashMap.get("ageOption");
						sexOption=(String)flashMap.get("sexOption");
						vaccineOption=(String)flashMap.get("vaccineOption");
						startDate=(String)flashMap.get("startDate");
						endDate=(String)flashMap.get("endDate");
						session.setAttribute("ageOption", ageOption);
						session.setAttribute("sexOption", sexOption);
						session.setAttribute("vaccineOption", vaccineOption);
						session.setAttribute("startDate", startDate);
						session.setAttribute("endDate", endDate);
				}
				if(ageOption.equals("전체"))
				{
					if(vaccineOption.equals("전체"))
					{
						if(sexOption.equals("전체"))
						{
							//셋다 전체인 경우
							list=vaccineService.getMembersByTerm(startDate, endDate, start);
							count=vaccineService.getCountbyTerm(startDate, endDate);
							
						}
						else
						{
							//age와 vaccine만 전체인 경우 성별만 선택 가능
							list=vaccineService.getMembersWithoutSex(sexOption, startDate, endDate, start);
							count=vaccineService.getCountWithoutSex(sexOption, startDate, endDate);
						}
					}
					else if(sexOption.equals("전체"))
					{
						//나이와 성별이 전체인 경우 백신만 선택 가능
						list=vaccineService.getMembersWithoutVac(vaccineOption, startDate, endDate, start);
						count=vaccineService.getCountWithoutVac(vaccineOption, startDate, endDate);
					}
					else
					{
						//나이만 전체인 경우
						list=vaccineService.getMembersWithoutSexAndVac(sexOption, vaccineOption, startDate, endDate, start);
						count=vaccineService.getCountWithoutSexAndVac(sexOption, vaccineOption, startDate, endDate);
					}
				}
				else if(vaccineOption.equals("전체"))
				{
					if(sexOption.equals("전체"))
					{
						//백신과 sex가 전체일 경우 age만 선택 가능
						list=vaccineService.getMembersWithoutAge(ageOption, startDate, endDate, start);
						count=vaccineService.getCountWithoutAge(ageOption, startDate, endDate);
					}
					else
					{
						//vaccine은 전체이나 성별과 나이가 전체 아닌경우 나이와 성별만 선택 가능
						list=vaccineService.getMembersWithoutAgeAndSex(ageOption, sexOption, startDate, endDate, start);
						count=vaccineService.getCountWithoutAgeAndSex(ageOption, sexOption, startDate, endDate);
						
					}
				}
				else if(sexOption.equals("전체"))
				{
					//성별만 전체인 경우 나이와 백신만 선택 가능
					list=vaccineService.getMembersWithoutAgeAndVac(ageOption, vaccineOption, startDate, endDate, start);
					count=vaccineService.getCountWithoutAgeAndVac(ageOption, vaccineOption, startDate, endDate);
				}
				else
				{
					//셋다 모두 전체가 아닌 경우
					list =vaccineService.getMembersByCondition(ageOption, sexOption, vaccineOption,startDate,endDate, start);
					count = vaccineService.getCountByCondition(ageOption, sexOption, vaccineOption,startDate,endDate);
				}
				

				int pageCount = count / vaccineService.LIMIT;
				if(count % vaccineService.LIMIT > 0)
					pageCount++;
				
				// 페이지 수만큼 start의 값을 리스트로 저장
				// 예를 들면 페이지수가 3이면
				// 0, 5, 10 이렇게 저장된다.
				// list?start=0 , list?start=5, list?start=10 으로 링크가 걸린다.
				List<Integer> pageStartList = new ArrayList<>();
				for(int i = 0; i < pageCount; i++) {
					pageStartList.add(i * vaccineService.LIMIT);
				}
				
				
				
				if(flashMap!=null)
				{
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
					
					for(int i = 0; i < pageCount; i++) {
					
						if(ageOption.equals("전체"))
						{
							if(vaccineOption.equals("전체"))
							{
								if(sexOption.equals("전체"))
								{
									//셋다 전체인 경우
									list=vaccineService.getMembersByTerm(startDate, endDate, start);
									count=vaccineService.getCountbyTerm(startDate, endDate);
									
								}
								else
								{
									//age와 vaccine만 전체인 경우 성별만 선택 가능
									list=vaccineService.getMembersWithoutSex(sexOption, startDate, endDate, start);
									count=vaccineService.getCountWithoutSex(sexOption, startDate, endDate);
								}
							}
							else if(sexOption.equals("전체"))
							{
								//나이와 성별이 전체인 경우 백신만 선택 가능
								list=vaccineService.getMembersWithoutVac(vaccineOption, startDate, endDate, start);
								count=vaccineService.getCountWithoutVac(vaccineOption, startDate, endDate);
							}
							else
							{
								//나이만 전체인 경우
								list=vaccineService.getMembersWithoutSexAndVac(sexOption, vaccineOption, startDate, endDate, start);
								count=vaccineService.getCountWithoutSexAndVac(sexOption, vaccineOption, startDate, endDate);
							}
						}
						else if(vaccineOption.equals("전체"))
						{
							if(sexOption.equals("전체"))
							{
								//백신과 sex가 전체일 경우 age만 선택 가능
								list=vaccineService.getMembersWithoutAge(ageOption, startDate, endDate, start);
								count=vaccineService.getCountWithoutAge(ageOption, startDate, endDate);
							}
							else
							{
								//vaccine은 전체이나 성별과 나이가 전체 아닌경우 나이와 성별만 선택 가능
								list=vaccineService.getMembersWithoutAgeAndSex(ageOption, sexOption, startDate, endDate, start);
								count=vaccineService.getCountWithoutAgeAndSex(ageOption, sexOption, startDate, endDate);
								
							}
						}
						else if(sexOption.equals("전체"))
						{
							//성별만 전체인 경우 나이와 백신만 선택 가능
							list=vaccineService.getMembersWithoutAgeAndVac(ageOption, vaccineOption, startDate, endDate, start);
							count=vaccineService.getCountWithoutAgeAndVac(ageOption, vaccineOption, startDate, endDate);
						}
						else
						{
							//셋다 모두 전체가 아닌 경우
							list =vaccineService.getMembersByCondition(ageOption, sexOption, vaccineOption,startDate,endDate, start);
							count = vaccineService.getCountByCondition(ageOption, sexOption, vaccineOption,startDate,endDate);
						}
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
						start=start+10;
					}
					
					int totalPrice=
							coronaPrice*covEA + docPrice*docEA + dePrice*deEA + prePrice*preEA + vitPrice*vitEA
							+ docfreePrice*docfreeEA + specialPrice*specialEA + docdaePrice*docdaeEA
							+ docpePrice*docpeEA + docdaepePrice*docdaepeEA + docvitdPrice*docvitdEA;

					
					session.setAttribute("totalPrice", totalPrice);
					model.addAttribute("totalPrice", totalPrice);
					
					//list에서 값 확인해서 새로운 표 만들기
					Map<String,Integer> totalEA= new HashMap<String,Integer>();
					totalEA.put("dockgam", numOfDoc);
					totalEA.put("prevena", numOfPre);
					totalEA.put("Daesang", numOfDae);
					totalEA.put("vitamin", numOfVit);
					totalEA.put("covid19",numOfCov);
					
					session.setAttribute("total", totalEA);
					model.addAttribute("total",totalEA);
					
				}
				else
				{
//					int totalPrice=Integer.valueOf(session.getAttribute("totalPrice"));
//					int totalEA=(Integer)session.getAttribute("total");
//					model.addAttribute("totalPrice", totalPrice);
//					model.addAttribute("total",totalEA);
				}
				
			
			
				
				
				
				model.addAttribute("list", list);
				model.addAttribute("count", count);
				model.addAttribute("pageStartList", pageStartList);
				model.addAttribute("cookieCount",value);
				
				
				return "selectResult";
			}
			}

			
			@GetMapping(path="/deleteInSelectResult")
			public String deleteInSelect(@RequestParam(name="id", required=true) int id, 
					             @SessionAttribute("isAdmin") String isAdmin,//세션이름으로 값을 꺼내고 있다.
					             HttpServletRequest request,
					             RedirectAttributes redirectAttr) {
				if(isAdmin == null || !"true".equals(isAdmin)) { // 세션값이 true가 아닐 경우
					redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
					return "redirect:/login";
				}
				String clientIp = request.getRemoteAddr(); //로그 테이블 삭제
				vaccineService.deleteMember(id, clientIp);
				return "redirect:/selectResult";		
			}		
			
}
