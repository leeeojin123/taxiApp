package taxiapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import taxiapp.infra.AbstractEvent;

@Data
public class DriveStarted extends AbstractEvent {

    private Long driveId;
    private String driverName;
    private Integer taxiNum;
    private Long callId;
    private String driveStatus;
}
