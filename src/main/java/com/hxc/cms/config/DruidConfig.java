package com.hxc.cms.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DruidConfig {
    
    @ConfigurationProperties(prefix="spring.druid")  //将配置文件中spring.druid.*配置到DruidDataSource中
    @Bean(initMethod="init",destroyMethod="close")
    public DruidDataSource dataSource(Filter statFilter) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return dataSource;
    }
    
    //配置慢连接过滤
    @Bean
    public Filter statFilter(){
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }
    
    //添加监控，可以帮助我们分析慢sql和每个sql的执行时间
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }
    
}