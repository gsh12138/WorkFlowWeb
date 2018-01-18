package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exceptionHandle")
public class ExceptionHandlingController {


    @RequestMapping(value = "launch",method = RequestMethod.GET)
    public String launch(){
        return "launchHandFrom";
    }

    @RequestMapping(value = "launch",method = RequestMethod.POST)
    public String submitFrom(HttpServletRequest request,Model model){

        String tt = request.getParameter("content");
        model.addAttribute("test",tt);
        return "home";
    }



}
