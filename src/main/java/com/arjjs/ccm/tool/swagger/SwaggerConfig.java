package com.arjjs.ccm.tool.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger-config
 * 
 * @author mao
 * @date 2019年10月09日 18:58:52
 */

@Configuration//不可缺少 相当于spring xml配置中的bean，放置在容器中
@EnableSwagger2 // 启动类注解
@EnableWebMvc
//@ComponentScan(basePackages = "com.arjjs.ccm.modules.ccm.rest.web")
public class SwaggerConfig {

		@Bean
		public Docket swaggerSpringMvcPlugin() {
			/*//添加head参数配置start
			ParameterBuilder tokenPar = new ParameterBuilder();
			List<Parameter> pars = new ArrayList<>();
			tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
			pars.add(tokenPar.build());*/
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					//.groupName("business-api")
					.select()
					.paths(PathSelectors.any())
					.apis(RequestHandlerSelectors.basePackage("com.arjjs.ccm.modules.ccm.rest.web"))
					//扫描Controller
					.build();
					//.globalOperationParameters(parms());
		}
		/**
		 * swagger api 信息描述<br/>
		 * @return {@link ApiInfo swagger API 对象}
		 */
		private ApiInfo apiInfo() {
			return new ApiInfoBuilder()
					.title("系统 API")
					.description("网格化管理 rest 接口")
					.termsOfServiceUrl("http://blog.didispace.com/")
					.license("License Version 1.0")
					.contact("xx开发组")
					.version("1.0")
					.build();
		}


	// 添加header参数
	private List<Parameter> parms(){
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name("Access-Token-T").description("token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		return pars;
	}
	/*@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.arjjs.ccm.modules.ccm.rest.web"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API文档")
				.description("简单优雅的restful风格")
				.version("1.0")
				.build();
	}*/
/*	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.arjjs.ccm.modules.ccm.rest.web"))
				.paths(PathSelectors.any()).build()//.groupName("1-通用接口文档").pathMapping("/")
				//.globalOperationParameters(parms())
				.apiInfo(apiInfo("通用接口文档", "", "1.0.0"));

	}
    private ApiInfo apiInfo(String name, String description, String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }*/
}

