package com.capgemini.types;



import java.util.List;

import com.capgemini.domain.Address;
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
public class OfficeTO {

	private Long id;
	private Address address;
	private String phone;
	private String email;
	private List<Long> employeesIdList;

}
