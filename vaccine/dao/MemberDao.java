package kr.or.connect.vaccine.dao;


import static kr.or.connect.vaccine.dao.MemberDaoSqls.*;


import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.vaccine.dto.Member;

@Repository
public class MemberDao {

	private NamedParameterJdbcTemplate jdbc;
	//jbbc 탬플릿은 바인딩 시 ?를 사용한다. sql 매핑되는지 어려운 경우 있어서 namedparameter 사용
	//이름을 이용해서 결과값 바인딩 가져올 수 있음.
	private SimpleJdbcInsert insertAction;
    //insert를 위한 객체

	
	private RowMapper<Member> rowMapper= BeanPropertyRowMapper.newInstance(Member.class);
	//beanpropertyRowMapper를 이용해서 column의 값을 자동으로 dto에 담는다.
	//자바와 sql 언어의 차이를 인지하고 이를 mapping해준다
	//role_id는 sql의 경우 이를 자바의 경우로 변환 시 roleId로 되어있음을 알 수 있다.
    //모든 Bean property를 담아주는 rowmapper를 자동으로 생성해주는 beanpropertyrowmapper 객체

	
	public MemberDao(DataSource dataSource) //dbconfig에서 bean으로 지정했던 것을 받아온다. db에대한 설명을 넣게되면
	{
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction= new SimpleJdbcInsert(dataSource)
				.withTableName("member")
				.usingGeneratedKeyColumns("memberId");
					//member table의 memberId로 사용 추후 안될시 member_id로 바꿔야 한다.
	}
	public List<Member> selectAllWithOutPage()
	{
		return jdbc.query(SELECT_ALL, Collections.emptyMap(),rowMapper);
		//Collections.emptyMap()의 경우 sql문에 바인딩할 값이 있는경우 바인딩 할 값을 전달할 목적으로 사용하고 있는 객체이다
		//rowMapper는 DTO에 저장하는 목적으로 사용된다.
		//query method는 결과가 여러건일 때 내부적으로 반복하며 dto를 생성한다.
		//생성한 dto를 list에다 담아주는 일을 하며 해당 list를 반환해준다.
	}
	
