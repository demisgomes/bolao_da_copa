package sga.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;
import sga.dominio.copa.ListaJogos;
import sga.dominio.copa.ListaTimes;
import sga.dominio.relatorios.GeradorRelatorio;
import sga.gui.panels.PanelResultados;
import sga.persistencia.ApostadorDAO;

import javax.swing.JMenu;

import org.eclipse.birt.report.engine.api.EngineException;

import java.awt.Font;

/**
 * Tela onde o admin põe os resultados 
 * @author Demis, Christian, Daniel
 * 
 */
public class TelaResultados extends JFrame {

	private JPanel contentPane;
	public PanelResultados panel;
	private PanelResultados panel_1;
	private PanelResultados panel_2;
	private PanelResultados panel_3;
	private PanelResultados panel_4;
	private PanelResultados panel_5;
	private PanelResultados panel_6;
	private PanelResultados panel_7;
	private Apostador apostador=new Apostador();
	private JButton btnVoltar;
	private ApostadorDAO a_dao=new ApostadorDAO();
	
	/**
	 * Método que gera a pontuação dos apostadores
	 * <br>
	 * Percorre a lista de apostadores e a quantidade de jogos da copa, que são 64
	 * <br>
	 * Caso a aposta ainda não foi computada, o método computa a pontuação e insere -a no banco
	 */
	public void gerarPontuacaoApostadores(){
		//percorrre a lista de apostadores
		for(int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			//percorre a quantidade de jogos
			for(int K=0;K<64;K++){
			//se aquela aposta não for nula
			//e o jogo também não
			//ou seja, se tanto a aposta quanto o jogo aconteceram, entra no if
			if((GrupoApostadores.getListaApostadores().get(i).getListaApostas()[K]!=null)&&(ListaJogos.getListaDeJogos()[K]!=null)){
				//se a aposta ainda não foi computada
				if(!a_dao.apostaJaComputada(K,GrupoApostadores.getListaApostadores().get(i).getNome())){
					//gera a pontuação da aposta através do método de mesmo nome
					//que está na classe Aposta
					GrupoApostadores.getListaApostadores().get(i).getListaApostas()[K].gerarPontuacao(GrupoApostadores.getListaApostadores().get(i).getListaApostas()[K].golsTime1, GrupoApostadores.getListaApostadores().get(i).getListaApostas()[K].golsTime2, ListaJogos.getListaDeJogos()[K].getGolsTime1(), ListaJogos.getListaDeJogos()[K].getGolsTime2(), GrupoApostadores.getListaApostadores().get(i), K);
					//insere os pontos no banco
					a_dao.incrementePontos(GrupoApostadores.getListaApostadores().get(i).getNome(), GrupoApostadores.getListaApostadores().get(i).getPontos());
					//computa a aposta no banco
					a_dao.computeAposta(K, GrupoApostadores.getListaApostadores().get(i).getNome());
				}
			}
			
			}
			
		}
		
	}
	

