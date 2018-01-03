package controllers;


import Flow.*;
import helpers.FullFlowProcessorImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repositorys.mySqlRepositorys.UserdetailRepository;


/**
 * Created by tech on 2017/12/19.
 * HOME控制器
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final
    FlowDateInterface flowDate;



    @Autowired
    public HomeController(FlowDateInterface flowDate) {
        this.flowDate = flowDate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String root(){
        return "home";
    }

    @RequestMapping(value = "privateProfile",method = RequestMethod.GET)
    public String privateProfile(){
        return "profile";
    }

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String home() {
        FullFlow fullFlow = flowDate.findFullFlow("test-20171127002");
        FullFlowProcessorImp processorImp = new FullFlowProcessorImp(fullFlow);
        flowDate.updateFullFlow(processorImp.agree("同意"));
        flowDate.updateFullFlow(processorImp.agree("不同意"));

        return "home";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
