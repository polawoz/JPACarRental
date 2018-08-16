package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.capgemini.domain.enums.CarType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@CreationTimestamp
@UpdateTimestamp
@Table(name = "CAR")
public class CarEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;
    @Column(nullable = false, length = 20)
    private String model;
    @Column(nullable = false)
    private Integer productionYear;
    @Column(nullable = false, length = 20)
    private String color;
    @Column(nullable = false)
    private Integer engineSize;
    @Column(nullable = false)
    private Integer power;
    @Column(nullable = false)
    private Integer mileage;
    

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time")
    private Date created;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modify_time")
    private Date modified;
    
    
 
    
    
    
    

    @OneToMany(cascade= CascadeType.REMOVE, mappedBy="car")
    private List<RentalEntity> rentals= new ArrayList<>();
    
    //carEntity jest wlascicielem employee
    @ManyToMany
    @JoinTable
    (
    		name="EMPLOYEE_CAR",
    		joinColumns={ @JoinColumn(name="car_id")},
    		inverseJoinColumns = { @JoinColumn(name="employee_id") }
    )
    private List<EmployeeEntity> carsSupervisors= new ArrayList<>();


	public void addSupervisor(EmployeeEntity employeeEntity) {
		
		carsSupervisors.add(employeeEntity);
		employeeEntity.addSupervisedCar(this);
		
	}
    
    
    

	
    
    
    
    
    
    
}
    
    
    
    
	
	
	
	
