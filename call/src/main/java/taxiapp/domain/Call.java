package taxiapp.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import taxiapp.CallApplication;
import taxiapp.domain.CallCancelled;
import taxiapp.domain.CallPlaced;

@Entity
@Table(name = "Call_table")
@Data
//<<< DDD / Aggregate Root
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long callId;

    private String userId;

    private String callStatus;

    private Date callDtm;

    private Integer distance;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        taxiapp.external.Payment payment = new taxiapp.external.Payment();
        // mappings goes here
        CallApplication.applicationContext
            .getBean(taxiapp.external.PaymentService.class)
            .pay(payment);

        CallPlaced callPlaced = new CallPlaced(this);
        callPlaced.publishAfterCommit();

        CallCancelled callCancelled = new CallCancelled(this);
        callCancelled.publishAfterCommit();
    }

    public static CallRepository repository() {
        CallRepository callRepository = CallApplication.applicationContext.getBean(
            CallRepository.class
        );
        return callRepository;
    }

    //<<< Clean Arch / Port Method
    public static void changeCallStatus(DriveStarted driveStarted) {
        //implement business logic here:

        /** Example 1:  new item 
        Call call = new Call();
        repository().save(call);

        */

        /** Example 2:  finding and process
        
        repository().findById(driveStarted.get???()).ifPresent(call->{
            
            call // do something
            repository().save(call);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void changeCallStatus(DriveEnded driveEnded) {
        //implement business logic here:

        /** Example 1:  new item 
        Call call = new Call();
        repository().save(call);

        */

        /** Example 2:  finding and process
        
        repository().findById(driveEnded.get???()).ifPresent(call->{
            
            call // do something
            repository().save(call);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancleCall(DriverNotAvailabled driverNotAvailabled) {
        //implement business logic here:

        /** Example 1:  new item 
        Call call = new Call();
        repository().save(call);

        CallCancelled callCancelled = new CallCancelled(call);
        callCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(driverNotAvailabled.get???()).ifPresent(call->{
            
            call // do something
            repository().save(call);

            CallCancelled callCancelled = new CallCancelled(call);
            callCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
