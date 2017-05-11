package my.sample;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.security.config.BeanIds;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

/**
 * @author Antony
 * 
 * Configuration for WebApplication Initializer
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new Log4jConfigListener());
		registerDefaultServlet(servletContext);
		registerDispatcherServlet(servletContext);
		registerSpringSecurityFilterChain(servletContext);
	}
	
	private void registerDefaultServlet(ServletContext servletContext) {
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("default", new DefaultServlet());
		dynamic.addMapping("/test.html", "/index.html");
		dynamic.setLoadOnStartup(1);
	}
	
	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext(); 
        ctx.register(AssignmentApplication.class, WebApplicationConfig.class);  
        ctx.setServletContext(servletContext);
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); 
		dynamic.setAsyncSupported(true);
	    dynamic.addMapping("/*");  
	    dynamic.setLoadOnStartup(1);
	    servletContext.addListener(new ContextLoaderListener(ctx));
	}
	
	private void registerSpringSecurityFilterChain(ServletContext servletContext) {
		FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter(BeanIds.SPRING_SECURITY_FILTER_CHAIN, new DelegatingFilterProxy());
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
	}

}


