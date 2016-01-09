package sga.gui.panels;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sga.dominio.aposta.Aposta;
import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;
import sga.dominio.copa.GrupoCopa;
import sga.dominio.copa.Time;
import sga.gui.Linha;
import sga.persistencia.ApostadorDAO;
import sga.persistencia.TimesDAO;

/**
 * Apesar do nome, trata-se de um panel, que mostra aos usuários a tela de apostas
 * <br>
 * Há construtores para a fase de grupos, oitavas de final, e etc.
 * @author Demis, Christian
 *
 */
public class PanelApostas extends JPanel {

	public GrupoCopa Grupo;
	private Apostador apostador=new Apostador();
	private Aposta aposta1;
	private Aposta aposta2;
	private Aposta aposta3;
	private Aposta aposta4;
	private Aposta aposta5;
	private Aposta aposta6;
	private Aposta aposta7;
	private Aposta aposta8;
	private Linha linha1=new Linha();
	private Linha linha2=new Linha();
	private Linha linha3=new Linha();
	private Linha linha4=new Linha();
	private Linha linha5=new Linha();
	private Linha linha6=new Linha();
	private Linha linha7=new Linha();
	private Linha linha8=new Linha();
	private int indice;
	private int idJogo;
	private ApostadorDAO apostadorDAO=new ApostadorDAO();
	private Time[] listaDeTimesOitavas=new Time[16];
	private TimesDAO timesDAO=new TimesDAO();
	private Time[] listaDeTimesQuartas = new Time[8];
	private Time[] listaDeTimesSemi = new Time[4];
	private Time[] listaDeTimesFinais = new Time[4];
	/**
	 * Método que realiza as apostas dos usuários.
	 * <br>
	 * Cria-se uma instância de cada aposta e utiliza-se o método auxilie o Aposte para pularmos os textfields que não tem nada escrito
	 *<br>
	 *Este método é apenas para a fase de grupos
	 */
	public void aposte(){
		//adicionam as apostas
		try{
			
			//CRIAM AS INSTÂNCIAS DE aPOSTAS
			aposta1=new Aposta(this.apostador, Grupo.getListaJogos()[0].getTime1(), Grupo.getListaJogos()[0].getTime2());
			aposta2=new Aposta(this.apostador, Grupo.getListaJogos()[1].getTime1(), Grupo.getListaJogos()[1].getTime2());
			aposta3=new Aposta(this.apostador, Grupo.getListaJogos()[2].getTime1(), Grupo.getListaJogos()[2].getTime2());
			aposta4=new Aposta(this.apostador, Grupo.getListaJogos()[3].getTime1(), Grupo.getListaJogos()[3].getTime2());
			aposta5=new Aposta(this.apostador, Grupo.getListaJogos()[4].getTime1(), Grupo.getListaJogos()[4].getTime2());
			aposta6=new Aposta(this.apostador, Grupo.getListaJogos()[5].getTime1(), Grupo.getListaJogos()[5].getTime2());
			
			
			//CHAMA O MÉTODO AUXILIEOAPOSTE PARA REALIZAR A VERIFICAÇÃO E INSERÇÃO NO BANCO
			this.auxilieOAposte(aposta1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), idJogo);
			
			this.auxilieOAposte(aposta2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), idJogo+1);
			
			this.auxilieOAposte(aposta3,  linha3.getPlacarTime1(), linha3.getPlacarTime2(), idJogo+2);
			
			this.auxilieOAposte(aposta4,  linha4.getPlacarTime1(), linha4.getPlacarTime2(), idJogo+3);
			
			this.auxilieOAposte(aposta5,  linha5.getPlacarTime1(), linha5.getPlacarTime2(), idJogo+4);
			
