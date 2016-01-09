package sga.gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;
import sga.dominio.copa.GrupoCopa;
import sga.dominio.copa.ListaJogos;
import sga.dominio.copa.ListaTimes;
import sga.persistencia.ApostadorDAO;
import sga.persistencia.ConectaBanco;
import sga.persistencia.JogosDAO;
import sga.persistencia.TimesDAO;


public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField textField_2;
	public Apostador apostador=new Apostador();
	private JTextField LoginADM;
	private JPasswordField SenhaADM;
	private ApostadorDAO AdApostador=new ApostadorDAO();
	private static TimesDAO timesDAO;

	/**
	 * Carrega os dados do banco antes mesmo do main
	 * <br>
	 * Carrega-se a lista de times, a lista dos apostadores, a lista dos jogos e a lista de apostas de cada apostador
	 */
	static{

		//CArrega os dados dos resultados já realizados antes mesmo do main
		//carrega os times do banco
		timesDAO = new TimesDAO();
		ApostadorDAO a_dao=new ApostadorDAO();
		JogosDAO JDao=new JogosDAO();
		
		//adiciona os jogos que já ocorreram na lista da jogos
		JDao.addInResultados();
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
		
		
		
		
	}
	

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		
		GrupoApostadores.gerarClassificacaoApostadores();
		GrupoApostadores.InserirPosicaoJogador();
		
		
		
		
		if((ListaJogos.conteJogos()>=48)&&(ListaJogos.conteJogos()<56)){
			if(timesDAO.verificarOitavas()==false){
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
				GrupoCopa grupoA=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoA.get(0)], ListaTimes.getListaTimes()[listaDoGrupoA.get(1)], ListaTimes.getListaTimes()[listaDoGrupoA.get(2)], ListaTimes.getListaTimes()[listaDoGrupoA.get(3)]);
				GrupoCopa grupoB=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoB.get(0)], ListaTimes.getListaTimes()[listaDoGrupoB.get(1)], ListaTimes.getListaTimes()[listaDoGrupoB.get(2)], ListaTimes.getListaTimes()[listaDoGrupoB.get(3)]);
				GrupoCopa grupoC=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoC.get(0)], ListaTimes.getListaTimes()[listaDoGrupoC.get(1)], ListaTimes.getListaTimes()[listaDoGrupoC.get(2)], ListaTimes.getListaTimes()[listaDoGrupoC.get(3)]);
				GrupoCopa grupoD=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoD.get(0)], ListaTimes.getListaTimes()[listaDoGrupoD.get(1)], ListaTimes.getListaTimes()[listaDoGrupoD.get(2)], ListaTimes.getListaTimes()[listaDoGrupoD.get(3)]);
				GrupoCopa grupoE=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoE.get(0)], ListaTimes.getListaTimes()[listaDoGrupoE.get(1)], ListaTimes.getListaTimes()[listaDoGrupoE.get(2)], ListaTimes.getListaTimes()[listaDoGrupoE.get(3)]);
				GrupoCopa grupoF=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoF.get(0)], ListaTimes.getListaTimes()[listaDoGrupoF.get(1)], ListaTimes.getListaTimes()[listaDoGrupoF.get(2)], ListaTimes.getListaTimes()[listaDoGrupoF.get(3)]);
				GrupoCopa grupoG=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoG.get(0)], ListaTimes.getListaTimes()[listaDoGrupoG.get(1)], ListaTimes.getListaTimes()[listaDoGrupoG.get(2)], ListaTimes.getListaTimes()[listaDoGrupoG.get(3)]);
				GrupoCopa grupoH=new GrupoCopa(ListaTimes.getListaTimes()[listaDoGrupoH.get(0)], ListaTimes.getListaTimes()[listaDoGrupoH.get(1)], ListaTimes.getListaTimes()[listaDoGrupoH.get(2)], ListaTimes.getListaTimes()[listaDoGrupoH.get(3)]);
				
				grupoA.gerarClassificacaoGrupo();
				grupoB.gerarClassificacaoGrupo();
				grupoC.gerarClassificacaoGrupo();
				grupoD.gerarClassificacaoGrupo();
				grupoE.gerarClassificacaoGrupo();
				grupoF.gerarClassificacaoGrupo();
				grupoG.gerarClassificacaoGrupo();
				grupoH.gerarClassificacaoGrupo();
				
				timesDAO.insiraPosicao(grupoA.getClassificacao()[0], "A1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoA.getClassificacao()[1], "A2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoB.getClassificacao()[0], "B1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoB.getClassificacao()[1], "B2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoC.getClassificacao()[0], "C1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoC.getClassificacao()[1], "C2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoD.getClassificacao()[0], "D1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoD.getClassificacao()[1], "D2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoE.getClassificacao()[0], "E1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoE.getClassificacao()[1], "E2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoF.getClassificacao()[0], "F1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoF.getClassificacao()[1], "F2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoG.getClassificacao()[0], "G1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoG.getClassificacao()[1], "G2","posicao_oitavas");
				timesDAO.insiraPosicao(grupoH.getClassificacao()[0], "H1","posicao_oitavas");
				timesDAO.insiraPosicao(grupoH.getClassificacao()[1], "H2","posicao_oitavas");
			}
		}
		
		//LABELS E TEXTFIELDS
		setTitle("P\u00E1gina Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBemVindiAo = new JLabel("Bem vindo ao Bol\u00E3o da Copa");
		lblBemVindiAo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBemVindiAo.setBounds(101, 11, 341, 40);
		contentPane.add(lblBemVindiAo);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(60, 97, 142, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(60, 125, 142, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 100, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 128, 46, 14);
		contentPane.add(lblSenha);
		
		//ENTRAR E APOSTAR
		JButton btnEntrar = new JButton("Entrar e Apostar");
		btnEntrar.addActionListener(new ActionListener() {
			/**
			 * Button que verifica se o usuário está cadastrado no sistema e , em caso positivo, manda-o para a tela de apostas
			 */
			public void actionPerformed(ActionEvent arg0) {
				//verifica se o usuário está cadastrado no banco através do verifyLogin
				if(AdApostador.verifyLogin(textField_1.getText(), textField_2.getText())==true){
					//procura o apostador no grupo
					for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
						if(GrupoApostadores.getListaApostadores().get(i).getNome().equals(AdApostador.getNomeBanco(textField_1.getText()))){
							apostador=GrupoApostadores.getListaApostadores().get(i);
						}
					}
				//manda o apostador como parâmetro na Tela de Apostas
					if((ListaJogos.conteJogos()>=48)&&(ListaJogos.conteJogos()<56)){
						TelaApostas telaOitavas=new TelaApostas(apostador, "Oitavas");
						telaOitavas.setVisible(true);
						TelaInicial.this.dispose();
					}
					else if((ListaJogos.conteJogos()>=56)&&(ListaJogos.conteJogos()<60)){
						TelaApostas telaQuartas = new TelaApostas(apostador,"oitavas","quartas");
						telaQuartas.setVisible(true);
						TelaInicial.this.dispose();
						
					}
					else if((ListaJogos.conteJogos()>=60)&&(ListaJogos.conteJogos()<62)){
						TelaApostas telaSemi = new TelaApostas(apostador,"oitavas","quartas", "semi");
						telaSemi.setVisible(true);
						TelaInicial.this.dispose();
						
					}
					else if((ListaJogos.conteJogos()>61)&&(ListaJogos.conteJogos()<64)){
						TelaApostas telaFinal = new TelaApostas(apostador,"oitavas","quartas", "semi", "finais");
						telaFinal.setVisible(true);
						TelaInicial.this.dispose();
						
					}else if(ListaJogos.conteJogos()>63){
						TelaApostas telaResultadoAposta = new TelaApostas(apostador, true);
						telaResultadoAposta.setVisible(true);
						TelaInicial.this.dispose();
					}
					else{
						TelaApostas telaApostas=new TelaApostas(apostador);
						contentPane.setVisible(false);
						telaApostas.setVisible(true);
						TelaInicial.this.dispose();
						
					}
				
				}
				//se o usuário não está cadastrado, esta mensagem aparece 
				else{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro. tente novamente");
				}
				
				
			}
		});
		btnEntrar.setBounds(70, 163, 132, 23);
		contentPane.add(btnEntrar);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(237, 11, 1, 228);
		contentPane.add(separator);
		
		/**
		 * Botão que leva o usuário para a tela de cadastro
		 */
		JButton btnNewButton = new JButton("Cadastre-se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro telaCadastro =new TelaCadastro();
				telaCadastro.setVisible(true);
				TelaInicial.this.dispose();
			
			}
		});
		btnNewButton.setBounds(299, 198, 113, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Entre e Aposte");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(70, 62, 132, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblAindaNo = new JLabel("Ainda n\u00E3o \u00E9 cadastrado?");
		lblAindaNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAindaNo.setBounds(270, 165, 182, 14);
		contentPane.add(lblAindaNo);
		
		JLabel lblNewLabel_1 = new JLabel("Inserir Resultados");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(70, 225, 132, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblLoginadm = new JLabel("LoginADM:");
		lblLoginadm.setBounds(10, 257, 82, 14);
		contentPane.add(lblLoginadm);
		
		LoginADM = new JTextField();
		LoginADM.setColumns(10);
		LoginADM.setBounds(85, 250, 117, 20);
		contentPane.add(LoginADM);
		
		JLabel lblSenhaadm = new JLabel("SenhaADM:");
		lblSenhaadm.setBounds(10, 293, 82, 14);
		contentPane.add(lblSenhaadm);
		
		SenhaADM = new JPasswordField();
		SenhaADM.setColumns(10);
		SenhaADM.setBounds(85, 290, 117, 20);
		contentPane.add(SenhaADM);
		/**
		 * Button que faz o administrador entrr na sua tela, e pôr os resultados das apostas
		 */
		JButton btnEntrar_1 = new JButton("Entrar");
		btnEntrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConectaBanco conecte=new ConectaBanco("jdbc:mysql://localhost/sga", "root", "1234");
				//verifica se o login é o informado, através do método verifyAdmin
				if(conecte.verifyAdmin(LoginADM.getText(), SenhaADM.getText())==true){		
					//Se é, direciona-o para a tela de resultados
					if((ListaJogos.conteJogos()>=48)&&(ListaJogos.conteJogos()<56)){
						TelaResultados telaOitavas=new TelaResultados(apostador,"Oitavas");
						telaOitavas.setVisible(true);
						TelaInicial.this.dispose();
					}else if((ListaJogos.conteJogos()>=56)&&(ListaJogos.conteJogos()<60)){
						TelaResultados telaQuartas = new TelaResultados(apostador,"oitavas", "quartas");
						telaQuartas.setVisible(true);
						TelaInicial.this.dispose();
					}
					else if((ListaJogos.conteJogos()>=60)&&(ListaJogos.conteJogos()<62)){
						TelaResultados telaSemi = new TelaResultados(apostador,"oitavas","quartas", "semi");
						telaSemi.setVisible(true);
						TelaInicial.this.dispose();
						
					}
					else if((ListaJogos.conteJogos()>=62)&&(ListaJogos.conteJogos()<64)){
						TelaResultados telaSemi = new TelaResultados(apostador,"oitavas","quartas", "semi", "finais");
						telaSemi.setVisible(true);
						TelaInicial.this.dispose();
						
					}
					else{
						TelaResultados tela=new TelaResultados(apostador);
						contentPane.setVisible(false);
						tela.setVisible(true);
						TelaInicial.this.dispose();
						
					}
					
					
					
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro. tente novamente");
				}
			}
		});
		btnEntrar_1.setBounds(70, 334, 113, 23);
		contentPane.add(btnEntrar_1);
		
		JLabel lblClassificao = new JLabel("Classifica\u00E7\u00E3o");
		lblClassificao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClassificao.setBounds(313, 336, 99, 14);
		contentPane.add(lblClassificao);
		
		JButton btnConferir = new JButton("Conferir");
		btnConferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaClassApostadores().setVisible(true);
				TelaInicial.this.dispose();
			}
		});
		btnConferir.setBounds(309, 360, 89, 23);
		contentPane.add(btnConferir);
		
		JLabel lblSgaAutoresChristian = new JLabel("Autores: Christian, Daniel, Demis, Diego.");
		lblSgaAutoresChristian.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSgaAutoresChristian.setBounds(290, 421, 182, 14);
		contentPane.add(lblSgaAutoresChristian);
		
		JButton btnRegras = new JButton("Conferir");
		btnRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRegras telaRegras = new TelaRegras();
				telaRegras.setVisible(true);
				TelaInicial.this.dispose();
			}
		});
		btnRegras.setBounds(81, 412, 89, 23);
		contentPane.add(btnRegras);
		
		JLabel lblRegras = new JLabel("Regras");
		lblRegras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegras.setBounds(100, 387, 70, 20);
		contentPane.add(lblRegras);
	}
}
