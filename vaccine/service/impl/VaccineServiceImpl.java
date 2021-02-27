package kr.or.connect.vaccine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.vaccine.dao.LogDao;
import kr.or.connect.vaccine.dao.MemberDao;
import kr.or.connect.vaccine.dao.NumVaccineDao;
import kr.or.connect.vaccine.dao.VaccinePriceDao;
import kr.or.connect.vaccine.dto.Log;
import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.dto.NumVaccine;
import kr.or.connect.vaccine.dto.VaccinePrice;
import kr.or.connect.vaccine.service.VaccineService;


@Service
public class VaccineServiceImpl implements VaccineService{

	@Autowired
	MemberDao memberDao;
	//MemberDao 이용할 것이다. 알아서 생성 후 주입 된다.
	@Autowired
	VaccinePriceDao vaccinePriceDao;
	@Autowired
	NumVaccineDao numVaccineDao;
	
	@Autowired
	LogDao logDao;
	//logDao도 알아서 생성 후 주입
	
	@Override
	@Transactional
	public List<Member> getMembers(Integer start) {
		List<Member> list = memberDao.selectAll(start, VaccineService.LIMIT);
		return list;
	}//MemberList 가지고 오기. 읽기만 하는 method는 transaction 처리시 transactional이라고 붙이면 좋다.
	//붙이게 되면 내부적으로 readOnly 형태로 connection을 사용하게 된다.
	@Override
	@Transactional
	public List<Member> getMembersInExcel(Integer start) {
		List<Member> list = memberDao.selectAll(start, 10000);
		return list;
	}//MemberList 가지고 오기. 읽기만 하는 method는 transaction 처리시 transactional이라고 붙이면 좋다.
	//붙이게 되면 내부적으로 readOnly 형태로 connection을 사용하게 된다.
	
	
	@Override
	@Transactional
	public List<Member> getAllMembers() {
		List<Member> list = memberDao.selectAllWithOutPage();
		return list;
	}// page 관계 없이 전부 다 들고 오는 것
	
	
	@Override
	@Transactional(readOnly=false)
	public int deleteMember(int id, String ip) {
		int deleteCount = memberDao.deleteById(id);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date()); 
		logDao.insert(log);
		return deleteCount;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Member addMember(Member member, String ip) {
		 //member에 클라이언트에게 받아온 정보
		member.setMemberDate(new Date());
		int id = memberDao.insert(member);
		//insert 실행시 long type 의 id 반환된다.
		member.setMemberId(id);
		//로그에 해당 부분 넣어서 채워주기 위함
//		if(1 == 1)
//			throw new RuntimeException("test exception");
//			
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		//트랜잭션은 나눌수 없는 하나의 단위기 때문에 guestbookDao가 잘 입력되어 들어가더라도
		//log부분에서 오류나게 되면 전체적인 데이터는 들어가지 않는다 이게 @Transactional에서 구현된 것이다.
		// 매서드 전체 성공하지 않으면 데이터는 처리 되지 않는다. 이것이 Service layer
		return member;
	}
	@Override
	public int getCount() {
		return memberDao.selectCount();
	}

