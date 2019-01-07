package tp.ms.common.bean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/head")
public class HeadsController {
	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result = new HashMap<>();
		result.put("hi", "xiaoming");
		
		response.addCookie(new Cookie("no", "wrong"));
		
		response.addCookie(new Cookie("ave", "八哥"));
		
		return JSON.toJSONString(result);
	}
}
