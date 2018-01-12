package message;


import entitys.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.MessageRepository;

import java.util.Date;

@Component
public class MessageWithChoice implements Message {

    private String from;
    private String to;
    private String text;
    private String agreeurl;
    private String refuseurl;
    private MessageType type = MessageType.WITH_CHOICE;
    private MessageRepository messageRepository;



    public MessageWithChoice() {
    }


    public MessageWithChoice(String from, String to, String text, String agreeurl, String refuseurl,MessageRepository messageRepository) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.agreeurl = agreeurl;
        this.refuseurl = refuseurl;
        this.messageRepository = messageRepository;
    }

    public void send() {
        MessageEntity entity = new MessageEntity();
        entity.setSender(from);
        entity.setReceiver(to);
        entity.setContent(text);
        entity.setAgreeurl(agreeurl);
        entity.setRefuseurl(refuseurl);
        entity.setType(type.toString());
        entity.setSenddate(new Date());
        messageRepository.save(entity);
    }
}
