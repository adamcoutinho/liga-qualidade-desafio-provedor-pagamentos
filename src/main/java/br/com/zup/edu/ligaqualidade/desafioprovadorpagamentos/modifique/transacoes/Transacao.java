package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

public interface Transacao {
	public abstract Double getDesconto();
	public abstract int getDiaTransacao();
	public abstract String getStatus();
}
