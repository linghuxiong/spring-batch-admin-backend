package org.linghuxiong.spring.batch.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
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
@RequestMapping("/noticeApi")
public class MockNoticeController {


    @GetMapping("/notices")
    public JSONArray currentUser() throws IOException {
        return JSON.parseArray(IOUtils.toString(new ClassPathResource("noticesData/notices.json").getInputStream()));
    }
}
