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
/**
 * URL-pattern:prefix="/topic"
 * @author Huhui
 * @date 2017-12-04
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class TopicControllerTest {
	@Autowired
	private MockMvc mvc;

	/**
	 * 按ID获取话题 url: /topic/{topicId} httpMethod: GET
	 *
	 * @throws Exception
	 */
	@Test
	public void selectTopicById() throws Exception {
		mvc.perform(get("/topic/1")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.id").isNumber()).andExpect(jsonPath("$.serial").isString())
				.andExpect(jsonPath("$.description").isString()).andExpect(jsonPath("$.groupLimit").isNumber())
				.andExpect(jsonPath("$.groupMemberLimit").isNumber()).andExpect(jsonPath("$.groupLeft").isNumber())
				.andDo(print());
	}

	/**
	 * 按ID修改话题 url: /topic/{topicId} httpMethod: PUT
	 *
	 * @throws Exception
	 */
	@Test
	public void updateTopicById() throws Exception {
		mvc.perform(put("/topic/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"serial\": \"A\", \"name\": \"领域模型与模块\", \"description\": \"Domain model与模块划分\", \"groupLimit\": 6, \"groupMemberLimit\": 6}"
						.getBytes()))
				.andExpect(status().isNoContent()).andDo(print());
	}

	/**
	 * 按ID删除话题 url: /topic/{topicId} httpMethod: DELETE
	 *
	 * @throws Exception
	 */
	@Test
	public void deleteTopicById() throws Exception {
		mvc.perform(delete("/topic/1")).andExpect(status().isNoContent()).andDo(print());
	}

	/**
	 * 按话题ID获取选择了该话题的小组 url: /topic/{topicId}/group httpMethod: GET
	 *
	 * @throws Exception
	 */
	@Test
	public void selectGroup() throws Exception {
		mvc.perform(get("/topic/1/group")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].name").isString()).andDo(print());
	}
}
