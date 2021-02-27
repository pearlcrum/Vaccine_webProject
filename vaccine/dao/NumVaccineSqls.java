package kr.or.connect.vaccine.dao;

public class NumVaccineSqls {

	public static final String SELECT_ALL_NUMVACCINE ="SELECT numvaccine_year, numvaccine_cold, numvaccine_corona, numvaccine_shingles, numvaccine_prevenar, numvaccine_vitd FROM numvaccine order by numvaccine_year";
	public static final String SELECT_ALL_NUMVACCINE_BY_YEAR ="SELECT numvaccine_year, numvaccine_cold, numvaccine_corona, numvaccine_shingles, numvaccine_prevenar, numvaccine_vitd FROM numvaccine WHERE numvaccine_year<= :numvaccineYear order by numvaccine_year";
	public static final String SELECT_ALL_NUMVACCINE_FROM_YEAR ="SELECT numvaccine_year, numvaccine_cold, numvaccine_corona, numvaccine_shingles, numvaccine_prevenar, numvaccine_vitd FROM numvaccine WHERE numvaccine_year>= :numvaccineYear order by numvaccine_year";

	public static final String SELECT_ALL_NUMVACCINE_BELOW_YEAR="SELECT numvaccine_year, numvaccine_cold, numvaccine_corona, numvaccine_shingles, numvaccine_prevenar, numvaccine_vitd FROM numvaccine WHERE numvaccine_year>=2017 && numvaccine_year< :numvaccineYear order by numvaccine_year";
	public static final String DELETE_NUMVACCINE="DELETE FROM numvaccine where numvaccine_year = :numvaccineYear";
	public static final String UPDATE_NUMVACCINE="UPDATE numvaccine set numvaccine_cold= :numvaccineCold, numvaccine_corona= :numvaccineCorona, numvaccine_prevenar= :numvaccinePrevenar ,numvaccine_shingles= :numvaccineShingles ,numvaccine_vitd= :numvaccineVitd where numvaccine_year= :numvaccineYear";
}
