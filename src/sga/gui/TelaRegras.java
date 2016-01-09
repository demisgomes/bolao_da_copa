package sga.gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class TelaRegras extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public TelaRegras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegrasDoBolo = new JLabel("Regras do Bol\u00E3o");
		lblRegrasDoBolo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegrasDoBolo.setBounds(164, 11, 151, 22);
		contentPane.add(lblRegrasDoBolo);
		
		JLabel label = new JLabel("1 - ");
		label.setBounds(10, 62, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("2 -");
		label_1.setBounds(10, 87, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("3 -");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 112, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("4 -");
		label_3.setBounds(10, 143, 46, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("5 -");
		label_4.setBounds(10, 168, 46, 14);
		contentPane.add(label_4);
		
		JLabel lblACor = new JLabel("A cor ");
		lblACor.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblACor.setBounds(40, 62, 46, 14);
		contentPane.add(lblACor);
		
		JLabel lblVermelho = new JLabel("Vermelha");
		lblVermelho.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVermelho.setForeground(Color.RED);
		lblVermelho.setBounds(76, 62, 46, 14);
		contentPane.add(lblVermelho);
		
		JLabel lblSignificaQueVoc = new JLabel("significa que voc\u00EA n\u00E3o acertou nem o placar nem o resultado.");
		lblSignificaQueVoc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSignificaQueVoc.setBounds(132, 62, 360, 14);
		contentPane.add(lblSignificaQueVoc);
		
		JLabel lblSignificaQueVoc_1 = new JLabel("significa que voc\u00EA acertou apenas o resultado.");
		lblSignificaQueVoc_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSignificaQueVoc_1.setBounds(132, 87, 310, 14);
		contentPane.add(lblSignificaQueVoc_1);
		
		JLabel lblSignificaQueVoc_2 = new JLabel("significa que voc\u00EA acertou o placar e o resultado.");
		lblSignificaQueVoc_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSignificaQueVoc_2.setBounds(132, 112, 310, 14);
		contentPane.add(lblSignificaQueVoc_2);
		
		JLabel lblVerde = new JLabel("Verde");
		lblVerde.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVerde.setForeground(Color.GREEN);
		lblVerde.setBounds(76, 87, 46, 14);
		contentPane.add(lblVerde);
		
		JLabel lblAzul = new JLabel("Azul");
		lblAzul.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblAzul.setForeground(Color.BLUE);
		lblAzul.setBounds(76, 112, 46, 14);
		contentPane.add(lblAzul);
		
		JLabel label_5 = new JLabel("A cor ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_5.setBounds(40, 87, 46, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("A cor ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_6.setBounds(40, 112, 46, 14);
		contentPane.add(label_6);
		
		JLabel lblVocSPoder = new JLabel("Voc\u00EA s\u00F3 poder\u00E1 ter acesso \u00E0 pr\u00F3xima fase quando a anterior estiver com os resultados completos. \r\nEnquanto isso n\u00E3o ocorrer, voc\u00EA poder\u00E1 apostar e editar livremente as apostas j\u00E1 realizadas.");
		lblVocSPoder.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVocSPoder.setBounds(40, 143, 799, 14);
		contentPane.add(lblVocSPoder);
		
		JLabel lblUmaVezQue = new JLabel("Uma vez que os resultados forem definidos pelo administrador, voc\u00EA n\u00E3o poder\u00E1 apostar nem editar suas apostas.");
		lblUmaVezQue.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblUmaVezQue.setBounds(40, 168, 560, 14);
		contentPane.add(lblUmaVezQue);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial tela = new TelaInicial();
				tela.setVisible(true);
				TelaRegras.this.dispose();
			}
		});
		btnVoltar.setBounds(175, 241, 89, 23);
		contentPane.add(btnVoltar);
	}

}
