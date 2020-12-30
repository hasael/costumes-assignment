package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    SalesRepository salesRepository;

    @KafkaListener(topics = "sales", groupId = "test-consumer-group")
    public void listenGroupFoo(String message) throws JsonProcessingException {

        Sale sale = fromJson(message);
        salesRepository.markAsSold(sale.getCostumeId());
    }

    private Sale fromJson(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, Sale.class);
    }
}
