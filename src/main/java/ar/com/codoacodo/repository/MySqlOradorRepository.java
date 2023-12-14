package ar.com.codoacodo.repository;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.interfaces.IOrador;

//executeUpdate()---> EJECUTA insert//update/deleted/merge --->DEVUELVE EN NUMERO DE FILAS AFRCTADOS.
//executeQuery()----> EJECUTA select ---> DEVUELVE UN OBJ RESULT. 
public class MySqlOradorRepository implements IOrador {

	@Override
	public void save(Orador orador) {
		// genero la conexion

		// preparo sql injection preparate statement
		String sql = "insert into oradores(nombre,apellido,mail,tema, tema_desarrollo) values(?,?,?,?,?)";
		try (Connection conn = AdminConnection.getConnection();) {
			PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getEmail());
			statement.setString(4, orador.getTema());
			statement.setString(5, orador.getTema_desarrollo());
			/*
			 * statement.setDate(4,Date.valueOf(orador.getFechaAlta()));-----> si quiero
			 * enviar el date a la db, lo formateo al tipo de dato requerido
			 */
			statement.executeUpdate();// insert//update/deleted
			ResultSet res = statement.getGeneratedKeys();
			if (res.next()) {
				Long id = res.getLong(1);// -->1 indica la columna de donde quiero obtener el dato, en este caso el id
				// Date fecha = res.getDate("fecha_alta");
				orador.setId(id);
				orador.setFechaAlta(LocalDateTime.now().toLocalDate());
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("no se pudo crear el orador", e); // TODO: handle exception
		}
	}

	public Orador getById(Long id) {
		// genero la conexion
		Orador orador = null;
		Connection conn = AdminConnection.getConnection();
		// preparo sql injection preparate statement
		String sql = "select id_orador, nombre,apellido,mail,tema,tema_desarrollo, fecha_alta from oradores where id_orador = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();
			if (res.next()) {
				Long dbId = res.getLong(1);// ------>| 1 indica la columna de donde quiero obtener
				String nombre = res.getString(2); // | el dato, en este caso el id
				String apellido = res.getString(3);
				String mail = res.getString(4);
				String tema = res.getString(5);
				String tema_desarrollo = res.getString(6);
				Date fecha_alta = res.getDate(7);
				orador = new Orador(dbId, nombre, apellido, mail, tema, tema_desarrollo, fecha_alta.toLocalDate());
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("no se pudo obtener el orador", e); // TODO: handle exception
		}
		return orador;
	}

	public ArrayList<Orador> getAll() {
		ArrayList<Orador> oradores = new ArrayList<>();
		Connection conn = AdminConnection.getConnection();

		String sql = "select * from oradores";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String mail = res.getString(4);
				String tema = res.getString(5);
				Date fecha_alta = res.getDate(6);
				String tema_desarrollo = res.getString(7);
				Orador orador = new Orador(dbId, nombre, apellido, mail, tema, tema_desarrollo,
						fecha_alta.toLocalDate());
				oradores.add(orador);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("no se pudo obtener el orador", e); // TODO: handle exception
		}
		return oradores;
	}

	public void deleteById(Long id) {

		Connection conn = AdminConnection.getConnection();

		String sql = "DELETE FROM oradores WHERE id_orador = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);

			int res = statement.executeUpdate();// insert//update/deleted
			if (res > 0) {
				System.out.println("SE borro el user id" + id);
			}
		} catch (Exception e) {

			System.out.println("no se pudo borrar user id" + id);
			throw new IllegalArgumentException("no se pudo borrrar el orador", e); // TODO: handle exception
		}

	}

	public Orador upDate(Orador orador) {
		Long id = orador.getId();

		Orador newOrador = null;
		Connection conn = AdminConnection.getConnection();
		String sql = "UPDATE oradores set nombre=?, apellido = ?, mail = ?, tema = ?, tema_desarrollo = ?  Where id_orador = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getEmail());
			statement.setString(4, orador.getTema());
			statement.setString(5, orador.getTema_desarrollo());
			statement.setLong(6, id);

			int res = statement.executeUpdate();

			if (res > 0) {
				System.out.println("orador" + id + "editado");
				newOrador = this.getById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("no se pudo editar el orador" + id);
		}
		return newOrador;
	}

}
