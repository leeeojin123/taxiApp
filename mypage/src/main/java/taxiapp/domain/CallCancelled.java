package taxiapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import taxiapp.infra.AbstractEvent;

@Data
public class CallCancelled extends AbstractEvent {

    private Long callId;
    private String userId;
    private String callStatus;
    private Date callDtm;
    private Integer distance;
}
