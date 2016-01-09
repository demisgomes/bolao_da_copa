package sga.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sga.dominio.aposta.Apostador;
import sga.dominio.copa.ListaJogos;
import sga.gui.panels.PanelApostas;
import sga.gui.panels.PanelResultados;
import sga.persistencia.ApostadorDAO;

public class Linha {

	private ApostadorDAO apDao=new ApostadorDAO();
	private JLabel labelTime1; //LABEL TIME1
	private JTextField placarTime1; //TEXTFIELD PLACAR TIME1
	private JLabel placarResultado1;//LABEL RESULTADO PLACAR1
	private JLabel versus; //LABEL X
	private JLabel placarResultado2;//LABEL RESULTADO PLACAR2
	private JTextField placarTime2;//TEXTFIELD PLACAR TIME2
	private JLabel labelTime2;//LABEL NOME TIME2
	private JLabel qtdApostas;//LABEL QTD APOSTAS DO JOGO
	private JLabel pontuacao;//LABEL PONTUACAO JOGO COM CORES
	
	public JLabel getLabelTime1() {
		return labelTime1;
	}
	public void setLabelTime1(JLabel labelTime1) {
		this.labelTime1 = labelTime1;
	}
	public JTextField getPlacarTime1() {
		return placarTime1;
	}
	public void setPlacarTime1(JTextField placarTime1) {
		this.placarTime1 = placarTime1;
	}
	public JLabel getPlacarResultado1() {
		return placarResultado1;
	}
	public void setPlacarResultado1(JLabel placarResultado1) {
		this.placarResultado1 = placarResultado1;
	}
	public JLabel getVersus() {
		return versus;
	}
	public void setVersus(JLabel versus) {
		this.versus = versus;
	}
	public JLabel getPlacarResultado2() {
		return placarResultado2;
	}
	public void setPlacarResultado2(JLabel placarResultado2) {
		this.placarResultado2 = placarResultado2;
	}
	public JTextField getPlacarTime2() {
		return placarTime2;
	}
	public void setPlacarTime2(JTextField placarTime2) {
		this.placarTime2 = placarTime2;
	}
	public JLabel getLabelTime2() {
		return labelTime2;
	}
	public void setLabelTime2(JLabel labelTime2) {
		this.labelTime2 = labelTime2;
	}
	public JLabel getQtdApostas() {
		return qtdApostas;
	}
	public void setQtdApostas(JLabel qtdApostas) {
		this.qtdApostas = qtdApostas;
	}
	public JLabel getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(JLabel pontuacao) {
		this.pontuacao = pontuacao;
	}
	
					
					
