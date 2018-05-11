package lato.nt1.model;

public class Pet {

	private int codigo;
	private String nome;
	private String tipo;
	private int idade;
	private double peso;
	private String tamanho;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	@Override
	public String toString() {
		return "{nome: \""+nome+"\", tipo: \""+tipo+"\", idade: "+idade+", peso: "+peso+", tamanho: \""+tamanho+"\"}";
	}

}
