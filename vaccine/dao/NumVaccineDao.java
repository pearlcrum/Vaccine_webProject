package kr.or.connect.vaccine.dao;

import static kr.or.connect.vaccine.dao.MemberDaoSqls.UPDATE;
import static kr.or.connect.vaccine.dao.NumVaccineSqls.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.vaccine.dto.NumVaccine;

@Repository
public class NumVaccineDao {

	private NamedParameterJdbcTemplate jdbc;
	//jbbc 탬플릿은 바인딩 시 ?를 사용한다. sql 매핑되는지 어려운 경우 있어서 namedparameter 사용
	//이름을 이용해서 결과값 바인딩 가져올 수 있음.
	private SimpleJdbcInsert insertAction;
    //insert를 위한 객체

	private RowMapper<NumVaccine> rowMapper= BeanPropertyRowMapper.newInstance(NumVaccine.class);
	//beanpropertyRowMapper를 이용해서 column의 값을 자동으로 dto에 담는다.
	//자바와 sql 언어의 차이를 인지하고 이를 mapping해준다
	//role_id는 sql의 경우 이를 자바의 경우로 변환 시 roleId로 되어있음을 알 수 있다.
    //모든 Bean property를 담아주는 rowmapper를 자동으로 생성해주는 beanpropertyrowmapper 객체
	
	public NumVaccineDao(DataSource dataSource) //dbconfig에서 bean으로 지정했던 것을 받아온다. db에대한 설명을 넣게되면
	{
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction= new SimpleJdbcInsert(dataSource)
				.withTableName("numvaccine")
				.usingGeneratedKeyColumns("numvaccine_year");
	}
	public List<NumVaccine> selectAllVaccine()
	{
		return jdbc.query(SELECT_ALL_NUMVACCINE, Collections.emptyMap(),rowMapper);
		//Collections.emptyMap()의 경우 sql문에 바인딩할 값이 있는경우 바인딩 할 값을 전달할 목적으로 사용하고 있는 객체이다
		//rowMapper는 DTO에 저장하는 목적으로 사용된다.
		//query method는 결과가 여러건일 때 내부적으로 반복하며 dto를 생성한다.
		//생성한 dto를 list에다 담아주는 일을 하며 해당 list를 반환해준다.
	}
	public List<NumVaccine> selectAllVaccineByYear(Integer numvaccineYear) {
			Map<String, Integer> params = new HashMap<>();
			params.put("numvaccineYear", numvaccineYear);
			return jdbc.query(SELECT_ALL_NUMVACCINE_BY_YEAR, params, rowMapper);
	}
	public List<NumVaccine> selectAllVaccineFromYear(Integer numvaccineYear) {
		Map<String, Integer> params = new HashMap<>();
		params.put("numvaccineYear", numvaccineYear);
		return jdbc.query(SELECT_ALL_NUMVACCINE_FROM_YEAR, params, rowMapper);
}
	public List<NumVaccine> selectAllVaccineBelowYear(Integer numvaccineYear) {
		Map<String, Integer> params = new HashMap<>();
		params.put("numvaccineYear", numvaccineYear);
		return jdbc.query(SELECT_ALL_NUMVACCINE_BELOW_YEAR, params, rowMapper);
}
	
	public int insertVaccine(NumVaccine vaccine)
	{
		SqlParameterSource params = new BeanPropertySqlParameterSource(vaccine);
		// Role 객체 map으로 바꿔주는데 rold_id로 알아서 될거임
		return insertAction.execute(params);
		//primary key를 자동으로 생성해야 되는 경우들도 존재한다.
		//simpleJdbc의 insertAction.execute로 전달을 할 경우에 값이 알아서 저장될 것이다. 
		
	}
	public int deleteByNum(Integer year)
	{
		Map<String,?> params= Collections.singletonMap("numvaccineYear",year);
		//collections.sigletonMap의 경우 값이 여러개 들어가지 않고 딱 한 건만 들어갔을 때
		//사용할 수 있다.
		return jdbc.update(DELETE_NUMVACCINE, params);
	}
	public int updateByNum(NumVaccine vaccine)
	{
		SqlParameterSource params= new BeanPropertySqlParameterSource(vaccine);
		return jdbc.update(UPDATE_NUMVACCINE, params);
		//sqlparametersource가 제공하는 부분이 알아서 객체에 들어있는 것을 알아서 db column명에 맞춰 map으로 바꿔줌
		//두번쨰 parameter는 매핑시켜줄 객체
	}
}
