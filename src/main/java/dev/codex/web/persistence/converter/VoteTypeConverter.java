package dev.codex.web.persistence.converter;

import dev.codex.web.application.type.VoteType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VoteTypeConverter implements AttributeConverter<VoteType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(VoteType attribute) {
        if (attribute == null)
            return null;

        return attribute.value();
    }

    @Override
    public VoteType convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return dbData == 1 ? VoteType.UP_VOTE : VoteType.DOWN_VOTE;
    }
}