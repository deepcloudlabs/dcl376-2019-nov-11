package com.example.world.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.world.entity.converter.BooleanCharacterConverter;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Entity
@Table(name = "countrylanguage")
public class CountryLanguage {
	@EmbeddedId
	private CountryLanguagePK id;
	@Column(name = "percentage")
	private double percentage;
	@Column(name = "isofficial")
	@Convert(converter = BooleanCharacterConverter.class)
	private boolean official;

	public CountryLanguage() {
	}

	public CountryLanguage(CountryLanguagePK id, double percentage, boolean official) {
		this.id = id;
		this.percentage = percentage;
		this.official = official;
	}

	public CountryLanguagePK getId() {
		return id;
	}

	public void setId(CountryLanguagePK id) {
		this.id = id;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}

	@Override
	public String toString() {
		return "CountryLanguage [id=" + id + ", percentage=" + percentage + ", official=" + official + "]";
	}

}
