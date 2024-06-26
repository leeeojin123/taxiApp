package taxiapp.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import taxiapp.DriveApplication;
import taxiapp.domain.DriveEnded;
import taxiapp.domain.DriveStarted;
import taxiapp.domain.DriverNotAvailabled;

@Entity
@Table(name = "Drive_table")
@Data
//<<< DDD / Aggregate Root
public class Drive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driveId;

    private String driverName;

    private Integer taxiNum;

    private Long callId;

    private String driveStatus;

    @PostPersist
    public void onPostPersist() {
        DriveStarted driveStarted = new DriveStarted(this);
        driveStarted.publishAfterCommit();

        DriveEnded driveEnded = new DriveEnded(this);
        driveEnded.publishAfterCommit();

        DriverNotAvailabled driverNotAvailabled = new DriverNotAvailabled(this);
        driverNotAvailabled.publishAfterCommit();
    }

    public static DriveRepository repository() {
        DriveRepository driveRepository = DriveApplication.applicationContext.getBean(
            DriveRepository.class
        );
        return driveRepository;
    }

    //<<< Clean Arch / Port Method
    public static void requestDriver(FarePaid farePaid) {
        //implement business logic here:

        /** Example 1:  new item 
        Drive drive = new Drive();
        repository().save(drive);

        DriverNotAvailabled driverNotAvailabled = new DriverNotAvailabled(drive);
        driverNotAvailabled.publishAfterCommit();
        DriveStarted driveStarted = new DriveStarted(drive);
        driveStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(farePaid.get???()).ifPresent(drive->{
            
            drive // do something
            repository().save(drive);

            DriverNotAvailabled driverNotAvailabled = new DriverNotAvailabled(drive);
            driverNotAvailabled.publishAfterCommit();
            DriveStarted driveStarted = new DriveStarted(drive);
            driveStarted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
