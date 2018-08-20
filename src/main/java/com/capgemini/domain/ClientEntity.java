package com.capgemini.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	@OneToMany(mappedBy = "client")
	private List<RentalEntity> rentals = new ArrayList<>();

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modified;

	@Version
	private Long version;

}
