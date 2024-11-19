package vehiclemgmt.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import vehiclemgmt.DrivingApplication;

@Entity
@Table(name = "VehiclePerformance_table")
@Data
//<<< DDD / Aggregate Root
public class VehiclePerformance {

    @Id
    private String registrationId;

    private String vehicleNumber;

    private Date registrationDate;

    private String purpose;

    private String departure;

    private String departureTime;

    private int accumulatedDistanceBefore;

    private String destination;

    private String arrivalTime;

    private int accumulatedDistanceAfter;

    public static VehiclePerformanceRepository repository() {
        VehiclePerformanceRepository vehiclePerformanceRepository = DrivingApplication.applicationContext.getBean(
            VehiclePerformanceRepository.class
        );
        return vehiclePerformanceRepository;
    }

    //<<< Clean Arch / Port Method
    public void modifyPerformance(
        ModifyPerformanceCommand modifyPerformanceCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
