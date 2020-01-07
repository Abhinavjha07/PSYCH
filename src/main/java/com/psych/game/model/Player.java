package com.psych.game.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
	private Stats stat = new Stats();

	@ManyToMany(mappedBy = "players") //to prevent duplicity of data
	@Getter @Setter
	@JsonIdentityReference
	private List<Game> games;

	public Player(){}

	public Player(Builder builder)
	{
		setName(builder.name);
		setPsychFaceURL(builder.psychFaceURL);
		setPicURL(builder.picURL);
	}

	public static final class Builder {
		private String name;
		private String psychFaceURL;
		private String picURL;

		private Builder() {
		}

		public Builder name(@NotBlank String name) {
			this.name = name;
			return this;
		}

		public Builder psychFaceURL(String psychFaceURL) {
			this.psychFaceURL = psychFaceURL;
			return this;
		}

		public Builder picURL(String picURL) {
			this.picURL = picURL;
			return this;
		}

		public Player build() {

			return new Player(this);
		}
	}
}
