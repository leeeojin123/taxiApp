package taxiapp.domain;

import java.util.*;
import lombok.*;
import taxiapp.domain.*;
import taxiapp.infra.AbstractEvent;

@Data
@ToString
public class FarePaid extends AbstractEvent {

    private Long payId;
    private Long callId;
    private Object fare;
    private Date payDtm;
    private String payStatus;
}
