package sga.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sga.dominio.aposta.Aposta;
import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;
import sga.dominio.copa.ListaTimes;
import sga.gui.panels.PanelApostas;
import sga.persistencia.TimesDAO;

/**
 * Tela onde são realizadas as apostas nos jogos
 * <br>
 * è composta de Panels da classse PanelApostas
 * @author Demis, Christian, Daniel
 *
 */
public class TelaApostas extends JFrame {

	//Atributos. São declarados aqui para que possam ser usados no ActionPerformed
	private JPanel contentPane;
	//Objeto de TimesParticipantes
	public PanelApostas panel;
	private PanelApostas panel_1;
	private PanelApostas panel_2;
	private PanelApostas panel_3;
	private PanelApostas panel_4;
	private PanelApostas panel_5;
	private PanelApostas panel_6;
	private PanelApostas panel_7;
	private Apostador apostador=new Apostador();
	private Aposta aposta1;
	private JTextField textField_12;
	private int indice;
	//construtor que já carrega a lista de times do banco
	private TimesDAO timeDAO = new TimesDAO();
	private Linha linha1=new Linha();
	private Linha linha2=new Linha();
	private Linha linha3=new Linha();
	private Linha linha4=new Linha();
	private Linha linha5=new Linha();
	private Linha linha6=new Linha();
	private Linha linha7=new Linha();
	private Linha linha8=new Linha();

