package com.psych.game.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

//Hibernate is an ORM-Object Relation Manager
@Entity
@Table(name = "players")
public class Player extends Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotBlank
	private String name;

	@Getter @Setter @URL
	private String psychFaceURL;

	@Getter @Setter @URL
	private String picURL;

	@OneToOne
	@Getter @Setter
	private Stats stats;

	@ManyToMany(mappedBy = "players") //to prevent duplicacy of data
	@Getter @Setter
	private List<Game> games;

}
