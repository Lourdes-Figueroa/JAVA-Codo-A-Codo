package ar.com.codoacodo.controllers;

public class OradorRequest {
	String nombre;
	String apellido;
	String email;
	String tema;
	String tema_desarrollo;

	public OradorRequest() {

	}

	public OradorRequest(String nombre, String apellido, String email, String tema, String tema_desarrollo) {
		// super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.tema = tema;
		this.tema = tema_desarrollo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTema_desarrollo() {
		return tema_desarrollo;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public String getTema() {
		return tema;
	}

	@Override
	public String toString() {
		return "OradorRequest [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", tema=" + tema
				+ "]";
	}

}
