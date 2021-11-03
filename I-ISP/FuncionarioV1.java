
public abstract class FuncionarioV1 {
	
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public abstract double getSalario();
	
	public abstract double getComissao();
}