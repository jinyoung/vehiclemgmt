package vehiclemgmt.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehiclemgmt.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/carAssignments")
@Transactional
public class CarAssignmentController {

    @Autowired
    CarAssignmentRepository carAssignmentRepository;

    @RequestMapping(
        value = "/carAssignments/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public CarAssignment cancelCarAssignment(
        @PathVariable(value = "id") Long id,
        @RequestBody CancelCarAssignmentCommand cancelCarAssignmentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /carAssignment/cancelCarAssignment  called #####"
        );
        Optional<CarAssignment> optionalCarAssignment = carAssignmentRepository.findById(
            id
        );

        optionalCarAssignment.orElseThrow(() -> new Exception("No Entity Found")
        );
        CarAssignment carAssignment = optionalCarAssignment.get();
        carAssignment.cancelCarAssignment(cancelCarAssignmentCommand);

        carAssignmentRepository.save(carAssignment);
        return carAssignment;
    }
}
//>>> Clean Arch / Inbound Adaptor
