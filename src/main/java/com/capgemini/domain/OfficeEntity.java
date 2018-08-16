package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "OFFICE")
public class OfficeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Address address;
    @Column(nullable = false, length = 11)
    private String phone;
    @Column(nullable = false, length = 254)
    private String email;
    
    
    @OneToMany(mappedBy="office")
    private List<EmployeeEntity> employees= new ArrayList<>();
    
    
    @OneToMany(mappedBy="pickUpOffice")
    private List<RentalEntity> rentalsFrom = new ArrayList<>();
    
    @OneToMany(mappedBy="returnOffice")
    private List<RentalEntity> rentalsTo = new ArrayList<>();
    
    
    
	
	
	
	
}
