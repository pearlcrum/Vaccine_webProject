package kr.or.connect.vaccine.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dto.NumVaccine;

public class NumVaccineDaoTest {

	public static void main(String[] args) {

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		NumVaccineDao dao= ac.getBean(NumVaccineDao.class);
		
//		List<NumVaccine> list= dao.selectAllVaccine();
//		for(NumVaccine vac : list)
//		{
//			System.out.println(vac);
//		}
//		
//		List<NumVaccine> list= dao.selectAllVaccineByYear(2015);
//		for(NumVaccine vac : list)
//		{
//			System.out.println(vac);
//		}
//		NumVaccine num = new NumVaccine();
//		num.setNumvaccineYear(2021);
//		num.setNumvaccineCold(5);
//		num.setNumvaccineCorona(6);
//		num.setNumvaccinePrevenar(5);
//		num.setNumvaccineShingles(5);
//		num.setNumvaccineVitd(5);
//		int u= dao.updateByNum(num);
//		System.out.println(u);
		List<NumVaccine> list= dao.selectAllVaccineBelowYear(2021);
		for(NumVaccine vac : list)
		{
			System.out.println(vac);
		}
		
	}

}
