package com.liping.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@ServletComponentScan //用于扫描所有的Servlet、filter、listener
public class DruidConfig
{
	@Value("${spring.datasource.druid.driverClassName}")  
    private String driverClassName;  
	@Value("${spring.datasource.druid.url}")  
    private String dbUrl;  
    @Value("${spring.datasource.druid.username}")  
    private String username;  
    @Value("${spring.datasource.druid.password}")  
    private String password;  
    @Value("${spring.datasource.druid.initialSize}")  
    private String initialSize;  
    @Value("${spring.datasource.druid.minIdle}")  
    private String minIdle;  
    @Value("${spring.datasource.druid.maxActive}")  
    private String maxActive;  
    @Value("${spring.datasource.druid.maxWait}")  
    private String maxWait;  
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")  
    private String timeBetweenEvictionRunsMillis;  
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")  
    private String minEvictableIdleTimeMillis;  
    @Value("${spring.datasource.druid.validationQuery}")  
    private String validationQuery;  
    @Value("${spring.datasource.druid.testWhileIdle}")  
    private String testWhileIdle;  
    @Value("${spring.datasource.druid.testOnBorrow}")  
    private String testOnBorrow;  
    @Value("${spring.datasource.druid.testOnReturn}")  
    private String testOnReturn;  
    @Value("${spring.datasource.druid.filters}")  
    private String filters;  

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfig.class.getName());
    
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.druid")
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(dbUrl);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setInitialSize(Integer.parseInt(initialSize));
		druidDataSource.setMinIdle(Integer.parseInt(minIdle));
		druidDataSource.setMaxWait(Integer.parseInt(maxWait));
		druidDataSource.setMaxActive(Integer.parseInt(maxActive));
		druidDataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
		druidDataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
		druidDataSource.setValidationQuery(validationQuery);
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(testWhileIdle));
		druidDataSource.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		druidDataSource.setTestOnReturn(Boolean.parseBoolean(testOnReturn));
		try{
			druidDataSource.setFilters(filters);
		}catch(Exception e){
			LOGGER.error("init druid dataSource error");
		}
		return druidDataSource;
	}
	
	@Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "root");
        reg.addInitParameter("loginPassword", "123456");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }
}
