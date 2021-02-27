package kr.or.connect.vaccine.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.vaccine.dto.Member;
import kr.or.connect.vaccine.dto.NumVaccine;
import kr.or.connect.vaccine.service.VaccineService;

@Controller
public class ColdPredictController {

	@Autowired
	VaccineService vaccineService;
	@GetMapping(path="/coldPredict")
	public String infoInsert(ModelMap model,HttpSession session,
			RedirectAttributes redirectAttr) {
		
	if(session.getAttribute("isAdmin")==null) {

		redirectAttr.addFlashAttribute("errorMessage","로그인이 필요합니다.");
 		return "redirect:/login";	
	}
	else {
		//2015-2020 //2022년되면 2016-2021까지 데이터를 보여줄 수 있어야 한다.
		Calendar cal= Calendar.getInstance(); 
		int year= cal.get(Calendar.YEAR); // 현재 값 2021
		
		List<NumVaccine> list =vaccineService.getNumVaccinesByYear(year);
		// 6개년치 데이터를 보낸다.
		//21년의 값을 예측하는 머신러닝 알고리즘 추가 부분//21 20 19 18 17 16년 데이터가 들어간다.
		//21년의 데이터는 쌓여야 한다.
		int check=0;
		cal.get(Calendar.YEAR);
		for(NumVaccine vac:list)
		{
			if(vac.getNumvaccineYear()==year) //오늘이 21년인데 실제 21년 인 경우
			{
				check++;
			}
		}
		if(check==1)//조회 시점이 당해 연도일 경우 인공지능이 당해 백신 값을 예측한다.
		{
			
			List<NumVaccine> machine= vaccineService.getNumVaccinesBelowYear(year);//2017년부터 해당년도 이전 모든 년도 데이터 긁어옴
			int predictForDependentVariable=1;
			for(NumVaccine nv: machine)
			{
				if(nv.getNumvaccineYear()==year-1)
				{
					predictForDependentVariable=nv.getNumvaccineCorona()+nv.getNumvaccinePrevenar()+nv.getNumvaccineShingles()
					+nv.getNumvaccineVitd();
				}
			}
			int numOfDoc=predictForValue(machine,predictForDependentVariable);
			
			NumVaccine num= new NumVaccine();
			num.setNumvaccineYear(year); // 해당 년도
			num.setNumvaccineCorona(0);
			num.setNumvaccineCold(numOfDoc);
			num.setNumvaccinePrevenar(0);
			num.setNumvaccineShingles(0);
			num.setNumvaccineVitd(0);
			int p=vaccineService.updateNumVaccine(num);
			
		}
		else if(check==0)//새로운 년 2022년이 될 경우 조회 시 2021년 데이터는 예측 값에서 실제 값으로 바뀐다.
		{
			//update로 2021 값은 현재까지 받아온 값이 되어야 한다.
			//기존에는 예측값으로 보이던 그래프를 이제는 현재 값으로 넣어주어야 한다.
			int numOfDoc=0,numOfPre=0,numOfDae=0,numOfVit=0,numOfCov =0;
			List<Member> checkList=vaccineService.getMembersByTermInExcel(Integer.toString(year-1)+"-01-01",Integer.toString(year-1)+"-12-31" , 0);
			for(Member mem: checkList)
			{
				String checkVac=mem.getMemberVac();
				if(checkVac.equals("코로나"))
				{
					numOfCov++;
				}
				else if(checkVac.equals("독감"))
				{
					numOfDoc++;
				}
				else if(checkVac.equals("대상포진"))
				{
					numOfDae++;
				}
				else if(checkVac.equals("프리베나"))
				{
					numOfPre++;
				}
				else if(checkVac.equals("독+대"))
				{
					numOfDoc++;
					numOfDae++;
				}
				else if(checkVac.equals("독+프"))
				{
					numOfDoc++;
					numOfPre++;
				}
				else if(checkVac.equals("독+VIT-D"))
				{
					numOfDoc++;
					numOfVit++;
				}
				else if(checkVac.equals("독+대+프"))
				{
					numOfDoc++;
					numOfDae++;
					numOfPre++;
				}
				else if(checkVac.equals("VIT-D"))
				{
					numOfVit++;
				}
				else if(checkVac.equals("독-Free"))
				{
					numOfDoc++;
				}	
			}
			//현재 numOfDoc, numOfDae, numOfPre, numOfVit, numOfCov 안에 갯수 담김
			int lastYear=year-1; //21년
			NumVaccine num= new NumVaccine();
			num.setNumvaccineYear(lastYear);
			num.setNumvaccineCorona(numOfCov);
			num.setNumvaccineCold(numOfDoc);
			num.setNumvaccinePrevenar(numOfPre);
			num.setNumvaccineShingles(numOfDae);
			num.setNumvaccineVitd(numOfVit);
			int p=vaccineService.updateNumVaccine(num);
			NumVaccine num2= new NumVaccine();
			num2.setNumvaccineYear(year);
			num2.setNumvaccineCorona(0);
			num2.setNumvaccineCold(numOfDoc);
			num2.setNumvaccinePrevenar(0);
			num2.setNumvaccineShingles(0);
			num2.setNumvaccineVitd(0);
			
			int pr=vaccineService.updateNumVaccine(num);
			NumVaccine tr=vaccineService.addNumVaccine(num2);
		}
		
		cal= Calendar.getInstance(); 
		year=cal.get(Calendar.YEAR);
		//6개의 데이터 출력// 2021 2020 2019 2018 2017 2016
		year=year-5;//얘보다 같거나 큰것들 출력해야 한다.
		list =vaccineService.getNumVaccinesFromYear(year); 
		NumVaccine numVaccine1=list.get(0);
		NumVaccine numVaccine2=list.get(1);
		NumVaccine numVaccine3=list.get(2);
		NumVaccine numVaccine4=list.get(3);
		NumVaccine numVaccine5=list.get(4);
		//추후 numVaccine6은 예상값이 되어야 한다.
		NumVaccine numVaccine6=list.get(5);
		
		
		model.addAttribute("numvaccine1", numVaccine1);//jsp로 list 값 보내기.
		model.addAttribute("numvaccine2", numVaccine2);//jsp로 list 값 보내기.
		model.addAttribute("numvaccine3", numVaccine3);//jsp로 list 값 보내기.
		model.addAttribute("numvaccine4", numVaccine4);//jsp로 list 값 보내기.
		model.addAttribute("numvaccine5", numVaccine5);//jsp로 list 값 보내기.
		model.addAttribute("numvaccine6", numVaccine6);//jsp로 list 값 보내기.
		
		
		
		return "coldPredict";
	}
	}
	
