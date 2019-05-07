package pom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author fz
 * @version 0.1 (2018年12月20日 10:10:26)
 * @since 0.1
 * @see
 */

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class WebSiteBootstrap extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(WebSiteBootstrap.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(WebSiteBootstrap.class, args);
	}
}
