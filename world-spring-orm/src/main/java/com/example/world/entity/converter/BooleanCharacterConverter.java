package com.example.world.entity.converter;

import javax.persistence.AttributeConverter;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class BooleanCharacterConverter implements AttributeConverter<Boolean, Character> {

	public Character convertToDatabaseColumn(Boolean value) {
		return value ? 'T' : 'F';
	}

	public Boolean convertToEntityAttribute(Character value) {
		return Character.toUpperCase(value) == 'T';
	}

}
