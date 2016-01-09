package sga.gui.panels;


import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;
import sga.dominio.copa.GrupoCopa;
import sga.dominio.copa.Jogo;
import sga.dominio.copa.ListaJogos;
import sga.dominio.copa.Time;
import sga.gui.Linha;
import sga.gui.TelaPenaltis;
import sga.persistencia.JogosDAO;
import sga.persistencia.TimesDAO;

/**
 * O nome tem Frame, porém é um panel
 * <br>
 * Lá acontece o envio dos resultados da aposta pelo administrador.
 * <br>
 * Também há construtores  para as oitavas, quartas, semi e final
 * @author Admin
 *
 */
public class PanelResultados extends JPanel {


	private Linha linha1=new Linha();
	private Linha linha2=new Linha();
	private Linha linha3=new Linha();
	private Linha linha4=new Linha();
	private Linha linha5=new Linha();
	private Linha linha6=new Linha();
	private Linha linha7=new Linha();
	private Linha linha8=new Linha();
	private GrupoApostadores group=new GrupoApostadores();
	public GrupoCopa Grupo;
	private Apostador apostador=new Apostador();
	private Jogo jogo1;
	private Jogo jogo2;
	private Jogo jogo3;
	private Jogo jogo4;
	private Jogo jogo5;
	private Jogo jogo6;
	private Jogo jogo7;
	private Jogo jogo8;
	private Time[] ListaDeTimesOitavas=new Time[16];
	private TimesDAO timesDAO=new TimesDAO();
	private Time[] ListaDeTimesQuartas = new Time[8];
	private Time[] ListaDeTimesSemi = new Time[4];
	private Time[] ListaDeTimesFinais = new Time[4];
	
	private ArrayList<Jogo> listaJogoGrupo=new ArrayList<Jogo>();
	private int idJogo;
	
	/**
	 * Método que gera o resultado dos times
	 * <br>
	 * verifica os resultados que o administrador colocou, gera todas as informações que o time possui e põe no banco
	 */
	public void gereResultado(){
		
		try{
			//chama o método auxiliar para pular os textfields e inserir os resultados no banco
			this.auxilieOResultado(jogo1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), idJogo);
			
			this.auxilieOResultado(jogo2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), idJogo+1);
			
			this.auxilieOResultado(jogo3,  linha3.getPlacarTime1(), linha3.getPlacarTime2(), idJogo+2);
			
			this.auxilieOResultado(jogo4,  linha4.getPlacarTime1(), linha4.getPlacarTime2(), idJogo+3);
			
			this.auxilieOResultado(jogo5,  linha5.getPlacarTime1(), linha5.getPlacarTime2(), idJogo+4);
			
