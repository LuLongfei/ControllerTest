package xmu.crms.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author zimu
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest()
public class SchoolControllerTest {
	@Autowired
	private MockMvc mvc;

	/**
	 * 获取学校列表，成功返回200
	 * @throws Exception
	 */
	@Test
	public void testGetSchoolList() throws Exception{
		mvc.perform(get("/school").param("city", "厦门"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").isNumber())
			.andExpect(jsonPath("$[0].name").isString())
			.andExpect(jsonPath("$[0].province").isString())
			.andExpect(jsonPath("$[0].city").isString());
	}
	
	/**
	 * 添加学校，成功返回201
	 * @throws Exception
	 */
	@Test
	public void testAddSchool() throws Exception{
		mvc.perform(post("/school")
					.param("name", "厦门市人民公园")
					.param("province", "福建")
					.param("city", "厦门"))
					.andExpect(status().isCreated());
		
	}
	
	/**
	 * 获取省份列表，成功返回200
	 * @throws Exception
	 */
	@Test
	public void testGetProvinceList() throws Exception{
		mvc.perform(get("/school/province"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").exists());
	}
	
	/**
	 * 获取城市列表，成功返回200
	 * @throws Exception
	 */
	@Test
	public void testGetCityList() throws Exception{
		mvc.perform(get("/school/city").param("province", "河北省"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").exists());
	}
}
