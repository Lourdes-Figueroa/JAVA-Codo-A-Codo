package ar.com.codoacodo.entity;

import java.time.LocalDate;

/*import java.util.ArrayList;*/

public class Orador {
	Long id;
	String nombre;
	String apellido;
	String email;
	String tema;
	String tema_desarrollo;
	LocalDate fechaAlta;

	public Orador(String nombre, String apellido, String email, String tema,
			String tema_desarrollo/* , LocalDate fechaAlta */) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.tema = tema;
		this.tema_desarrollo = tema_desarrollo;
		/* this.fechaAlta = fechaAlta; */
	}

	public Orador(Long id, String nombre, String apellido, String email, String tema, String tema_desarrollo,
			LocalDate fechaAlta) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.tema = tema;
		this.tema_desarrollo = tema_desarrollo;
		this.fechaAlta = fechaAlta;
	}

	/*
	 * public static ArrayList<Orador> get(ArrayList<Orador> oradores) {
	 * 
	 * for (Orador orador : oradores) { System.out.println(orador); } return
	 * oradores;
	 * 
	 * }
	 */

	public String getTema_desarrollo() {
		return tema_desarrollo;
	}

	public void setTema_desarrollo(String tema_desarrollo) {
		this.tema_desarrollo = tema_desarrollo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", tema="
				+ tema + ", tema_desarrollo=" + tema_desarrollo + ", fechaAlta=" + fechaAlta + "]";
	}

}