			this.auxilieOResultado(jogo6,  linha6.getPlacarTime1(), linha6.getPlacarTime2(), idJogo+5);
			
			
			
		}
		catch(Exception exc){
			System.out.println(exc.getMessage());
			System.err.print(exc);
		}
	}
	
	/**
	 * Método auxiliar de gereResultado()
	 * <br>
	 * Primeiro, verifica se os textfields da linha são editáveis. Se não, quer dizer que o jogo já ocorreu
	 * <br>
	 * Se sim, verifica se não há espaço vazio. Em caso positivo, pega os dados e insere no banco
	 * @param jogo
	 * @param gols1
	 * @param gols2
	 * @param idJogo
	 */
	public void auxilieOResultado(Jogo jogo, JTextField gols1, JTextField gols2, int idJogo){
		if((gols1.isEditable())||(gols2.isEditable())){
			if((!gols1.getText().equals(""))&&(!gols2.getText().equals(""))){
				//seta os gols de cada time no objeto jogo
				jogo.setGolsTime1(Integer.parseInt(gols1.getText()));
				jogo.setGolsTime2(Integer.parseInt(gols2.getText()));
				JogosDAO dao=new JogosDAO();
				//INSERE APOSTA NO BANCO
				dao.inserirResultado(jogo, idJogo);
				//gera dados do time através deste método da classe jogo
				//como gols, pontos, vitórias e etc
				//e também insere estes dados no banco
				jogo.gerarResultado();
				//povoa a lista de jogos estática no banco
				ListaJogos.getListaDeJogos()[idJogo]=jogo;
				Time timeVencedor=null;
				Time timePerdedor=null;
				TimesDAO t_dao=new TimesDAO();
				if(idJogo>47){
					//se o jogo já for das oitavas de final, entra neste if
					//para pôr a posição das quartas de final no banco
				
					if(Integer.parseInt(gols1.getText())>Integer.parseInt(gols2.getText())){
						timeVencedor=jogo.getTime1();
						timePerdedor=jogo.getTime2();
					}
					else if(Integer.parseInt(gols1.getText())<Integer.parseInt(gols2.getText())){
						timeVencedor=jogo.getTime2();
						timePerdedor=jogo.getTime1();
					}
					else{
						TelaPenaltis telaPenaltis=new TelaPenaltis(jogo.getTime1(), jogo.getTime2(), idJogo);
						telaPenaltis.setVisible(true);
					}
					if(timeVencedor!=null){	
						if (idJogo==48){
							t_dao.insiraPosicao(timeVencedor,"V48", "posicao_quartas" );
						}
						if (idJogo==49){
							t_dao.insiraPosicao(timeVencedor,"V49", "posicao_quartas" );
						}
						if (idJogo==50){
							t_dao.insiraPosicao(timeVencedor,"V50","posicao_quartas" );
						}
						if (idJogo==51){
							t_dao.insiraPosicao(timeVencedor,"V51", "posicao_quartas" );
						}
						if (idJogo==52){
							t_dao.insiraPosicao(timeVencedor,"V52","posicao_quartas" );
						}
						if (idJogo==53){
							t_dao.insiraPosicao(timeVencedor,"V53", "posicao_quartas");
						}
						if (idJogo==54){
							t_dao.insiraPosicao(timeVencedor,"V54", "posicao_quartas" );
						}
						if (idJogo==55){
							t_dao.insiraPosicao(timeVencedor,"V55","posicao_quartas" );
						}
					
					
						if (idJogo==56){
							t_dao.insiraPosicao(timeVencedor,"V56","posicao_semi" );
						}
						if (idJogo==57){
							t_dao.insiraPosicao(timeVencedor,"V57","posicao_semi" );
						}
						if (idJogo==58){
							t_dao.insiraPosicao(timeVencedor,"V58","posicao_semi" );
						}
						if (idJogo==59){
							t_dao.insiraPosicao(timeVencedor,"V59","posicao_semi" );
						}
						
					
						if (idJogo==60){
							t_dao.insiraPosicao(timeVencedor,"V60","posicao_final" );
							t_dao.insiraPosicao(timePerdedor, "P60", "posicao_final");
						}
						if (idJogo==61){
							t_dao.insiraPosicao(timeVencedor,"V61","posicao_final" );
							t_dao.insiraPosicao(timePerdedor, "P61", "posicao_final");
							
						}
						
						
					
						if (idJogo==63){
							t_dao.insiraPosicao(timeVencedor,"Campeão","campeao" );
							t_dao.insiraPosicao(timePerdedor, "Vice-Campeão", "campeao");
						}
						if (idJogo==62){
							t_dao.insiraPosicao(timeVencedor,"3º Lugar","campeao" );
							t_dao.insiraPosicao(timePerdedor, "4º Lugar", "campeao");
							
						}
					}
						
				}	
					
			}
		}
		
	}
	public GrupoCopa getGrupo(){
		return Grupo;
	}
	
	/**
	 * Método que gera o resultado nas oitavas de final
	 * <br>
	 * muito parecido com o anterior, só muda que são oito linhas
	 */
