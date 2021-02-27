package kr.or.connect.vaccine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.or.connect.vaccine.dao", "kr.or.connect.vaccine.service"})
@Import({DBConfig.class})
public class ApplicationConfig {
	//해당 환경 설정 클래스는, Spring과 관련된 설정을 모아둔 클래스이며 스프링 환경 설정 클래스의 root역할을 할 예정이다.
}
//componentScan을 씀으로서 daoexam.dao에 있는 repository를 bean으로 등록해준 것랑 같은 역할을 해준다.