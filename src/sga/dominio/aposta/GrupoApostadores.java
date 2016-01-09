package sga.dominio.aposta;
import java.util.ArrayList;
import java.util.Collections;

public class GrupoApostadores {
	private static ArrayList<Apostador> listaApostadores=new ArrayList<Apostador>();
	private static ArrayList<Apostador> classificacao=new ArrayList<Apostador>();
	private static int numApostadores;
	
	public static int getNumApostadores() {
		return numApostadores;
	}
	public static void setNumApostadores(int numApostadores) {
		GrupoApostadores.numApostadores = numApostadores;
	}
	public static ArrayList<Apostador> getListaApostadores() {
		return listaApostadores;
	}
	public static void setListaApostadores(ArrayList<Apostador> listaApostadores) {
		GrupoApostadores.listaApostadores = listaApostadores;
	}
	public static ArrayList<Apostador> getClassificacao() {
		return classificacao;
	}
	
	//add na lista de classifica��o um determinado apostador
	//add na lista de classifica��o um determinado apostador
		/**
		 * Adiciona um apostador na listade classifica��o
		 * @param apostador (Apostador)
		 */
	public static void addInClassificacao(Apostador apostador) {
		GrupoApostadores.classificacao.add(apostador);
	}
	
	//add na Lista de Apostadores um apostador
	public static void addApostador(Apostador apostador) {
		GrupoApostadores.listaApostadores.add(apostador);
	}
	
	
	//m�todo que gera a classifica��o
	/**
	 * Compara os pontos dos apostadores da lista de apostadores e adiciona os apostadores na lista de classifica��o
	 * na ordem em que eles est�o classificados.
	 */
	public static void gerarClassificacaoApostadores(){
		//limpa a lista de classifica��o, para que n�o d� erro em ocasi�es posteriores
		GrupoApostadores.getClassificacao().clear();
		
		ArrayList<Integer> listaAux=new ArrayList<Integer>();
		//add os pontos em uma lista
		for(int i=0;i<GrupoApostadores.listaApostadores.size();i++){
		listaAux.add(GrupoApostadores.listaApostadores.get(i).getPontos());
		
		}
		 
		//ordena e depois deixa a lista em ordem decrescente
		Collections.sort(listaAux);
		Collections.reverse(listaAux);
		
		//se o apostador tiver o n�mero de pontos que est� nma lista e n�o estiver na lista de classifica��o, ele � adicionado na lista de classifica��o
			
		for(int k=0;k<listaAux.size();k++){
			for(Apostador apostador:GrupoApostadores.listaApostadores){
		
				if((apostador.getPontos()==listaAux.get(k))&&(!GrupoApostadores.getClassificacao().contains(apostador))){
					GrupoApostadores.addInClassificacao(apostador);
					break;
				}
			}
		}	
	}
	
	public static void InserirPosicaoJogador(){
		for (int i = 0; i <GrupoApostadores.getClassificacao().size(); i++) {
			GrupoApostadores.getClassificacao().get(i).setRanking(i+1);
		}
	}
		

}
