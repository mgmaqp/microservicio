package com.javacodegeeks.example.rest.kpideclientes;

import java.util.logging.Logger;
import java.sql.*;
import java.util.ResourceBundle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KpideclientesController {

	protected Logger logger = Logger.getLogger(KpideclientesController.class
			.getName());

	@RequestMapping("/kpideclientes")
	public String doKpideclientes() {

		String json = dataKpideclientes();

		return json;
	}

	private String dataKpideclientes() {
		String json = "";
		ResourceBundle mybundle = ResourceBundle.getBundle("kpideclientes-server");
		String jdbcurl = mybundle.getString("jdbcurl");
		String user = mybundle.getString("user");
		String password = mybundle.getString("password");

		String query = "SELECT id, nombre, apellido, edad, fechanacimiento, (SELECT avg(edad) FROM cliente) promedio, (SELECT sqrt(sum(  power( edad-(SELECT avg(edad) FROM cliente) , 2 ))/(SELECT count(1) FROM cliente)) FROM cliente) desviacionestandar FROM cliente order by nombre";
		
		try {
			Connection con = DriverManager.getConnection(jdbcurl, user, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			json = "{\"kpideclientes\":[";
			while (rs.next()) {
				json += "{";
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					json += "\"" + rsmd.getColumnName(i) + "\":\"" + columnValue + "\",";
				}
				json = json.substring(0, json.length() - 1);
				json += "},";
			}
			json = json.substring(0, json.length() - 1);
			rs.close();
			json += "]}";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
}
