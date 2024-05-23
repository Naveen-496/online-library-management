package dev.reddy.olm.enums.converters;

import dev.reddy.olm.enums.Authority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class RoleConverted implements AttributeConverter<Authority, String> {

    @Override
    public String convertToDatabaseColumn(Authority authority) {
        return Objects.isNull(authority) ? null : authority.getValue();
    }

    @Override
    public Authority convertToEntityAttribute(String code) {
        if ( Objects.isNull(code) )
             return null;

        return Arrays.stream(Authority.values())
                .filter(a -> a.getValue().equals(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
