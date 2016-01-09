package sga.dominio.copa;

import java.util.ArrayList;


public class InfoTime {
	private int vitorias;
	private int derrotas;
	private int empates;
	private int golsPro;
	private int golsContra;
	private int saldoDeGols;
	private int pontos=0;
	private ArrayList<Jogo> jogos;
	private int indiceJogos;
	
	public InfoTime(){
		this.indiceJogos = 0;
		this.jogos = new ArrayList<Jogo>();
	}
	
	public void setPontos(int pontos){
		this.pontos+=pontos;
	}
	
	public int getPontos(){
		return this.pontos;
	}
	
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias() {
		this.vitorias +=1;
		this.setPontos(3);
	}
	
	//overload do método de vitórias para inserir o número de vitórias no objeto 
		// e não só somar 1 ao número de vitórias.
		
		public void setVitorias(int vitorias){
			this.vitorias = vitorias;
		}
		public int getEmpates() {
			return empates;
		}
		public void setEmpates() {
			this.empates+=1;
			this.setPontos(1);
		}

		//overload do método de empates para inserir o número de empatess no objeto 
		// e não só somar 1 ao número de empates.
		public void setEmpates(int empates){
			this.empates = empates;
		}
		public int getDerrotas() {
			return derrotas;
		}
		public void setDerrotas() {
			this.derrotas+=1;
		}
		

		//overload do método de derrotas para inserir o número de derrotas no objeto 
		// e não só somar 1 ao número de derrotas.
		
		public void setDerrotas(int derrotas){
			this.derrotas = derrotas;
		}
		public int getGolsPro() {
			return golsPro;
		}
		public void setGolsPro(int golsPro) {
			this.golsPro += golsPro;
		}
		public int getGolsContra() {
			return golsContra;
		}
		public void setGolsContra(int golsContra) {
			this.golsContra += golsContra;
		}
		public int getSaldoDeGols() {
			return saldoDeGols;
		}
		/**
		 * Gera o saldo de gols do time de acordo com os atributos de gol pro e gol contra dele.
		 */
		public void gerarSaldoDeGols() {
			this.saldoDeGols = this.golsPro - this.golsContra;
		}
		
		public void setSaldo(int saldo){
			this.saldoDeGols = saldo;
		}
		public ArrayList<Jogo> getJogos() {
			return jogos;
		}
		
		public Jogo getJogo(int numeroJogo){
			return jogos.get(numeroJogo-1);
		}
		public void setJogo(Jogo jogo) {
			this.jogos.add(this.indiceJogos,jogo);//adiciona o jogo na posicao indice jogos
			this.indiceJogos+=1;//soma 1 a posicao.
			jogo.setNumeroJogo(indiceJogos);//informa ao jogo qual é o indice que ele está.
		}
}