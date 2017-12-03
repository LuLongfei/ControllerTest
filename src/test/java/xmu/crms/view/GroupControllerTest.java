package xmu.crms.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author YangYouran
 * @date 2017-12-3
 */

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Test
	public void testGetGroup() throws Exception {
		mvc.perform(get("/group/{groupId}",1))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$").isArray())
        	.andExpect(jsonPath("$.id").isNumber())
        	.andExpect(jsonPath("$.members[0]").exists())
        	.andExpect(jsonPath("$.members[0].id").isNumber())
        	.andExpect(jsonPath("$.members[0].name").isString())
        	.andDo(print());
	}
	
	@Test
	public void testResign() throws Exception {
		mvc.perform(put("/group/{groupId}/resign",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	@Test
	public void testAssign() throws Exception {
		mvc.perform(put("/group/{groupId}/assign",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	@Test
	public void testAdd() throws Exception {
		mvc.perform(put("/group/{groupId}/add",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	@Test
	public void testRemove() throws Exception {
		mvc.perform(put("/group/{groupId}/remove",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	@Test
	public void testChooseTopic() throws Exception {
		mvc.perform(post("/group/{groupId}/topic",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 23}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	@Test
	public void testDeleteTopic() throws Exception {
		mvc.perform(delete("/group/{groupId}/topic/{topicId}",1,2))
			.andExpect(status().isNoContent())
	        .andDo(print());
	}
	
	@Test
	public void testGetGrade() throws Exception {
		mvc.perform(get("/group/{groupId}/grade",1))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$").isArray())
	    	.andExpect(jsonPath("$.presentationGrade[0]").exists())
	    	.andExpect(jsonPath("$.presentationGrade[0].topicId").isNumber())
	    	.andExpect(jsonPath("$.presentationGrade[0].grade").isNumber())
	    	.andExpect(jsonPath("$.reportGrade").isNumber())
	    	.andExpect(jsonPath("$.grade").isNumber())
	    	.andDo(print());
	}
	
	@Test
	public void testPutReport() throws Exception {
		mvc.perform(put("/group/{groupId}/grade/report",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"reportGrade\": 5}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	@Test
	public void testPutPreGrade() throws Exception {
		mvc.perform(put("/group/{groupId}/grade/presentation/{studentId}",1,12)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"presentationGrade\": [{\"topicId\": 257, \"grade\": 5}, {\"topicId\": 258, \"grade\": 4}]}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
}
