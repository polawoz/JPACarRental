package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "EMPLOYEE")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String position;
    @Column(nullable = false, length = 30)
    private String lastName;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false)
    private Date dateOfBirth;
    
    @ManyToOne
    private OfficeEntity office= new OfficeEntity();

    @ManyToMany(mappedBy= "carsSupervisors")
    private List<CarEntity> carsUnderSupervision = new ArrayList<>();


	
	public void addSupervisedCar(CarEntity car){
		
		carsUnderSupervision.add(car);
		
		
	}
    
    
    


}
