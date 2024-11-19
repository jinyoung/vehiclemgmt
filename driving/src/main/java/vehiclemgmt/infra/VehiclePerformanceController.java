package vehiclemgmt.infra;
import vehiclemgmt.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/vehiclePerformances")
@Transactional
public class VehiclePerformanceController {
    @Autowired
    VehiclePerformanceRepository vehiclePerformanceRepository;

    @RequestMapping(value = "/vehiclePerformances/{id}/modfiyevent",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public VehiclePerformance modifyPerformance(@PathVariable(value = "id") String id, @RequestBody ModifyPerformanceCommand modifyPerformanceCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /vehiclePerformance/modifyPerformance  called #####");
            Optional<VehiclePerformance> optionalVehiclePerformance = vehiclePerformanceRepository.findById(id);
            
            optionalVehiclePerformance.orElseThrow(()-> new Exception("No Entity Found"));
            VehiclePerformance vehiclePerformance = optionalVehiclePerformance.get();
            vehiclePerformance.modifyPerformance(modifyPerformanceCommand);
            
            vehiclePerformanceRepository.save(vehiclePerformance);
            return vehiclePerformance;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
