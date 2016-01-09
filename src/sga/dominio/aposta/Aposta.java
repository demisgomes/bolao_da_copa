package sga.dominio.aposta;

import sga.dominio.copa.Time;
import sga.persistencia.ApostadorDAO;

/**
 * Clase Aposta, onde mora a lógica de cada aposta que o apostaor faz
 * <br>
 * Cada aposta será relacionada a um apostador
 *
 */
public class Aposta {
	public int golsTime1;
	public int golsTime2;
	public Time time1;
	public Time time2;
	//pontos se acertou apnas o resultado
	private int ptsResultado=10;
	//pontos se acertou o placar
	private int ptsPlacar=25;
	private Apostador apostador=new Apostador();
	private boolean apostaRealizada;
	ApostadorDAO apostadorDAO=new ApostadorDAO();
	
	//verifica se a aposta foi realizada(nenhuma utilidade a princpio´)
	public boolean isApostaRealizada() {
		return apostaRealizada;
	}

	public void setApostaRealizada(boolean apostaRealizada) {
		this.apostaRealizada = apostaRealizada;
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

	public Apostador getApostador() {
		return apostador;
	}

	public void setApostador(Apostador apostador) {
		this.apostador = apostador;
	}
	

	public int getPtsResultado() {
		return ptsResultado;
	}

	public void setPtsResultado(int ptsResultado) {
		this.ptsResultado = ptsResultado;
	}

	public int getPtsPlacar() {
		return ptsPlacar;
	}

	public void setPtsPlacar(int ptsPlacar) {
		this.ptsPlacar = ptsPlacar;
	}
	
	/***
	 * Compara os parâmetros dos gols apostados com o do resultado e atualiza as informações do apostador.
	 * @param g1 (int)
	 * @param g2 (int)
	 * @param res1 (int)
	 * @param res2 (int)
	 * @param apostador (Apostador)
	 * @param idJogo (int)
	 */
	
	//passa os gols da aposta(g1 e g2) e os gols do resultado(res 1 e res 2)
	public void gerarPontuacao(int g1, int g2, int res1, int res2, Apostador apostador,int idJogo ){
		int idApostador = apostadorDAO.getIdApostador(apostador.getNome());
		// se os gols do time 1 e os gols do time 2 forem iguais aos do resultado, o apostador
		// acertou o placar e irá inserir no banco na pontuaçao daquele apostador o valor de pontuação 
		// para placar.
		
		if((g1==res1)&&(g2==res2)){
			apostador.addPontos(this.getPtsPlacar());
			//insere no banco através deste método
			apostadorDAO.setPontuacaoAposta(this.getPtsPlacar(), idJogo, idApostador);
			
		}
		
		else{
			// se os gols do time 1 forem maiores que os gols do time 2 e no reultado também o apostador
			// acertou o resultado e irá inserir no banco na pontuaçao daquele apostador o valor de pontuação 
			// para resultado.
			if(g1>g2){
				if(res1>res2){
					apostador.addPontos(this.getPtsResultado());
					apostadorDAO.setPontuacaoAposta(this.getPtsResultado(), idJogo, idApostador);
				}
			}
			else{
				// se os gols do time 1 forem menores que os gols do time 2 e no reultado também o apostador
				// acertou o resultado e irá inserir no banco na pontuaçao daquele apostador o valor de pontuação 
				// para resultado.
				if(g1<g2){
					if(res1<res2){
						apostador.addPontos(this.getPtsResultado());
						apostadorDAO.setPontuacaoAposta(this.getPtsResultado(), idJogo, idApostador);
					}
					
				}
				
			}
			// se os gols do time 1 forem iguais que os gols do time 2 e no reultado também o apostador
			// acertou o resultado e irá inserir no banco na pontuaçao daquele apostador o valor de pontuação 
			// para resultado.
		if(g1==g2){
				if(res1==res2){
					apostador.addPontos(this.getPtsResultado());
					apostadorDAO.setPontuacaoAposta(this.getPtsResultado(), idJogo, idApostador);
			}
		
		}
		
		
		
		}
	}
	//Construtores
	public Aposta(Apostador apostador, Time time1, Time time2){
		this.setApostador(apostador);
		this.setTime1(time1);
		this.setTime2(time2);
	}
	public Aposta(Apostador apostador){
		this.setApostador(apostador);
	}
}
