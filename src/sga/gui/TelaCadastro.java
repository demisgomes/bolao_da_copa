package sga.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;
import sga.persistencia.ApostadorDAO;

/**
 * Tela onde é realizado o cadastro dos usuários
 * @author Daniel
 *
 */
public class TelaCadastro extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private ApostadorDAO a_dao = new ApostadorDAO();
	

	
	public TelaCadastro() {
		//Labels e TextFields
		setBounds(100, 100, 282, 210);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 26, 49, 14);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 55, 49, 14);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 86, 49, 14);
		
		textField = new JTextField();
		textField.setBounds(69, 23, 109, 20);

		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(69, 52, 109, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(69, 83, 109, 20);
		textField_2.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10, 139, 94, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			
			/**
			 * Realiza o cadastro dos clientes
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				//cadastra o apostador no banco, através do método cadastrarApostador
				//se o cadastro foi realizado com sucesso, o método retorna true
				if(a_dao.cadastrarApostador(textField.getText(), textField_1.getText(), textField_2.getText())==true){
					JOptionPane.showMessageDialog(null, "Usuário Cadastrado com sucesso");
					//adiciona-o na lista de apostadores
					//criando uma instância do objeto apostador
					GrupoApostadores.addApostador(new Apostador(textField.getText()));
					TelaInicial tela=new TelaInicial();
					tela.setVisible(true);
					TelaCadastro.this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro no cadastro. tente novamente");
					
				}
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblNome);
		getContentPane().add(textField);
		getContentPane().add(lblLogin);
		getContentPane().add(textField_1);
		getContentPane().add(lblSenha);
		getContentPane().add(textField_2);
		getContentPane().add(btnCadastrar);
		
		//button para voltar à tela inicial.
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInicial().setVisible(true);
				TelaCadastro.this.dispose();
				
			}
		});
		btnVoltar.setBounds(138, 139, 89, 23);
		getContentPane().add(btnVoltar);
		
	}
	}
