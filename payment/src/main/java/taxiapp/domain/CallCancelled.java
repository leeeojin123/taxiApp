package taxiapp.domain;

import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

@Data
@ToString
public class CallCancelled extends AbstractEvent {

    private Long callId;
    private String userId;
    private String callStatus;
    private Date callDtm;
    private Integer distance;
}
