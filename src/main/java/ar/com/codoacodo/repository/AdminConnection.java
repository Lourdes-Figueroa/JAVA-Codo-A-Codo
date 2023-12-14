package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdminConnection {

	public static Connection getConnection() {
		String username = "root";
		String password = "24805816";
		String port = "3306";
		String host = "127.0.0.1";
		String dbName = "integrador_cac";
		String diverName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?allowPublicKeyRetrieval=true&serverTimeZone=UTCuseSSL=false";
		try {
			Class.forName(diverName);
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (Exception e) {
			throw new IllegalArgumentException("no se pudo conectar a la db:" + dbUrl);
		}
	};

}
