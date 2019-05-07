package generation;

import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fz
 * @version 0.1 (2018年8月13日 下午1:57:28)
 * @since 0.1
 * @see
 */

public class CodeGenerator {

	private final static String TARGET_PATH = "D:/workspaceGit/pom-api-parent/pom-api-gxg"; // 目标项目在硬盘上的基础路径
	private static final String PROJECT_PATH = "D:/workspaceGit/pom-api-parent/pom-api-gxg";// 当前项目在硬盘上的基础路径

	private final static String BASE_PACKAGE = "pom.api.gxg.service.account";// 目标项目包生成路径【domain、service、impl、xml、dao】
	private final static String CONTROLLER_PACKAGE = "pom.api.gxg.controller.account";// 当前项目controller生成包路径
	private final static String API_DESC = null;// controller描述，如果是null则默认为类对应的实体名

	private final static String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
	private final static String SERVICE_IMPL_PACKAGE = BASE_PACKAGE + ".service.impl";
	private final static String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";
	private final static String MODEL_PACKAGE = BASE_PACKAGE + ".model";
	private final static String DOMAIN_PACKAGE = BASE_PACKAGE + ".domain";

	// JDBC配置，请修改为你项目的实际配
	private static final String JDBC_URL = "jdbc:oracle:thin:@10.7.142.100:1521:test";
	private static final String JDBC_USERNAME = "bobus";
	private static final String JDBC_PASSWORD = "bobus";
	private static final String JDBC_DIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";

	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/generator/template";// 模板位置

	private static final String JAVA_PATH = "/src/main/java"; // java文件路径
	private static final String RESOURCES_PATH = "/src/main/resources";// 资源文件路径

