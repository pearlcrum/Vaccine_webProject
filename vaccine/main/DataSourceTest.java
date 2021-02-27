package kr.or.connect.vaccine.main;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;

public class DataSourceTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		DataSource ds=ac.getBean(DataSource.class);
		Connection conn=null; //connection 객체의 setautoCommit 메소드에 false를 파라미터로 지정하면 트랜잭션 처리 가능
		//입력 수정 삭제 sql이 실행한 후 connection이 가지고 있는 commit 메소드를 호출한다.
		try {
			conn=ds.getConnection();
			if(conn!=null)
			{
				System.out.println("접속성공");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			if(conn!=null)
			{
				try {
					conn.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
