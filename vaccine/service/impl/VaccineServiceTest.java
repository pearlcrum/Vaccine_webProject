package kr.or.connect.vaccine.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.dto.NumVaccine;
import kr.or.connect.vaccine.service.VaccineService;

public class VaccineServiceTest {



public static void main(String[] args) {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		VaccineService vaccineService = ac.getBean(VaccineService.class);
		
		
		List<NumVaccine> list= vaccineService.getNumVaccinesBelowYear(2021);
		
		for(NumVaccine vac: list)
		{
			System.out.println(vac);
		}
		
//		int count=vaccineService.getCountByCondition("20대", "남", "독감","2020-01-01","2021-02-18");
//		List<Member> list= vaccineService.getMembersByCondition("20대", "남", "독감","2020-01-01","2021-02-18",0);
//		
//		System.out.println(count);
//		for(Member member:list)
//		{
//			System.out.println(member);
//		}
//		System.out.println("시험");
//		int count2= vaccineService.getCountWithoutVac("독감","2020-01-01","2021-02-18");
//		System.out.println(count2);
//		List<Member> list2= vaccineService.getMembersWithoutVac("독감","2020-01-01","2021-02-18",0);
//		for(Member member:list2)
//		{
//			System.out.println(member);
//		}
//		System.out.println("시험7");
//		int count3= vaccineService.getCountbyTerm("2020-01-01","2021-02-20");
//		System.out.println(count3);
//		List<Member> list3= vaccineService.getMembersByTerm("2020-01-01","2021-02-20",0);
//		for(Member member:list3)
//		{
//			System.out.println(member);
//		}
		
		
		
	}
}
