package vehiclemgmt.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ModifyPerformanceCommand {

    private String registrationId;
    private String vehicleNumber;
    private date registrationDate;
    private String purpose;
    private String departure;
    private String departureTime;
    private int accumulatedDistanceBefore;
    private String destination;
    private String arrivalTime;
    private int accumulatedDistanceAfter;
}
