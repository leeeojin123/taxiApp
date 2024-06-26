package taxiapp.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import taxiapp.config.kafka.KafkaProcessor;
import taxiapp.domain.*;

@Service
public class CallViewViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private CallViewRepository callViewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCallPlaced_then_CREATE_1(@Payload CallPlaced callPlaced) {
        try {
            if (!callPlaced.validate()) return;

            // view 객체 생성
            CallView callView = new CallView();
            // view 객체에 이벤트의 Value 를 set 함
            callView.setCallId(String.valueOf(callPlaced.getCallId()));
            callView.setCallStatus(callPlaced.getCallStatus());
            callView.setUserId(callPlaced.getUserId());
            // view 레파지 토리에 save
            callViewRepository.save(callView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCallCancelled_then_UPDATE_1(
        @Payload CallCancelled callCancelled
    ) {
        try {
            if (!callCancelled.validate()) return;
            // view 객체 조회

            List<CallView> callViewList = callViewRepository.findByCallId(
                String.valueOf(callCancelled.getCallId())
            );
            for (CallView callView : callViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                callView.setCallStatus(callCancelled.getCallStatus());
                // view 레파지 토리에 save
                callViewRepository.save(callView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDriveStarted_then_UPDATE_2(
        @Payload DriveStarted driveStarted
    ) {
        try {
            if (!driveStarted.validate()) return;
            // view 객체 조회

            List<CallView> callViewList = callViewRepository.findByCallId(
                String.valueOf(driveStarted.getCallId())
            );
            for (CallView callView : callViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                callView.setDriverName(driveStarted.getDriverName());
                callView.setTaxiNum(String.valueOf(driveStarted.getTaxiNum()));
                callView.setDriveStatus(driveStarted.getDriveStatus());
                // view 레파지 토리에 save
                callViewRepository.save(callView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDriveEnded_then_UPDATE_3(@Payload DriveEnded driveEnded) {
        try {
            if (!driveEnded.validate()) return;
            // view 객체 조회

            List<CallView> callViewList = callViewRepository.findByCallId(
                String.valueOf(driveEnded.getCallId())
            );
            for (CallView callView : callViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                callView.setDriveStatus(driveEnded.getDriveStatus());
                callView.setCallStatus("complete");
                // view 레파지 토리에 save
                callViewRepository.save(callView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
