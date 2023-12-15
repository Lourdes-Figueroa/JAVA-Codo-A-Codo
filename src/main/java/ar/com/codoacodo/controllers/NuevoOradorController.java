package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.List;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySqlOradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador")

public class NuevoOradorController extends AppBaseController {
	private MySqlOradorRepository repository = new MySqlOradorRepository();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String json = super.toJson(req); // ---> captura el jsonStrin del front
		// convertimos de json string a java usando libreria jackson2

		// deserealizamos la fecha local date que viene de la db a un formato soportado
		// por java para poder enviarlo al front
		// obtengo los datos del orador que esta en el json string por medio de la clase
		// oradorRequest
		OradorRequest oradorRequest = super.mapper.readValue(json, OradorRequest.class);
		// valido que ningun campo venga null
		if (oradorRequest.getNombre() == null || oradorRequest.getApellido() == null || oradorRequest.getEmail() == null
				|| oradorRequest.getTema() == null || oradorRequest.getTema_desarrollo() == null) {

		}
		// instancio la clase que contiene los metodos

		// creo un nuevo orador con los datos obtenidos del json string y cargados al
		// oradorRequest
		Orador orador = new Orador(oradorRequest.getNombre(), oradorRequest.getApellido(), oradorRequest.getEmail(),
				oradorRequest.getTema(), oradorRequest.getTema_desarrollo());
		// subo el nuevo orador a la db
		this.repository.save(orador);
		// respongo al front con el orador que cree
		res.getWriter().print(mapper.writeValueAsString(orador));
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Orador> oradores = this.repository.getAll();

		// mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
		resp.getWriter().print(super.mapper.writeValueAsString(oradores));
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		String json = super.toJson(req);

		OradorRequest oradorRequest = super.mapper.readValue(json, OradorRequest.class);

		Orador orador = this.repository.getById(Long.parseLong(id));
		orador.setApellido(oradorRequest.getApellido());
		orador.setNombre(oradorRequest.getNombre());
		orador.setEmail(oradorRequest.getEmail());
		orador.setTema(oradorRequest.getTema());
		orador.setTema_desarrollo(oradorRequest.getTema_desarrollo());

		this.repository.upDate(orador);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		this.repository.deleteById(Long.parseLong(id));
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
