package kr.or.connect.vaccine.dao;

public class VaccinePriceSqls {

	public static final String SELECT_ALL_VACCINEPRICE ="SELECT vaccineprice_year, vaccineprice_corona, vaccineprice_cold, vaccineprice_shingles, vaccineprice_prevenar, vaccineprice_vitd, vaccineprice_coldfree, vaccineprice_special, vaccineprice_docdae, vaccineprice_docpre, vaccineprice_docdaepre, vaccineprice_docvitd FROM vaccineprice order by vaccineprice_year";
//	public static final String INSERT_VACCINEPRICE="INSERT INTO vaccineprice(vaccineprice_year, vaccineprice_corona, vaccineprice_cold, vaccineprice_shingles, vaccineprice_prevenar, vaccineprice_vitd,"
//			+ "vaccineprice_coldfree, vaccineprice_special, vaccineprice_docdae, vaccineprice_docpre, vaccineprice_docdaepre, vaccineprice_docvitd) VALUES (:vaccinepriceYear,:vaccinepriceCorona,:vaccinepriceCold,:vaccinepriceShingles,:vaccinepricePrevenar,:vaccinepriceVitd"
//			+ ",:vaccinepriceColdfree,:vaccinepriceSpecial,:vaccinepriceDocdae,:vaccinepriceDocpre,:vaccinepriceDocdaepre,:vaccinepriceDocvitd);";


	public static final String DELETE_VACCINEPRICE="DELETE FROM vaccineprice where vaccineprice_year=:vaccinepriceYear";
}
