package kr.or.connect.vaccine.service;

import java.util.List;

import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.dto.NumVaccine;
import kr.or.connect.vaccine.dto.VaccinePrice;

public interface VaccineService {

	public List<VaccinePrice> getPrices();
	public int deletePrice(int id);
	public VaccinePrice addPrice(VaccinePrice price);
	//////////////////////////////////////////////////////////////////////////
	public List<NumVaccine> getNumVaccines();
	public List<NumVaccine> getNumVaccinesByYear(int start);

	public List<NumVaccine> getNumVaccinesFromYear(int start);
	public List<NumVaccine> getNumVaccinesBelowYear(int standard);
	public int deleteNumVaccine(int year);
	public NumVaccine addNumVaccine(NumVaccine vaccine);
	public int updateNumVaccine(NumVaccine vaccine);
	///////////////////////////////////////////////////////////////////////////
	public static final Integer LIMIT = 10;
	public List<Member> getMembers(Integer start);
	public List<Member> getMembersInExcel(Integer start);
	public List<Member> getAllMembers();
	public int deleteMember(int id, String ip);
	public Member addMember(Member member, String ip);
	public int getCount();
	public int getCountByCondition(String memberAge, String memberSex, String memberVac, String startDate, String endDate);
	public List<Member> getMembersByCondition(String memberAge, String memberSex, String memberVac, String startDate, String endDate,Integer start);
	public List<Member> getMembersByConditionInExcel(String memberAge, String memberSex, String memberVac, String startDate, String endDate,Integer start);
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	public List<Member> getMembersByTerm(String startDate, String endDate, Integer start);// 모두 전체
	public List<Member> getMembersByTermInExcel(String startDate, String endDate, Integer start);
	public int getCountbyTerm(String startDate, String endDate);
	
	////////////////////////////////////////////////////////////////////////////////
	
	
	public List<Member> getMembersWithoutVac(String memberVac,
			String startDate, String endDate, Integer start);// 백신 빼고 전체
	public List<Member> getMembersWithoutVacInExcel(String memberVac,
			String startDate, String endDate, Integer start);// 백신 빼고 전체
	public int getCountWithoutVac(String memberVac, String startDate, String endDate);
	
	public List<Member> getMembersWithoutSex(String memberSex,
			String startDate, String endDate, Integer start);// 성별 빼고 전체
	public List<Member> getMembersWithoutSexInExcel(String memberSex,
			String startDate, String endDate, Integer start);// 성별 빼고 전체
	public int getCountWithoutSex(String memberSex, String startDate, String endDate);
	
	public List<Member> getMembersWithoutAge(String memberAge,
			String startDate, String endDate, Integer start);// 나이 빼고 전체
	public List<Member> getMembersWithoutAgeInExcel(String memberAge,
			String startDate, String endDate, Integer start);// 나이 빼고 전체
	public int getCountWithoutAge(String memberAge, String startDate, String endDate);
	
	
	public List<Member> getMembersWithoutSexAndVac(String memberSex, String memberVac,
			String startDate, String endDate, Integer start);// 나이만 선택
	public List<Member> getMembersWithoutSexAndVacInExcel(String memberSex, String memberVac,
			String startDate, String endDate, Integer start);// 나이만 선택
	public int getCountWithoutSexAndVac(String memberSex, String memberVac, String startDate, String endDate);
	
	public List<Member> getMembersWithoutAgeAndSex(String memberAge, String memberSex,
			String startDate, String endDate, Integer start);// 백신만 선택
	public List<Member> getMembersWithoutAgeAndSexInExcel(String memberAge, String memberSex,
			String startDate, String endDate, Integer start);// 백신만 선택
	public int getCountWithoutAgeAndSex(String memberAge, String memberSex, String startDate, String endDate);
	
	public List<Member> getMembersWithoutAgeAndVac(String memberAge, String memberVac,
			String startDate, String endDate, Integer start);// 성별만 선택
	public List<Member> getMembersWithoutAgeAndVacInExcel(String memberAge, String memberVac,
			String startDate, String endDate, Integer start);// 성별만 선택
	public int getCountWithoutAgeAndVac(String memberAge, String memberVac, String startDate, String endDate);
	
	
}
