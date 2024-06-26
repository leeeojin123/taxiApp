package taxiapp.domain;

import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

@Data
@ToString
public class DriveStarted extends AbstractEvent {

    private Long driveId;
    private String driverName;
    private Integer taxiNum;
    private Long callId;
    private String driveStatus;
}
