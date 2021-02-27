package kr.or.connect.vaccine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.or.connect.vaccine.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"kr.or.connect.vaccine.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	//DispathcerServlet이 실행될 때 읽어 들이는 설정 파일

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
	// 모든 요청이 들어왔을 때 서블릿이 실행하게 하는데, 들어오는 요청 중 컨트롤러의 url이 매핑 안된 css img js 들어올 수 있음
	// css img js 이런식으로 시작되는 url요청은 어플리케이션 root directory 아래에 있는 각각의 디렉토리를 만들고 알맞게 사용하게 해준다.
	// 이러한 요청이 들어오는 것은 여기서 찾아요라는 뜻이다. 이부분 없다면 controller가 가진 requestMapping 내에서 찾으려하며 오류를 발생시킬 수 있다.
 
    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //파라미터로 전달받은 enable 메서드 사용함으로서 DefaultServletHandlerConfigurer 사용하도록 해주는 것
    //매핑 정보가 없는 url 요청은 스프링의 DefaultServletHandlerConfigurer가 처리해준다. Was에 Default servlet에게 해당 일을 넘기게 된다.
    //그러면 was 는 Default servlet이 static한 자원을 읽어서 보여주게 해주는 것이다.
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }
    //특정 url에 대한 처리를  controller class를 작정하지 않고 매핑할 수 있도록 해주는 것이다.
    // 슬러쉬(/)라는 애가 오게되면 main이라는 뷰를 보여주도록 하는 것이다. 
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    //view 네임은 view 리졸버에서 찾아온다.  addviewcontroller와 합쳐지면
    // web-inf/vies/main.jsp와 같은 형식으로 가능해진다.
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
    		registry.addInterceptor(new LoginInterceptor());
	} // 인터셉터 클래스 등록
    
    
    
}