public void gereResultadoNasOitavas(){
		
		jogo1=new Jogo(ListaDeTimesOitavas[0], ListaDeTimesOitavas[3]);
		jogo2=new Jogo(ListaDeTimesOitavas[2], ListaDeTimesOitavas[1]);
		jogo3=new Jogo(ListaDeTimesOitavas[4], ListaDeTimesOitavas[7]);
		jogo4=new Jogo(ListaDeTimesOitavas[6], ListaDeTimesOitavas[5]);
		jogo5=new Jogo(ListaDeTimesOitavas[8], ListaDeTimesOitavas[11]);
		jogo6=new Jogo(ListaDeTimesOitavas[10], ListaDeTimesOitavas[9]);
		jogo7=new Jogo(ListaDeTimesOitavas[12], ListaDeTimesOitavas[15]);
		jogo8=new Jogo(ListaDeTimesOitavas[14], ListaDeTimesOitavas[13]);
		
		
		//CHAMA O MÉTODO AUXILIEOAPOSTE PARA REALIZAR A VERIFICAÇÃO E INSERÇÃO NO BANCO
		this.auxilieOResultado(jogo1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 48);
		
		this.auxilieOResultado(jogo2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 49);
		
		this.auxilieOResultado(jogo3,  linha3.getPlacarTime1(), linha3.getPlacarTime2(), 50);
		
		this.auxilieOResultado(jogo4,  linha4.getPlacarTime1(), linha4.getPlacarTime2(), 51);
		
		this.auxilieOResultado(jogo5,  linha5.getPlacarTime1(), linha5.getPlacarTime2(), 52);
		
		this.auxilieOResultado(jogo6,  linha6.getPlacarTime1(), linha6.getPlacarTime2(), 53);
		
		this.auxilieOResultado(jogo7,  linha7.getPlacarTime1(), linha7.getPlacarTime2(), 54);
		
		this.auxilieOResultado(jogo8,  linha8.getPlacarTime1(), linha8.getPlacarTime2(), 55);
		
	}

public void gereResultadoNasQuartas(){
	
	jogo1=new Jogo(ListaDeTimesQuartas[0], ListaDeTimesQuartas[3]);
	jogo2=new Jogo(ListaDeTimesQuartas[2], ListaDeTimesQuartas[1]);
	jogo3=new Jogo(ListaDeTimesQuartas[4], ListaDeTimesQuartas[7]);
	jogo4=new Jogo(ListaDeTimesQuartas[6], ListaDeTimesQuartas[5]);
	
	
	
	//CHAMA O MÉTODO AUXILIEOAPOSTE PARA REALIZAR A VERIFICAÇÃO E INSERÇÃO NO BANCO
	this.auxilieOResultado(jogo1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 56);
	
	this.auxilieOResultado(jogo2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 57);
	
	this.auxilieOResultado(jogo3,  linha3.getPlacarTime1(), linha3.getPlacarTime2(), 58);
	
	this.auxilieOResultado(jogo4,  linha4.getPlacarTime1(), linha4.getPlacarTime2(), 59);
	
	
}
public void gereResultadoNasSemi(){
	
	jogo1=new Jogo(ListaDeTimesSemi[0], ListaDeTimesSemi[3]);
	jogo2=new Jogo(ListaDeTimesSemi[2], ListaDeTimesSemi[1]);
	
	
	//CHAMA O MÉTODO AUXILIEOAPOSTE PARA REALIZAR A VERIFICAÇÃO E INSERÇÃO NO BANCO
	this.auxilieOResultado(jogo1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 60);
	
	this.auxilieOResultado(jogo2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 61);
	
}

