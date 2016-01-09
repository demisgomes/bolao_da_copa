package sga.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sga.dominio.copa.Time;
import sga.persistencia.TimesDAO;

public class TelaPenaltis extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Time timeVencedor;
	private int idJogo;
	private Time time1;
	private Time time2;
	private Time timePerdedor;
	private TimesDAO t_dao=new TimesDAO();

	
	/**
	 * Create the frame.
	 */
	public Time getTimeVencedor() {
		return timeVencedor;
	}
	public TelaPenaltis(){}
	
	public TelaPenaltis(Time primeirotime, Time segundotime, int idJogo1) {
		this.time1=primeirotime;
		this.time2=segundotime;
		this.idJogo=idJogo1;
		setTitle("P\u00EAnaltis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEsteJogoTerminou = new JLabel("Este jogo terminou empatado. Ent\u00E3o ponha o placar das penalidades m\u00E1ximas.");
		lblEsteJogoTerminou.setBounds(5, 5, 401, 14);
		contentPane.add(lblEsteJogoTerminou);
		
		JLabel lblNewLabel = new JLabel(time1.getNome());
		lblNewLabel.setBounds(5, 57, 132, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel(time2.getNome());
		label.setBounds(269, 57, 132, 14);
		contentPane.add(label);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(176, 57, 28, 14);
		contentPane.add(lblX);
		
		textField = new JTextField();
		textField.setBounds(146, 54, 20, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(186, 54, 20, 20);
		contentPane.add(textField_1);
		
		JButton btnOk = new JButton("Por Resultado");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if((Integer.parseInt(textField.getText()))>((Integer.parseInt(textField_1.getText())))){
						timeVencedor=time1;
						timePerdedor=time2;
					}
					else{
						if((Integer.parseInt(textField.getText()))<((Integer.parseInt(textField_1.getText())))){
							timeVencedor=time2;
							timePerdedor=time1;
						}
						else{
							JOptionPane.showMessageDialog(null, "O resultado dos pênaltis não pode ser um empate, insira novamente os resultados");
							TelaPenaltis tela=new TelaPenaltis(time1, time2, idJogo);
							tela.setVisible(true);
							TelaPenaltis.this.dispose();
						}
					}
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
					t_dao.insiraPosicao(timeVencedor,"V56", "posicao_semi" );
				}
				if (idJogo==57){
					t_dao.insiraPosicao(timeVencedor,"V57", "posicao_semi");
				}
				if (idJogo==58){
					t_dao.insiraPosicao(timeVencedor,"V58", "posicao_semi" );
				}
				if (idJogo==59){
					t_dao.insiraPosicao(timeVencedor,"V59", "posicao_semi" );
				}
				if (idJogo==60){
					t_dao.insiraPosicao(timeVencedor,"V60", "posicao_final" );
					t_dao.insiraPosicao(timePerdedor,"P60", "posicao_final" );
				}
				if (idJogo==61){
					t_dao.insiraPosicao(timeVencedor,"V61", "posicao_final" );
					t_dao.insiraPosicao(timePerdedor,"P61", "posicao_final" );
				}
				if (idJogo==62){
					t_dao.insiraPosicao(timeVencedor,"3º Lugar", "campeao" );
					t_dao.insiraPosicao(timePerdedor,"4º Lugar", "campeao" );
				}
				if (idJogo==63){
					t_dao.insiraPosicao(timeVencedor,"Campeão", "campeao" );
					t_dao.insiraPosicao(timePerdedor,"Vice-Campeão", "campeao" );
				}
				TelaPenaltis.this.dispose();
			}
			
		
	
		});
		btnOk.setBounds(106, 109, 149, 23);
		contentPane.add(btnOk);
	}
}
