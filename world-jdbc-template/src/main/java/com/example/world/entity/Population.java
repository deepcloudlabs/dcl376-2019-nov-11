package com.example.world.entity;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Population {
	private long value;

	public Population() {
	}

	public Population(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Population [value=" + value + "]";
	}

}