   public List<Member> selectAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING, params, rowMapper);
   }
	
	
	public int insert(Member member)
	{
		SqlParameterSource params = new BeanPropertySqlParameterSource(member);
		// Role 객체 map으로 바꿔주는데 rold_id로 알아서 될거임
		return insertAction.executeAndReturnKey(params).intValue();
		//primary key를 자동으로 생성해야 되는 경우들도 존재한다.
		//simpleJdbc의 insertAction.execute로 전달을 할 경우에 값이 알아서 저장될 것이다. 
		
	}
	public int update(Member member)
	{
		SqlParameterSource params= new BeanPropertySqlParameterSource(member);
		return jdbc.update(UPDATE, params);
		//sqlparametersource가 제공하는 부분이 알아서 객체에 들어있는 것을 알아서 db column명에 맞춰 map으로 바꿔줌
		//두번쨰 parameter는 매핑시켜줄 객체
	}
	public int deleteById(Integer id)
	{
		Map<String,?> params= Collections.singletonMap("memberId",id);
		//collections.sigletonMap의 경우 값이 여러개 들어가지 않고 딱 한 건만 들어갔을 때
		//사용할 수 있다.
		return jdbc.update(DELETE_BY_Member_ID, params);
	}
	public Member selectById(Integer id)
	{
		try {
			Map<String,?> params= Collections.singletonMap("memberId", id);
			return jdbc.queryForObject(SELECT_BY_Member_ID,params,rowMapper);
			//한건 select시 queryForobject 사용, 두번째 파라미터는 params들어간다.
			//param에 해당하는 것이 없는 경우 있을 수 있으니 exception 꼭 check
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
	public int selectCountByMemberDate(Date date){
		Map<String,Date> params= new HashMap<>();
		params.put("date",date);
		return jdbc.queryForObject(SELECT_COUNT_BY_MEMBER_DATE,params,Integer.class);
	}
	//전체 다 선택일 경우 갯수
	public int selectCountByCondition(String memberAge, String memberSex, String memberVac, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("memberSex", memberSex);
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		return jdbc.queryForObject(SELECT_COUNT_BY_CONDITION, params, Integer.class);
	}
	//전체 다 선택일 경우 LIST
	public List<Member> selectByCondition(String memberAge, String memberSex, String memberVac,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("memberSex", memberSex);
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); //Int 값인데 넣기 위해 to String 해 놓았다 추후에 반드시 ParseInt 해줄 것.
		params.put("limit", limit);
		return jdbc.query(SELECT_BY_CONDITION_PAGING, params, rowMapper);
	}
	//전체 모두 선택 업을 경우 개수
	public int selectCountByTerm(String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return jdbc.queryForObject(SELECT_COUNT_BY_TERM, params, Integer.class);
	}
	//전체 모두 선택 없을 경우 LIST
	public List<Member> selectByTerm(String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); //Int 값인데 넣기 위해 to String 해 놓았다 추후에 반드시 ParseInt 해줄 것.
		params.put("limit", limit);
		return jdbc.query(SELECT_BY_TERM, params, rowMapper);
	}
	//나머지 전체 선택후 VAC만 선택할 경우 LIST
	public List<Member> selectAllWithoutVac(String memberVac,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); //Int 값인데 넣기 위해 to String 해 놓았다 추후에 반드시 ParseInt 해줄 것.
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_WITHOUT_VAC, params, rowMapper);
	}
	//나머지 전체 선택 후 VAC만 선택할 경우 갯수
	public int selectCountWithoutVac(String memberVac, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return jdbc.queryForObject(SELECT_COUNT_WITHOUT_VAC, params, Integer.class);
	}
	//나머지 전체 선택 후 성별만 선택일 경우 갯수
	public int selectCountWithoutSex(String memberSex, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberSex", memberSex);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		return jdbc.queryForObject(SELECT_COUNT_WITHOUT_SEX, params, Integer.class);
	}
	//나머지 전체 선택 후 성별만 선택일 경우 List
	public List<Member> selectAllWithoutSex(String memberSex,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberSex", memberSex);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); 
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_WITHOUT_SEX, params, rowMapper);
	}
	//나머지 전체 선택 후 나이만 선택일 경우 갯수
	public int selectCountWithoutAge(String memberAge, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		return jdbc.queryForObject(SELECT_COUNT_WITHOUT_AGE, params, Integer.class);
	}
	//나머지 전체 선택후 나이만 선택일 경우 list
	public List<Member> selectAllWithoutAge(String memberAge,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); 
		params.put("limit", limit);
			return jdbc.query(SELECT_ALL_WITHOUT_AGE, params, rowMapper);
	}
	//Age만 전체이고 성별 백신은 선택인 경우
	public int selectCountWithoutSexAndVac(String memberSex, String memberVac, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberSex", memberSex);
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		return jdbc.queryForObject(SELECT_COUNT_WITHOUT_SEX_AND_VAC, params, Integer.class);
	}
	//Age만 전체이고 성별 백신은 선택인 경우
	public List<Member> selectAllWithoutSexAndVac(String memberSex, String memberVac,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberSex", memberSex);
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); 
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_WITHOUT_SEX_AND_VAC, params, rowMapper);
	}
	//Vac만 전체일 경우
	public int selectCountWithoutAgeAndSex(String memberAge, String memberSex, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("memberSex", memberSex);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		return jdbc.queryForObject(SELECT_COUNT_WITHOUT_AGE_AND_SEX, params, Integer.class);
	}
	//Vac만 전체일 경우
	public List<Member> selectAllWithoutAgeAndSex(String memberAge, String memberSex,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("memberSex", memberSex);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); //Int 값인데 넣기 위해 to String 해 놓았다 추후에 반드시 ParseInt 해줄 것.
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_WITHOUT_AGE_AND_SEX, params, rowMapper);
	}
	//Sex만 전체일 경우
	public int selectCountWithoutAgeAndVac(String memberAge, String memberVac, String startDate, String endDate) {
		//들어갈 parameter 정의한다.
		Map<String, String> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		return jdbc.queryForObject(SELECT_COUNT_WITHOUT_AGE_AND_VAC, params, Integer.class);
	}
	//Sex만 전체일 경우
	public List<Member> selectAllWithoutAgeAndVac(String memberAge, String memberVac,
			String startDate, String endDate, Integer start, Integer limit){
		Map<String, Object> params = new HashMap<>();
		params.put("memberAge", memberAge);
		params.put("memberVac", memberVac);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("start", start); //Int 값인데 넣기 위해 to String 해 놓았다 추후에 반드시 ParseInt 해줄 것.
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_WITHOUT_AGE_AND_VAC, params, rowMapper);
	}
}
