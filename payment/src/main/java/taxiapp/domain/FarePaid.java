package taxiapp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class FarePaid extends AbstractEvent {

    private Long payId;
    private Long callId;
    private BigDecimal fare;
    private Date payDtm;
    private String payStatus;

    public FarePaid(Payment aggregate) {
        super(aggregate);
    }

    public FarePaid() {
        super();
    }
}
//>>> DDD / Domain Event
