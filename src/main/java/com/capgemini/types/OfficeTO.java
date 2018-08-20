package com.capgemini.types;

import java.util.List;

import com.capgemini.domain.Address;
import lombok.Getter;

import lombok.Setter;


@Getter
@Setter

public class OfficeTO {

	private Long id;
	private Address address;
	private String phone;
	private String email;
	private List<Long> employeesIdList;

	public OfficeTO(Long id, Address address, String phone, String email, List<Long> employeesIdList) {
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.employeesIdList = employeesIdList;
	}

	public static OfficeTOBuilder builder() {
		return new OfficeTOBuilder();

	}

	public static class OfficeTOBuilder {

		private Long id;
		private Address address;
		private String phone;
		private String email;
		private List<Long> employeesIdList;

		public OfficeTOBuilder() {
			super();
		}

		public OfficeTOBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public OfficeTOBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public OfficeTOBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public OfficeTOBuilder email(String email) {
			this.email = email;
			return this;
		}

		public OfficeTOBuilder employeesIdList(List<Long> employeesIdList) {
			this.employeesIdList = employeesIdList;
			return this;
		}

		public OfficeTO build() {
			return new OfficeTO(id, address, phone, email, employeesIdList);
		}

	}

}
