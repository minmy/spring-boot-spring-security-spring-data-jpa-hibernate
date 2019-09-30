package com.xinmy.springbootbase.act;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lijianxin
 * @date 2019/9/26 15:07
 * @desc
 */
@Controller
public class SecurityController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}