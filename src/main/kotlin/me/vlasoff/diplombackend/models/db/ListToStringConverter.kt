package me.vlasoff.diplombackend.models.db

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class ListToStringConverter : AttributeConverter<List<String>, String> {

    companion object {
        private const val SPLIT_CHAR = ","
    }

    override fun convertToDatabaseColumn(attribute: List<String>?): String {
        return attribute?.joinToString(SPLIT_CHAR) ?: ""
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        return dbData?.split(SPLIT_CHAR)?.toList() ?: emptyList()
    }
}