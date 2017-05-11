package my.sample;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author Antony
 * 
 * Configuration for MySql Database
 *
 */
@Configuration
public class MySqlDBConfig implements EnvironmentAware {

@Autowired
private Environment env;

public MySqlDBConfig() {  }


@Bean(name="mysqlDataSource")
public DataSource mysqlDataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName(env.getProperty("mysql.driverClassName"));
	dataSource.setUrl(env.getProperty("mysql.url"));
	dataSource.setUsername(env.getProperty("mysql.username"));
	dataSource.setPassword(env.getProperty("mysql.password"));
	dataSource.setDefaultAutoCommit(env.getProperty("mysql.autoCommit", Boolean.class));
	dataSource.setInitialSize(env.getProperty("mysql.initialSize", Integer.class));
	dataSource.setMaxActive(env.getProperty("mysql.maxActive", Integer.class));
	dataSource.setMaxIdle(env.getProperty("mysql.maxIdle", Integer.class));
	dataSource.setMinIdle(env.getProperty("mysql.minIdle", Integer.class));
	dataSource.setValidationQuery(env.getProperty("mysql.validationQuery"));
	/*dataSource.setMaxWait(env.getProperty("mysql.maxWait", Long.class));	
	dataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("mysql.timeBetweenEvictionRunsMillis", Long.class));
	dataSource.setMinEvictableIdleTimeMillis(env.getProperty("mysql.minEvictableIdleTimeMillis", Long.class));
	dataSource.setTestWhileIdle(env.getProperty("mysql.testWhileIdle", Boolean.class));	*/
	return dataSource;
}


@Bean
public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(mysqlDataSource());
}



@Bean(name="sqlSessionFactory")
public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(mysqlDataSource());
    sqlSessionFactoryBean.setTypeAliasesPackage("my.sample.models");
    PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:my/sample/mapper/*.xml"));
    return sqlSessionFactoryBean.getObject();   
}


@Bean
public MapperScannerConfigurer sqlMapperScannerConfigurer() throws Exception {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("my.sample.mapper");
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    return configurer;
}


@Override
public void setEnvironment(final Environment env) {
  this.env = env;
}


}


