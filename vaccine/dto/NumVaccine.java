package kr.or.connect.vaccine.dto;

public class NumVaccine {

	private int numvaccineYear;
	private int numvaccineCold;
	private int numvaccineCorona;
	private int numvaccinePrevenar;
	private int numvaccineShingles;
	private int numvaccineVitd;
	public int getNumvaccineYear() {
		return numvaccineYear;
	}
	public void setNumvaccineYear(int numvaccineYear) {
		this.numvaccineYear = numvaccineYear;
	}
	public int getNumvaccineCold() {
		return numvaccineCold;
	}
	public void setNumvaccineCold(int numvaccineCold) {
		this.numvaccineCold = numvaccineCold;
	}
	public int getNumvaccineCorona() {
		return numvaccineCorona;
	}
	public void setNumvaccineCorona(int numvaccineCorona) {
		this.numvaccineCorona = numvaccineCorona;
	}
	public int getNumvaccinePrevenar() {
		return numvaccinePrevenar;
	}
	public void setNumvaccinePrevenar(int numvaccinePrevenar) {
		this.numvaccinePrevenar = numvaccinePrevenar;
	}
	public int getNumvaccineShingles() {
		return numvaccineShingles;
	}
	public void setNumvaccineShingles(int numvaccineShingles) {
		this.numvaccineShingles = numvaccineShingles;
	}
	public int getNumvaccineVitd() {
		return numvaccineVitd;
	}
	public void setNumvaccineVitd(int numvaccineVitd) {
		this.numvaccineVitd = numvaccineVitd;
	}
	@Override
	public String toString() {
		return "NumVaccine [numvaccineYear=" + numvaccineYear + ", numvaccineCold=" + numvaccineCold
				+ ", numvaccineCorona=" + numvaccineCorona + ", numvaccinePrevenar=" + numvaccinePrevenar
				+ ", numvaccineShingles=" + numvaccineShingles + ", numvaccineVitd=" + numvaccineVitd + "]";
	}
	
	
}
