package taxiapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DriverNotAvailabled extends AbstractEvent {

    private Long driveId;
    private String driverName;
    private Integer taxiNum;
    private Long callId;
    private String driveStatus;

    public DriverNotAvailabled(Drive aggregate) {
        super(aggregate);
    }

    public DriverNotAvailabled() {
        super();
    }
}
//>>> DDD / Domain Event
