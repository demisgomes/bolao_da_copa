package sga.dominio.copa;

import sga.persistencia.TimesDAO;

public class Jogo {
	private Time time1;
	private Time time2;
	private int golsTime1;
	private int golsTime2;
	private int numeroJogo;
	private TimesDAO tDao = new TimesDAO();
	
	public int getNumeroJogo() {
		return numeroJogo;
	}

	public void setNumeroJogo(int numeroJogo) {
		this.numeroJogo = numeroJogo;
	}

	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public int getGolsTime1() {
		return golsTime1;
	}

	public void setGolsTime1(int golsTime1) {
		this.golsTime1 = golsTime1;
	}

	public int getGolsTime2() {
		return golsTime2;
	}

	public void setGolsTime2(int golsTime2) {
		this.golsTime2 = golsTime2;
	}
	
	/**
	 * De acordo com as informações dos gols dos times que estão participando desse objeto jogo
	 * gera o resultado e atualiza nos times as suas informações.
	 */
	public void gerarResultado(){
		//se o time 1 venceu o time 1 soma uma vitória e o time 2 soma uma derrota
		if(this.getGolsTime1()>this.getGolsTime2()){
			
			this.time1.getInfoTime().setVitorias();
			this.getTime2().getInfoTime().setDerrotas();
			
		}
		//se o time 2 venceu o time 2 soma uma vitória e o time 1 soma uma derrota
		else if(this.getGolsTime1()<this.getGolsTime2()){

			this.getTime1().getInfoTime().setDerrotas();
			this.getTime2().getInfoTime().setVitorias();
			
		}
		// se os times empataram eles recebem mais 1 empate
		else{
			this.getTime1().getInfoTime().setEmpates();
			this.getTime2().getInfoTime().setEmpates();
			
		}
	// e no final insere o jogo, os gols pro e contra e gera o saldo daquele jogo para o time 
	// independente do resultado.
		this.time1.getInfoTime().setJogo(this);
		this.time1.getInfoTime().setGolsPro(this.getGolsTime1());
		this.time1.getInfoTime().setGolsContra(this.getGolsTime2());
		this.time1.getInfoTime().gerarSaldoDeGols();
		this.time2.getInfoTime().setJogo(this);
		this.time2.getInfoTime().setGolsPro(this.getGolsTime2());
		this.time2.getInfoTime().setGolsContra(this.getGolsTime1());
		this.time2.getInfoTime().gerarSaldoDeGols();
		
		//salva essas informações no banco de dados.
		tDao.inserirTimes(time1);
		tDao.inserirTimes(time2);
	}
	

	public void atualizarPontuacao(){
		
	}
	
	public void atualizarInfoTime(){
	}
	
	public Jogo(Time time1, Time time2){
		this.setTime1(time1);
		this.setTime2(time2);
	}

	public Jogo(){}
}
