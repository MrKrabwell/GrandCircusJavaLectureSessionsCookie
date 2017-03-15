package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String helloWorld(HttpServletResponse response) {
        response.addCookie(new Cookie("userid","yosuke"));
        return "welcome";
    }

    @RequestMapping("welcome")
    public ModelAndView helloWorld2()
    {
        return new
                ModelAndView("welcome2","message","Hello World2");

    }

    @RequestMapping("getSession")
    public ModelAndView keepSession(HttpSession session)
    {
        if (session.getAttribute("counter") == null) {
            session.setAttribute("counter",0);
        }

        // Cast object returned from the session.
        Integer c = (Integer)session.getAttribute("counter");
        c++;

        session.setAttribute("counter",c);

        return new ModelAndView("sessions","sessionCounter",c);

    }

    @RequestMapping("cookies")
    public String checkCookie(Model model, @CookieValue("userid") String userID) {
        model.addAttribute("user",userID);

        return "cookies";
    }

}
