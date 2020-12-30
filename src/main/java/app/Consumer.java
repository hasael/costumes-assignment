package app;

import app.input.CostumeInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Slf4j
@Component
public class Consumer implements MessageListener {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    CostumeAssigner costumeAssigner;

    @Autowired
    AmazonIntegrationNotifier amazonIntegrationNotifier;


    @Override
    @JmsListener(destination = "assignment")
    public void onMessage(Message message) {
        try {
            log.info("Received message: " + message);

            var text = ((TextMessage) message).getText();
            var assignedCostume = costumeAssigner.assignedCostume(fromJson(text));
            assignmentRepository.save(assignedCostume);
            amazonIntegrationNotifier.notifyAssignment(assignedCostume);
        } catch (Exception ex) {
            log.error("Error consuming message: " + ex.getLocalizedMessage());
        }
    }

    private CostumeInput fromJson(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, CostumeInput.class);
    }
}
