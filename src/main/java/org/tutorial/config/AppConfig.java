package org.tutorial.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"org.tutorial"})
@PropertySource(value = {"classpath:application.properties"})
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource myDriverManagerDataSource = new BasicDataSource();
        myDriverManagerDataSource.setDriverClassName(env.getProperty("mysql.driver"));
        myDriverManagerDataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
        myDriverManagerDataSource.setUsername(env.getProperty("mysql.username"));
        myDriverManagerDataSource.setPassword(env.getProperty("mysql.password"));
        return myDriverManagerDataSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        System.out.println("Setting up resources");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
    }
}
