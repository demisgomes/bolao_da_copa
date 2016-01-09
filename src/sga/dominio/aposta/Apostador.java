package sga.dominio.aposta;

/**
 * Classe apostador, onde há os gets e sets do apostadores.
 * <br>
 * Há também uma lista de apostas de 64 posições, que é um array
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
	//método necessário pois a lista é um array com posições determinadas
		//preenchidas com null, portanto esse método irá apenas contar as posições
		// do array que não são nulas.
	/**
	*
	 * Método que conta as apostas feitas pelo apostador
	 * <br>
	 * Se um objeto da lista não for nulo, quer dizer que a aposta ocorreu. Então ele incrementa a variável count.
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
	
	//Se o Apostador receber o nome como parâmetro, ele será "setado" lá no nome do apostador
	public Apostador(String nome){
		this.setNome(nome);
	}

}
