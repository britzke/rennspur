/**
 * 
 */
package de.rennspur.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter for boolean database values "Y" and "N" to a Boolean entity
 * property.
 * 
 * @author burghard.britzke
 */
@Converter(autoApply = true)
public class BooleanYNConverter implements AttributeConverter<Boolean, Character> {
	/**
	 * Converts the value true or false to the character "Y" or "N".
	 * 
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public Character convertToDatabaseColumn(Boolean value) {
		if (Boolean.TRUE.equals(value)) {
			return 'Y';
		} else {
			return 'N';
		}
	}

	/**
	 * Converts the character "Y" or "N" to true or false.
	 * 
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public Boolean convertToEntityAttribute(Character value) {
		return value.equals('Y');
	}

}