	private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);// 生成的Service存放路径
	private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);// 生成的Service实现存放路径
	private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);// 生成的Controller存放路径
	private static final String PACKAGE_PATH_MODEL = packageConvertPath(MODEL_PACKAGE);// 生成的Controller存放路径

	private static final String AUTHOR = "fz";// @author
	private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());// @date

	public static void main(String[] args) {

		genCode("pure_user");
	}

	/**
	 * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。 如输入表名称 "t_user_detail" 将生成
	 * TUserDetail、TUserDetailMapper、TUserDetailService ...
	 * 
	 * @param tableNames
	 *            数据表名称...
	 */
	public static void genCode(String... tableNames) {
		for (String tableName : tableNames) {
			genCode(tableName, null);
		}
	}

	/**
	 * 通过数据表名称，和自定义的 Model 名称生成代码 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User"
	 * 将生成 User、UserMapper、UserService ...
	 * 
	 * @param tableName
	 *            数据表名称
	 * @param modelName
	 *            自定义的 Model 名称
	 */
	public static void genCode(String tableName, String modelName) {
		// modelName = tableName.substring(tableName.indexOf("_")+1);
		modelName = getUrlByCode(tableName);
		modelName= do17(modelName);
		genModelAndMapper(tableName, modelName);
		genService(modelName, modelName);
		genController(modelName, modelName);
		genModel(modelName, modelName);
	}

	public static void genModelAndMapper(String tableName, String modelName) {
		Context context = new Context(ModelType.FLAT);
		context.setId("Potato");
		context.setTargetRuntime("MyBatis3Simple");
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
		context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.setConfigurationType("generation.MyCommentGenerator");// 自定义的全局类名
		context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
		jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
		jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
		jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetProject(TARGET_PATH + JAVA_PATH);
		javaModelGeneratorConfiguration.setTargetPackage(DOMAIN_PACKAGE);
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetProject(TARGET_PATH + RESOURCES_PATH);
		sqlMapGeneratorConfiguration.setTargetPackage("mapper");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetProject(TARGET_PATH + JAVA_PATH);
		javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

		TableConfiguration tableConfiguration = new TableConfiguration(context);
		tableConfiguration.setTableName(tableName);
		tableConfiguration.setDomainObjectName(modelName);
		tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Oracle", true, null));
		context.addTableConfiguration(tableConfiguration);

		List<String> warnings;
		MyBatisGenerator generator;
		try {
			Configuration config = new Configuration();
			config.addContext(context);
			config.validate();

			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);	
			warnings = new ArrayList<String>();
			generator = new MyBatisGenerator(config, callback, warnings);
			generator.generate(null);
		} catch (Exception e) {
			throw new RuntimeException("生成Model和Mapper失败", e);
		}

		if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
			throw new RuntimeException("生成Model和Mapper失败" + warnings);
		}
		if (StringUtils.isEmpty(modelName))
		System.out.println(modelName + ".java 生成成功");
		System.out.println(modelName + "Mapper.java 生成成功");
		System.out.println(modelName + "Mapper.xml 生成成功");
	}

	public static void genService(String tableName, String modelName) {
		try {
			freemarker.template.Configuration cfg = getConfiguration();

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("date", DATE);
			data.put("author", AUTHOR);
//			String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName)
//					: tableNameConvertUpperCamel(modelName);
			String modelNameUpperCamel = tableName;
			data.put("modelNameUpperCamel", modelNameUpperCamel);
			data.put("modelNameLowerCamel", modelNameUpperCamel);
			data.put("basePackage", BASE_PACKAGE);

			File file = new File(TARGET_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("service.ftl").process(data, new FileWriter(file));
			System.out.println(modelNameUpperCamel + "Service.java 生成成功");

			File file1 = new File(
					TARGET_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
			if (!file1.getParentFile().exists()) {
				file1.getParentFile().mkdirs();
			}
			cfg.getTemplate("service-impl.ftl").process(data, new FileWriter(file1));
			System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Service失败", e);
		}
	}

	public static void genController(String tableName, String modelName) {
		try {
			freemarker.template.Configuration cfg = getConfiguration();

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("date", DATE);
			data.put("author", AUTHOR);
//			String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName)
//					: tableNameConvertUpperCamel(modelName);
			String modelNameUpperCamel =tableName;
			data.put("baseRequestMapping", modelNameUpperCamel);
			data.put("modelNameUpperCamel", modelNameUpperCamel);
			data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
			data.put("controllerPackage", CONTROLLER_PACKAGE);
			data.put("basePackage", BASE_PACKAGE);
			data.put("apiDesc", API_DESC);

			File file = new File(
					PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// cfg.getTemplate("controller-restful.ftl").process(data, new
			// FileWriter(file));
			cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

			System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Controller失败", e);
		}

	}

	public static void genModel(String tableName, String modelName) {
		try {
			freemarker.template.Configuration cfg = getConfiguration();

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("date", DATE);
			data.put("author", AUTHOR);
//			String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName)
//					: tableNameConvertUpperCamel(modelName);
			String modelNameUpperCamel = tableName;
			data.put("modelNameUpperCamel", modelNameUpperCamel);
			data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
			data.put("basePackage", BASE_PACKAGE);

			File file = new File(TARGET_PATH + JAVA_PATH + PACKAGE_PATH_MODEL + modelNameUpperCamel + "Model.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// cfg.getTemplate("controller-restful.ftl").process(data, new
			// FileWriter(file));
			cfg.getTemplate("model.ftl").process(data, new FileWriter(file));
			System.out.println(modelNameUpperCamel + "Model.java 生成成功");

		} catch (Exception e) {
			throw new RuntimeException("生成Constant失败", e);
		}

	}

	private static freemarker.template.Configuration getConfiguration() throws IOException {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration(
				freemarker.template.Configuration.VERSION_2_3_0);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}

//	private static String tableNameConvertLowerCamel(String tableName) {
//		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
//	}

//	private static String tableNameConvertUpperCamel(String tableName) {
//		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
//
//	}

//	private static String tableNameConvertMappingPath(String tableName) {
//		tableName = tableName.toLowerCase();// 兼容使用大写的表
//		return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
//	}

//	private static String modelNameConvertMappingPath(String modelName) {
//		String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
//		return tableNameConvertMappingPath(tableName);
//	}

	private static String packageConvertPath(String packageName) {
		return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}

	public static String getUrlByCode(String code) {
		char[] charArray = code.toCharArray();
		// A-Z 对应数字65-90 a-z 对应数字97-122
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '_') {
				// 字符在97-122之间的都是小写字母，在原基础上加32转换成大写
				if (charArray[i + 1] >= 97 && charArray[i + 1] <= 122) {
					int upper = charArray[i + 1] - 32;
					charArray[i + 1] = (char) upper;
				}
			}
		}
		StringBuffer url = new StringBuffer("");
		for (int i = 0; i < charArray.length; i++) {
			url.append(charArray[i]);
		}
		return url.toString().replace("_", "");
	}

	public static String do17(String str) {
		if (str != null && str != "") {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}
}
