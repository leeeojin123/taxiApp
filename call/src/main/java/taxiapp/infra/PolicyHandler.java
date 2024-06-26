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
    CallRepository callRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DriveStarted'"
    )
    public void wheneverDriveStarted_ChangeCallStatus(
        @Payload DriveStarted driveStarted
    ) {
        DriveStarted event = driveStarted;
        System.out.println(
            "\n\n##### listener ChangeCallStatus : " + driveStarted + "\n\n"
        );

        // Sample Logic //
        Call.changeCallStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DriveEnded'"
    )
    public void wheneverDriveEnded_ChangeCallStatus(
        @Payload DriveEnded driveEnded
    ) {
        DriveEnded event = driveEnded;
        System.out.println(
            "\n\n##### listener ChangeCallStatus : " + driveEnded + "\n\n"
        );

        // Sample Logic //
        Call.changeCallStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DriverNotAvailabled'"
    )
    public void wheneverDriverNotAvailabled_CancleCall(
        @Payload DriverNotAvailabled driverNotAvailabled
    ) {
        DriverNotAvailabled event = driverNotAvailabled;
        System.out.println(
            "\n\n##### listener CancleCall : " + driverNotAvailabled + "\n\n"
        );

        // Sample Logic //
        Call.cancleCall(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
