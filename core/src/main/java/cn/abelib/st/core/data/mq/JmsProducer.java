package cn.abelib.st.core.data.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Destination;

/**
 *
 * @author abel
 * @date 2018/4/5
 */
@Slf4j
public class JmsProducer{
    // JmsMessagingTemplate是对JmsTemplate的封装
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String message){
        log.debug("send message {}", message);
        jmsTemplate.convertAndSend(destination, message);
    }
}
