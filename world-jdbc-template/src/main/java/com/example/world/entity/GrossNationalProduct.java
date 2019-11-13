package com.example.world.entity;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class GrossNationalProduct {
	private double value;

	public GrossNationalProduct() {
	}

	public GrossNationalProduct(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GrossNationalProduct [value=" + value + "]";
	}

}
