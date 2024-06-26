package taxiapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CallPlaced extends AbstractEvent {

    private Long callId;
    private String userId;
    private String callStatus;
    private Date callDtm;
    private Integer distance;

    public CallPlaced(Call aggregate) {
        super(aggregate);
    }

    public CallPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
