package lato.nt1.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// jdbc:mysql://ip/nome_do_banco
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/lato_pet?autoReconnect=true&useSSL=false", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar conexão com o banco de dados.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Driver de conexão não encontrado.");
		}
	}

}
