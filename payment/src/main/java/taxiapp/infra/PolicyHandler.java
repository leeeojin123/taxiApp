package taxiapp.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import taxiapp.config.kafka.KafkaProcessor;
import taxiapp.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CallCancelled'"
    )
    public void wheneverCallCancelled_CanclePay(
        @Payload CallCancelled callCancelled
    ) {
        CallCancelled event = callCancelled;
        System.out.println(
            "\n\n##### listener CanclePay : " + callCancelled + "\n\n"
        );

        // Sample Logic //
        Payment.canclePay(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
