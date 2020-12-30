package app;

import app.db.AssignedCostume;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsAmazonProducer implements AmazonIntegrationNotifier{

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void notifyAssignment(AssignedCostume assignedCostume) {
        try {
            log.info("sending assigned costume to amazon integration component: " + assignedCostume.getId());
            jmsTemplate.convertAndSend("amazon", toJson(assignedCostume));
        } catch (Exception ex) {
            log.error("error: " + ex.getLocalizedMessage());
        }
    }

    private String toJson(AssignedCostume costume){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(costume);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
