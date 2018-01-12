package controllers;

import entitys.UserEntity;
import entitys.YcclgroupEntity;
import entitys.YcclgroupmemberEntity;
import helpers.DepartementJudger;
import message.Message;
import message.MessageFactory;
import message.MessageNormol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repositorys.mySqlRepositorys.UserRespository;
import repositorys.mySqlRepositorys.YcclgroupRepository;
import repositorys.mySqlRepositorys.YcclgroupmemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/ycclgroup")
public class YcclGroupController {

    private YcclgroupRepository repository;
    private UserRespository userRespository;
    private YcclgroupmemberRepository ycclgroupmemberRepository;
    private static final String ID_PRF="yccl";


    @Autowired
    public YcclGroupController(YcclgroupRepository repository, UserRespository userRespository, YcclgroupmemberRepository ycclgroupmemberRepository){
        this.repository=repository;
        this.userRespository=userRespository;
        this.ycclgroupmemberRepository = ycclgroupmemberRepository;
    }

    @RequestMapping(value = "creat",method = RequestMethod.GET)
    public String creatGroup(){
        return "addycclgroup";
    }

    @RequestMapping(value = "creat",method = RequestMethod.POST)
    public String creat(YcclgroupEntity entity){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){
            UserEntity userEntity = userRespository.findByUserid(((UserDetails) userDetails).getUsername());
            entity.setCharger(userEntity.getUserid());
            entity.setCreatdate(new Date());
            entity.setCreater(userEntity.getUserid());
            entity.setGroupid(makeid());
            repository.save(entity);
            YcclgroupmemberEntity member = new YcclgroupmemberEntity();
            member.setMemberid(userEntity.getUserid());
            member.setMembername(userEntity.getName());
            member.setGroupid(entity.getGroupid());
            member.setJoindate(entity.getCreatdate());
            member.setState("1");
            ycclgroupmemberRepository.save(member);
            return "redirect:/ycclgroup/members/"+entity.getGroupid();
        }
        else {
            return "login";
        }
    }

    @RequestMapping(value = "selectMember/{groupid}/select",method = RequestMethod.POST)
    public String select(HttpServletRequest request, Model model,@PathVariable String groupid){
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        List<UserEntity> userlist=new ArrayList<UserEntity>();
        if(!name.equals("")){
            if(!department.equals("")){
                userlist = userRespository.findByUseridOrNameAndDepartment(name,name,department);
                model.addAttribute("members",userlist);
            }
            else {
                userlist=userRespository.findByUseridOrName(name,name);
                model.addAttribute("members",userlist);
            }
        }
        else {
            userlist=userRespository.findByDepartment(department);
            model.addAttribute("members",userlist);
        }
        model.addAttribute("groupid",groupid);
        return "selectUser";
    }

    @RequestMapping(value = "seletcMember",method = RequestMethod.GET)
    public String selectMember(@RequestParam String groupid,Model model){
        List<UserEntity> userlist = new ArrayList<UserEntity>();
        model.addAttribute("groupid",groupid);
        model.addAttribute("members",userlist);
        return "selectUser";
    }

    @RequestMapping(value = "add/{groupid}/{userid}",method = RequestMethod.GET)
    public String addMember(@PathVariable String userid,@PathVariable String groupid){
        UserEntity adder;
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){
            adder=userRespository.findByUserid(((UserDetails) userDetails).getUsername());
        }
        else {
            return "login";
        }
        YcclgroupEntity ycclgroupEntity = repository.findByGroupid(groupid);
        if(!ycclgroupEntity.getCharger().equals(adder.getUserid())){
            return "redirect:/ycclgroup/members/"+groupid;
        }
        UserEntity user = userRespository.findByUserid(userid);

        if(DepartementJudger.canAddToGroup(adder.getDepartment(),user.getDepartment())){
            YcclgroupmemberEntity member = new YcclgroupmemberEntity();
            member.setMemberid(userid);
            member.setMembername(user.getName());
            member.setGroupid(groupid);
            member.setJoindate(new Date());
            member.setState("1");
            ycclgroupmemberRepository.save(member);
            String messageContent = adder.getName()+"已将你加入"+ycclgroupEntity.getGroupname()+
                    "异常处理小组!";
            Message message = MessageFactory.creatNormol(
                    adder.getUserid(),userid,messageContent
            );
            message.send();
            return "redirect:/ycclgroup/members/"+groupid;
        }
        else {
            String messageContent = adder.getName()+"想要将你加入"+ycclgroupEntity.getGroupname()+
                    "异常处理小组,你是否同意加入该小组?";
            Message message = MessageFactory.creatWithChoice(
                    adder.getUserid(),userid,messageContent,"",""
            );
            message.send();
            return "redirect:/ycclgroup/members/"+groupid;
        }

    }

    @RequestMapping(value = "members/{groupid}",method = RequestMethod.GET)
    public String members(Model model, @PathVariable String groupid){
        List<YcclgroupmemberEntity> members = new ArrayList<YcclgroupmemberEntity>();
        YcclgroupEntity group = repository.findByGroupid(groupid);
        members=ycclgroupmemberRepository.findAllByGroupid(groupid);
        model.addAttribute("charger",group.getCharger());
        model.addAttribute("groupid",groupid);
        model.addAttribute("members",members);
        return "ycclmembers";
    }

    @RequestMapping(value = "delete/{groupid}/{userid}",method = RequestMethod.GET)
    public String deleteMember(@PathVariable String groupid,@PathVariable String userid){
        UserEntity user;
        Object userdetil = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userdetil instanceof UserDetails){
            user = userRespository.findByUserid(((UserDetails) userdetil).getUsername());
            YcclgroupEntity ycclgroupEntity = repository.findByGroupid(groupid);
            if(user.getUserid().equals(ycclgroupEntity.getCharger())){
                ycclgroupmemberRepository.deleteByGroupidAndMemberid(groupid,userid);
                String messageContent = user.getName()+"已经将你移出"+ycclgroupEntity.getGroupname()+
                        "异常处理小组!";
                Message message = MessageFactory.creatNormol(user.getUserid(),userid,messageContent);
                message.send();
                return "redirect:/ycclgroup/members/"+groupid;
            }
            else {
                return "redirect:/ycclgroup/members/"+groupid;
            }
        }
        else {
            return "login";
        }
    }

    @RequestMapping(value = "dissolution/{groupid}",method = RequestMethod.GET)
    public String dissolutionGroup(@PathVariable String groupid){
        UserEntity user;
        Object userdetil = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userdetil instanceof UserDetails){
            user=userRespository.findByUserid(((UserDetails) userdetil).getUsername());
            YcclgroupEntity ycclgroupEntity = repository.findByGroupid(groupid);
            if(ycclgroupEntity.getCharger().equals(user.getUserid())){
                List<YcclgroupmemberEntity> members = ycclgroupmemberRepository.findAllByGroupid(groupid);
                for (YcclgroupmemberEntity member:members
                     ) {
                    if(!member.getMemberid().equals(user.getUserid())){
                        String messageContent = user.getName()+"已经解散"+ycclgroupEntity.getGroupname()+
                                "异常处理小组!";
                        Message message = MessageFactory.creatNormol(
                                user.getUserid(),member.getMemberid(),messageContent);
                        message.send();
                    }

                }
                ycclgroupmemberRepository.deleteAllByGroupid(groupid);
                repository.deleteByGroupid(groupid);
                return "redirect:/privateProfile";
            }
            else {
                return "redirect:/privateProfile";
            }
        }
        else {
            return "login";
        }
    }

    private String makeid(){
        List<YcclgroupEntity> entitys = repository.findmaxid();
        if(entitys.size()==0){
            return ID_PRF+"-"+"00001";
        }
        int maxid = repository.findmaxid().get(0).getId();
        String newid = String.format("%05d",maxid+1);
        return ID_PRF+"-"+newid;
    }
}
