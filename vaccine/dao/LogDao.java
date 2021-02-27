package kr.or.connect.vaccine.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.vaccine.dto.Log;

@Repository
public class LogDao {

	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public LogDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("log")
                .usingGeneratedKeyColumns("id");
    }
    //usingGeneratedKeyColumns는 spring jdbc에서 아이디가 자동으로 입력되는 것
    //id column 값을 자동으로 입력하도록 됨 
    
	public Long insert(Log log) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(log);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	//executeAndReturnKey는 insert문을 내부적으로 실행하고 자동으로 생성된 id값을 return한다.
	//이때 return 된 id값도 사용할 수 있다.
}
