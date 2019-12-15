package com.javacodegeeks.example.rest.listclientes;

import java.util.logging.Logger;
import java.sql.*;
import java.util.ResourceBundle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListclientesController {

	protected Logger logger = Logger.getLogger(ListclientesController.class
			.getName());

	@RequestMapping("/listclientes")
	public String dolistclientes() {

		String json = dataListclientes();
		return json;
	}
	//Esta función corresponde a la capa de datos
	private String dataListclientes() {
		String json = "";
		ResourceBundle mybundle = ResourceBundle.getBundle("listclientes-server");
		String jdbcurl = mybundle.getString("jdbcurl");
		String user = mybundle.getString("user");
		String password = mybundle.getString("password");

		String query = "SELECT  id, nombre, apellido, edad, fechanacimiento, CASE WHEN 75 - TIMESTAMPDIFF(YEAR,fechanacimiento,CURDATE()) < 75 THEN ADDDATE(CURDATE(),((75 - TIMESTAMPDIFF(YEAR,fechanacimiento,CURDATE())) * 365)) ELSE CURDATE() END fechamuerte_base75 from cliente order by nombre";
		try {
			Connection con = DriverManager.getConnection(jdbcurl, user, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			json = "{\"listclientes\":[";
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
