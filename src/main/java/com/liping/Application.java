package com.liping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.liping.config.TokenIntercepter;

@SpringBootApplication
@MapperScan("com.liping.mapper")
public class Application extends WebMvcConfigurerAdapter
{
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
	}
	
	/**
	 * 配置token拦截器
	 * */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenIntercepter()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
