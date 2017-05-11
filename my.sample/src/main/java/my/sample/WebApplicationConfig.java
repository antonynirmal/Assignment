package my.sample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Antony
 * 
 * Application Configuration
 *
 */
@ComponentScan(basePackages = { "my.sample" }, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class),
		@Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class) })
@Configuration
@EnableWebMvc
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

	/**
	 * 
	 */
	public WebApplicationConfig() {}



}
