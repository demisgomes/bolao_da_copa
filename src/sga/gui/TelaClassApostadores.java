package sga.gui;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;

/**
 * Tela que mostra a classificação para os usuários
 * @author Demis
 *
 */
public class TelaClassApostadores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;


	/**
	 * Create the frame.
	 */
	public TelaClassApostadores() {
		
		setTitle("Classifica\u00E7\u00E3o dos Apostadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 281);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//chama o método que gera a classificação
		//na classe GrupoApostadores
		GrupoApostadores.gerarClassificacaoApostadores();
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaClassApostadores.this.dispose();
			}
		});
		btnVoltar.setBounds(77, 201, 89, 23);
		contentPane.add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 11, 183, 166);
		contentPane.add(scrollPane);
		
		
		//método que o professor Gabriel fez
		ArrayList<Apostador> classificacao = GrupoApostadores.getClassificacao();
		//cria um objeto chamado dados
		Object[][] dados = new Object[classificacao.size()][2];
		int idx = 0;
		//para cada apostador na classificação
		for (Apostador apostador : classificacao) {
			//o primeiro dado será o nome
			//o segundo será os pontos
			dados[idx][0] = apostador.getNome();
			dados[idx][1] = apostador.getPontos();
			idx++;
		}
		//Os nomes das colunas serão Nome e pontos
		Object[] colunas = {"Nome","Pontos"};
		table_1 = new JTable(dados,colunas);
		//adiciona a JTable no scrollpane
		scrollPane.setViewportView(table_1);
		
		}
}
