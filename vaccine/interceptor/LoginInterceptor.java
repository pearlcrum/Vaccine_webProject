package kr.or.connect.vaccine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//controller method 실행된 후에 실행 될 것이다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("{} 가종료되었습니다. {} 를 view로 사용합니다.", handler.toString(), modelAndView.getViewName());
		//중괄호 부분이 나타내고 있는 것은 각각 handler.toString(), modelAndView도 차례대로 맵핑
		//System.out.println(handler.toString() + " 가 종료되었습니다.  " + modelAndView.getViewName() + "을 view로 사용합니다.");
	}

	//controller method가 실행되기 전에 실행 될 것
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.debug("{} 를 호출했습니다.", handler.toString());
		//System.out.println(handler.toString() + " 를 호출했습니다.");
		//현재는 console에 추가하는 정도이지만 실제로는 logic을 넣어서 수행할 수 있음
		return true; // 해당 boolean값은 이후 controller를 수행할지 말지에 대한 값이다. false두면 실행 안함.
	}
}
