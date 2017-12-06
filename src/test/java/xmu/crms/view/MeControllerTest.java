package xmu.crms.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * MeController测试类
 * @author 艾星
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class MeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    /**
     * 测试getCurrentUser方法
     * @author 艾星
     * @throws Exception
     */
    @Test
    public void testGetCurrentUser() throws Exception{
        //如果成功返回JSON数据
        this.mockMvc.perform(get("/me"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.type").isString())
                        .andExpect(jsonPath("$.name").isString())
                        .andExpect(jsonPath("$.phone").isString())
                        .andExpect(jsonPath("$.email").isString())
                        .andExpect(jsonPath("$.gender").isString())
                        .andExpect(jsonPath("$.school").isMap())
                        .andExpect(jsonPath("$.title").isString())
                        .andExpect(jsonPath("$.avatar").isString());
    }
    
    /**
     * 测试updateCurrentUser方法更新用户信息是否成功
     * @author 艾星
     * @throws Exception
     */
    @Test
    public void testUpdateCurrentUser() throws Exception{
        //如果成功返回204
        this.mockMvc.perform(put("/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\": 233,\"name\":\"张三\",\"number\"：\"24320152202333\",\"email\":\"24320152202333@stu.xmu.edu.cn\",\"gender\":\"female\",\"province\":\"XX省\",\"city\":\"XX市\",\"school\":\"XX学校\",\"title\":\"\"}".getBytes()))
                .andExpect(status().isNoContent());
    }
    
    /**
     * 测试signinWechat方法更新用户信息是否成功
     * @author 艾星
     * @throws Exception
     */
    @Test
    public void testSigninWechat() throws Exception{
        //如果成功返回用户信息的JSON形式
        this.mockMvc.perform(get("/signin?code=ghjiugh&state=wrerfwe3er&success_url=student_index.html"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").isNotEmpty())
                    .andExpect(jsonPath("$.type").isString())
                    .andExpect(jsonPath("$.name").isNotEmpty())
                    .andExpect(jsonPath("$.jwt").isString());
    }
    
    /**
     * 测试signinAccount方法更新用户信息是否成功
     * @author 艾星
     * @throws Exception
     */
    @Test
    public void testSigninAccount() throws Exception{
        //如果成功返回用户信息
        this.mockMvc.perform(post("/signin")
                        .param("phone", "189111114514")
                        .param("password","qwer2345!"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.type").isString())
                        .andExpect(jsonPath("$.name").isString())
                        .andExpect(jsonPath("$.jwt").isString());
    }
    
    /**
     * 测试createNewAccount方法更新用户信息是否成功
     * @author 艾星
     * @throws Exception
     */
    @Test
    public void testCreateNewAccount() throws Exception{
        //如果成功返回用户信息
        this.mockMvc.perform(post("/register")
                .param("phone", "189111114514")
                .param("password","qwer2345!"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.name").isString())
                        .andExpect(jsonPath("$.type").isString())
                        .andExpect(jsonPath("$.jwt").isString());
    }
}
