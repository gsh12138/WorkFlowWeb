package message;

import entitys.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.MessageRepository;

import java.util.Date;

@Component
public class MessageNormol implements Message {

    private String from;
    private String to;
    private String text;
    private MessageType type = MessageType.NORMOL;
    private MessageRepository messageRepository;

    public MessageNormol(){

    }



    public MessageNormol(String from, String to, String text,MessageRepository messageRepository) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.messageRepository=messageRepository;

    }

    public void send() {
        MessageEntity entity = new MessageEntity();
        entity.setSender(from);
        entity.setReceiver(to);
        entity.setContent(text);
        entity.setType(type.toString());
        entity.setSenddate(new Date());
        messageRepository.save(entity);
    }
}
