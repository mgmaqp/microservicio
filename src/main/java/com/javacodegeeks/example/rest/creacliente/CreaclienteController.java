package com.javacodegeeks.example.rest.creacliente;

import java.util.logging.Logger;
import java.sql.*;
import java.util.ResourceBundle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreaclienteController {

	protected Logger logger = Logger.getLogger(CreaclienteController.class
			.getName());

	@RequestMapping("/creacliente")
	public String doCreacliente(@RequestParam(defaultValue="") String nombre,
			@RequestParam(defaultValue="") String apellido, @RequestParam(defaultValue="") String edad, @RequestParam(defaultValue="") String fechanacimiento) {

		ResourceBundle mybundle = ResourceBundle.getBundle("creacliente-server");
		String jdbcurl = mybundle.getString("jdbcurl");
		String user = mybundle.getString("user");
		String password = mybundle.getString("password");
				
				String query = "INSERT INTO cliente(nombre, apellido, edad, fechanacimiento) values('"+nombre+"','"+apellido+"','"+edad+"','"+fechanacimiento+"')";
				try
				{
					Connection con = DriverManager.getConnection(jdbcurl, user, password);
					Statement st = con.createStatement();
					st.executeUpdate(query);

				}
				catch (SQLException e)
				{
					// do something appropriate with the exception, *at least*:
					e.printStackTrace();
				}

		return "{\"nombre\":\"" + nombre + "\", \"apellido\":\"" + apellido + "\", \"edad\":\"" + edad + "\", \"fechanacimiento\": \"" + fechanacimiento + "\"}";
	}
}
