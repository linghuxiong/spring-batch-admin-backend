package org.linghuxiong.spring.batch.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author linghuxiong
 * @date 2019/11/18 11:13 下午
 */
@RestController
@RequestMapping("/userApi")
public class MockUserController {


    @GetMapping("/currentUser")
    public JSONObject currentUser() throws IOException {
        return JSON.parseObject(IOUtils.toString(new ClassPathResource("userData/currentUser.json").getInputStream()));
    }

    @GetMapping("/users")
    public JSONObject users() throws IOException {
        return JSON.parseObject(IOUtils.toString(new ClassPathResource("userData/userList.json").getInputStream()));
    }

}
