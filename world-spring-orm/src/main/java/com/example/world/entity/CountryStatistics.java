package com.example.world.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.DynamicUpdate;

@Embeddable
@DynamicUpdate
public class CountryStatistics {
	@Column(name = "surfacearea", nullable = false)
	private double surfaceArea;
	@Column(name = "population")
	private long population;
	@Column(name = "gnp")
	private Double gnp;

	public CountryStatistics() {
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public Double getGnp() {
		return gnp;
	}

	public void setGnp(Double gnp) {
		this.gnp = gnp;
	}

	@Override
	public String toString() {
		return "CountryStatistics [surfaceArea=" + surfaceArea + ", population=" + population + ", gnp=" + gnp + "]";
	}

}
