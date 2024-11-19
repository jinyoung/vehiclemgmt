package vehiclemgmt.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import vehiclemgmt.DrivingApplication;
import vehiclemgmt.domain.DrivingLogRegistered;
import vehiclemgmt.domain.PerformanceDeleted;
import vehiclemgmt.domain.PerformanceModified;
import vehiclemgmt.domain.PerformanceRegistered;

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

    @Embedded
    private int accumulatedDistanceBefore;

    private String destination;

    private String arrivalTime;

    @Embedded
    private int accumulatedDistanceAfter;

    @Embedded
    private int drivingDistance;

    @Embedded
    private Purpose purpose;

    @PostPersist
    public void onPostPersist() {
        PerformanceRegistered performanceRegistered = new PerformanceRegistered(
            this
        );
        performanceRegistered.publishAfterCommit();

        PerformanceModified performanceModified = new PerformanceModified(this);
        performanceModified.publishAfterCommit();

        PerformanceDeleted performanceDeleted = new PerformanceDeleted(this);
        performanceDeleted.publishAfterCommit();

        DrivingLogRegistered drivingLogRegistered = new DrivingLogRegistered(
            this
        );
        drivingLogRegistered.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

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
