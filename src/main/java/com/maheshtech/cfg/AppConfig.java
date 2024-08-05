package com.maheshtech.cfg;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.maheshtech.entity.Product;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages= {"com.maheshtech.web" , "com.maheshtech.dao" , "com.maheshtech.aspects"})
@Configuration
@EnableWebMvc
public class AppConfig implements WebApplicationInitializer, WebMvcConfigurer{
		
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx;
		ctx=new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		Dynamic servlet = servletContext.addServlet("ds", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		
	}
	
	@Value("${jdbc.driver}")
	private String DriverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	

	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(DriverClassName);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		bds.setInitialSize(10);
		bds.setMaxTotal(100);
		bds.setMaxWaitMillis(500);
		bds.setMaxIdle(50);
		bds.setMinIdle(2);
		return bds;
	} 
		
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean lsbf = new LocalSessionFactoryBean();
		lsbf.setDataSource(dataSource);
		lsbf.setAnnotatedClasses(Product.class);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");

		lsbf.setHibernateProperties(props);
		return lsbf;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Bean
	public HibernateTransactionManager txmgr(SessionFactory sesssionFactory) {
	return new HibernateTransactionManager(sesssionFactory);
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver irvr=new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/pages/");
		irvr.setSuffix(".jsp");
	return irvr;	
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/price-form").setViewName("price-form");
	 
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource src=new ReloadableResourceBundleMessageSource();
		src.setBasenames("classpath:error-messages");
		src.setDefaultEncoding("UTF-8");
		return src;
	}
	
	
}