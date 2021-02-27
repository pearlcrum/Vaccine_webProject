package kr.or.connect.vaccine.dao;

import static kr.or.connect.vaccine.dao.VaccinePriceSqls.*;

import java.util.Collections;
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

import kr.or.connect.vaccine.dto.VaccinePrice;

@Repository
public class VaccinePriceDao {

	private NamedParameterJdbcTemplate jdbcPrice;
	//jbbc 탬플릿은 바인딩 시 ?를 사용한다. sql 매핑되는지 어려운 경우 있어서 namedparameter 사용
	//이름을 이용해서 결과값 바인딩 가져올 수 있음.
	private SimpleJdbcInsert insertActionPrice;
    //insert를 위한 객체

	
	private RowMapper<VaccinePrice> rowMapperPrice= BeanPropertyRowMapper.newInstance(VaccinePrice.class);
	//beanpropertyRowMapper를 이용해서 column의 값을 자동으로 dto에 담는다.
	//자바와 sql 언어의 차이를 인지하고 이를 mapping해준다
	//role_id는 sql의 경우 이를 자바의 경우로 변환 시 roleId로 되어있음을 알 수 있다.
    //모든 Bean property를 담아주는 rowmapper를 자동으로 생성해주는 beanpropertyrowmapper 객체

	public VaccinePriceDao(DataSource dataSource) //dbconfig에서 bean으로 지정했던 것을 받아온다. db에대한 설명을 넣게되면
	{
		this.jdbcPrice=new NamedParameterJdbcTemplate(dataSource);
		this.insertActionPrice= new SimpleJdbcInsert(dataSource)
				.withTableName("vaccineprice")
				.usingGeneratedKeyColumns("vaccinepriceYear");
					//member table의 memberId로 사용 추후 안될시 member_id로 바꿔야 한다.
	}
	public List<VaccinePrice> selectAllPrice()
	{
		return jdbcPrice.query(SELECT_ALL_VACCINEPRICE, Collections.emptyMap(),rowMapperPrice);
		//Collections.emptyMap()의 경우 sql문에 바인딩할 값이 있는경우 바인딩 할 값을 전달할 목적으로 사용하고 있는 객체이다
		//rowMapper는 DTO에 저장하는 목적으로 사용된다.
		//query method는 결과가 여러건일 때 내부적으로 반복하며 dto를 생성한다.
		//생성한 dto를 list에다 담아주는 일을 하며 해당 list를 반환해준다.
	}
	public int insertPrice(VaccinePrice price)
	{
		SqlParameterSource params = new BeanPropertySqlParameterSource(price);
		// Role 객체 map으로 바꿔주는데 rold_id로 알아서 될거임
		return insertActionPrice.execute(params);
		//primary key를 자동으로 생성해야 되는 경우들도 존재한다.
		//simpleJdbc의 insertAction.execute로 전달을 할 경우에 값이 알아서 저장될 것이다. 
		
	}
	public int deleteByPrice(Integer year)
	{
		Map<String,?> params= Collections.singletonMap("vaccinepriceYear",year);
		//collections.sigletonMap의 경우 값이 여러개 들어가지 않고 딱 한 건만 들어갔을 때
		//사용할 수 있다.
		return jdbcPrice.update(DELETE_VACCINEPRICE, params);
	}
}
