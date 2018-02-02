package controllers;

import entitys.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import repositorys.mySqlRepositorys.MessageRepository;

import java.sql.Date;

@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository){
        this.messageRepository=messageRepository;
    }

    @RequestMapping(value = "nomorl/{messageid}",method = RequestMethod.GET)
    @ResponseBody
    public String getNomorlContent(@PathVariable int messageid){
        MessageEntity entity = messageRepository.findById(messageid);
        setMessageNoNew(entity);
        return entity.getContent();
    }

    @RequestMapping(value = "withChoice/{messageid}",method = RequestMethod.GET)
    @ResponseBody
    public String getWithChoiceContent(@PathVariable int messageid){
        MessageEntity entity = messageRepository.findById(messageid);
        setMessageNoNew(entity);
        return entity.getContent();
    }

    @RequestMapping(value = "agree/{messageid}",method = RequestMethod.GET)
    public String agree(@PathVariable int messageid){
        MessageEntity entity = messageRepository.findById(messageid);
        setMessageNoNew(entity);
        return "redirect:"+entity.getAgreeurl()+"/"+messageid;
    }

    @RequestMapping(value = "refuse/{messageid}",method = RequestMethod.GET)
    public String refuse(@PathVariable int messageid){
        MessageEntity entity = messageRepository.findById(messageid);
        setMessageNoNew(entity);
        return "redirect:"+entity.getRefuseurl()+"/"+messageid;
    }

    @RequestMapping(value = "withLink/{messageid}",method = RequestMethod.GET)
    public String getwithLink(@PathVariable int messageid){
        MessageEntity entity = messageRepository.findById(messageid);
        setMessageNoNew(entity);
        return "redirect:"+entity.getLinkurl();
    }

    private void setMessageNoNew(MessageEntity entity){
        entity.setIsnew(false);
        entity.setReaddate(new Date(System.currentTimeMillis()));
        messageRepository.save(entity);

    }


}
