public class VendedorV1 extends FuncionarioV1{

	private double salario;
	private int totalVendas;
	
	public VendedorV1(double salario, int totalVendas) {
		this.salario = salario;
		this.totalVendas = totalVendas;
	}
	
	@Override
	public double getSalario() {
		return this.salario + this.getComissao();
	}

	@Override
	public double getComissao() {
		return this.totalVendas * 0.2;
	}

}