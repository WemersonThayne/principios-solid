class FolhaDePagamentoV2{

    protected saldo;
    
    public double calcular(Remuneravel funcionario){
        this.saldo = funcionario.remuneracao();
    }

}