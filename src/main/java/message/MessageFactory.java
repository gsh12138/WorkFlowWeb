package message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.MessageRepository;

@Component
public class MessageFactory {

    private static MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        MessageFactory.messageRepository = messageRepository;
    }

    public static Message creatNormol(String sender, String receiver, String content){
        return new MessageNormol(sender,receiver,content,messageRepository);
    }

    public static Message creatWithLink(String sender,String receiver,String content,String link){
        return new MessageWithLink(sender,receiver,content,link,messageRepository);
    }

    public static Message creatWithChoice(String sender,String receiver,String content,String agree,String refuse){
        return new MessageWithChoice(sender,receiver,content,agree,refuse,messageRepository);
    }

}
