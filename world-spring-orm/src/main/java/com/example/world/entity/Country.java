package com.example.world.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */

/*
DELIMITER //
CREATE PROCEDURE continent_countries_capital
(IN cont CHAR(20))
BEGIN
  SELECT co.code, co.Name as "Name", ci.id, ci.Name as "Capital" FROM Country as co, City ci
  WHERE co.Continent = cont AND co.capital=ci.id;
END //
DELIMITER ;
 */
@Entity
@Table(name = "country")
@NamedQueries({ @NamedQuery(name = "Country.findAll", query = "select c from Country c"),
		@NamedQuery(name = "Country.findAllByContinent", query = "select c from Country c where c.continent=:continent"),
		@NamedQuery(name = "Country.findDistinctContinent", query = "select distinct c.continent from Country c"), })
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
	    name = "ContinentCapitals",
	    procedureName = "continent_countries_capital",
	    parameters = {
	    	@StoredProcedureParameter(
	    			mode = ParameterMode.IN,
	    			type = String.class,
	    			name = "cont"
	    	)	
	    }
	)
})
@NamedEntityGraphs({
	@NamedEntityGraph(
		name="graph.Country.cities",
	    attributeNodes = {
	    		@NamedAttributeNode(value="cities",subgraph = "cities")
	    },
	    subgraphs = {
	    		@NamedSubgraph(name="cities",attributeNodes = {
	    				@NamedAttributeNode("country")
	    		})
	    }
	),
	@NamedEntityGraph(
			name = "graph.Country.citylangs", 
			attributeNodes = {
				@NamedAttributeNode(value = "cities", subgraph = "cities"),
				@NamedAttributeNode(value = "languages", subgraph = "languages") 
			}, 
			subgraphs = {
				@NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country")),
				@NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country")) 
			}
		),
		@NamedEntityGraph(
			name = "graph.Country.languages", 
			attributeNodes = @NamedAttributeNode(value = "languages", subgraph = "languages"), 
			subgraphs = @NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country")))
})
@DynamicUpdate
public class Country {
	@Id
	@Column(name = "code")
	@Pattern(regexp = "^[A-Z]{3}$",message = "Country code must have three capital letters only!")
	private String code;
	@Column(name = "name")
	@Size(min=3)
	private String name;
	@Column(name = "continent", nullable = false)
	private String continent;
	@Embedded
	@NotNull
	private CountryStatistics statistics;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "capital")
	private City capital;
	@OneToMany(mappedBy = "country",
			fetch = FetchType.LAZY ,
			cascade =  {CascadeType.PERSIST,CascadeType.MERGE})
	private List<City> cities;
	@OneToMany(mappedBy = "id.code",fetch = FetchType.LAZY)
	private List<CountryLanguage> languages;

	public Country() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public CountryStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(CountryStatistics statistics) {
		this.statistics = statistics;
	}

	public City getCapital() {
		return capital;
	}

	public void setCapital(City capital) {
		this.capital = capital;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<CountryLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(List<CountryLanguage> languages) {
		this.languages = languages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", statistics=" + statistics
				+ "]";
	}

}
