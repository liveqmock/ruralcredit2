package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by v-weizhang on 2015-3-6.
 * 查询各类信息，如权限信息
 */
@Controller
@RequestMapping("searchInfo")
public class SearchVariousTypeInfoByConController {
    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getAuthInJSON(HttpServletRequest request) {
        try {
            return new ObjectMapper().writeValueAsString(SpringSecurityUtils.getAuthList(request.getSession()));
        } catch (IOException e) {
            return "Nothing found!";
        }
    }
}
