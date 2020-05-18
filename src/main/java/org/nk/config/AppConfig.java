package org.nk.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:app.properties")
@ComponentScan("org.nk")
public class AppConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource ds() {
		
		BasicDataSource d=new BasicDataSource();
		d.setDriverClassName(env.getProperty("db.driver"));
		d.setUrl(env.getProperty("db.url"));
		d.setUsername(env.getProperty("db.user"));
		d.setPassword(env.getProperty("db.password"));
		
		return d;
	}
	
	@Bean
	public LocalSessionFactoryBean sf() {
		
		LocalSessionFactoryBean s=new LocalSessionFactoryBean();
		s.setDataSource(ds());
		s.setHibernateProperties(props());
		//s.setAnnotatedClasses(ShipmentType.class);
		s.setPackagesToScan("org.nk.model");
		return s;
	}
	
	@Bean
	public Properties props() {
		
		Properties p=new Properties();
		p.put("hibernate.dialect", env.getProperty("orm.dialect"));
		p.put("hibernate.show_sql", env.getProperty("orm.showsql"));
		p.put("hibernate.format_sql", env.getProperty("orm.fmtsql"));
		p.put("hibernate.hbm2ddl.auto", env.getProperty("orm.dllauto"));
		
		return p;
	}
	@Bean
	public HibernateTemplate ht() {
		
		HibernateTemplate ht=new HibernateTemplate();
		ht.setSessionFactory(sf().getObject());
		return ht;
	}
	
	@Bean
	public HibernateTransactionManager htx() {
	
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sf().getObject());
		return htm;
	}
	
	@Bean
	public InternalResourceViewResolver ivr() {
		
		InternalResourceViewResolver irvs=new InternalResourceViewResolver();
		irvs.setPrefix(env.getProperty("mvc.prefix"));
		irvs.setSuffix(env.getProperty("mvc.suffix"));
		return irvs;
	}
	
	//Activate CMP
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		return new CommonsMultipartResolver();
	}
	
	//Active Resource folder
			
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
		
	
	//Mail Config Bean
		@Bean
		public JavaMailSender mailSender() {
			
			JavaMailSenderImpl jms=new JavaMailSenderImpl();
			jms.setHost(env.getProperty("mail.host"));
			jms.setPort(env.getProperty("mail.port",Integer.class));
			jms.setUsername(env.getProperty("mail.user"));
			jms.setPassword(env.getProperty("mail.pwd"));
			jms.setJavaMailProperties(mailProps());
			
			return jms;
		}

		@Bean
		public Properties mailProps() {

			Properties p=new Properties();
			p.put("mail.smtp.starttls.enable", env.getProperty("mail.secure.enable"));
			p.put("mail.smtp.auth", env.getProperty("mail.auth.enable"));
			return p;
			
		}

		
}
