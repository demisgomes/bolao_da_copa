package sga.dominio.copa;

import java.util.ArrayList;
import java.util.Collections;

public class GrupoCopa {
	private Time listaTimes[] = new Time[4];
	private Time classificacao[] =new Time[4];
	int indice=0;
	public Jogo listaJogos[]=new Jogo[6];
	
	public void inserirTime(Time time){
		
		listaTimes[indice]=time;
		indice++;
	}
	public Time[] getListaTimes() {
		return listaTimes;
	}
	public void setListaTimes(Time[] listaTimes) {
		this.listaTimes = listaTimes;
	}
	public Time[] getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Time[] classificacao) {
		this.classificacao = classificacao;
	}
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	
	public Jogo[] getListaJogos() {
		return listaJogos;
	}
	public void setListaJogos(Jogo[] listaJogos) {
		this.listaJogos = listaJogos;
	}
	
	/**
	 * Gera a classificação dos times que estão na lista de times do sistema e os coloca na lista de classificação.
	 */
	public void gerarClassificacaoGrupo(){
		//incrementa a variável auxiliar do time
		int incrementeAuxPosGrupo;
		for(int i=0;i<4;i++){
			incrementeAuxPosGrupo=0;
			//Se o time tiver mais pontos que os demais, essa variável torna-se cada vez maior
			for(int j=0;j<4;j++){
				if(this.getListaTimes()[i].getInfoTime().getPontos()>this.getListaTimes()[j].getInfoTime().getPontos()){
					incrementeAuxPosGrupo+=100;
					this.getListaTimes()[i].setAuxPosGrupo(incrementeAuxPosGrupo);
				}
					
			}
		}
			
		
			for(int l=0;l<4;l++){
				
				for(int m=0;m<4;m++){
					//verifica o saldo de gols
					// se os times tiverem empatados em pontos
					//se um time tiver menos saldo de gols do que o outro
					if((this.getListaTimes()[l].getInfoTime().getPontos()==this.getListaTimes()[m].getInfoTime().getPontos())&&(l!=m)){
						if(this.getListaTimes()[l].getInfoTime().getSaldoDeGols()>this.getListaTimes()[m].getInfoTime().getSaldoDeGols()){
							this.getListaTimes()[m].setAuxPosGrupo(this.getListaTimes()[m].getAuxPosGrupo()-10);
						}
						else if(this.getListaTimes()[l].getInfoTime().getSaldoDeGols()<this.getListaTimes()[m].getInfoTime().getSaldoDeGols()){
							this.getListaTimes()[l].setAuxPosGrupo(this.getListaTimes()[l].getAuxPosGrupo()-10);			
						}
					}
						
				}
			}
			ArrayList<Integer> lista=new ArrayList<Integer>();

			ArrayList<Time> listaTimesCl=new ArrayList<Time>();
			
			lista.add(this.getListaTimes()[0].getAuxPosGrupo());
			lista.add(this.getListaTimes()[1].getAuxPosGrupo());
			lista.add(this.getListaTimes()[2].getAuxPosGrupo());
			lista.add(this.getListaTimes()[3].getAuxPosGrupo());
			Collections.sort(lista);
			for(int k=0;k<4;k++){
				for (int mn=0;mn<4;mn++){
					if((lista.get(k)==this.getListaTimes()[mn].getAuxPosGrupo())&& (!listaTimesCl.contains(this.getListaTimes()[mn]))){
						listaTimesCl.add(this.getListaTimes()[mn]);
						break;
					}
				}
			}
			
			Collections.reverse(listaTimesCl);
			for (int y=0;y<4;y++){
			
				this.classificacao[y]=listaTimesCl.get(y);
			
			}
					
		
		}
	public GrupoCopa(Time time1, Time time2, Time time3, Time time4){
		//Quando o GrupoCopa recebe os quatro times como parâmetro, ele insere-os no grupo
		//e gera automaticamente os jogos

		this.inserirTime(time1);
		this.inserirTime(time2);
		this.inserirTime(time3);
		this.inserirTime(time4);
		
		//time posição 1 x time posição 2
		Jogo jogo1=new Jogo(this.getListaTimes()[0], this.getListaTimes()[1]);
		//time posição 3 x time posição 4
		Jogo jogo2=new Jogo(this.getListaTimes()[2], this.getListaTimes()[3]);
		//...
		Jogo jogo3=new Jogo(this.getListaTimes()[0], this.getListaTimes()[2]);
		//... e assim por diante
		Jogo jogo4=new Jogo(this.getListaTimes()[3], this.getListaTimes()[1]);
		Jogo jogo5=new Jogo(this.getListaTimes()[1], this.getListaTimes()[2]);
		Jogo jogo6=new Jogo(this.getListaTimes()[3], this.getListaTimes()[0]);
		
		//depois add na lista de jogos
		listaJogos[0]=jogo1;
		listaJogos[1]=jogo2;
		listaJogos[2]=jogo3;
		listaJogos[3]=jogo4;
		listaJogos[4]=jogo5;
		listaJogos[5]=jogo6;
	}
}
