package lato.nt1.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lato.nt1.model.Pet;

public class PetDAO {
	
	private Connection connection;
	
	public void openConnection() throws Exception {
		connection = ConnectionFactory.getConnection();
	}
	
	public void closeConnection() {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void salvar(Pet pet) throws Exception {
		String sql = "insert into pet (nome, tipo, idade, peso, tamanho) values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, pet.getNome());
			stmt.setString(2, pet.getTipo());
			stmt.setInt(3, pet.getIdade());
			stmt.setDouble(4, pet.getPeso());
			stmt.setString(5, pet.getTamanho());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao acessar o banco de dados.");
		}
	}
	
	public void deletar(int codigo) throws Exception {
		String sql = "delete from pet where codigo = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<Pet> retornarTodosRegistros() throws Exception{
		try {
			List<Pet> pets = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pet");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Pet pet = new Pet();
				pet.setCodigo(rs.getInt("codigo"));
				pet.setNome(rs.getString("nome"));
				pet.setTipo(rs.getString("tipo"));
				pet.setIdade(rs.getInt("idade"));
				pet.setPeso(rs.getDouble("peso"));
				pet.setTamanho(rs.getString("tamanho"));
				pets.add(pet);
			}
			rs.close();
			stmt.close();
			return pets;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao acessar o banco de dados.");
		}
	}

}
