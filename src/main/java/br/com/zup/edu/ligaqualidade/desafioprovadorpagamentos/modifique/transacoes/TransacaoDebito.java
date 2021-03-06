package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

public class TransacaoDebito implements Transacao {
	public Double deconto = 0.03d;
	public String status = "pago";
	public int diaTransacao = 0;
	
	@Override
	public Double getDesconto() {
		return deconto;
	}
	
	@Override
	public int getDiaTransacao() {
		return diaTransacao;
	}
	
	@Override
	public String getStatus() {
		return status;
	}
}
