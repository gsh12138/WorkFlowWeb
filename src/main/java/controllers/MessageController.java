package controllers;

import entitys.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import repositorys.mySqlRepositorys.MessageRepository;

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
        return entity.getContent();
    }


}
