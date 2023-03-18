package com.intern.carRental.primary.abstrct;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.intern.carRental.primary.CarRentalLocation;
import com.intern.carRental.primary.ParkingStall;
import com.intern.carRental.primary.VehicleLog;
import com.intern.carRental.primary.VehicleReservation;
import com.intern.primary.enums.VehicleStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public abstract class Vehicle {
	public Vehicle() {
		super();
	}
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	private String numberPlate;
	private String stockNumber;
	private int passengerCapacity;

	private Boolean hasSunroof;

	private String model;	
	
	private String make;
	private int manufacturingYear;
	private int mileage;
	
	@Column(unique = true)
	private String barcode;
	
	@Enumerated(EnumType.STRING)
	private VehicleStatus status;
	
	@JsonBackReference(value="CRLocation") 
	@ManyToOne(optional = true)//,fetch = FetchType.LAZY)
	private CarRentalLocation carRentalLocation;
	
	@JsonManagedReference(value = "log")
	@OneToMany(mappedBy = "vehicle")
	private List<VehicleLog> vehicle_log;
	
	
	@Nullable
	@JsonManagedReference(value = "Vehicle")
	@OneToMany(mappedBy="vehicle")//,fetch = FetchType.LAZY)
	private List<VehicleReservation> vehiclereservation;
	
	@Nullable
	@OneToOne(targetEntity = ParkingStall.class)
	private ParkingStall parkingstall;
	
	public abstract Boolean reserveVehicle();
	
	public abstract Boolean returnVehicle();

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", numberPlate=" + numberPlate + ", stockNumber=" + stockNumber
				+ ", passengerCapacity=" + passengerCapacity + ", hasSunroof=" + hasSunroof + ", model=" + model
				+ ", make=" + make + ", manufacturingYear=" + manufacturingYear + ", mileage=" + mileage + ", barcode="
				+ barcode + ", status=" + status + ", carRentalLocation=" + carRentalLocation + ", vehicle_log="
				+ vehicle_log + ", vehiclereservation=" + vehiclereservation + ", parkingstall=" + parkingstall + "]";
	}
	
}
