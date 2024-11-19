package vehiclemgmt.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import vehiclemgmt.AssignApplication;
import vehiclemgmt.domain.CarAssignmentRequested;

@Entity
@Table(name = "CarAssignment_table")
@Data
//<<< DDD / Aggregate Root
public class CarAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String requesterName;

    private String organization;

    private String employeeNumber;

    private String officeNumber;

    private String mobileNumber;

    private Date requestDate;

    private String approverInfo;

    private String approverPosition;

    private String usagePurpose;

    private String numberOfPassengers;

    private String operationPeriod;

    private String routeSetting;

    private String remarks;

    private String passengerContact;

    private String attachedDocuments;

    private String cancellationReason;

    @Embedded
    private UsageCategory usageCategory;

    @Embedded
    private CarType carType;

    @Embedded
    private MainDepartment mainDepartment;

    @Embedded
    private OperationSection operationSection;

    @Embedded
    private OperationType operationType;

    @Embedded
    private IncludeDriver includeDriver;

    @Embedded
    private ProgressStage progressStage;

    @PostPersist
    public void onPostPersist() {
        CarAssignmentRequested carAssignmentRequested = new CarAssignmentRequested(
            this
        );
        carAssignmentRequested.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static CarAssignmentRepository repository() {
        CarAssignmentRepository carAssignmentRepository = AssignApplication.applicationContext.getBean(
            CarAssignmentRepository.class
        );
        return carAssignmentRepository;
    }

    //<<< Clean Arch / Port Method
    public void cancelCarAssignment(
        CancelCarAssignmentCommand cancelCarAssignmentCommand
    ) {
        //implement business logic here:

        CarAssignmentCancelled carAssignmentCancelled = new CarAssignmentCancelled(
            this
        );
        carAssignmentCancelled.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
