package controllers;


import Flow.FlowDateInterface;
import Flow.FullFlow;
import entitys.YcclgroupEntity;
import entitys.YcclgroupmemberEntity;
import helpers.FullFlowProcessorImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repositorys.mySqlRepositorys.YcclgroupRepository;
import repositorys.mySqlRepositorys.YcclgroupmemberRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tech on 2017/12/19.
 * HOME控制器
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final
    FlowDateInterface flowDate;

    private YcclgroupRepository repository;

    private YcclgroupmemberRepository ycclgroupmemberRepository;






    @Autowired
    public HomeController(FlowDateInterface flowDate, YcclgroupRepository repository,
                          YcclgroupmemberRepository ycclgroupmemberRepository) {
        this.flowDate = flowDate;
        this.repository=repository;
        this.ycclgroupmemberRepository=ycclgroupmemberRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String root(){


        return "home";
    }

    @RequestMapping(value = "privateProfile",method = RequestMethod.GET)
    public String privateProfile(Model model){
        Object userdetil = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userdetil instanceof UserDetails){

            List<YcclgroupmemberEntity> ycclgroupmemberEntityList = ycclgroupmemberRepository
                    .findAllByMemberid(((UserDetails) userdetil).getUsername());
            List<YcclgroupEntity> ycclgroupEntityList = new ArrayList<YcclgroupEntity>();
            if(ycclgroupmemberEntityList.size()!=0){
                for (YcclgroupmemberEntity member:ycclgroupmemberEntityList
                        ) {
                    YcclgroupEntity entity = repository.findByGroupid(member.getGroupid());
                    ycclgroupEntityList.add(entity);
                }
            }
            model.addAttribute("grouplist",ycclgroupEntityList);
            return "profile";
        }
        return "login";
    }

    @RequestMapping(value = "editProfile",method = RequestMethod.GET)
    public String editProfile(){
        return "editprofile";
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
