package pom.api.gxg.db;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pom.api.gxg.PlatformConstants;



/**
* @author fz
* @version 0.1 (2018年8月27日 下午1:30:05)
* @since 0.1
* @see
*/
@Configuration
public class MybatisConfigurer {
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		final MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(PlatformConstants.MAPPER_PACKAGE);//dao扫描路径
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		return mapperScannerConfigurer;
	}
}
