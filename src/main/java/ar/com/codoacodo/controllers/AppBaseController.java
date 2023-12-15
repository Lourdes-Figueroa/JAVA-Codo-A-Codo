package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class AppBaseController extends HttpServlet {

	protected ObjectMapper mapper = new ObjectMapper();

	public AppBaseController() {
		super();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
	}

	protected String toJson(HttpServletRequest req) throws IOException {
		String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		return json;
	}
}
