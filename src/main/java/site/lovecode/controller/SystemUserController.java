package site.lovecode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import site.lovecode.entity.SystemUser;
import site.lovecode.service.SystemUserService;
import site.lovecode.service.WechatThridPartyService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/15.
 */
@Controller
public class SystemUserController {

    @Resource
    private SystemUserService systemUserService;

    @Resource
    private WechatThridPartyService wechatThridPartyService;

    @RequestMapping("/login.html")
    public ModelAndView login(Model model){
         return new ModelAndView("login");
    }

    @RequestMapping("/check.html")
    public ModelAndView checkPassword(String username,String password,Model model){
        if(systemUserService.checkUsernameAndPassword(username,password)){
            try {
                model.addAttribute("url", wechatThridPartyService.getCompoentLoginUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ModelAndView("index");
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return new ModelAndView("login");
        }
    }
}