	public void criar(String nomeTime1, String nomeTime2, JPanel panel,int id_jogo, int posicaoy, Apostador apostador, int pontos){
		//o método cria a linha através dos parâmetros
		//o último parâmetro é a posição y
		//é apenas lá onde os valores são mudados de linha a linha
		
		//NOME DO TIME 1
		this.labelTime1 = new JLabel(nomeTime1);
		this.labelTime1.setBounds(10, 67+posicaoy, 90, 15);
		panel.add(labelTime1);
		//this.add(this.labelTime1);
		
		//PLACAR TIME 1
		
		this.placarTime1 = new JTextField();
		this.placarTime1.setBounds(105, 65+posicaoy, 20, 20);
		panel.add(placarTime1);
		PanelApostas fr = new PanelApostas();
		String labelplacar1="";
		//se a tela que estiver sendo executado for a tela de apostas, mostramos o resultado do jogo, caso ele já aconteceu
		//se for a tela de resultados, ele não mostra nada (labelplacar="");
		if (panel.getClass().equals(fr.getClass())){
			//LABEL RESULTADO TIME1
			
			if(ListaJogos.getListaDeJogos()[id_jogo]!=null){
				labelplacar1=Integer.toString(ListaJogos.getListaDeJogos()[id_jogo].getGolsTime1());
			}
			else{
				labelplacar1="";
			}
			
		}
		this.placarResultado1= new JLabel(labelplacar1);
		this.placarResultado1.setBounds(135, 68+posicaoy, 20, 14);
		panel.add(placarResultado1);
		
		this.versus = new JLabel("X");
		this.versus.setBounds(153, 68+posicaoy, 12, 14);
		panel.add(versus);
		
		//LABEL RESULTADO TIME2
		String labelplacar2="";
		if (panel.getClass().equals(fr.getClass())){
			
			if(ListaJogos.getListaDeJogos()[id_jogo]!=null){
				labelplacar2=Integer.toString(ListaJogos.getListaDeJogos()[id_jogo].getGolsTime2());
			}
			else{
				labelplacar2="";
			}
			
		}
		this.placarResultado2 = new JLabel(labelplacar2);
		this.placarResultado2.setBounds(170, 68+posicaoy, 20, 14);
		panel.add(placarResultado2);
		
		//PLACAR TIME 2
		this.placarTime2 = new JTextField();
		this.placarTime2.setBounds(190, 65+posicaoy, 20, 20);
		panel.add(placarTime2);
		
		//NOME TIME 2
		this.labelTime2 = new JLabel(nomeTime2);
		this.labelTime2.setBounds(216, 67+posicaoy, 90, 15);
		panel.add(labelTime2);
		
		//LABEL APOSTAS NO JOGO
		//pega do banco a qtd de apostas já realizadas no jogo em questão
		this.qtdApostas = new JLabel(Integer.toString(apDao.getQtdApostas(id_jogo)));
		this.qtdApostas.setBounds(334, 65+posicaoy, 32, 14);
		panel.add(qtdApostas);
		
		//LABEL PONTUACAO
		PanelResultados frr = new PanelResultados();
		//se for a tela de resultados
		//tira os zeros da coluna pontuacao
		if(panel.getClass().equals(frr.getClass())){
			this.pontuacao=new JLabel("");
			this.pontuacao.setBounds(420, 65+posicaoy, 46, 14);
			panel.add(pontuacao);
		}
		else{
			this.pontuacao = new JLabel(Integer.toString(pontos));
			this.pontuacao.setBounds(420, 65+posicaoy, 46, 14);
			panel.add(pontuacao);
		}
		
		//faz o gettext em pontuacao
		//se for 0, vermelho
		//10, verde
		//25 azul
		if(pontuacao.getText().equals("0")){
			placarResultado1.setForeground(Color.red);
			placarResultado2.setForeground(Color.red);
		}
		else if(pontuacao.getText().equals("10")){
			placarResultado1.setForeground(Color.green);
			placarResultado2.setForeground(Color.green);
		}
		else if(pontuacao.getText().equals("25")){
			placarResultado1.setForeground(Color.blue);
			placarResultado2.setForeground(Color.blue);
		}
		
		//se a aposta já foi feita, colocamos o resultado do jogo nos placares do apostador
		if(apostador.getListaApostas()[id_jogo]!=null){
			placarTime1.setText(Integer.toString(apostador.getListaApostas()[id_jogo].getGolsTime1()));
			placarTime2.setText(Integer.toString(apostador.getListaApostas()[id_jogo].getGolsTime2()));
		}
		
	}
	/**
	 * Método que desativa os textFields da linha caso o jogo já tenha ocorrido
	 * <br>
	 * 
	 * @param linha
	 * @param id_jogo
	 * @param apostador
	 * @param panel
	 */
	public void desativeTextFields(Linha linha, int id_jogo, Apostador apostador, JPanel panel){
		if(ListaJogos.getListaDeJogos()[id_jogo]!=(null)){
			linha.placarTime1.setEditable(false);
			linha.placarTime2.setEditable(false);
			//procura na lista de jogos
			//se o jogo já ocorreu, desativa os textFields correspondentes 
			PanelResultados fr = new PanelResultados();
			if(panel.getClass().equals(fr.getClass())){
				//se o panel especificado for o PanelResultados, ele põe o resultado do jogo daquele id correspondente
				linha.placarTime1.setText(Integer.toString(ListaJogos.getListaDeJogos()[id_jogo].getGolsTime1()));
				linha.placarTime2.setText(Integer.toString(ListaJogos.getListaDeJogos()[id_jogo].getGolsTime2()));

			}
			else{
				//se não, passa as apostas referentes a este jogo que o apostador fez
				if(apostador.getListaApostas()[id_jogo]!=null){
					linha.placarTime1.setText(Integer.toString(apostador.getListaApostas()[id_jogo].getGolsTime1()));
					linha.placarTime2.setText(Integer.toString(apostador.getListaApostas()[id_jogo].getGolsTime2()));
				
				}
				else{
					//se o jogo não aconteceu, passamos uma string vazia nos placares do jogo.
					linha.placarTime1.setText("");
					linha.placarTime2.setText("");
							
				}
			}
		}
	}
	public void criar(String nomeTime1, String nomeTime2, JFrame frame,int id_jogo, int posicaoy, Apostador apostador, int pontos){
		//o método cria a linha através dos parâmetros
		//o último parâmetro é a posição y
		//é apenas lá onde os valores são mudados de linha a linha
		
		//NOME DO TIME 1
		this.labelTime1 = new JLabel(nomeTime1);
		this.labelTime1.setBounds(10, 67+posicaoy, 90, 15);
		frame.add(labelTime1);
		//this.add(this.labelTime1);
		
		//PLACAR TIME 1
		
		this.placarTime1 = new JTextField();
		this.placarTime1.setBounds(105, 65+posicaoy, 20, 20);
		frame.add(placarTime1);
		PanelApostas fr = new PanelApostas();
		String labelplacar1="";
		//se a tela que estiver sendo executado for a tela de apostas, mostramos o resultado do jogo, caso ele já aconteceu
		//se for a tela de resultados, ele não mostra nada (labelplacar="");
		if (frame.getClass().equals(fr.getClass())){
			//LABEL RESULTADO TIME1
			
			if(ListaJogos.getListaDeJogos()[id_jogo]!=null){
				labelplacar1=Integer.toString(ListaJogos.getListaDeJogos()[id_jogo].getGolsTime1());
			}
			else{
				labelplacar1="";
			}
			
		}
		this.placarResultado1= new JLabel(labelplacar1);
		this.placarResultado1.setBounds(135, 68+posicaoy, 20, 14);
		frame.add(placarResultado1);
		
		this.versus = new JLabel("X");
		this.versus.setBounds(153, 68+posicaoy, 12, 14);
		frame.add(versus);
		
		//LABEL RESULTADO TIME2
		String labelplacar2="";
		if (frame.getClass().equals(fr.getClass())){
			
			if(ListaJogos.getListaDeJogos()[id_jogo]!=null){
				labelplacar2=Integer.toString(ListaJogos.getListaDeJogos()[id_jogo].getGolsTime2());
			}
			else{
				labelplacar2="";
			}
			
		}
		this.placarResultado2 = new JLabel(labelplacar2);
		this.placarResultado2.setBounds(170, 68+posicaoy, 20, 14);
		frame.add(placarResultado2);
		
		//PLACAR TIME 2
		this.placarTime2 = new JTextField();
		this.placarTime2.setBounds(190, 65+posicaoy, 20, 20);
		frame.add(placarTime2);
		
		//NOME TIME 2
		this.labelTime2 = new JLabel(nomeTime2);
		this.labelTime2.setBounds(216, 67+posicaoy, 90, 15);
		frame.add(labelTime2);
		
		//LABEL APOSTAS NO JOGO
		//pega do banco a qtd de apostas já realizadas no jogo em questão
		this.qtdApostas = new JLabel(Integer.toString(apDao.getQtdApostas(id_jogo)));
		this.qtdApostas.setBounds(334, 65+posicaoy, 32, 14);
		frame.add(qtdApostas);
		
		//LABEL PONTUACAO
		this.pontuacao = new JLabel(Integer.toString(pontos));
		this.pontuacao.setBounds(394, 65+posicaoy, 46, 14);
		frame.add(pontuacao);
		
		//faz o gettext em pontuacao
		//se for 0, vermelho
		//10, verde
		//25 azul
		if(pontuacao.getText().equals("0")){
			placarResultado1.setForeground(Color.red);
			placarResultado2.setForeground(Color.red);
		}
		else if(pontuacao.getText().equals("10")){
			placarResultado1.setForeground(Color.green);
			placarResultado2.setForeground(Color.green);
		}
		else if(pontuacao.getText().equals("25")){
			placarResultado1.setForeground(Color.blue);
			placarResultado2.setForeground(Color.blue);
		}
		
		//se a aposta já foi feita, colocamos o resultado do jogo nos placares do apostador
		if(apostador.getListaApostas()[id_jogo]!=null){
			placarTime1.setText(Integer.toString(apostador.getListaApostas()[id_jogo].getGolsTime1()));
			placarTime2.setText(Integer.toString(apostador.getListaApostas()[id_jogo].getGolsTime2()));
		}
		
	}
}
