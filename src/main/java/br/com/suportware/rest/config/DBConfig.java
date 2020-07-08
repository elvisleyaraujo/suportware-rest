package br.com.suportware.rest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("br.com.suportware.rest.mapper")
public class DBConfig {

	public static final String TRANSACTION_SQL_SERVER     = "transactionSqlServer";
	private static final String SESSION_FACTORY_SQL_SERVER = "sqlServerSessionFactory";
	private static final String DATASOURCE_SQL_SERVER      = "app.datasource";

	//@Value("${mybatis.type-handlers-package}")
	private String TYPEHANDLER_VALUE;

	@Autowired
	private ResourceLoader resourceLoader;

	@Bean(name = DATASOURCE_SQL_SERVER)
	@ConfigurationProperties(prefix = DATASOURCE_SQL_SERVER)
	public DataSource dataSource() {
		return new HikariDataSource();
	}

	@Bean(name = TRANSACTION_SQL_SERVER)
	public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(DATASOURCE_SQL_SERVER) DataSource dataSource) {
		DataSourceTransactionManager dtm = new DataSourceTransactionManager(dataSource);
		return dtm;
	}

	@Bean(name = SESSION_FACTORY_SQL_SERVER, destroyMethod = "")
	public SqlSessionFactoryBean sessionFactoryBean(@Qualifier(DATASOURCE_SQL_SERVER) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeHandlersPackage(TYPEHANDLER_VALUE);
		sqlSessionFactoryBean.setMapperLocations(
				ResourcePatternUtils
						.getResourcePatternResolver(resourceLoader)
						.getResources("classpath*:**/mappers/*Mapper.xml"));
		return sqlSessionFactoryBean;
	}


}