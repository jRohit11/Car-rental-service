package com.intern.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.carRental.primary.VehicleLog;
import com.intern.carRental.primary.abstrct.Vehicle;

public interface VehicleLogRepository extends JpaRepository<VehicleLog,Integer>{
	List<VehicleLog>  findAllByVehicleId(int id);
}
