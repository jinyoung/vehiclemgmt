package vehiclemgmt.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehiclemgmt.domain.*;
import vehiclemgmt.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PerformanceRegistered extends AbstractEvent {

    private String registrationId;

    public PerformanceRegistered(VehiclePerformance aggregate) {
        super(aggregate);
    }

    public PerformanceRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