	/**
	 * Create the frame.
	 */
	public TelaResultados(Apostador apostador1) {
		this.apostador=apostador1;
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//este construtor possui praticamente a mesma coisa da Tela APostas
		ArrayList<Integer> listaDoGrupoA=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoB=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoC=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoD=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoE=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoF=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoG=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoH=new ArrayList<Integer>();
		for (int i = 0; i < ListaTimes.getListaTimes().length; i++) {
			if(ListaTimes.getListaTimes()[i].getGrupo().equals("A")){	
				listaDoGrupoA.add(i);
			}
			else if (ListaTimes.getListaTimes()[i].getGrupo().equals("B")) {
				listaDoGrupoB.add(i);	
			}
			else if(ListaTimes.getListaTimes()[i].getGrupo().equals("C")){
				listaDoGrupoC.add(i);
			}
			else if(ListaTimes.getListaTimes()[i].getGrupo().equals("D")){
				listaDoGrupoD.add(i);
			}
			else if(ListaTimes.getListaTimes()[i].getGrupo().equals("E")){
				listaDoGrupoE.add(i);
			}
			else if(ListaTimes.getListaTimes()[i].getGrupo().equals("F")){
				listaDoGrupoF.add(i);
			}
			else if(ListaTimes.getListaTimes()[i].getGrupo().equals("G")){
				listaDoGrupoG.add(i);
			}
			else if(ListaTimes.getListaTimes()[i].getGrupo().equals("H")){
				listaDoGrupoH.add(i);
			}
		}
		//cada panel receberá uma instância de FrameApostas, onde terá 5 parâmetros
				//esses parâmetros são necessários para configurarmos o Grupo onde cada time vai participar.
				//o apostador sempre será passado como parâmetro
		this.panel = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoA.get(0)], ListaTimes.getListaTimes()[listaDoGrupoA.get(1)], ListaTimes.getListaTimes()[listaDoGrupoA.get(2)], ListaTimes.getListaTimes()[listaDoGrupoA.get(3)], apostador, 0 );
		//neste caso, também passamos o id do jogo. Ele será de suma importância na comparação dos jogos e das apostas
		tabbedPane.addTab("Grupo A", null, panel, null);
		panel.setLayout(null);
		
		
		this.panel_1 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoB.get(0)], ListaTimes.getListaTimes()[listaDoGrupoB.get(1)], ListaTimes.getListaTimes()[listaDoGrupoB.get(2)], ListaTimes.getListaTimes()[listaDoGrupoB.get(3)], apostador, 6);
		tabbedPane.addTab("Grupo B", null, panel_1, null);
		
		this.panel_2 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoC.get(0)], ListaTimes.getListaTimes()[listaDoGrupoC.get(1)], ListaTimes.getListaTimes()[listaDoGrupoC.get(2)], ListaTimes.getListaTimes()[listaDoGrupoC.get(3)],apostador,12);
		tabbedPane.addTab("Grupo C", null, panel_2, null);
		
		
		this.panel_3 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoD.get(0)], ListaTimes.getListaTimes()[listaDoGrupoD.get(1)], ListaTimes.getListaTimes()[listaDoGrupoD.get(2)], ListaTimes.getListaTimes()[listaDoGrupoD.get(3)],apostador,18);
		tabbedPane.addTab("Grupo D", null, panel_3, null);
		
		this.panel_4 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoE.get(0)], ListaTimes.getListaTimes()[listaDoGrupoE.get(1)], ListaTimes.getListaTimes()[listaDoGrupoE.get(2)], ListaTimes.getListaTimes()[listaDoGrupoE.get(3)],apostador,24);
		tabbedPane.addTab("Grupo E", null, panel_4, null);
		
		this.panel_5 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoF.get(0)], ListaTimes.getListaTimes()[listaDoGrupoF.get(1)], ListaTimes.getListaTimes()[listaDoGrupoF.get(2)], ListaTimes.getListaTimes()[listaDoGrupoF.get(3)],apostador,30);
		tabbedPane.addTab("Grupo F", null, panel_5, null);
		
		this.panel_6 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoG.get(0)], ListaTimes.getListaTimes()[listaDoGrupoG.get(1)], ListaTimes.getListaTimes()[listaDoGrupoG.get(2)], ListaTimes.getListaTimes()[listaDoGrupoG.get(3)],apostador,36);
		tabbedPane.addTab("Grupo G", null, panel_6, null);
		
		this.panel_7 = new PanelResultados(ListaTimes.getListaTimes()[listaDoGrupoH.get(0)], ListaTimes.getListaTimes()[listaDoGrupoH.get(1)], ListaTimes.getListaTimes()[listaDoGrupoH.get(2)], ListaTimes.getListaTimes()[listaDoGrupoH.get(3)],apostador,42);
		tabbedPane.addTab("Grupo H", null, panel_7, null);
		
		//título do JFrame
		JLabel lblNewLabel = new JLabel("Agora é hora de pôr os resultados.");
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		/**
		 * Button que gera a pontuação dos apostadores
		 */
		
		JButton btnNewButton = new JButton("Gerar Pontuação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clicando no botão, chamamos o método gereResultado() da classe PanelResultados
				panel.gereResultado();
				panel_1.gereResultado();
				panel_2.gereResultado();
				panel_3.gereResultado();
				panel_4.gereResultado();
				panel_5.gereResultado();
				panel_6.gereResultado();
				panel_7.gereResultado();
				//e no final, chamamos o método que gera a pontuação
				gerarPontuacaoApostadores();
				//mostramos a tela de classificação dos apostadores
				TelaClassApostadores telaClassApostadores=new TelaClassApostadores();
				telaClassApostadores.setVisible(true);
				TelaResultados.this.dispose();
			}
		});
		btnNewButton.setBounds(20, 385, 134, 23);
		contentPane.add(btnNewButton);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial().setVisible(true);
				TelaResultados.this.dispose();
			}
		});
		btnVoltar.setBounds(177, 385, 89, 23);
		contentPane.add(btnVoltar);
		
		//botoes dos relatórios
				JButton btnNewButton_1 = new JButton("Apostas");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostas");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_1.setBounds(595, 76, 129, 23);
				contentPane.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Times");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_2.setBounds(595, 142, 129, 23);
				contentPane.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("Apostadores");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostadores");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_3.setBounds(595, 176, 129, 23);
				contentPane.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("Pontuação de Times");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times_Pontuacao");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_4.setBounds(595, 108, 129, 23);
				contentPane.add(btnNewButton_4);
				
				JLabel lblNewLabel_1 = new JLabel("Opções de Relatórios");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setBounds(590, 55, 146, 14);
				contentPane.add(lblNewLabel_1);
		
		JButton btNovaCopa = new JButton("Começar nova copa");
		if(ListaJogos.conteJogos()<64){
			btNovaCopa.setVisible(true);
			
		}
		btNovaCopa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				a_dao.comecarNovaCopa();
				GrupoApostadores.getListaApostadores().clear();
				for (int i = 0; i < 64; i++) {
					ListaJogos.getListaDeJogos()[i]=null;
				}
				//adiciona os apostadores na lista de apostadores a paritr do nome
				if (GrupoApostadores.getListaApostadores().size()==0) {
					//adiciona todos os apostadores na lista de apostadores
					a_dao.addApostadorGrupo();
					for(int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
						//adiciona as apostas de cada apostador na lista de Apostas do mesmo
						a_dao.addInListaApostas(GrupoApostadores.getListaApostadores().get(i).getNome());
					}
					GrupoApostadores.setNumApostadores(GrupoApostadores.getListaApostadores().size());
				}
				
				//CARREGA OS PONTOS DO APOSTADOR A PARTIR DO SEU NOME
				for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
					a_dao.carreguePontos(GrupoApostadores.getListaApostadores().get(i).getNome());
					
				}
				new TelaInicial().setVisible(true);
				TelaResultados.this.dispose();
				
			}
		});
		
		btNovaCopa.setBounds(300, 385, 200,23);
		contentPane.add(btNovaCopa);
		
		

		
		
	/**
	 * COnstrutor para as quartas de Final	
	 */
		
	}
	public TelaResultados(Apostador apostador1, String nome, String nome2){
		this.apostador=apostador1;
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelResultados(apostador, "oitavas","quartas");
		tabbedPane.addTab("Quartas de Final", null, panel, null);
		panel.setLayout(null);
		
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		


		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel("Agora é hora de você pôr os resultados");
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		//Neste botâo apostar, o usuário realiza suas apostas
		
		JButton btnNewButton = new JButton("Gerar pontuação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panel.aposteNasQuartas();
				//JOptionPane.showMessageDialog(null, ""+apostador.getNome()+" possui "+ apostador.conteApostas()+" Apostas");
				panel.gereResultadoNasQuartas();
				gerarPontuacaoApostadores();
				TelaClassApostadores tela=new TelaClassApostadores();
				tela.setVisible(true);
				TelaResultados.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 134, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaResultados.this.dispose();
			}
		});
		btnVoltar.setBounds(177, 385, 89, 23);
		contentPane.add(btnVoltar);	
		//botoes dos relatórios
				JButton btnNewButton_1 = new JButton("Apostas");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostas");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_1.setBounds(595, 76, 129, 23);
				contentPane.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Times");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_2.setBounds(595, 142, 129, 23);
				contentPane.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("Apostadores");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostadores");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_3.setBounds(595, 176, 129, 23);
				contentPane.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("Pontuação de Times");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times_Pontuacao");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_4.setBounds(595, 108, 129, 23);
				contentPane.add(btnNewButton_4);
				
				JLabel lblNewLabel_1 = new JLabel("Opções de Relatórios");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setBounds(590, 55, 146, 14);
				contentPane.add(lblNewLabel_1);
		
	}
	
	/**
	 * Construtor para as oitavas de final
	 * @param apostador1
	 * @param nome
	 * @wbp.parser.constructor
	 */
	public TelaResultados(Apostador apostador1, String nome) {
		this.apostador=apostador1;
		//Apostador recebe o parâmetro apostador1
		//timesCopa recebe uma instância de TimesParticipantes, uma classe que possui os times que participam do mundial, e que são acessados publicamente.
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelResultados(apostador, "oitavas");
		tabbedPane.addTab("Oitavas de Final", null, panel, null);
		panel.setLayout(null);
		
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		
		/**
		 * Estes arrayLists servem para pegarmos quais são os tiems de cada grupo
		 */
		

		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel("Agora é hora de você pôr os resultados");
		
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Gerar Pontuação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.gereResultadoNasOitavas();
				gerarPontuacaoApostadores();
				TelaClassApostadores tela=new TelaClassApostadores();
				tela.setVisible(true);
				TelaResultados.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 124, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaResultados.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);		
		
		JButton btnNewButton_1 = new JButton("Apostas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeradorRelatorio g = new GeradorRelatorio();
				try {
					g.gerarRelatorio("Relatorio_Apostas");
				} catch (EngineException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(595, 76, 129, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Times");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeradorRelatorio g = new GeradorRelatorio();
				try {
					g.gerarRelatorio("Relatorio_Times");
				} catch (EngineException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(595, 142, 129, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Apostadores");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeradorRelatorio g = new GeradorRelatorio();
				try {
					g.gerarRelatorio("Relatorio_Apostadores");
				} catch (EngineException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(595, 176, 129, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Pontuação de Times");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeradorRelatorio g = new GeradorRelatorio();
				try {
					g.gerarRelatorio("Relatorio_Times_Pontuacao");
				} catch (EngineException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(595, 108, 129, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Opções de Relatórios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(590, 55, 146, 14);
		contentPane.add(lblNewLabel_1);
		
	}
	
	/**
	 * COnstrutor para as semi-finais
	 * @param apostador1
	 * @param nome
	 * @param nome2
	 */
	public TelaResultados(Apostador apostador1, String nome, String nome2, String semi){
		this.apostador=apostador1;
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelResultados(apostador, "oitavas","quartas", "semi");
		tabbedPane.addTab("Semi-Final", null, panel, null);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Agora é hora de você pôr os resultados");
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);

		
		JButton btnNewButton = new JButton("Gerar pontuação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.gereResultadoNasSemi();
				gerarPontuacaoApostadores();
				TelaClassApostadores tela=new TelaClassApostadores();
				tela.setVisible(true);
				TelaResultados.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 134, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaResultados.this.dispose();
			}
		});
		btnVoltar.setBounds(177, 385, 89, 23);
		contentPane.add(btnVoltar);	
		
		//botoes dos relatórios
				JButton btnNewButton_1 = new JButton("Apostas");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostas");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_1.setBounds(595, 76, 129, 23);
				contentPane.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Times");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_2.setBounds(595, 142, 129, 23);
				contentPane.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("Apostadores");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostadores");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_3.setBounds(595, 176, 129, 23);
				contentPane.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("Pontuação de Times");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times_Pontuacao");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_4.setBounds(595, 108, 129, 23);
				contentPane.add(btnNewButton_4);
				
				JLabel lblNewLabel_1 = new JLabel("Opções de Relatórios");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setBounds(590, 55, 146, 14);
				contentPane.add(lblNewLabel_1);
		
	}
	
	
	/**
	 * Construtor para as finais
	 * @param apostador1
	 * @param nome
	 * @param nome2
	 */
	public TelaResultados(Apostador apostador1, String nome, String nome2, String semi, String finais){
		this.apostador=apostador1;
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelResultados(apostador, "oitavas","quartas", "semi", "final");
		tabbedPane.addTab("Final", null, panel, null);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Agora é hora de você pôr os resultados");
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);

		
		JButton btnNewButton = new JButton("Gerar pontuação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.gereResultadoNasFinais();
				gerarPontuacaoApostadores();
				TelaClassApostadores tela=new TelaClassApostadores();
				tela.setVisible(true);
				TelaResultados.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 134, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaResultados.this.dispose();
			}
		});
		btnVoltar.setBounds(177, 385, 89, 23);
		contentPane.add(btnVoltar);	
		//botoes dos relatórios
				JButton btnNewButton_1 = new JButton("Apostas");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostas");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_1.setBounds(595, 76, 129, 23);
				contentPane.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Times");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_2.setBounds(595, 142, 129, 23);
				contentPane.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("Apostadores");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Apostadores");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_3.setBounds(595, 176, 129, 23);
				contentPane.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("Pontuação de Times");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorRelatorio g = new GeradorRelatorio();
						try {
							g.gerarRelatorio("Relatorio_Times_Pontuacao");
						} catch (EngineException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_4.setBounds(595, 108, 129, 23);
				contentPane.add(btnNewButton_4);
				
				JLabel lblNewLabel_1 = new JLabel("Opções de Relatórios");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setBounds(590, 55, 146, 14);
				contentPane.add(lblNewLabel_1);
		
		
	}
}
