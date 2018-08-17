package com.capgemini.types;

import java.sql.Timestamp;


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
public class RentalTO {
	
	
	private Long id;
	private Timestamp startDatetime;
	private Timestamp endDatetime;
	private Float loanAmount;
	private Long clientId;
	private Long carId;
	private Long pickUpOfficeId;
	private Long returnOfficeId;

}
