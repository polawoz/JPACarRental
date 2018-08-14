package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;
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


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String lastName;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Embedded
    private Address address;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false, length = 11)
    private String phone;
    @Column(nullable = false, length = 254)
    private String email;
    @Column(nullable = false, length = 16)
    private String pan;
    
    @OneToMany(mappedBy="client")
    private List<RentalEntity> rentals= new ArrayList<>();
    
    
    
    
    
    
    
    
}
    