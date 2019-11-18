package com.example.entity;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Entity
@DiscriminatorValue("developer")
public class Developer extends Employee {

	@ElementCollection
	private Collection<String> languages;

	public Developer() {
	}

	public Collection<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Collection<String> languages) {
		this.languages = languages;
	}

	@Override
	public String toString() {
		return "Developer [languages=" + languages + ", getLanguages()=" + getLanguages() + "]";
	}

}
