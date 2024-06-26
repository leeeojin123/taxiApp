package taxiapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DriveStarted extends AbstractEvent {

    private Long driveId;
    private String driverName;
    private Integer taxiNum;
    private Long callId;
    private String driveStatus;

    public DriveStarted(Drive aggregate) {
        super(aggregate);
    }

    public DriveStarted() {
        super();
    }
}
//>>> DDD / Domain Event
