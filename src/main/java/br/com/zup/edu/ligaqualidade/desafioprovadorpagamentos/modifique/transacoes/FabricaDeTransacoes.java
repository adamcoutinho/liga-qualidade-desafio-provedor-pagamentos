package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

public class FabricaDeTransacoes {
	private static FabricaDeTransacoes instancia;
	
	private FabricaDeTransacoes() {
    }

    public static FabricaDeTransacoes getInstance() {
        if (instancia == null) {
        	instancia = new FabricaDeTransacoes();
        }
        return instancia;
    }
    
    public Transacao getTransacaoInstance(MetodoPagamento metodo) {
    	return "CREDITO".equals(metodo.name()) ?
    			new TransacaoCredito() : new TransacaoDebito();
    }
}