	@Override
	public int getCountByCondition(String memberAge, String memberSex, String memberVac,String startDate, String endDate) {
		return memberDao.selectCountByCondition(memberAge, memberSex, memberVac,startDate, endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersByCondition(String memberAge, String memberSex, String memberVac,String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectByCondition(memberAge, memberSex, memberVac, startDate,endDate,start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersByConditionInExcel(String memberAge, String memberSex, String memberVac,String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectByCondition(memberAge, memberSex, memberVac, startDate,endDate,start, 10000);
		return list;
	}
	
	@Override
	@Transactional
	public List<Member> getMembersByTerm(String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectByTerm(startDate,endDate,start,VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersByTermInExcel(String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectByTerm(startDate,endDate,start,10000);
		return list;
	}

	@Override
	public int getCountbyTerm(String startDate, String endDate) {
		return memberDao.selectCountByTerm(startDate,endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersWithoutVac(String memberVac, String startDate, String endDate, Integer start) {
		List<Member> list=memberDao.selectAllWithoutVac(memberVac, startDate, endDate, start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersWithoutVacInExcel(String memberVac, String startDate, String endDate, Integer start) {
		List<Member> list=memberDao.selectAllWithoutVac(memberVac, startDate, endDate, start, 10000);
		return list;
	}
	
	@Override
	public int getCountWithoutVac(String memberVac, String startDate, String endDate) {
		return memberDao.selectCountWithoutVac(memberVac, startDate, endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersWithoutSex(String memberSex, String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectAllWithoutSex(memberSex, startDate, endDate, start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersWithoutSexInExcel(String memberSex, String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectAllWithoutSex(memberSex, startDate, endDate, start, 10000);
		return list;
	}

	@Override
	public int getCountWithoutSex(String memberSex, String startDate, String endDate) {
		return memberDao.selectCountWithoutSex(memberSex, startDate, endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersWithoutAge(String memberAge, String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectAllWithoutAge(memberAge, startDate, endDate, start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersWithoutAgeInExcel(String memberAge, String startDate, String endDate, Integer start) {
		List<Member> list = memberDao.selectAllWithoutAge(memberAge, startDate, endDate, start, 10000);
		return list;
	}

	@Override
	public int getCountWithoutAge(String memberAge, String startDate, String endDate) {
		return memberDao.selectCountWithoutAge(memberAge, startDate, endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersWithoutSexAndVac(String memberSex, String memberVac, String startDate, String endDate,
			Integer start) {
		List<Member> list=memberDao.selectAllWithoutSexAndVac(memberSex, memberVac, startDate, endDate, start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersWithoutSexAndVacInExcel(String memberSex, String memberVac, String startDate, String endDate,
			Integer start) {
		List<Member> list=memberDao.selectAllWithoutSexAndVac(memberSex, memberVac, startDate, endDate, start, 10000);
		return list;
	}

	@Override
	public int getCountWithoutSexAndVac(String memberSex, String memberVac, String startDate, String endDate) {
		return memberDao.selectCountWithoutSexAndVac(memberSex, memberVac, startDate, endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersWithoutAgeAndSex(String memberAge, String memberSex, String startDate, String endDate,
			Integer start) {
		List<Member> list=memberDao.selectAllWithoutAgeAndSex(memberAge, memberSex, startDate, endDate, start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersWithoutAgeAndSexInExcel(String memberAge, String memberSex, String startDate, String endDate,
			Integer start) {
		List<Member> list=memberDao.selectAllWithoutAgeAndSex(memberAge, memberSex, startDate, endDate, start, 10000);
		return list;
	}

	@Override
	public int getCountWithoutAgeAndSex(String memberAge, String memberSex, String startDate, String endDate) {
		return memberDao.selectCountWithoutAgeAndSex(memberAge, memberSex, startDate, endDate);
	}

	@Override
	@Transactional
	public List<Member> getMembersWithoutAgeAndVac(String memberAge, String memberVac, String startDate, String endDate,
			Integer start) {
		List<Member> list=memberDao.selectAllWithoutAgeAndVac(memberAge, memberVac, startDate, endDate, start, VaccineService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<Member> getMembersWithoutAgeAndVacInExcel(String memberAge, String memberVac, String startDate, String endDate,
			Integer start) {
		List<Member> list=memberDao.selectAllWithoutAgeAndVac(memberAge, memberVac, startDate, endDate, start, 10000);
		return list;
	}

	@Override
	public int getCountWithoutAgeAndVac(String memberAge, String memberVac, String startDate, String endDate) {
		return memberDao.selectCountWithoutAgeAndVac(memberAge, memberVac, startDate, endDate);
	}
	
	@Override
	@Transactional
	public List<VaccinePrice> getPrices() {
		List<VaccinePrice> list= vaccinePriceDao.selectAllPrice();
		return list;
	}
	@Override
	@Transactional(readOnly=false)
	public int deletePrice(int year) {
		int deleteCount = vaccinePriceDao.deleteByPrice(year);
		return deleteCount;
	}
	@Override
	@Transactional(readOnly=false)
	public VaccinePrice addPrice(VaccinePrice price) {
		return price;
	}
	//////
	@Override
	@Transactional
	public List<NumVaccine> getNumVaccines() {
		List<NumVaccine> list= numVaccineDao.selectAllVaccine();
		return list;
	}
	@Override
	@Transactional(readOnly=false)
	public int deleteNumVaccine(int year) {
		int deleteCount= numVaccineDao.deleteByNum(year);
		return deleteCount;
	}
	@Override
	@Transactional(readOnly=false)
	public NumVaccine addNumVaccine(NumVaccine vaccine) {
		return vaccine;
	}
	@Override
	@Transactional
	public List<NumVaccine> getNumVaccinesByYear(int start) {
		List<NumVaccine> list= numVaccineDao.selectAllVaccineByYear(start);
		return list;
	}
	@Override
	@Transactional(readOnly=false)
	public int updateNumVaccine(NumVaccine vaccine) {
		int updateCount= numVaccineDao.updateByNum(vaccine);
		return updateCount;
	}
	@Override
	@Transactional
	public List<NumVaccine> getNumVaccinesBelowYear(int standard) {
		List<NumVaccine> list= numVaccineDao.selectAllVaccineBelowYear(standard);
		return list;
	}
	@Override
	public List<NumVaccine> getNumVaccinesFromYear(int start) {
		List<NumVaccine> list= numVaccineDao.selectAllVaccineFromYear(start);
		return list;
	}

	
}
