package tp.ms.base.rest.resource.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class MsSwaggerConfig implements WebMvcConfigurer {
	
	private static final String AUTH_SERVER = "https://acc.m96.co:9443/";
	private static final String CLIENT_ID = "rootid";

	/***********************WEB MVC 配置部分开始***************************************/

	/**
	 * 配置消息转换器为fastjson
	 * @return
	 */
//	@Bean 
//	public HttpMessageConverter<Object> fastJsonHttpMessageConverters() {
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//		List<MediaType> supportedMediaTypes = new ArrayList<>();
//		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
////		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
////		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
////		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
////		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
////		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
////		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
////		supportedMediaTypes.add(MediaType.APPLICATION_XML);
////		supportedMediaTypes.add(MediaType.IMAGE_GIF);
////		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
////		supportedMediaTypes.add(MediaType.IMAGE_PNG);
////		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
//		supportedMediaTypes.add(MediaType.TEXT_HTML);
////		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
//		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
////		supportedMediaTypes.add(MediaType.TEXT_XML);
//		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
//		return fastConverter;
//	}
	
	/**
	 * 配置消息转换器为string
	 * @return
	 */
	@Bean 
	public HttpMessageConverter<String> stringMessageConverters() {
		StringHttpMessageConverter converter = 
				new StringHttpMessageConverter(Charset.forName("UTF-8"));
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		converter.setSupportedMediaTypes(supportedMediaTypes);
		return converter;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
	/**
	 * 将Swagger2 的swagger-ui.html加入资源路径下,否则Swagger2静态页面不能访问。
	 * 要想使资源能够访问，可以有两种方法
	 * 一：需要配置类继承WebMvcConfigurationSupport 类，实现addResourceHandlers方法。
	 * 但是实现了WebMvcConfigurationSupport以后，Spring Boot的 WebMvc自动配置就会失效，具体表现比如访问不到
	 * 静态资源（js，css等）了，这是因为WebMvc的自动配置都在WebMvcAutoConfiguration类中，但是类中有这个注解
	 * 
	 * @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})，
	 * 意思是一旦在容器中检测到 WebMvcConfigurationSupport这个类，WebMvcAutoConfiguration类中的配置都不生效。
	 * 所以一旦我们自己写的配置类继承了WebMvcConfigurationSupport，
	 * 相当于容器中已经有了WebMvcConfigurationSupport，
	 * 所有默认配置都不会生效，都得自己在配置文件中配置。
	 *                                                                            
	 * 二：继承WebMvcConfigurer接口，这里采用此方法
	 *     网上有人说使用该方法会导致date编译等问题，可能在配置文件中得到解决，
	 *     本人没有碰到，不多做解释
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html")
//		.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("/static/**")
		.addResourceLocations("classpath:/static/");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")// 设置允许跨域的路径
				.allowedOrigins("*")// 设置允许跨域请求的域名
				.allowCredentials(true)// 是否允许证书 不再默认开启
				.allowedMethods("GET", "POST", "PUT", "DELETE")// 设置允许的方法
				.maxAge(3600);// 跨域允许时间
	}
	
	/***********************WEB MVC 配置部分结束**********************************/

	//https://blog.csdn.net/cl_andywin/article/details/53992353

	@Bean
	public Docket commonDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.forCodeGeneration(true)
				.globalOperationParameters(setToken())
				.groupName("通用API接口文档")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())//basePackage("com"))
				.paths(PathSelectors.any())
				.build()

//				.enableUrlTemplating(false)
//				.directModelSubstitute(LocalDate.class, String.class)
//				.genericModelSubstitutes(ResponseEntity.class)
//				.useDefaultResponseMessages(false)

				// 如果需要鉴权，请放开以下注释行->
//				.securitySchemes(newArrayList(securityScheme()))
//				.securityContexts(newArrayList(securityContext()))
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,
//						newArrayList(
//								new ResponseMessageBuilder()
//								.code(500)
//								.message("500 message")
//								.responseModel(new ModelRef("Error"))
//								.build(),
//								new ResponseMessageBuilder()
//								.code(403)
//								.message("Forbidden!")
//								.build()
//								))
//				.produces(produecetypes())
		// <-如果需要鉴权，请放开以上注释行
		// 如果需要全局性参数，请放开以下注释行->
		// .globalOperationParameters(
		// newArrayList(new ParameterBuilder()
		// .name("someGlobalParameter")
		// .description(
		// "Description of someGlobalParameter")
		// .modelRef(new ModelRef("string"))
		// .parameterType("query").required(true).build()));
		// <-如果需要全局性参数，请放开以上注释行
		// 如果需要UrlTemplating，请放开以下注释行->
		// .enableUrlTemplating(true);
		// <-如果需要UrlTemplating，请放开以上注释行

		;
	}
	
	/**
     * 设置token参数
     * @return
     */
    private List<Parameter> setToken(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("X-CSRF-TOKEN").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

	@SuppressWarnings("unused")
	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
				.tokenRequestEndpoint(new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
				.build();

		SecurityScheme oauth = new OAuthBuilder()
				.name("spring_oauth")
				.grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes()))
				.build();
		return oauth;
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for write operations"),
				new AuthorizationScope("foo", "Access foo API") };
		return scopes;
	}

	@SuppressWarnings("unused")
	private SecurityContext securityContext() {
		return SecurityContext
				.builder()
				.securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
				.forPaths(PathSelectors.regex("/foos.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("swagger descripted API")
				.description("这是一个Swagger描述")
				.version("2.0")
				.build();
	}

//	private HashSet<String> produecetypes() {
//		HashSet<String> hs = new HashSet<String>();
//		hs.add(MediaType.APPLICATION_JSON_VALUE);
//		hs.add(MediaType.TEXT_HTML_VALUE);
//		return hs;
//	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("对象销毁了");
		super.finalize();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private <T> List<T> newArrayList(T... t) {
		return Lists.newArrayList(t);
	}
}

/**
 * 	|获取授权code
 * 	|https://acc.m96.co:9443/oauth2.0/authorize?client_id=1100660&redirect_uri=https://acc.m96.co:9443/qnit-base&response_type=code&code_verifier=opiod
 * 	|返回跳转路径并携带的授权code
 * 	|https://acc.m96.co:9443/qnit-base?code=OC-9-RwyGsgz0ZDrpVa8xHcb3LicGO-zLUWWo
 * 	|OC-6-wVVmkcdGoSrWV4ERk-ySv9qhq9Dm8k9V
 * 
 * 	|得到授权code 获取访问token code过期时间默认很短30s
 * 	|https://acc.m96.co:9443/oauth2.0/accessToken?
 * 		grant_type=authorization_code&
 * 		code=OC-9-RwyGsgz0ZDrpVa8xHcb3LicGO-zLUWWo&
 * 		client_id=1100660&
 * 		client_secret=e65fa828bedf4a0392cf1e005921dab6&
 * 		code_verifier=opiod&
 * 		redirect_uri=https://acc.m96.co:9443/qnit-base
 * 	|
 * 	|https://acc.m96.co:9443/oauth2.0/accessToken?grant_type=authorization_code&code=OC-9-RwyGsgz0ZDrpVa8xHcb3LicGO-zLUWWo&client_id=1100660&client_secret=e65fa828bedf4a0392cf1e005921dab6&code_verifier=opiod&redirect_uri=https://acc.m96.co:9443/qnit-base
 * 
 */
/*
 * Docket类的方法： Docket groupName(String var):设置栏目名
 * 
 * Docket apiInfo(ApiInfo apiInfo):设置文档信息
 * 
 * Docket pathMapping(String path):设置api根路径
 * 
 * Docket protocols(Set<String>
 * protocols):设置协议，Sets为com.goolge.common下的类，Sets.newHashSet("https","http")
 * 相当于new HashSet(){{add("https");add("http");}};
 * 
 * ApiSelectorBuilder select():初始化并返回一个API选择构造器
 * 
 * 
 * 
 * ApiSelectorBuilder类的方法：
 * 
 * ApiSelectorBuilder apis(Predicate<RequestHandler>
 * selector):添加选择条件并返回添加后的ApiSelectorBuilder对象
 * 
 * ApiSelectorBuilder paths(Predicate<String>
 * selector):设置路径筛选，该方法中含一句pathSelector = and(pathSelector, selector);表明条件为相与
 * 
 * 
 * 
 * RequestHandlerSelectors类的方法：
 * 
 * Predicate<RequestHandler> any()：返回包含所有满足条件的请求处理器的断言，该断言总为true
 * 
 * Predicate<RequestHandler> none()：返回不满足条件的请求处理器的断言,该断言总为false
 * 
 * Predicate<RequestHandler> basePackage(final String
 * basePackage)：返回一个断言(Predicate)，该断言包含所有匹配basePackage下所有类的请求路径的请求处理器
 * 
 * 
 * 
 * PathSelectors类的方法：
 * 
 * Predicate<String> any():满足条件的路径，该断言总为true
 * 
 * Predicate<String> none():不满足条件的路径,该断言总为false
 * 
 * Predicate<String> regex(final String pathRegex):符合正则的路径
 * 
 * 
 * 
 * 设置swagger-ui.html默认路径，servlet的配置文件添加:
 * 
 * <mvc:annotation-driven></mvc:annotation-driven> <mvc:resources
 * mapping="/webjars/**" location="classpath:/META-INF/resources/webjars"/>
 * 
 * swagger-ui.html位于springfox-swagger-ui
 * jar包中的META-INF/resources/目录下，项目编译后swagger-ui.html将添加到classpath的/META-INF/
 * resources/下，所以添加mapping="/webjars/**"
 * 可通过localhost:端口号/项目名/swagger-ui.html打开SwaggerUI
 * 
 * 常用注解:
 * 
 * Swagger所有注解并非必须，若不加就只显示类目/方法名/参数名没有注释而已，但若注解中含@ApiParam不对应@RequestParam将无效果
 * 
 * @Api:注解controller，value为@RequestMapping路径
 * 
 * @ApiOperation:注解方法，value为简要描述,notes为全面描述,hidden=true Swagger将不显示该方法，默认为false
 * 
 * @ApiParam:注解参数,hidden=true
 * Swagger参数列表将不显示该参数,name对应参数名，value为注释，defaultValue设置默认值,allowableValues设置范围值,
 * required设置参数是否必须，默认为false
 * 
 * @ApiModel:注解Model
 * 
 * @ApiModelProperty:注解Model下的属性，
 * 当前端传过来的是一个对象时swagger中该对象的属性注解就是ApiModelProperty中的value
 * 
 * @ApiIgnore:注解类、参数、方法，注解后将不在Swagger UI中显示
 * 
 * 
 */