	public static int predictForValue(List<NumVaccine> list, int predictForDependentVariable)
	{
		List<Integer> x = new ArrayList<Integer>();
		List<Integer> y = new ArrayList<Integer>();
		for(NumVaccine num: list)
		{
			int elsetot= num.getNumvaccineCorona()+num.getNumvaccinePrevenar()+num.getNumvaccineShingles()+num.getNumvaccineVitd();
			int doctot=num.getNumvaccineCold();
			x.add(elsetot);
			y.add(doctot);
		}
		if (x.size() != y.size())
            throw new IllegalStateException("Must have equal X and Y data points");

        Integer numberOfDataValues = x.size(); //x의 사이즈를 정의한다.

        List<Double> xSquared = x
                .stream()
                .map(position -> Math.pow(position, 2))
                .collect(Collectors.toList());

        List<Integer> xMultipliedByY = IntStream.range(0, numberOfDataValues)
                .map(i -> x.get(i) * y.get(i))
                .boxed()
                .collect(Collectors.toList());

        Integer xSummed = x
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Integer ySummed = y
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Double sumOfXSquared = xSquared
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Integer sumOfXMultipliedByY = xMultipliedByY
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        int slopeNominator = numberOfDataValues * sumOfXMultipliedByY - ySummed * xSummed;
        Double slopeDenominator = numberOfDataValues * sumOfXSquared - Math.pow(xSummed, 2);
        Double slope = slopeNominator / slopeDenominator;

        double interceptNominator = ySummed - slope * xSummed;
        double interceptDenominator = numberOfDataValues;
        Double intercept = interceptNominator / interceptDenominator;

        return (int) ((slope * predictForDependentVariable) + intercept);
	}
}
