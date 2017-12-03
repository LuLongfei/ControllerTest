package xmu.crms.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 课程相关 API 测试
 * url-pattern: prefix="/course"
 *
 * @author LuLongfei
 * @date 2017-12-3
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class CourseControllerTest {
    @Autowired
    private MockMvc mvc;

    /**
     * 获取课程列表测试
     * url: /course
     * httpMethod: GET
     *
     * @throws Exception
     */
    @Test
    public void selectCourses() throws Exception {
        mvc
                .perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").isString())
                .andExpect(jsonPath("$[0].numClass").isNumber())
                .andExpect(jsonPath("$[0].numStudent").isNumber())
                .andExpect(jsonPath("$[0].startTime").isString())
                .andExpect(jsonPath("$[0].endTime").isString())
                .andDo(print());
    }

    /**
     * 创建课程测试
     * url: /course
     * httpMethod: POST
     *
     * @throws Exception
     */
    @Test
    public void createCourse() throws Exception {
        mvc
                .perform(post("/course")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\": \"OOAD\", \"description\": \"面向对象分析与设计\", \"startTime\": \"2017-09-20\", \"endTime\": \"2018-1-31\", \"proportions\": {\"report\": 50, \"presentation\": 50, \"3\": 20, \"4\": 60, \"5\": 20 }}".getBytes()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andDo(print());
    }

    /**
     * 获取课程详细信息测试
     * url: /course/{courseId}
     * httpMethod: GET
     *
     * @throws Exception
     */
    @Test
    public void selectCourse() throws Exception {
        Long courseId = 5L;
        mvc
                .perform(get("/course/{courseId}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.description").isString())
                .andExpect(jsonPath("$.teacherName").isString())
                .andExpect(jsonPath("$.teacherEmail").isString())
                .andDo(print());
    }

    /**
     * 修改课程信息测试
     * url: /course/{courseId}
     * httpMethod: PUT
     *
     * @throws Exception
     */
    @Test
    public void updateCourse() throws Exception {
        mvc
                .perform(put("/course/{courseId}", 1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\": \"OOAD\", \"description\": \"面向对象分析与设计\", \"startTime\": \"2017-09-20\", \"endTime\": \"2018-1-1\", \"proportions\": {\"report\": 70, \"presentation\": 30, \"3\": 20, \"4\": 60, \"5\": 20 }}".getBytes())
                )
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    /**
     * 删除课程测试
     * url: /course/{courseId}
     * httpMethod: DELETE
     *
     * @throws Exception
     */
    @Test
    public void deleteCourse() throws Exception {
        mvc
                .perform(delete("/course/{courseId}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    /**
     * 获取课程班级列表测试
     * url: /course/{courseId}/class
     * httpMethod: GET
     *
     * @throws Exception
     */
    @Test
    public void selectClassesByCourse() throws Exception {
        mvc
                .perform(get("/course/{courseId}/class", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").isString())
                .andDo(print());
    }

    /**
     * 创建课程班级测试
     * url: /course/{courseId}/class
     * httpMethod: POST
     *
     * @throws Exception
     */
    @Test
    public void createClassForCourse() throws Exception {
        mvc
                .perform(post("/course/{courseId}/class", 1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("\"name\": \"周三1-2节\", \"site\": \"海韵212\",\"time\": [{ \"week\": 1, \"day\": 1, \"time\": [1,2]}, {\"week\": 0, \"day\": 3, \"time\": [3,4]}], \"proportions\": {\"report\": 50, \"presentation\": 50, \"3\": 10, \"4\": 60, \"5\": 30 }}".getBytes()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andDo(print());
    }

    /**
     * 获取课程讨论课列表测试
     * url: /course/{courseId}/seminar
     * httpMethod: GET
     *
     * @throws Exception
     */
    @Test
    public void selectSeminarsByCourse() throws Exception {
        mvc
                .perform(get("/course/{courseId}/seminar", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").isString())
                .andExpect(jsonPath("$[0].description").isString())
                .andExpect(jsonPath("$[0].groupingMethod").isString())
                .andExpect(jsonPath("$[0].startTime").isString())
                .andExpect(jsonPath("$[0].endTime").isString())
                .andDo(print());
    }

    /**
     * 创建课程讨论课测试
     * url: /course/{courseId}/seminar
     * httpMethod: POST
     *
     * @throws Exception
     */
    @Test
    public void createSeminarForCourse() throws Exception {
        mvc
                .perform(post("/course/{courseId}/seminar", 1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\": \"概要设计\", \"description\": \"模型层与数据库设计\", \"groupingMethod\": \"fixed\", \"startTime\": \"2017-10-10\", \"endTime\": \"2017-10-24\", \"proportions\": {\"report\": 50, \"presentation\": 50, \"3\": 20, \"4\": 60, \"5\": 20 }}".getBytes()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andDo(print());
    }

    /**
     * 获取课程正在进行的讨论课测试
     * url: /course/{courseId}/seminar/current
     * httpMethod: GET
     *
     * @throws Exception
     */
    @Test
    public void selectCurrentSeminar() throws Exception {
        mvc
                .perform(get("/course/{courseId}/seminar/current", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.groupingMethod").isString())
                .andExpect(jsonPath("$.startTime").isString())
                .andExpect(jsonPath("$.endTime").isString())
                .andExpect(jsonPath("$.classes").isArray())
                .andDo(print());
    }

    /**
     * 获取学生在课程所以讨论课的成绩测试
     * url: /course/{courseId}/grade
     * httpMethod: GET
     *
     * @throws Exception
     */
    @Test
    public void selectSeminarsGrade() throws Exception {
        mvc
                .perform(get("/course/{courseId}/grade", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].seminarName").isString())
                .andExpect(jsonPath("$[0].groupName").isString())
                .andExpect(jsonPath("$[0].leaderName").isString())
                .andExpect(jsonPath("$[0].presentationGrade").isNumber())
                .andExpect(jsonPath("$[0].reportGrade").isNumber())
                .andExpect(jsonPath("$[0].grade").isNumber())
                .andDo(print());
    }

}