			this.auxilieOAposte(aposta6,  linha6.getPlacarTime1(), linha6.getPlacarTime2(), idJogo+5);
			
			
		}
		//captura alguma exceção caso ninguém aposte
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public void  auxilieOAposte(Aposta aposta, JTextField gols1, JTextField gols2, int idJogo){
		//ESTE MÉTODO REALIZA A INSERÇÃO NO BANCO, ALÉM DE "PULAR" OS TEXTFIELDS QUE NÃO TEM DADOS
	
		ApostadorDAO dao=new ApostadorDAO();
		
			
			//CASO OS DOIS TEXTFIELDS ESTEJAM COM DADOS, ELE ENTRA NESTE IF 
			if((!gols1.getText().equals(""))&&(!gols2.getText().equals(""))){
				//INSERE OS GOLS NO OBJETO APOSTA
				int gol1 = 0;
				int gol2 = 0;
				try{
				gol1 = Integer.parseInt(gols1.getText());
				gol2 = Integer.parseInt(gols2.getText());
				if(gol1 >=0 && gol2 >=0 ){
					aposta.setGolsTime1(gol1);
					aposta.setGolsTime2(gol2);
					//INSERE APOSTA NO BANCO
					dao.inserirAposta(aposta, idJogo);
					//INSERE A APOSTA NA LISTA DE APOSTAS DO APOSTADOR COM O ÍNDICE DO JOGO EM QUESTÃO
					GrupoApostadores.getListaApostadores().get(indice).getListaApostas()[idJogo]=(aposta);
					
				}
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não aceitamos valores que não sejam inteiros");
				}
				
			}
			
			
	
	}
	
	/**
	 * Método parecido com o aposte, porém com duas linhas a mais.
	 */
	public void aposteNasOitavas(){
		
		aposta1=new Aposta(this.apostador, listaDeTimesOitavas[0], listaDeTimesOitavas[3]);
		aposta2=new Aposta(this.apostador, listaDeTimesOitavas[2], listaDeTimesOitavas[1]);
		aposta3=new Aposta(this.apostador, listaDeTimesOitavas[4], listaDeTimesOitavas[7]);
		aposta4=new Aposta(this.apostador, listaDeTimesOitavas[6], listaDeTimesOitavas[5]);
		aposta5=new Aposta(this.apostador, listaDeTimesOitavas[8], listaDeTimesOitavas[11]);
		aposta6=new Aposta(this.apostador, listaDeTimesOitavas[10], listaDeTimesOitavas[9]);
		aposta7=new Aposta(this.apostador, listaDeTimesOitavas[12], listaDeTimesOitavas[15]);
		aposta8=new Aposta(this.apostador, listaDeTimesOitavas[14], listaDeTimesOitavas[13]);
		
		
		//CHAMA O MÉTODO AUXILIEOAPOSTE PARA REALIZAR A VERIFICAÇÃO E INSERÇÃO NO BANCO
		this.auxilieOAposte(aposta1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 48);
		
		this.auxilieOAposte(aposta2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 49);
		
		this.auxilieOAposte(aposta3,  linha3.getPlacarTime1(), linha3.getPlacarTime2(), 50);
		
		this.auxilieOAposte(aposta4,  linha4.getPlacarTime1(), linha4.getPlacarTime2(), 51);
		
		this.auxilieOAposte(aposta5,  linha5.getPlacarTime1(), linha5.getPlacarTime2(), 52);
		
		this.auxilieOAposte(aposta6,  linha6.getPlacarTime1(), linha6.getPlacarTime2(), 53);
		
		this.auxilieOAposte(aposta7,  linha7.getPlacarTime1(), linha7.getPlacarTime2(), 54);
		
		this.auxilieOAposte(aposta8,  linha8.getPlacarTime1(), linha8.getPlacarTime2(), 55);
		
	}
	
	public void aposteNasQuartas(){
		
		aposta1=new Aposta(this.apostador, listaDeTimesQuartas[0], listaDeTimesQuartas[3]);
		aposta2=new Aposta(this.apostador, listaDeTimesQuartas[2], listaDeTimesQuartas[1]);
		aposta3=new Aposta(this.apostador, listaDeTimesQuartas[4], listaDeTimesQuartas[7]);
		aposta4=new Aposta(this.apostador, listaDeTimesQuartas[6], listaDeTimesQuartas[5]);
		
		this.auxilieOAposte(aposta1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 56);
		
		this.auxilieOAposte(aposta2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 57);
		
		this.auxilieOAposte(aposta3,  linha3.getPlacarTime1(), linha3.getPlacarTime2(), 58);
		
		this.auxilieOAposte(aposta4,  linha4.getPlacarTime1(), linha4.getPlacarTime2(), 59);
		
	}
	
public void aposteNasSemi(){
		
		aposta1=new Aposta(this.apostador, listaDeTimesSemi[0], listaDeTimesSemi[3]);
		aposta2=new Aposta(this.apostador, listaDeTimesSemi[2], listaDeTimesSemi[1]);
		
		
		this.auxilieOAposte(aposta1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 60);
		
		this.auxilieOAposte(aposta2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 61);
		
		
	}
