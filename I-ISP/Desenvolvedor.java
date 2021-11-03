public class Desenvolvedor extends FuncionarioV1{

	private double salario;
	
	public Desenvolvedor(double salario) {
		this.salario = salario;
	}
	
	@Override
	public double getSalario() {
		return this.salario;
	}

	@Override
	public double getComissao() {
		return 0d;
	}

}