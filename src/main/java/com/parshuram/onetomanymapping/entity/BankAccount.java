package com.parshuram.onetomanymapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sr_no")
	private Integer id;
	private String accountId;
	@NotEmpty
	private String bankName;
	@NotEmpty
	private String accountType;
	@NotNull
	private Long balance;
	
	
	//many bankAccount has one user
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
