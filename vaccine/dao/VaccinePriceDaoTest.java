package kr.or.connect.vaccine.dao;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dto.VaccinePrice;

public class VaccinePriceDaoTest {


	public static void main(String[] args) {

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		VaccinePriceDao vaccinePriceDao= ac.getBean(VaccinePriceDao.class);
		
//		VaccinePrice price=new VaccinePrice();
//		price.setVaccinepriceCold(0);
//		price.setVaccinepriceColdfree(0);
//		price.setVaccinepriceCorona(0);
//		price.setVaccinepriceDocdae(0);
//		price.setVaccinepriceDocdaepre(0);
//		price.setVaccinepriceDocpre(0);
//		price.setVaccinepriceDocvitd(0);
//		price.setVaccinepricePrevenar(0);
//		price.setVaccinepriceShingles(0);
//		price.setVaccinepriceSpecial(0);
//		price.setVaccinepriceVitd(0);
//		price.setVaccinepriceYear(457);
//		int id= vaccinePriceDao.insertPrice(price);
//		System.out.println("id :"+ id);
//		Member member = new Member();
//		member.setMemberAge("67");
//		member.setMemberSex("여성");
//		member.setMemberVac(7);
//		member.setMemberDate(new Date());
//		int id=memberDao.insert(member);
//		System.out.println("id: " + id);

		
//		int count=memberDao.selectCount();
//		System.out.println(count);
//		int del=memberDao.deleteById(14);
//		System.out.println(del);
//		int count=memberDao.selectCountWithoutVac("독감","2020-01-01","2021-02-18");
//		System.out.println(count);
//		System.out.println("----------------------------");
//		
//		System.out.println("----------------------------");
//		List<Member> theallbypage= new ArrayList<>();
//		theallbypage=memberDao.selectAllWithoutVac("독감","2020-01-01","2021-02-18",0,4);
//		for(Member member : theallbypage)
//		{
//			System.out.println(member);
//		}
//		int count=memberDao.selectCountBySQL("member_sex=남", "2020-01-01", "2021-02-18");
//		System.out.println(count);
		Calendar cal= Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		System.out.println(year);
	}
}
