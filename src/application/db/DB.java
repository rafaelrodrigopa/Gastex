package application.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	//Atributo de conexão
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			
			try {
				// Pega as propriedades do banco de dados
				Properties props = loadProperties();
				
				//Pega a variavel dburl do arquivo db.properties
				String url = props.getProperty("dburl");
				
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
			
		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		//Carrega o arquivo de configuração com o banco de dados
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}
