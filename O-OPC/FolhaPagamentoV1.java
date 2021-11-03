class FolhaDePagamentoV1 {
    protected saldo;
    
    public void calcular(funcionario)
    {
        if ( funcionario instanceof ContratoClt ) {
            this.saldo = funcionario.salario();
        } else if (funcionario instanceof Estagio) {
            this.saldo = funcionario.bolsaAuxilio();
        }
    }

}

class ContratoClt {
    public double salario(){}
}

class ContratoPj{
    public double contrato(){}
}


class Estagio {
    public double bolsaAuxilio(){
    }
}