	/**
	 * Create the frame.
	 */
	public TelaApostas(){
		getContentPane().setLayout(null);}
	//COnstrutor vazio para que o run, mesmo sem parâmetros, rode alguma tela.
	public TelaApostas(Apostador apostador1) {
		//salva o indice do listaApostadores
		
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		this.apostador=apostador1;
		//Apostador recebe o parâmetro apostador1
		//timesCopa recebe uma instância de TimesParticipantes, uma classe que possui os times que participam do mundial, e que são acessados publicamente.
		setTitle("Apostas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		
		/**
		 * Estes arrayLists servem para pegarmos quais são os tiems de cada grupo
		 */
		ArrayList<Integer> listaDoGrupoA=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoB=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoC=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoD=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoE=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoF=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoG=new ArrayList<Integer>();
		ArrayList<Integer> listaDoGrupoH=new ArrayList<Integer>();
		//adiciona cada time em sua respectiva lista de seu respectivo grupo
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
		//cada panel receberá uma instância de PanelApostas, onde terá 5 parâmetros
				//esses parâmetros são necessários para configurarmos o Grupo onde cada time vai participar.
				//o apostador sempre será passado como parâmetro
		this.panel = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoA.get(0)], ListaTimes.getListaTimes()[listaDoGrupoA.get(1)], ListaTimes.getListaTimes()[listaDoGrupoA.get(2)], ListaTimes.getListaTimes()[listaDoGrupoA.get(3)], apostador, 0 );
		//neste caso, também passamos o id do jogo. Ele será de suma importância na comparação dos jogos e das apostas
		tabbedPane.addTab("Grupo A", null, panel, null);
		panel.setLayout(null);
		
		
		this.panel_1 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoB.get(0)], ListaTimes.getListaTimes()[listaDoGrupoB.get(1)], ListaTimes.getListaTimes()[listaDoGrupoB.get(2)], ListaTimes.getListaTimes()[listaDoGrupoB.get(3)], apostador, 6);
		tabbedPane.addTab("Grupo B", null, panel_1, null);
		
		this.panel_2 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoC.get(0)], ListaTimes.getListaTimes()[listaDoGrupoC.get(1)], ListaTimes.getListaTimes()[listaDoGrupoC.get(2)], ListaTimes.getListaTimes()[listaDoGrupoC.get(3)],apostador,12);
		tabbedPane.addTab("Grupo C", null, panel_2, null);
		
		
		this.panel_3 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoD.get(0)], ListaTimes.getListaTimes()[listaDoGrupoD.get(1)], ListaTimes.getListaTimes()[listaDoGrupoD.get(2)], ListaTimes.getListaTimes()[listaDoGrupoD.get(3)],apostador,18);
		tabbedPane.addTab("Grupo D", null, panel_3, null);
		
		this.panel_4 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoE.get(0)], ListaTimes.getListaTimes()[listaDoGrupoE.get(1)], ListaTimes.getListaTimes()[listaDoGrupoE.get(2)], ListaTimes.getListaTimes()[listaDoGrupoE.get(3)],apostador,24);
		tabbedPane.addTab("Grupo E", null, panel_4, null);
		
		this.panel_5 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoF.get(0)], ListaTimes.getListaTimes()[listaDoGrupoF.get(1)], ListaTimes.getListaTimes()[listaDoGrupoF.get(2)], ListaTimes.getListaTimes()[listaDoGrupoF.get(3)],apostador,30);
		tabbedPane.addTab("Grupo F", null, panel_5, null);
		
		this.panel_6 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoG.get(0)], ListaTimes.getListaTimes()[listaDoGrupoG.get(1)], ListaTimes.getListaTimes()[listaDoGrupoG.get(2)], ListaTimes.getListaTimes()[listaDoGrupoG.get(3)],apostador,36);
		tabbedPane.addTab("Grupo G", null, panel_6, null);
		
		this.panel_7 = new PanelApostas(ListaTimes.getListaTimes()[listaDoGrupoH.get(0)], ListaTimes.getListaTimes()[listaDoGrupoH.get(1)], ListaTimes.getListaTimes()[listaDoGrupoH.get(2)], ListaTimes.getListaTimes()[listaDoGrupoH.get(3)],apostador,42);
		tabbedPane.addTab("Grupo H", null, panel_7, null);
		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel(apostador.getNome()+" é hora de você apostar. Estás com "+apostador.getPontos()+ " pontos");
		
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		//Neste botâo apostar, o usuário realiza suas apostas
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chama o método aposte() do PanelApostas
				//cada panel realiza o método aposte, apostando nos jogos
				panel.aposte();
				panel_1.aposte();
				panel_2.aposte();
				panel_3.aposte();
				panel_4.aposte();
				panel_5.aposte();
				panel_6.aposte();
				panel_7.aposte();
				
				//mostra quantas apostas já foram feitas
				JOptionPane.showMessageDialog(null, ""+apostador.getNome()+" possui "+ apostador.conteApostas()+" Apostas");

				
				
				TelaInicial tela=new TelaInicial();
				tela.setVisible(true);
				TelaApostas.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaApostas.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);		
		
	}
	
	/**
	 * Construtor para as oitavas de final
	 * @param apostador1
	 * @param nome
	 */
	//construtor para as oitavas
	public TelaApostas(Apostador apostador1, String nome) {
		//salva o indice do listaApostadores
		
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		this.apostador=apostador1;
		//Apostador recebe o parâmetro apostador1
		//timesCopa recebe uma instância de TimesParticipantes, uma classe que possui os times que participam do mundial, e que são acessados publicamente.
		setTitle("Apostas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelApostas(apostador, "oitavas");
		tabbedPane.addTab("Oitavas de Final", null, panel, null);
		panel.setLayout(null);
		
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		
		/**
		 * Estes arrayLists servem para pegarmos quais são os tiems de cada grupo
		 */
		

		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel(apostador.getNome()+" é hora de você apostar. Estás com "+apostador.getPontos()+ " pontos");
		
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		//Neste botâo apostar, o usuário realiza suas apostas
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.aposteNasOitavas();
				JOptionPane.showMessageDialog(null, ""+apostador.getNome()+" possui "+ apostador.conteApostas()+" Apostas");
				
				TelaInicial tela=new TelaInicial();
				tela.setVisible(true);
				TelaApostas.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaApostas.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);		
		
	}
	
	/**
	 * Construtor para as quartas de final
	 * @param apostador1
	 * @param nome
	 * @param nome2
	 */
	public TelaApostas (Apostador apostador1, String nome, String nome2){
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		this.apostador=apostador1;
		//Apostador recebe o parâmetro apostador1
		//timesCopa recebe uma instância de TimesParticipantes, uma classe que possui os times que participam do mundial, e que são acessados publicamente.
		setTitle("Apostas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelApostas(apostador, "oitavas","quartas");
		tabbedPane.addTab("Quartas de Final", null, panel, null);
		panel.setLayout(null);
		
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		
		/**
		 * Estes arrayLists servem para pegarmos quais são os tiems de cada grupo
		 */
		

		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel(apostador.getNome()+" é hora de você apostar. Estás com "+apostador.getPontos()+ " pontos");
		
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		//Neste botâo apostar, o usuário realiza suas apostas
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.aposteNasQuartas();
				JOptionPane.showMessageDialog(null, ""+apostador.getNome()+" possui "+ apostador.conteApostas()+" Apostas");
				
				TelaInicial tela=new TelaInicial();
				tela.setVisible(true);
				TelaApostas.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaApostas.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);	
		
	}
	
	
	/**
	 * Construtor para a semifinal
	 * @param apostador1
	 * @param oitavas
	 * @param quartas
	 * @param semi
	 */
	
	public TelaApostas (Apostador apostador1, String oitavas, String quartas, String semi){
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		this.apostador=apostador1;
		setTitle("Apostas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelApostas(apostador, "oitavas","quartas", "semi");
		tabbedPane.addTab("Semi- Final", null, panel, null);
		panel.setLayout(null);
		
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		
		/**
		 * Estes arrayLists servem para pegarmos quais são os tiems de cada grupo
		 */
		

		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel(apostador.getNome()+" é hora de você apostar. Estás com "+apostador.getPontos()+ " pontos");
		
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		//Neste botâo apostar, o usuário realiza suas apostas
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.aposteNasSemi();
				JOptionPane.showMessageDialog(null, ""+apostador.getNome()+" possui "+ apostador.conteApostas()+" Apostas");
				
				TelaInicial tela=new TelaInicial();
				tela.setVisible(true);
				TelaApostas.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaApostas.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);	
		
	}
	
	/**
	 * Construtor para a final
	 * @param apostador1
	 * @param oitavas
	 * @param quartas
	 * @param semi
	 */
	public TelaApostas (Apostador apostador1, String oitavas, String quartas, String semi, String finais){
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		this.apostador=apostador1;
		setTitle("Apostas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o TabbedPane. São as abas que vimos na tela.
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 575, 324);
		contentPane.add(tabbedPane);
		//chama o construtor das oitavas de final
		this.panel=new PanelApostas(apostador, "oitavas","quartas", "semi", "final");
		tabbedPane.addTab("Final", null, panel, null);
		panel.setLayout(null);
		
		
		//Cada panel criado será adicionado ao Tabbed Pane. 
		
		/**
		 * Estes arrayLists servem para pegarmos quais são os tiems de cada grupo
		 */
		

		
		//JLabel que diz o nome do apostador e quantos pontos ele possui no momento
		JLabel lblNewLabel = new JLabel(apostador.getNome()+" é hora de você apostar. Estás com "+apostador.getPontos()+ " pontos");
		
		lblNewLabel.setBounds(10, 21, 373, 21);
		contentPane.add(lblNewLabel);
		//Neste botâo apostar, o usuário realiza suas apostas
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.aposteNasFinais();
				JOptionPane.showMessageDialog(null, ""+apostador.getNome()+" possui "+ apostador.conteApostas()+" Apostas");
				
				TelaInicial tela=new TelaInicial();
				tela.setVisible(true);
				TelaApostas.this.dispose();
				//deixa a tela inicial visível
			}
		});
		btnNewButton.setBounds(20, 385, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaApostas.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);	
		
	}
	
	public TelaApostas(Apostador apostador1, boolean acabou){
		for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
			if(GrupoApostadores.getListaApostadores().get(i)==(apostador1)){
				this.indice=i;
				}
		}
		this.apostador=apostador1;
		setTitle("Apostas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 476);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaApostas.this.dispose();
			}
		});
		btnVoltar.setBounds(154, 385, 89, 23);
		contentPane.add(btnVoltar);	
		JLabel lblResultado = null;
		
		if(GrupoApostadores.getClassificacao().get(0).equals(apostador1)){
		lblResultado = new JLabel("Parabéns "+apostador1.getNome()+" você foi o 1º colocado do bolão com "+apostador.getPontos()+" pontos");
		}
		else{
			lblResultado = new JLabel(apostador1.getNome()+" você terminou em "+apostador.getRanking()+"º com "+apostador.getPontos()+" pontos");
		}
		
		lblResultado.setBounds(100, 200, 400, 50);
		contentPane.add(lblResultado);
	}
	
	
}
