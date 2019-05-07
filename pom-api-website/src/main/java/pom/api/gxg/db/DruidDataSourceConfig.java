package pom.api.gxg.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;

import pom.api.gxg.PlatformConstants;



/**
 * @author fz 
 * @version 0.1 (2018年8月27日 下午2:26:34)
 * @since 0.1
 * @see
 * @mybatis和数据库连接配置
 */
@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidDataSourceConfig {

	@Autowired
	private DruidDataSourceProperties druidDataSourceProperties;

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(druidDataSourceProperties.getUrl());
		ds.setUsername(druidDataSourceProperties.getUsername());
		ds.setPassword(druidDataSourceProperties.getPassword());
		ds.setDriverClassName(druidDataSourceProperties.getDriverClassName());

		ds.setInitialSize(druidDataSourceProperties.getInitialSize());
		ds.setMinIdle(druidDataSourceProperties.getMinIdle());
		ds.setMaxActive(druidDataSourceProperties.getMaxActive());
		// 配置获取连接等待超时的时间
		ds.setMaxWait(druidDataSourceProperties.getMaxWait());
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		ds.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		ds.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
		ds.setValidationQuery(druidDataSourceProperties.getValidationQuery());
		ds.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
		ds.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
		ds.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
		ds.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
		ds.setMaxPoolPreparedStatementPerConnectionSize(
				druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
		ds.setUseGlobalDataSourceStat(druidDataSourceProperties.isUseGlobalDataSourceStat());

		try {
			ds.setFilters(druidDataSourceProperties.getFilters());
		} catch (SQLException e) {
			// logger.error("druid configuration initialization filter", e);
		}

		// druid日志过滤器
		List<Filter> filters = new ArrayList<Filter>();
		Slf4jLogFilter logFilter = new Slf4jLogFilter();
		logFilter.setDataSourceLogEnabled(false);
		logFilter.setConnectionLogEnabled(false);
		logFilter.setStatementLogEnabled(false);
		logFilter.setResultSetLogEnabled(false);
		logFilter.setStatementExecutableSqlLogEnable(true);
		filters.add(logFilter);
		ds.setProxyFilters(filters);

		ds.setConnectionProperties(druidDataSourceProperties.getConnectionProperties());

		return ds;

	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setUseGeneratedKeys(false); // 使用jdbc的getGeneratedKeys获取数据库自增主键值
		configuration.setUseColumnLabel(true);// 使用列别名替换列名 select user as User
		configuration.setMapUnderscoreToCamelCase(true);// -自动使用驼峰命名属性映射字段
														// userId -> user_id
		configuration.setLogImpl(Slf4jImpl.class);
		configuration.setLogPrefix("dao.");
		bean.setConfiguration(configuration);
		bean.setFailFast(true);
		bean.setTypeAliasesPackage(PlatformConstants.DOMAIN_PATH);//domain路径配置

		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + PlatformConstants.XML_MAPPER_PATH;//xml扫描配置
		bean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