public void gereResultadoNasFinais(){
	
	jogo1=new Jogo(ListaDeTimesFinais[0], ListaDeTimesFinais[1]);
	jogo2=new Jogo(ListaDeTimesFinais[2], ListaDeTimesFinais[3]);
	
	
	//CHAMA O MÉTODO AUXILIEOAPOSTE PARA REALIZAR A VERIFICAÇÃO E INSERÇÃO NO BANCO
	this.auxilieOResultado(jogo1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 63);
	
	this.auxilieOResultado(jogo2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 62);
	
}

	
	/**
	 * Create the panel.
	 */
	
	public PanelResultados(Apostador apostador1, String nome1, String nome2){
		
		this.ListaDeTimesQuartas=timesDAO.getListaQuartas();
		
		
		this.apostador=apostador1;
		
		this.linha1.criar(ListaDeTimesQuartas[0].getNome(), ListaDeTimesQuartas[3].getNome(),this, 56, 0, this.apostador, 0);
		this.linha2.criar(ListaDeTimesQuartas[2].getNome(), ListaDeTimesQuartas[1].getNome(),this, 57, 30, this.apostador, 0);
		this.linha3.criar(ListaDeTimesQuartas[4].getNome(), ListaDeTimesQuartas[7].getNome(),this, 58, 60, this.apostador, 0);
		this.linha4.criar(ListaDeTimesQuartas[6].getNome(), ListaDeTimesQuartas[5].getNome(),this, 59, 90, this.apostador,0);
		
		//desativamos os textfields, com o id do jogo em questão
		linha1.desativeTextFields(linha1, 56, this.apostador, this);
		linha2.desativeTextFields(linha2, 57, this.apostador, this);
		linha3.desativeTextFields(linha3, 58, this.apostador, this);
		linha4.desativeTextFields(linha4, 59, this.apostador, this);
		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
	}
	public PanelResultados(){
		setLayout(null);}
	
	
	/**
	 * Construtor para a fase de grupos
	 * @param time1
	 * @param time2
	 * @param time3
	 * @param time4
	 * @param apostador1
	 * @param idJogo
	 */
	public PanelResultados(Time time1, Time time2, Time time3, Time time4, Apostador apostador1, int idJogo) {
		this.setLayout(null);
		Grupo=new GrupoCopa(time1, time2, time3, time4);
		this.apostador=apostador1;
		this.idJogo=idJogo;
		//add jogos
		jogo1=Grupo.getListaJogos()[0];
		jogo2=Grupo.getListaJogos()[1];
		jogo3=Grupo.getListaJogos()[2];
		jogo4=Grupo.getListaJogos()[3];
		jogo5=Grupo.getListaJogos()[4];
		jogo6=Grupo.getListaJogos()[5];
		
		linha1.criar(Grupo.getListaJogos()[0].getTime1().getNome(), Grupo.getListaJogos()[0].getTime2().getNome(), this, idJogo, 0, this.apostador, 0);
		linha2.criar(Grupo.getListaJogos()[1].getTime1().getNome(), Grupo.getListaJogos()[1].getTime2().getNome(), this, idJogo+1, 37, this.apostador,0);
		linha3.criar(Grupo.getListaJogos()[2].getTime1().getNome(), Grupo.getListaJogos()[2].getTime2().getNome(), this, idJogo+2, 74, this.apostador,0);
		linha4.criar(Grupo.getListaJogos()[3].getTime1().getNome(), Grupo.getListaJogos()[3].getTime2().getNome(), this, idJogo+3, 111, this.apostador,0);
		linha5.criar(Grupo.getListaJogos()[4].getTime1().getNome(), Grupo.getListaJogos()[4].getTime2().getNome(), this, idJogo+4, 148, this.apostador,0);
		linha6.criar(Grupo.getListaJogos()[5].getTime1().getNome(), Grupo.getListaJogos()[5].getTime2().getNome(), this, idJogo+5, 181, this.apostador,0);
		
		linha1.desativeTextFields(linha1, idJogo, this.apostador, this);
		linha2.desativeTextFields(linha2, idJogo+1, this.apostador, this);
		linha3.desativeTextFields(linha3, idJogo+2, this.apostador, this);
		linha4.desativeTextFields(linha4, idJogo+3, this.apostador, this);
		linha5.desativeTextFields(linha5, idJogo+4, this.apostador, this);
		linha6.desativeTextFields(linha6, idJogo+5, this.apostador, this);

		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		
	}
	
	public PanelResultados(Apostador apostador1, String oitavas){
		this.ListaDeTimesOitavas=timesDAO.getListaTimesOitavas();
		
		//OS ID'S DOS JOGOS SERÃO GUARDADOS NESTA VARIÁVEL 
		this.apostador=apostador1;
		
		this.linha1.criar(ListaDeTimesOitavas[0].getNome(), ListaDeTimesOitavas[3].getNome(),this, 48, 0, this.apostador, 0);
		this.linha2.criar(ListaDeTimesOitavas[2].getNome(), ListaDeTimesOitavas[1].getNome(),this, 49, 30, this.apostador, 0);
		this.linha3.criar(ListaDeTimesOitavas[4].getNome(), ListaDeTimesOitavas[7].getNome(),this, 50, 60, this.apostador, 0);
		this.linha4.criar(ListaDeTimesOitavas[6].getNome(), ListaDeTimesOitavas[5].getNome(),this, 51, 90, this.apostador, 0);
		this.linha5.criar(ListaDeTimesOitavas[8].getNome(), ListaDeTimesOitavas[11].getNome(),this, 52, 120, this.apostador, 0);
		this.linha6.criar(ListaDeTimesOitavas[10].getNome(), ListaDeTimesOitavas[9].getNome(),this, 53, 150, this.apostador, 0);
		this.linha7.criar(ListaDeTimesOitavas[12].getNome(), ListaDeTimesOitavas[15].getNome(),this, 54, 180, this.apostador, 0);
		this.linha8.criar(ListaDeTimesOitavas[14].getNome(), ListaDeTimesOitavas[13].getNome(),this, 55, 210, this.apostador, 0);
			
		linha1.desativeTextFields(linha1, 48, this.apostador, this);
		linha2.desativeTextFields(linha2, 49, this.apostador, this);
		linha3.desativeTextFields(linha3, 50, this.apostador, this);
		linha4.desativeTextFields(linha4, 51, this.apostador, this);
		linha5.desativeTextFields(linha5, 52, this.apostador, this);
		linha6.desativeTextFields(linha6, 53, this.apostador, this);
		linha7.desativeTextFields(linha7, 54, this.apostador, this);
		linha8.desativeTextFields(linha8, 55, this.apostador, this);

		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		
	}
	
	/**
	 * Construtor para as semifinais
	 * @param apostador1
	 * @param nome1
	 * @param nome2
	 * @param semi
	 */
