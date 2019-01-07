package tp.ms.common.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报销管理控制器
 *
 * @author fengshuonan
 * @Date 2017-12-04 21:11:36
 */
@RestController
@CrossOrigin
@RequestMapping("/wf")
public class WfController  {


   /*
     * 跳转到添加报销管理
     */
    @GetMapping("/exp")
    public Object expenseAdd() {
    	List<Map<String,String>> result = new ArrayList<>();
    	Map<String,String> param1 = new HashMap<>();
    	param1.put("path", "http://localhost:8280/cache/images/bm.png");
    	param1.put("text", "我们班两个同学打赌，内容是在 厕所吃");
    	Map<String,String> param2 = new HashMap<>();
    	param2.put("path", "http://localhost:8280/cache/images/3320180515170713.jpg");
    	param2.put("text", "我们班，谁先吃完谁赢，输了的请 赢了的容是在 厕所吃");
    	Map<String,String> param3 = new HashMap<>();
    	param3.put("path", "http://localhost:8280/cache/images/coachplan.png");
    	param3.put("text", "我们班，谁先吃完谁赢，输了的请 赢了的容是在 厕所吃");
    	result.add(param1);
    	result.add(param2);
    	result.add(param3);
        return result;
    }


}
