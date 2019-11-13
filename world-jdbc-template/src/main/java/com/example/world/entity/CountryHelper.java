package com.example.world.entity;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class CountryHelper {

	public static Builder newCountryBuilder(String code) {
		return new Builder(code);
	}

	public static class Builder {
		private String code;
		private String name;
		private String continent;
		private double surfaceArea;
		private Population population;
		private GrossNationalProduct gnp;
		private City capital;

		private Builder(String code) {
			this.code = code;
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder continent(String continent) {
			this.continent = continent;
			return this;
		}

		public Builder surfaceArea(double surfaceArea) {
			this.surfaceArea = surfaceArea;
			return this;
		}

		public Builder population(long value) {
			this.population = new Population(value);
			return this;
		}

		public Builder gnp(double value) {
			this.gnp = new GrossNationalProduct(value);
			return this;
		}

		public Builder setCapital(int capital) {
			this.capital = new City();
			this.capital.setId(capital);
			return this;
		}

		public Country build() {
			final Country country = new Country();
			country.setCode(code);
			country.setName(name);
			country.setContinent(continent);
			country.setPopulation(population);
			country.setSurfaceArea(surfaceArea);
			country.setGnp(gnp);
			country.setCapital(capital);
			return country;
		}
	}
}