public PanelResultados(Apostador apostador1, String nome1, String nome2, String semi){
		
		this.ListaDeTimesSemi=timesDAO.getListaSemi();
		
		
		this.apostador=apostador1;

		this.linha1.criar(ListaDeTimesSemi[0].getNome(), ListaDeTimesSemi[3].getNome(),this, 60, 0, this.apostador,0);
		this.linha2.criar(ListaDeTimesSemi[2].getNome(), ListaDeTimesSemi[1].getNome(),this, 61, 30, this.apostador, 0);
		
		//desativamos os textfields, com o id do jogo em questão
		linha1.desativeTextFields(linha1, 60, this.apostador, this);
		linha2.desativeTextFields(linha2, 61, this.apostador, this);

		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		
		
	}

public PanelResultados(Apostador apostador1, String nome1, String nome2, String semi, String finais){
	
	this.ListaDeTimesFinais=timesDAO.getListaFinais();
	
	
	this.apostador=apostador1;

	JLabel lblFinal = new JLabel("Final");
	lblFinal.setBounds(140, 30, 100, 14);
	add(lblFinal);
	this.linha1.criar(ListaDeTimesFinais[0].getNome(), ListaDeTimesFinais[1].getNome(),this, 63, 0, this.apostador, 0);
	this.linha2.criar(ListaDeTimesFinais[2].getNome(), ListaDeTimesFinais[3].getNome(),this, 62, 90, this.apostador, 0);
	
	//desativamos os textfields, com o id do jogo em questão
	linha1.desativeTextFields(linha1, 63, this.apostador, this);
	linha2.desativeTextFields(linha2, 62, this.apostador, this);
	
	JLabel lbllugar = new JLabel("3º Lugar");
	lbllugar.setBounds(140, 120, 100, 14);
	add(lbllugar);

	
	JLabel lblqtdApostas = new JLabel("Qtd Apostas");
	lblqtdApostas.setBounds(300, 11, 82, 14);
	add(lblqtdApostas);
}
}
