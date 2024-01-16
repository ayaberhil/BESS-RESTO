package com.projet.projet.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	public MvcConfig() {
		super();
	}

	@Bean
	public ClassLoaderTemplateResolver yourTemplateResolver() {
		ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
		configurer.setPrefix("templates/");
		configurer.setSuffix(".html");
		configurer.setTemplateMode(TemplateMode.HTML);
		configurer.setCharacterEncoding("UTF-8");
		configurer.setCacheable(false);
		configurer.setOrder(0); 
		configurer.setCheckExistence(true);
		return configurer;
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html");
        registry.addViewController("/login.html");
    }
	
	
	@Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
