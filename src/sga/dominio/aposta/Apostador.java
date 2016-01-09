package sga.dominio.aposta;

/**
 * Classe apostador, onde h� os gets e sets do apostadores.
 * <br>
 * H� tamb�m uma lista de apostas de 64 posi��es, que � um array
 *
 *
 */
public class Apostador {
	public Aposta[] listaApostas=new Aposta[64];
	private int pontos=0;
	public int ranking;
	private String nome;
	//gets e sets
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void addPontos(int pontos) {
		this.pontos+= pontos;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public Aposta[] getListaApostas() {
		return listaApostas;
	}
	//m�todo necess�rio pois a lista � um array com posi��es determinadas
		//preenchidas com null, portanto esse m�todo ir� apenas contar as posi��es
		// do array que n�o s�o nulas.
	/**
	*
	 * M�todo que conta as apostas feitas pelo apostador
	 * <br>
	 * Se um objeto da lista n�o for nulo, quer dizer que a aposta ocorreu. Ent�o ele incrementa a vari�vel count.
	 * @return
	 */
	public int conteApostas(){
		int count=0;
		for (Aposta aposta: this.getListaApostas()){
			if(aposta!=null){
				count++;
			}
		}
		return count;
	}
	
	
	//Construtores
	public Apostador(){}
	
	public Apostador(String nome, Aposta aposta){
		
	}
	
	//Se o Apostador receber o nome como par�metro, ele ser� "setado" l� no nome do apostador
	public Apostador(String nome){
		this.setNome(nome);
	}

}
