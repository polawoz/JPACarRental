package com.capgemini.types;


import java.util.List;

import com.capgemini.domain.enums.CarType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class CarTO {
	
    private Long id;
    private CarType carType;
    private String model;
    private Integer productionYear;
    private String color;
    private Integer engineSize;
    private Integer power;
    private Integer mileage;
    private List<Long> supervisorsIdList;
    
    
	public CarTO(Long id, CarType carType, String model, Integer productionYear, String color, Integer engineSize,
			Integer power, Integer mileage, List<Long> supervisorsIdList) {
		this.id = id;
		this.carType = carType;
		this.model = model;
		this.productionYear = productionYear;
		this.color = color;
		this.engineSize = engineSize;
		this.power = power;
		this.mileage = mileage;
		this.supervisorsIdList = supervisorsIdList;
	}
	
	
	public CarTO(){
		
		
		
	}
	
	
	public static CarTOBuilder builder(){
		
		return new CarTOBuilder();
		
	}
    
	public static class CarTOBuilder {
		
		
	    private Long id;
	    private CarType carType;
	    private String model;
	    private Integer productionYear;
	    private String color;
	    private Integer engineSize;
	    private Integer power;
	    private Integer mileage;
	    private List<Long> supervisorsIdList;
	    
	    
	    
	    public CarTOBuilder(){
	    	super();
	    }



		public CarTOBuilder id(Long id) {
			this.id = id;
			return this;
		}



		public CarTOBuilder carType(CarType carType) {
			this.carType = carType;
			return this;
			
		}



		public CarTOBuilder model(String model) {
			this.model = model;
			return this;
		}



		public CarTOBuilder productionYear(Integer productionYear) {
			this.productionYear = productionYear;
			return this;
		}



		public CarTOBuilder color(String color) {
			this.color = color;
			return this;
		}



		public CarTOBuilder engineSize(Integer engineSize) {
			this.engineSize = engineSize;
			return this;
		}



		public CarTOBuilder power(Integer power) {
			this.power = power;
			return this;
		}



		public CarTOBuilder mileage(Integer mileage) {
			this.mileage = mileage;
			return this;
		}



		public CarTOBuilder supervisorsIdList(List<Long> supervisorsIdList) {
			this.supervisorsIdList = supervisorsIdList;
			return this;
		}
	    
	    
	    
		public CarTO build(){
			return new CarTO(id, carType, model, productionYear, color, engineSize, power, mileage, supervisorsIdList);
		}
		
		
		
		
	}
    
    








    
    
	
	
    
    
    
    

}
