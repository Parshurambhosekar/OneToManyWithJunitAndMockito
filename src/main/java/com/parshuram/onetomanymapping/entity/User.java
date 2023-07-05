package com.parshuram.onetomanymapping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@NotEmpty(message = "Name is not empty...")
	private String name;
	@NotEmpty(message = "city is not empty......")
	private String city;
	@Email(message = "Invalid Email Address..........")
	private String email;
	@NotNull(message="Mobile Number must be in 10 digits.....")
	private Long mobileNumber;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<BankAccount> account;


	public User(Integer userId, @NotEmpty(message = "Name is not empty...") String name,
			@NotEmpty(message = "city is not empty......") String city,
			@Email(message = "Invalid Email Address..........") String email,
			@NotNull(message = "Mobile Number must be in 10 digits.....") Long mobileNumber) {
		super();
		this.userId = userId;
		this.name = name;
		this.city = city;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
	
	
	
	
	
	
	
}
