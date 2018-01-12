package message;

import entitys.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.MessageRepository;

import java.util.Date;

@Component
public class MessageWithLink implements Message {

    private String from;
    private String to;
    private String text;
    private String link;
    private MessageType type = MessageType.WITH_LINK;
    private MessageRepository messageRepository;

    public MessageWithLink() {
    }

    public MessageWithLink(String from, String to, String text, String link,MessageRepository messageRepository) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.link = link;
        this.messageRepository=messageRepository;
    }


    public void send() {
        MessageEntity entity = new MessageEntity();
        entity.setSender(from);
        entity.setReceiver(to);
        entity.setContent(text);
        entity.setLinkurl(link);
        entity.setType(type.toString());
        entity.setSenddate(new Date());
        messageRepository.save(entity);
    }
}
