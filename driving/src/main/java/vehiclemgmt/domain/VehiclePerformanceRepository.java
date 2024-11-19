package vehiclemgmt.domain;

import vehiclemgmt.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel="vehiclePerformances", path="vehiclePerformances")
public interface VehiclePerformanceRepository extends PagingAndSortingRepository<VehiclePerformance, >{
}