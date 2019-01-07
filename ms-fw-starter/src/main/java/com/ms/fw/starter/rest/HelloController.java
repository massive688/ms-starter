package com.ms.fw.starter.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {


	
	@SuppressWarnings("unused")
	@RequestMapping("/index")
    public String index(HttpServletRequest request) {

        //获取cas给我们传递回来的对象，这个东西放到了session中
        //session的 key是 _const_cas_assertion_
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);  

        Assertion assertion3 =  AssertionHolder.getAssertion();
        
//        //获取登录用户名
//        String loginName =assertion.getPrincipal().getName();
//        System.out.printf("登录用户名:%s\r\n",loginName);
        Object vo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取自定义返回值的数据
        Principal principal  = request.getUserPrincipal();
        if (request.getUserPrincipal() != null) {
        	
            if (principal instanceof CasAuthenticationToken) {
            	CasAuthenticationToken token = (CasAuthenticationToken)principal;
            	
            	 //cas传递过来的数据
                Map<String,Object> result = token.getAssertion().getPrincipal().getAttributes();
                result.put("vodetails", vo);
                for(Map.Entry<String, Object> entry :result.entrySet()) {
                    String key = entry.getKey();
                    Object val = entry.getValue();
                    System.out.printf("%s:%s\r\n",key,val);
                }
                return JSON.toJSONString(result);
            }
            if (principal instanceof AttributePrincipal) {
                //cas传递过来的数据
                Map<String,Object> result =( (AttributePrincipal)principal).getAttributes();
                for(Map.Entry<String, Object> entry :result.entrySet()) {
                    String key = entry.getKey();
                    Object val = entry.getValue();
                    System.out.printf("%s:%s\r\n",key,val);
                }
            }
        }

        return JSON.toJSONString(principal);
    }
	@SuppressWarnings("unused")
	@RolesAllowed({"USER", "ADMIN"}) 
	@GetMapping("hello")
	public Object hello(HttpServletRequest   request) {
//		return session.getAttribute(DefaultGatewayResolverImpl.CONST_CAS_GATEWAY); 
		Assertion assertion=AssertionHolder.getAssertion();
        Assertion assertion2 = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);  

        //获取登录用户名
//        String loginName =assertion.getPrincipal().getName();
//        System.out.printf("登录用户名:%s\r\n",loginName);

        //获取自定义返回值的数据
        CasAuthenticationToken principal  =  (CasAuthenticationToken) request.getUserPrincipal();
//        if(Boolean.valueOf(true)) {
//        	return JSON.toJSONString(principal.get);
//        }
        
		request.getUserPrincipal();
		Map<String,Object> map = null;
		if(assertion!=null)
		{
			AttributePrincipal attributePrincipal=assertion.getPrincipal();
			if(attributePrincipal!=null)
			{
				 String name=attributePrincipal.getName();
//				 model.addAttribute("name", name);
				 map=attributePrincipal.getAttributes();
//				 if(map!=null)
//				 {
//					 Object id=map.get("id");
//					 Object account=map.get("account");
//					 model.addAttribute("id", id);
//					 model.addAttribute("account", account);
//				 }
			}
		}
		return JSON.toJSONString(map);
	}
	//https://blog.csdn.net/luenxin/article/details/50159311
	//https://blog.csdn.net/zhang89xiao/article/details/60580226
//	@PreAuthorize("hasRole('ROLE_USER')")
//	@PostAuthorize("")
//	@PreAuthorize("hasAnyRole('ROLE_USER')")
//	@PermitAll
//	@DenyAll
//	@RolesAllowed({})
	@RolesAllowed({"USER", "ADMIN"}) 
	@PostMapping("/abc/{id}")
	public String get(@PathVariable String id, @Validated @RequestBody Aman man, BindingResult br) {
//		public String get(@PathVariable String id,@PathVariable String name,@PathVariable String age) {
//		log.error("id:{},ids:{}", id, name);
		log.error("id:{},ids:{}", id, man.getName());
		if(br.hasErrors()) {
			 List<FieldError> errors = br.getFieldErrors();
			 List<Map<String,Object>> ms = new ArrayList<Map<String,Object>>();
			 for(FieldError error: errors) {
				 Map<String,Object> m = new HashMap<String,Object>();
				 m.put("errorMsg", error.getDefaultMessage());
				 m.put("code", error.getCode());
				 m.put("field", error.getField());
				 ms.add(m);
			 }
			 return JSON.toJSONString(ms);
		}
		return "id:"+id+man.getAge();
//		return "id:"+id+age;
	}
}
