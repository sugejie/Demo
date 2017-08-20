package com.example.controller;

import com.example.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private GirlService girlService;

    /**
     * 在页面显示Hello admin，测试El表达式
     * 注：IDEA中使用EL会标红，要加注释(Alt+回车)，或setting=>editor=>Inspection将thymeleaf中的表达式校验勾选去掉
     * @param model
     * @return
     */
    @GetMapping(value = "/hello")
    public String sayHello(Model model) {
        logger.info("sayHello");
        model.addAttribute("name", "admin");
        return "hello";
    }

    /**
     * 查找女生列表，结果显示在girl_list页面上，girl_list使用thymeleaf模板
     * @param model
     * @return
     */
    @GetMapping(value = "/girllist")
    public String girllist(Model model) {
        logger.info("girllist");
        model.addAttribute("girls", girlService.findAll());
        return "girl_list";
    }
}
