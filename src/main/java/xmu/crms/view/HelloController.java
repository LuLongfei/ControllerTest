package xmu.crms.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller 示例
 *
 * @author LuLongfei
 * @date 2017-12-2
 */
@RestController
public class HelloController {

    /**
     * 响应格式为 application/json
     * {"message":"hello world!","endPoint":{"ip":"127.0.0.1","port":8080}}
     *
     * @return
     */
    @RequestMapping("/hello")
    public Object hello() {
        return new Object() {
            public String message = "hello world!";
            public Object endPoint = new Object() {
                public String ip = "127.0.0.1";
                public int port = 8080;
            };
        };
    }
}