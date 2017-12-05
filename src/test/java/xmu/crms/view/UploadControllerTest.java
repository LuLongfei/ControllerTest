package controller;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.URL;

/**
 * 
 * UploadController class
 * 上传文件 controller层
 * @author 艾星
 * @date 2017/11/28
 *  
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private ObjectMapper mapper=new ObjectMapper();
    private Object obj;
    
    /**
     * 上传头像
     * @author 艾星
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value="/avatar",method=POST)
    @ResponseBody
    public Object getAvatar() throws JsonProcessingException{
        URL url=new URL();
        url.setUrl("//XXXX//XXXX");
        return url;
    }
    
    /**
     * 上传班级学生名单
     * @author 艾星
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value="/classroster",method=POST)
    public String getClassRoster() throws JsonProcessingException{
        return mapper.writeValueAsString(obj);
    }
    
    /**
     * 上传小组报告
     * @author 艾星
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value="/report",method=POST)
    public String uploadGroupReport() throws JsonProcessingException{
        return mapper.writeValueAsString(obj);
    }
}
