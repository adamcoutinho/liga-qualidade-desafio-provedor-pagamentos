package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;

public class ManipuladorDeTransacoes {
	private static ManipuladorDeTransacoes instancia;
	
	private ManipuladorDeTransacoes() {
    }

    public static ManipuladorDeTransacoes getInstance() {
        if (instancia == null) {
        	instancia = new ManipuladorDeTransacoes();
        }
        return instancia;
    }
    
    public List<String[]> executarTransacoes(List<DadosTransacao> transacoes, List<DadosRecebimentoAdiantado> adiantamentos) {
    	return transacoes
    			.parallelStream()
    			.map(transacao -> montarTransacao(transacao, FabricaDeTransacoes.getInstance().getTransacaoInstance(transacao.metodo)))
    			.collect(Collectors.toList());
    }
    
	public String[] montarTransacao(DadosTransacao dadoTransacao, Transacao transacao) {
		return new String[] {
			transacao.getStatus(),
			String.valueOf(dadoTransacao.valor.doubleValue()),
			String.valueOf(aplicarDesconto(dadoTransacao.valor.doubleValue(), transacao)),
			LocalDate.now().plusDays(transacao.getDiaTransacao()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
		};	
	}
	
	private Double aplicarDesconto(Double valor, Transacao transacao) {
		return valor * (1d - transacao.getDesconto());
	}
}
