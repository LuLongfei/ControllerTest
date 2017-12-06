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
 * UploadController测试类
 * @author 艾星
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class UploadControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    /**
     * 测试uploadAvatar方法
     * @author 艾星
     * @throws Exception
     */
    @Test
    public void testUploadAvatar() throws Exception{
        //如果成功返回1个URL
        this.mockMvc.perform(post("/upload/avatar"))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.url").isString());
                        
        
    }
    
    /*
    @Test
    public void testUploadClassRoster() throws Exception{
        //如果成功返回1个URL  
        this.mockMvc.perform(post("/upload/classroster"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("message").isString())
                        .andExpect(jsonPath("endPoint").isMap());
 
    }
    @Test
    public void testUploadReport() throws Exception{
        //如果成功返回1个URL  
        this.mockMvc.perform(post("/upload/report"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("message").isString())
                        .andExpect(jsonPath("endPoint").isMap());
    }
    */
}
