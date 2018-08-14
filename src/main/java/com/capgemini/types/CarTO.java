package com.capgemini.types;

import java.time.Year;
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
public class CarTO {
	
    private Long id;
    private CarType carType;
    private String model;
    private Integer productionYear;
    private String color;
    private Integer engineSize;
    private Integer power;
    private Integer mileage;
    
//    private Collection<Long> rentals= new HashSet<>();
//    private Collection<Long> carsSupervisors= new HashSet<>();
    








    
    
	
	
    
    
    
    

}
