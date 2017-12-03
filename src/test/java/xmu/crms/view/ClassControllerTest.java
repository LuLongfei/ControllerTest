package xmu.crms.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import xmu.crms.controller.ClassController;

@RunWith(SpringRunner.class)
@WebMvcTest(ClassController.class)
public class ClassControllerTest {
	@Autowired
    private MockMvc mvc;
	
	 /**
     * 班级小组组长辞职
     * url: /class/{classId}/classgroup/resign
     * httpMethod: PUT
     *
     * @throws Exception
     */
    @Test
    public void resignLeader() throws Exception {
        mvc
                .perform(put("/class/{classId}/classgroup/resign")
                		.contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"id\": 247}".getBytes())
                		) 
                .andExpect(status().isNoContent())
                .andDo(print());
    }
	
    /**
     * 成为班级小组组长
     * url: /class/{classId}/classgroup/assign
     * httpMethod: PUT
     *
     * @throws Exception
     */
    @Test
    public void assignLeader() throws Exception {
        mvc
                .perform(put("/class/{classId}/classgroup/assign")
                		.contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"id\": 247}".getBytes())
                		) 
                .andExpect(status().isNoContent())
                .andDo(print());
    }
    
    /**
     * 添加班级小组成员
     * url: /class/{classId}/classgroup/add
     * httpMethod: PUT
     *
     * @throws Exception
     */
    @Test
    public void addMember() throws Exception {
        mvc
                .perform(put("/class/{classId}/classgroup/add")
                		.contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"id\": 247}".getBytes())
                		) 
                .andExpect(status().isNoContent())
                .andDo(print());
    }
    
    /**
     * 添加班级小组成员
     * url: /class/{classId}/classgroup/remove
     * httpMethod: PUT
     *
     * @throws Exception
     */
    @Test
    public void removeMember() throws Exception {
        mvc
                .perform(put("/class/{classId}/classgroup/remove")
                		.contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"id\": 247}".getBytes())
                		) 
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
