package com.psych.game.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
public abstract class Employee extends Auditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotBlank
	private String name;

	@Getter
	@Setter
	@NotBlank
	@Email
	private String email;

	@Getter
	@Setter
	@NotBlank
	private String address;

	@Getter
	@Setter
	@NotBlank
	private String phoneNumber;


}
