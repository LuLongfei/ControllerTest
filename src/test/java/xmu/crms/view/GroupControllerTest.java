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
 * @date 2017-12-4
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class GroupControllerTest {

	@Autowired
    private MockMvc mvc;
	
	/**
     * 按小组ID获取小组详情
     * url: /group/{groupId}
     * httpMethod: GET
     *
     * @throws Exception
     */
	@Test
	public void testGetGroup() throws Exception {
		mvc.perform(get("/group/{groupId}",1))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.id").isNumber())
        	.andExpect(jsonPath("$.members[0]").exists())
        	.andExpect(jsonPath("$.members[0].id").isNumber())
        	.andExpect(jsonPath("$.members[0].name").isString())
        	.andDo(print());
	}

	/**
     * 组长辞职
     * url: /group/{groupId}/resign
     * httpMethod: PUT
     *
     * @throws Exception
     */
	@Test
	public void testResign() throws Exception {
		mvc.perform(put("/group/{groupId}/resign",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	/**
     * 成为组长
     * url: /group/{groupId}/assign
     * httpMethod: PUT
     *
     * @throws Exception
     */
	@Test
	public void testAssign() throws Exception {
		mvc.perform(put("/group/{groupId}/assign",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	/**
     * 添加成员
     * url: /group/{groupId}/add
     * httpMethod: PUT
     *
     * @throws Exception
     */
	@Test
	public void testAddMember() throws Exception {
		mvc.perform(put("/group/{groupId}/add",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	/**
     * 移除成员
     * url: /group/{groupId}/remove
     * httpMethod: PUT
     *
     * @throws Exception
     */
	@Test
	public void testRemoveMember() throws Exception {
		mvc.perform(put("/group/{groupId}/remove",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 247}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	/**
     * 小组按ID选择话题
     * url: /group/{groupId}/topic
     * httpMethod: POST
     *
     * @throws Exception
     */
	@Test
	public void testChooseTopic() throws Exception {
		mvc.perform(post("/group/{groupId}/topic",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\": 23}".getBytes())
				)
			.andExpect(status().isCreated())
			.andDo(print());
	}
	
	/**
     * 小组按ID取消选择话题
     * url: /group/{groupId}/topic/{topicId}
     * httpMethod: DELETE
     *
     * @throws Exception
     */
	@Test
	public void testDeleteTopic() throws Exception {
		mvc.perform(delete("/group/{groupId}/topic/{topicId}",1,2))
			.andExpect(status().isNoContent())
	        .andDo(print());
	}
	
	/**
     * 按ID获取小组成绩
     * url: /group/{groupId}/grade
     * httpMethod: GET
     *
     * @throws Exception
     */
	@Test
	public void testGetGrade() throws Exception {
		mvc.perform(get("/group/{groupId}/grade",1))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.presentationGrade[0]").exists())
	    	.andExpect(jsonPath("$.presentationGrade[0].topicId").isNumber())
	    	.andExpect(jsonPath("$.presentationGrade[0].grade").isNumber())
	    	.andExpect(jsonPath("$.reportGrade").isNumber())
	    	.andExpect(jsonPath("$.grade").isNumber())
	    	.andDo(print());
	}
	
	/**
     * 按ID设置小组的报告分
     * url: /group/{groupId}/grade/report
     * httpMethod: PUT
     *
     * @throws Exception
     */
	@Test
	public void testSetReportGrade() throws Exception {
		mvc.perform(put("/group/{groupId}/grade/report",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"reportGrade\": 5}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}
	
	/**
     * 提交对其他小组的打分
     * url: /group/{groupId}/grade/presentation/{studentId}
     * httpMethod: PUT
     *
     * @throws Exception
     */
	@Test
	public void testSetPresentationGrade() throws Exception {
		mvc.perform(put("/group/{groupId}/grade/presentation/{studentId}",1,1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"presentationGrade\": [{\"topicId\": 257, \"grade\": 4}, {\"topicId\": 258, \"grade\": 5}]}".getBytes())
				)
			.andExpect(status().isNoContent())
			.andDo(print());
	}

}
