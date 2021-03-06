package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

public class TransacaoCredito implements Transacao {
	public Double desconto = 0.05d;
	public int diaTransacao = 30;
	public String status = "aguardando_pagamento";
	
	@Override
	public Double getDesconto() {
		return desconto;
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
