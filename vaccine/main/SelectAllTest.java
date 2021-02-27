package kr.or.connect.vaccine.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dao.VaccinePriceDao;
import kr.or.connect.vaccine.dto.VaccinePrice;


public class SelectAllTest {

	public static void main(String[] args) {
//		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//		
//		MemberDao memberDao= ac.getBean(MemberDao.class);
//		
//		List<Member>list=memberDao.selectAllWithOutPage();
//		
//		for(Member member:list)
//		{
//			System.out.println(member);
//		}
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		VaccinePriceDao vaccinePrice= ac.getBean(VaccinePriceDao.class);
		List<VaccinePrice>list= vaccinePrice.selectAllPrice();
		
		for(VaccinePrice price: list)
		{
			System.out.println(price);
		}
	}

}
