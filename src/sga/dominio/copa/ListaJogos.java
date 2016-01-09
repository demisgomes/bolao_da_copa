package sga.dominio.copa;

import java.util.ArrayList;

public class ListaJogos {
private static Jogo ListaDeJogos[]=new Jogo[64];
private int indice=0;
public static ArrayList<Jogo> listaJogoGrupo=new ArrayList<Jogo>();
	
	public static Jogo[] getListaDeJogos(){
		return ListaDeJogos;
	}
	public static ArrayList<Jogo> getListaJogoGrupo() {
		return listaJogoGrupo;
	}
	public static void addJogoGrupo(Jogo jogo) {
		listaJogoGrupo.add(jogo);
	}
	public void addJogo(Jogo jogo){
		this.ListaDeJogos[indice]=jogo;
		indice++;
	}
	
	public static int conteJogos(){
		int count=0;
		for (Jogo jogo: ListaJogos.getListaDeJogos()){
			if(jogo!=null){
				count++;
			}
		}
		return count;
	}

}
