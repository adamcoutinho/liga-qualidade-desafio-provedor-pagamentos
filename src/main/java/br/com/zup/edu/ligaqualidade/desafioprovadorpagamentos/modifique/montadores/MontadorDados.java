package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.montadores;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

public class MontadorDados {
	private static MontadorDados instancia;
	
	private MontadorDados() {
    }

    public static MontadorDados getInstance() {
        if (instancia == null) {
        	instancia = new MontadorDados();
        }
        return instancia;
    }
    
    public List<DadosRecebimentoAdiantado> montarDadosRecebimentoAdiantado(List<String> infoAdiantamentos) {
    	if (infoAdiantamentos == null || infoAdiantamentos.isEmpty()) return Collections.emptyList();
    	return infoAdiantamentos.parallelStream().map(infoAdiantamento -> {
    		String[] data = infoAdiantamento.split(",");
    		return montarDadosRecebimentoAdiantado(data[0], data[1]);
    	}).collect(Collectors.toList());
    }
    
    public List<DadosTransacao> montarDadosTransacao(List<String> infoTransacoes) {
    	if (infoTransacoes == null || infoTransacoes.isEmpty()) return Collections.emptyList();
    	return infoTransacoes.parallelStream().map(infoTransacao -> {
    		String[] data = infoTransacao.split(",");
    		return montarDadosTransacao(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
    	}).collect(Collectors.toList());
    }
    
    private DadosTransacao montarDadosTransacao(String valor, String metodo,
    		String numero, String nome, String validade, String cvv, String id) {
    	return new DadosTransacao(
    			new BigDecimal(valor),
    			MetodoPagamento.valueOf(metodo),
    			numero,
    			nome,
    			LocalDate.parse(validade, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    			Integer.valueOf(cvv),
    			Integer.valueOf(id));
    }
    
    private DadosRecebimentoAdiantado montarDadosRecebimentoAdiantado(String idTransacao, String valorAdiantamento) {
    	return new DadosRecebimentoAdiantado(
    			Integer.valueOf(idTransacao),
    			new BigDecimal(valorAdiantamento));
    }
}