public void aposteNasFinais(){
	
	aposta1=new Aposta(this.apostador, listaDeTimesFinais[0], listaDeTimesFinais[1]);
	aposta2=new Aposta(this.apostador, listaDeTimesFinais[2], listaDeTimesFinais[3]);
	
	
	this.auxilieOAposte(aposta1, linha1.getPlacarTime1(), linha1.getPlacarTime2(), 63);
	
	this.auxilieOAposte(aposta2,  linha2.getPlacarTime1(), linha2.getPlacarTime2(), 62);
	
	
}
		
	/**
	 * Create the panel.
	 */

	//parâmetros do criarLinha()
	//LABEL TIME1
	//TEXTFIELD PLACAR TIME1
	//LABEL RESULTADO PLACAR1
	//LABEL X
	//LABEL RESULTADO PLACAR2
	//TEXTFIELD PLACAR TIME2
	//LABEL NOME TIME2
	//LABEL QTD APOSTAS DO JOGO
	//LABEL PONTUACAO JOGO COM CORES
	
	//construtor vazio para não haver erro
	public PanelApostas(){
		}
	
	/**
	 * Construtor para as oitavas de final.
	 * <br>
	 * Pegamos a lista de times classificados para as oitavas de final do banco. Criamos as linhas com os nomes dos times e fazemos os cruzamentos automáticos com os mesmos
	 * <br>
	 * Por exemplo, o primeiro do grupo A {posição 0] pega o segundo do grupo B [posição 3]
	 * @param apostador1
	 * @param oitavas
	 */
	//construtor para as oitavas de final
	public PanelApostas(Apostador apostador1, String oitavas){
		//A lista de times das oitavas de final é guardada aqui
		this.listaDeTimesOitavas=timesDAO.getListaTimesOitavas();
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		
		this.apostador=apostador1;
		//CRIAMOS AS LINHAS
		//e consequentemente os cruzamentos automáticos
		//1º do grupo A- Indice 0
		//2º do grupo A- Indice 1
		// e assim por diante
		this.linha1.criar(listaDeTimesOitavas[0].getNome(), listaDeTimesOitavas[3].getNome(),this, 48, 0, this.apostador, apostadorDAO.getPontuacaoAposta(48, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha2.criar(listaDeTimesOitavas[2].getNome(), listaDeTimesOitavas[1].getNome(),this, 49, 30, this.apostador, apostadorDAO.getPontuacaoAposta(49, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha3.criar(listaDeTimesOitavas[4].getNome(), listaDeTimesOitavas[7].getNome(),this, 50, 60, this.apostador, apostadorDAO.getPontuacaoAposta(50, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha4.criar(listaDeTimesOitavas[6].getNome(), listaDeTimesOitavas[5].getNome(),this, 51, 90, this.apostador, apostadorDAO.getPontuacaoAposta(51, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha5.criar(listaDeTimesOitavas[8].getNome(), listaDeTimesOitavas[11].getNome(),this, 52, 120, this.apostador, apostadorDAO.getPontuacaoAposta(52, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha6.criar(listaDeTimesOitavas[10].getNome(), listaDeTimesOitavas[9].getNome(),this, 53, 150, this.apostador, apostadorDAO.getPontuacaoAposta(53, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha7.criar(listaDeTimesOitavas[12].getNome(), listaDeTimesOitavas[15].getNome(),this, 54, 180, this.apostador, apostadorDAO.getPontuacaoAposta(54, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha8.criar(listaDeTimesOitavas[14].getNome(), listaDeTimesOitavas[13].getNome(),this, 55, 210, this.apostador, apostadorDAO.getPontuacaoAposta(55, apostadorDAO.getIdApostador(this.apostador.getNome())));
			
		//desativamos os textfields, com o id do jogo em questão
		linha1.desativeTextFields(linha1, 48, this.apostador, this);
		linha2.desativeTextFields(linha2, 49, this.apostador, this);
		linha3.desativeTextFields(linha3, 50, this.apostador, this);
		linha4.desativeTextFields(linha4, 51, this.apostador, this);
		linha5.desativeTextFields(linha5, 52, this.apostador, this);
		linha6.desativeTextFields(linha6, 53, this.apostador, this);
		linha7.desativeTextFields(linha7, 54, this.apostador, this);
		linha8.desativeTextFields(linha8, 55, this.apostador, this);
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o");
		lblPontuao.setBounds(400, 11, 62, 14);
		add(lblPontuao);
		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		
	}
	/**
	 * Construtor para a fase de grupos
	 * <br>
	 * Passamos os times do grupo, já que cada panel é um grupo da copa.
	 * <br>
	 * O apostador e o id do jogo também são passados. Cada jogo tem o seu id, que é a sua identificação. Fica muito mais fácil para compararmos os resultados e suas apostas
	 * @param time1
	 * @param time2
	 * @param time3
	 * @param time4
	 * @param apostador1
	 * @param idJogo
	 */
	public PanelApostas(Time time1, Time time2, Time time3, Time time4, Apostador apostador1, int idJogo) {
		this.setLayout(null);
		Grupo=new GrupoCopa(time1, time2, time3, time4);
		
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		
		//OS ID'S DOS JOGOS SERÃO GUARDADOS NESTA VARIÁVEL 
		this.idJogo=idJogo;
		this.apostador=apostador1;
		
		//criamos as linhas, com cada time do grupo
		//os jogos são gerados automaticamente, na classe GrupoCopa
		linha1.criar(Grupo.getListaJogos()[0].getTime1().getNome(), Grupo.getListaJogos()[0].getTime2().getNome(), this, idJogo, 0, this.apostador,apostadorDAO.getPontuacaoAposta(idJogo, apostadorDAO.getIdApostador(this.apostador.getNome())));
		linha2.criar(Grupo.getListaJogos()[1].getTime1().getNome(), Grupo.getListaJogos()[1].getTime2().getNome(), this, idJogo+1, 37, this.apostador, apostadorDAO.getPontuacaoAposta(idJogo+1, apostadorDAO.getIdApostador(this.apostador.getNome())));
		linha3.criar(Grupo.getListaJogos()[2].getTime1().getNome(), Grupo.getListaJogos()[2].getTime2().getNome(), this, idJogo+2, 74, this.apostador, apostadorDAO.getPontuacaoAposta(idJogo+2, apostadorDAO.getIdApostador(this.apostador.getNome())));
		linha4.criar(Grupo.getListaJogos()[3].getTime1().getNome(), Grupo.getListaJogos()[3].getTime2().getNome(), this, idJogo+3, 111, this.apostador, apostadorDAO.getPontuacaoAposta(idJogo+3, apostadorDAO.getIdApostador(this.apostador.getNome())));
		linha5.criar(Grupo.getListaJogos()[4].getTime1().getNome(), Grupo.getListaJogos()[4].getTime2().getNome(), this, idJogo+4, 148, this.apostador, apostadorDAO.getPontuacaoAposta(idJogo+4, apostadorDAO.getIdApostador(this.apostador.getNome())));
		linha6.criar(Grupo.getListaJogos()[5].getTime1().getNome(), Grupo.getListaJogos()[5].getTime2().getNome(), this, idJogo+5, 181, this.apostador, apostadorDAO.getPontuacaoAposta(idJogo+5, apostadorDAO.getIdApostador(this.apostador.getNome())));
		
		//desativamos os textFields caso algum jogo já aconteceu.
		//cada jogo do grupo tem seu id
		//ele está em ordem crescente, do primeiro ao último jogo.]
		//se passarmos o id 0 como parâmetro para o FrameApostas
		//o primeiro jogo terá id 0, e o último id 5.
		linha1.desativeTextFields(linha1, idJogo, this.apostador, this);
		linha2.desativeTextFields(linha2, idJogo+1, this.apostador, this);
		linha3.desativeTextFields(linha3, idJogo+2, this.apostador, this);
		linha4.desativeTextFields(linha4, idJogo+3, this.apostador, this);
		linha5.desativeTextFields(linha5, idJogo+4, this.apostador, this);
		linha6.desativeTextFields(linha6, idJogo+5, this.apostador, this);
		
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o");
		lblPontuao.setBounds(400, 11, 62, 14);
		add(lblPontuao);
		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		

	}
	
	/**
	 * Construtor para as quartas de Final
	 * @param apostador1
	 * @param oitavas
	 * @param quartas
	 */
	public PanelApostas(Apostador apostador1, String oitavas, String quartas){
		//A lista de times das oitavas de final é guardada aqui
		this.listaDeTimesQuartas=timesDAO.getListaQuartas();
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		
		this.apostador=apostador1;

		this.linha1.criar(listaDeTimesQuartas[0].getNome(), listaDeTimesQuartas[3].getNome(),this, 56, 0, this.apostador, apostadorDAO.getPontuacaoAposta(56, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha2.criar(listaDeTimesQuartas[2].getNome(), listaDeTimesQuartas[1].getNome(),this, 57, 30, this.apostador, apostadorDAO.getPontuacaoAposta(57, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha3.criar(listaDeTimesQuartas[4].getNome(), listaDeTimesQuartas[7].getNome(),this, 58, 60, this.apostador, apostadorDAO.getPontuacaoAposta(58, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha4.criar(listaDeTimesQuartas[6].getNome(), listaDeTimesQuartas[5].getNome(),this, 59, 90, this.apostador, apostadorDAO.getPontuacaoAposta(59, apostadorDAO.getIdApostador(this.apostador.getNome())));
		
		//desativamos os textfields, com o id do jogo em questão
		linha1.desativeTextFields(linha1, 56, this.apostador, this);
		linha2.desativeTextFields(linha2, 57, this.apostador, this);
		linha3.desativeTextFields(linha3, 58, this.apostador, this);
		linha4.desativeTextFields(linha4, 59, this.apostador, this);
		
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o");
		lblPontuao.setBounds(400, 11, 62, 14);
		add(lblPontuao);
		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		
	}
	
	/**
	 * Construtor para as semifinais
	 * @param apostador1
	 * @param oitavas
	 * @param quartas
	 */
	public PanelApostas(Apostador apostador1, String oitavas, String quartas, String semi){
		//A lista de times das oitavas de final é guardada aqui
		this.listaDeTimesSemi=timesDAO.getListaSemi();
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		
		this.apostador=apostador1;

		this.linha1.criar(listaDeTimesSemi[0].getNome(), listaDeTimesSemi[3].getNome(),this, 60, 0, this.apostador, apostadorDAO.getPontuacaoAposta(60, apostadorDAO.getIdApostador(this.apostador.getNome())));
		
		this.linha2.criar(listaDeTimesSemi[2].getNome(), listaDeTimesSemi[1].getNome(),this, 61, 30, this.apostador, apostadorDAO.getPontuacaoAposta(61, apostadorDAO.getIdApostador(this.apostador.getNome())));
		
		//desativamos os textfields, com o id do jogo em questão
		linha1.desativeTextFields(linha1, 60, this.apostador, this);
		linha2.desativeTextFields(linha2, 61, this.apostador, this);
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o");
		lblPontuao.setBounds(400, 11, 62, 14);
		add(lblPontuao);
		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
		
		
	}
	
	/**
	 * Construtor para a final
	 * @param apostador1
	 * @param oitavas
	 * @param quartas
	 */
	public PanelApostas(Apostador apostador1, String oitavas, String quartas, String semi, String finais){
		//A lista de times das oitavas de final é guardada aqui
		this.listaDeTimesFinais=timesDAO.getListaFinais();
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		
		this.apostador=apostador1;
		
		JLabel lblFinal = new JLabel("Final");
		lblFinal.setBounds(140, 30, 100, 14);
		add(lblFinal);
		this.linha1.criar(listaDeTimesFinais[0].getNome(), listaDeTimesFinais[1].getNome(),this, 63, 0, this.apostador, apostadorDAO.getPontuacaoAposta(63, apostadorDAO.getIdApostador(this.apostador.getNome())));
		this.linha2.criar(listaDeTimesFinais[2].getNome(), listaDeTimesFinais[3].getNome(),this, 62, 90, this.apostador, apostadorDAO.getPontuacaoAposta(62, apostadorDAO.getIdApostador(this.apostador.getNome())));
		
		//desativamos os textfields, com o id do jogo em questão
		linha1.desativeTextFields(linha1, 63, this.apostador, this);
		linha2.desativeTextFields(linha2, 62, this.apostador, this);
		
		JLabel lbllugar = new JLabel("3º Lugar");
		lbllugar.setBounds(140, 120, 100, 14);
		add(lbllugar);
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o");
		lblPontuao.setBounds(400, 11, 62, 14);
		add(lblPontuao);
		
		JLabel lblqtdApostas = new JLabel("Qtd Apostas");
		lblqtdApostas.setBounds(300, 11, 82, 14);
		add(lblqtdApostas);
	}
	
	
}
