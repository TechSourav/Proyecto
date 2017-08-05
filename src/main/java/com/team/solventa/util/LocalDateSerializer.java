package com.team.solventa.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component("LocalDateSerializer")
public class LocalDateSerializer extends JsonSerializer<Object> {

	public LocalDateSerializer() {
		super();
	}

	@Override
	public void serialize(final Object localDate, final JsonGenerator jsonGenerator, final SerializerProvider provider) throws IOException {
		final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (localDate instanceof LocalDate) {
			jsonGenerator.writeString(((LocalDate) localDate).format(dateFormat));
		}
		if (localDate instanceof LocalDateTime) {
			jsonGenerator.writeString(((LocalDateTime) localDate).format(dateFormat));
		}
	}
}
