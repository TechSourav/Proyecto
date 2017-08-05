package com.team.solventa.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component("LocalDateDeserializer")
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	public LocalDateDeserializer() {
		super();
	}

	@Override
	public LocalDate deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final String dateJson = jp.getValueAsString();
		if (StringUtils.isEmpty(jp)) {
			return null;
		}
		final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dateJson, dateFormat);
	}

}
