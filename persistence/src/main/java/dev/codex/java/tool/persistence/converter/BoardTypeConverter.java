package dev.codex.java.tool.persistence.converter;

import dev.codex.java.tool.persistence.type.BoardType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoardTypeConverter implements AttributeConverter<BoardType, Character> {
    @Override
    public Character convertToDatabaseColumn(BoardType attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.name().charAt(0);
    }

    @Override
    public BoardType convertToEntityAttribute(Character dbData) {
        if (dbData == null) {
            return null;
        }

        return BoardType.lookupBoardType(dbData);
    }
}