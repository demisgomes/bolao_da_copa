package sga.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;

import sga.dominio.aposta.Aposta;
import sga.dominio.aposta.Apostador;
import sga.dominio.aposta.GrupoApostadores;

public class ApostadorDAO extends Apostador {
	ConectaBanco conectaBanco = new ConectaBanco("jdbc:mysql://localhost/sga", "root", "1234");
	
	
	/**
	 * M�todo que incrementa o n�mero de apostas em um jogo
	 * @param idJogo
	 */
	public void setQtdApostas(int idJogo){
		Connection conn = null;
		Statement st = null;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conn.createStatement();

			String consulta= "Select * from jogos_apostas where id_jogo='"+idJogo+"'";
			ResultSet rs=st.executeQuery(consulta);
			
			
			rs.last();  
			int i = rs.getRow(); 
			if (i==0) {
				Statement st1= conn.createStatement();
				String consulta1= "INSERT INTO jogos_apostas (id_jogo, qtd_apostas) VALUES ('"+idJogo+"', 1) ";
				boolean rs1=st1.execute(consulta1);
			
				
			}else {
				Statement st1= conn.createStatement();
				String consulta1= "Update jogos_apostas set qtd_apostas = qtd_apostas+1 where id_jogo='"+idJogo+"'";
				boolean rs1=st1.execute(consulta1);
				
			}
			
			
			
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}		
	}
	/**
	 * M�todo que resgata a quantidade de apostas do banco de dados
	 * @param idJogo
	 * @return
	 */	
	public int getQtdApostas(int idJogo){
		Connection conn = null;
		Statement st = null;
		int qtd_apostas=0;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conn.createStatement();			
			String consulta= "Select qtd_apostas from jogos_apostas where id_jogo='"+idJogo+"'";
			//selecionamos do banco todas as linhas que possuem a coluna id_jogo igual ao que passamos			
			ResultSet rs=st.executeQuery(consulta);
			if(rs.next()){
				qtd_apostas=rs.getInt("qtd_apostas");
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}
		//No final, ele retorna count. Quantas apostas j� foram feitas no jogo.
		return qtd_apostas;
	}
	
	/**
	 * Carrega os apostadores do banco e os adicionam numa lista
	 * @return
	 */
	public ArrayList<Apostador> addApostadorGrupo(){
		Connection conn = null;
		Statement st = null;
		int pontuacao = 0;
		ArrayList<String>lista=new ArrayList<String>();
		//pega todos os nomes do banco da coluna nome
		//atrav�s do m�todo getNomeColuna
		lista=conectaBanco.getNomeColuna("nome");
		try{
		 conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
		st = conn.createStatement();
		String pesquisa = "Select * from usuarios where nome = '"+lista+"'";
		ResultSet rs = st.executeQuery(pesquisa);
		if(rs.next()){
			pontuacao = rs.getInt("pontos");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}
		for(int i=0;i<lista.size();i++){
			//cria uma inst�ncia de Apostador para cada nome
			//e adiciona na lista est�tica da classe GrupoApostadores.
			Apostador apostador = new Apostador(lista.get(i));
			apostador.addPontos(pontuacao);
    		GrupoApostadores.addApostador(apostador);
    	}
		
		return GrupoApostadores.getListaApostadores();
		
		
	}
	/**
	 * Verifica se o jogo ainda n�o aconteceu.
	 * @param idJogo
	 * @return
	 */
	
	public boolean verifyAposta(int idJogo){
		Connection conn = null;
		try{
			
			String comando="Select * from resultados where id_jogo='"+idJogo+"'";
			//seleciona  da tabela resultados a linha que posswui o id daquele jogo
			ResultSet rs=conectaBanco.conectar().executeQuery(comando);
			if(rs.next()){
				//se achou, ou seja, se o jogo j� aconteceu, retorna true
				conn.close();
				return true;
			}
			conn.close();
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//se n�o, retorna false
		return false;
	}
	
	
	/**
	 * Insere a aposta no banco de dados
	 * <br>
	 * Tamb�m d� o update, ou seja, se a aposta j� ocorreu apenas atualiza a aposta, se o usu�rio atualizou-a.
	 * @param aposta
	 * @param idJogo
	 */
	public void inserirAposta(Aposta aposta, int idJogo){
		try{
			boolean trocou=false;
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			//Verifica se h� uma aposta com mesmo id
			String verificacao="Select * from apostas where id_jogo='"+idJogo+"'";
			Statement st= conn.createStatement();
    		ResultSet rs=st.executeQuery(verificacao);
    		//se existir, entra neste while
    		while(rs.next()){
    			//enquanto um jogo tiver sido apostado, ele procura o usu�rio que fez a aposta
    
    			//pega o id do usu�rio que fez a aposta
    			int id_jogo_comparacao=rs.getInt("id_usuario");
    			//se o id do usu�rio for o mesmo do da aposta, ele altera os valores.
    			
    			//se o usu�rio fez a aposta, ele entra no if
	    		if(this.getIdApostador(aposta.getApostador().getNome())==id_jogo_comparacao){
	    			//executa-se esta linha
	    			//troca os valores dos gols do time 1 e dos gols do time 2 onde o id do jogo � o informada
	    			// e onde o id do usu�rio � o id dele
	    			verificacao="UPDATE apostas set gols_time_1='"+aposta.getGolsTime1()+"',gols_time_2='"+aposta.getGolsTime2()+"' where id_usuario='"+this.getIdApostador(aposta.getApostador().getNome())+"' and id_jogo='"+idJogo+"'";
	    			PreparedStatement stmt = conn.prepareStatement(verificacao);
	    			stmt.execute();
	    			stmt.close();
	    			//se foi feito o update, a vari�vel trocou recebe true.
	    			trocou=true;
	    			break;
	    		}
	    		else continue;
    		}
	    	//se o update n�o aconteceu, quer dizer que a aposta ainda n�o foi feita
    		if(trocou==false){
					String comando="Insert into apostas (time_1, time_2, gols_time_1, gols_time_2, id_usuario, id_jogo) VALUES(?,?,?,?,?,?)";
					//inserimos normalmente os dados no banco
					try{
						PreparedStatement stmt = conn.prepareStatement(comando);      
						  
		                stmt.setString(1, aposta.getTime1().getNome());      
		                stmt.setString(2, aposta.getTime2().getNome());      
		                stmt.setInt(3, aposta.getGolsTime1());
		                stmt.setInt(4, aposta.getGolsTime2());
		                stmt.setInt(5, this.getIdApostador(aposta.getApostador().getNome()));
		                stmt.setInt(6, idJogo);
		                stmt.execute(); //executa comando     
		                stmt.close();  
		                conn.close();
		                setQtdApostas(idJogo);
					}
					catch(SQLException exception){
						exception.printStackTrace();
						throw new RuntimeException(exception);
					}
	    		
    		}
    		
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
			
			
	}
	
	/**
	 * Retorna o nome de um usu�rio do banco de dados
	 * 
	 */
	public String getNomeBanco(String login){
		Connection conn = null;
		Statement st = null;
		
		try{			
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
    		st= conn.createStatement();
    		String comando="Select * from usuarios where login='"+login+"'";
    		ResultSet rs=st.executeQuery(comando);
    		if (rs.next()){
    			String nome=rs.getString("nome");
    			return nome;
    		}
    		else return null;
    		
    	}
    	catch (Exception e){
    			e.printStackTrace();
    		}
		finally{
			fecharConexao(st, conn);
		}
		
		return null;
    	
    	}
	
	/**
	 * Retorna o id do apostador cadastrado no banco
	 * <br>
	 * � �til para opera��es de inser��o no banco, como em inserirAposta
	 * @param nomeApostador
	 * @return
	 */
	public int getIdApostador(String nomeApostador){
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conn.createStatement();
			String comando="Select * from usuarios where nome='"+nomeApostador+"'";
    		ResultSet rs=st.executeQuery(comando);
    		//verifica se o nome est� no banco 
    		if (rs.next()){
    			int id=rs.getInt("idusuarios");
    			return id;
    		}
    		//se sim, pega o id do usu�rio e o retorna
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return -1;
		
	}
	
	/**
	 * Adiciona as apostas que o usu�rio fez na sua lista de apostas
	 * @param nomeApostador
	 */
	public void addInListaApostas(String nomeApostador){
		Connection conn = null;
		Statement st = null;
		try {
			Apostador apostador=new Apostador();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conn.createStatement();
			//seleciona do banco a linha onde o id_usuario(chave estrangeira) da tabela apostas � igual ao id do apostador 
			String comando="Select * from apostas where id_usuario='"+this.getIdApostador(nomeApostador)+"'";
			ResultSet rs=st.executeQuery(comando);
			int i;

			//pega o �ndice da lista de apostadores. 
			//Ou seja, salva o apostador que fez a aposta
			for (i=0;i<GrupoApostadores.getListaApostadores().size();i++){
				if(GrupoApostadores.getListaApostadores().get(i).getNome().equals(nomeApostador)){
					apostador=GrupoApostadores.getListaApostadores().get(i);
					break;
					}
			} 
			while (rs.next()){
				//para cada aposta encontrada
				//cria uma inst�ncia da classe Aposta
				//seta todos os atributos nela
    			int idAposta=rs.getInt("id_jogo");
    			int golsTime1 =rs.getInt("gols_time_1");
    			int golsTime2=rs.getInt("gols_time_2");
    			Aposta aposta=new Aposta(apostador);
    			aposta.setGolsTime1(golsTime1);
    			aposta.setGolsTime2(golsTime2);
    			apostador.listaApostas[idAposta]=(aposta);
    			
    			//adiciona-a na lista de apostas do Apostador que foi salvo
    			//no id correspondente
    			GrupoApostadores.getListaApostadores().get(i).getListaApostas()[idAposta]=aposta;
    		}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}
		
		
	}
	/**
	 * Insere os pontos no banco, na tabela usu�rios
	 * @param nomeApostador
	 * @param pontos
	 */
	public void incrementePontos(String nomeApostador, int pontos){
		Connection conn = null;
		Statement st = null;
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			 st= conn.createStatement();
			//realiza uma consulta na tabela usu�rios
			//onde h� o nome do apostador
			String comando="Select * from usuarios where nome='"+nomeApostador+"'";
			ResultSet rs=st.executeQuery(comando);
			if(rs.next()){
				//se achou o nome dele
				//insere os pontos na tabela
				//e onde o id do usu�rio for aquele.
				String update_pontos="update usuarios set pontos='"+pontos+"' where idusuarios='"+this.getIdApostador(nomeApostador) +"'";
				PreparedStatement stmt = conn.prepareStatement(update_pontos);
    			stmt.execute();
    			stmt.close();
			}
		}
		
		catch(Exception exc){
			exc.printStackTrace();
			
		}
		//fecha a conex�o
		finally{
			fecharConexao(st, conn);
		}
		
	}
	
	/**
	 * Carrega os pontos do banco para inserir as informa��es na classifica��o dos apostadores
	 * @param nomeApostador
	 */
	
	
	public void carreguePontos(String nomeApostador){
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conn.createStatement();
			//realiza uma consulta na tabela usu�rios
			//onde h� o nome do apostador
			String comando="Select * from usuarios where nome='"+nomeApostador+"'";
			ResultSet rs=st.executeQuery(comando);
			
			if (rs.next()){
    			int pontos=rs.getInt("pontos");
    			//se achou, percorre a lista est�tica ListaApostadores
    			for (int i=0;i<GrupoApostadores.getListaApostadores().size();i++){
    				//se o nome do apostador for igual ao nome passado com par�metro
    				if(GrupoApostadores.getListaApostadores().get(i).getNome().equals(nomeApostador)){
    					//p�e os pontos no apostador
    					//contido na lista est�tica
    					GrupoApostadores.getListaApostadores().get(i).addPontos(pontos);
    					break;
    					}
    			}
    			
    		}
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}
		
		
	}
	
	/**
	 * Verifica se a aposta j� foi feita
	 * <br>
	 * M�todo para auxiliar na hora de computar a aposta 
	 * <br>
	 * Procura no banco a coluna situacao_jogo. Se o valor � 1, a aposta j� foi computada, retornando true.
	 * <br>
	 * Se n�o, retorna false, fazendo com que a aposta possa ser contabilizada posteriormente.
	 * @param idJogo
	 * @param nomeApostador
	 * @return
	 */
	public boolean apostaJaComputada(int idJogo, String nomeApostador){
		Connection conn = null;
		Statement st = null;
		try{
			if(idJogo==49){
	
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			String comando="Select * from apostas where id_jogo='"+idJogo+"' AND id_usuario='"+this.getIdApostador(nomeApostador)+"'";
			st=conn.createStatement();
			ResultSet rs=st.executeQuery(comando);
			if(rs.next()){
				int situacao_jogo=rs.getInt("situacao_aposta");
				if(situacao_jogo==1){
					return true;
				}
				else return false;
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}
		return false;
	}
	
	/**
	 * COMPUTA A APOSTA NO BANCO
	 * <BR>
	 * Caso o jogo foi realizado e a aposta ainda n�o foi contabilizada, ele muda a situacao da aposta de 0 (n�o computada) para 1 (j� computada)
	 * 	 
	 * @param idJogo
	 * @param nomeApostador
	 */
	
	public void computeAposta(int idJogo, String nomeApostador){
		Connection conn = null;
		Statement st = null;
		try{
			if(idJogo==49){
				
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			String comando="Select * from apostas where id_jogo='"+idJogo+"' AND id_usuario='"+this.getIdApostador(nomeApostador)+"'";
			st=conn.createStatement();
			ResultSet rs=st.executeQuery(comando);
			if(rs.next()){
				int situacao_jogo=rs.getInt("situacao_aposta");
				if(situacao_jogo==0){
					String update="update apostas set situacao_aposta='"+1+"' where id_jogo='"+idJogo +"' AND id_usuario='"+this.getIdApostador(nomeApostador)+"'";
					PreparedStatement stmt = conn.prepareStatement(update);
	    			stmt.execute();
	    			stmt.close();
				
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			fecharConexao(st, conn);
		}
	}
	
	/**
	 * P�e na tabela apostas a pontuacao que a aposta obteve
	 * <br>
	 * Na mesma linha do id da aposta, p�e a pontua��o para que possamos resgat�-la no banco
	 * @param pontuacao
	 * @param idJogo
	 * @param idUsuario
	 */
	
	public void setPontuacaoAposta(int pontuacao, int idJogo, int idUsuario){
		Connection conn = null;
		Statement st = null;
		try {
			if(idJogo==49){
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			String pesquisa="select * from apostas  where id_usuario='"+idUsuario+"' and id_jogo='"+idJogo+"'";
			st=conn.createStatement();
			ResultSet rs=st.executeQuery(pesquisa);			
			if(rs.next()){
			//se achou o id do jogo
			//muda a pontua��o naquela linha
			//onde o id do usu�rio � aquele e o id do jogo tbm
			String comando="UPDATE apostas set pontuacao='"+pontuacao+"' where id_usuario='"+idUsuario+"' and id_jogo='"+idJogo+"'";
			PreparedStatement stmt = conn.prepareStatement(comando);
			stmt.execute();
			stmt.close();
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fecharConexao(st, conn);
		}
		
	}
	
	
	/**
	 * Retorna os pontos que o usu�rio boteve naquela aposta espec�fica
	 * @param idJogo
	 * @param id_usuario
	 * @return
	 */
	public int getPontuacaoAposta(int idJogo, int id_usuario){
		int pontos=0;
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			String pesquisa="select * from apostas  where id_usuario='"+id_usuario+"' and id_jogo='"+idJogo+"'";
			st=conn.createStatement();
			ResultSet rs=st.executeQuery(pesquisa);
			
			if(rs.next()){
				//se a aposta aconteceu, pega a pontua��o naquela coluna
				pontos=rs.getInt("pontuacao");
				
			}
			
			//se n�o, retorna 0
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			fecharConexao(st, conn);
		}

		return pontos;
	}
	
	 public void getNomeUsuario(){
		 Connection conn = null;
		 String q = null;
	    	try{
	    		
	    		q+="Select * from usuarios";// cria a pesquisa
	    		ResultSet rs=conectaBanco.conectar().executeQuery(q);
	    		while (rs.next()){//enquanto encontrar resultados na tabela usu�rio.
	    			String nome=rs.getString("nome");// captura o nome
	    			String senha=rs.getString("senha");// captura a senha
	    			String login=rs.getString("login");// captura o login 
	    			
	    		}
	    	}
	    	catch (Exception e){
	    			e.printStackTrace();
	    		}
	    	finally{// fecha a conex�o
				try {
					if(conn!=null){
						conn.close();
						}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
	    	
	    	}
	 
	 /**
 	 * Cadastra um apostador na tabela de usu�rios com nome, login e senha dos par�metros.
 	 * @param nome (String)
 	 * @param login (String)
 	 * @param senha (String)S
 	 * @return true se cadastrar, false se encontrar algum erro.
 	 */
 	public boolean cadastrarApostador(String nome, String login, String senha){
 		Connection conn = null;
 		PreparedStatement stmt = null;
 		try{
 			conn = DriverManager.getConnection(conectaBanco.getUrl(),conectaBanco.getLogin(), conectaBanco.getSenha());
 			String comando="Insert into usuarios (nome, login, senha) VALUES(?,?,?)";
 			
 			try{
 				stmt = conn.prepareStatement(comando);      
 				  
 				stmt.setString(1, nome);  // manda colocar o nome informado no par�metro na 1 coluna informada no comando(coluna nome)    
                 stmt.setString(2, login); // manda colocar o login informado no par�metro na 2 coluna informada no comando(coluna login)     
                 stmt.setString(3, senha); // manda colocar o senha informado no par�metro na 3 coluna informada no comando(coluna senha)  

                 stmt.execute(); //executa comando    
                 stmt.close();  
                 conn.close();
                 return true;
 			}
 			
 			catch(SQLException exception){
 				exception.printStackTrace();
 				throw new RuntimeException(exception);
 			}
 			
 		}
 		catch(Exception exc){
 			exc.printStackTrace();
 		}
 		finally{
 			fecharConexao(stmt, conn);
 		}
 		
 		return false;
 	}
 	
 	/**
     * verifica se o login e senha informados no par�metro est�o na tabela de usuarios.
     * @param login(String)
     * @param senha(String)
     * @return se o login e senha estiverem corretos retorna true, se o login e senha 
     * n�o estiverem no banco ou acontecer algum erro retorna false.
     */
    	public boolean verifyLogin(String login, String senha){
    		Connection conn = null;
    		try{ 		
    			//cria a pesquisa
        		String comando = "Select * From usuarios where login='"+login+"'";  
        		ResultSet resultSet = conectaBanco.conectar().executeQuery(comando);
        		
        		if (resultSet.next()){//se achar o login na coluna login da tabela usuarios.
        			String senha1=resultSet.getString("senha");// pega a senha associada a ele e atribui a uma vari�vel.
        			if (senha.equals(senha1)){//se a senha for igual a senha informada no par�metro.
        				return true;// o login e senha est�o corretos e retorna true.
        			}
        			else{
        				return false;// se n�o, o login existe, mas a senha encontrada n�o � a informada e retorna false.
        			}
        		}
        		
        	}
    		catch (Exception e){//se encontrar algum erro, � provavelmente porque o login n�o existe e retorna false.
    			e.printStackTrace();
    			return false;
    		}
		finally{// por �ltimo n�o importando o retorna que deu o m�todo fecha a conex�o com o banco.
			try {
    				if(conn!=null){
    					conn.close();
    					}
  
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}

    		
			return false;
        	
        	}
    	
    	 public void comecarNovaCopa(){
    		 Statement st = null;
    		 boolean rs1 = false;
    		 Connection conn = null;
    		 try {
    			 conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
        		 st= conn.createStatement();
        		 String consulta1="TRUNCATE jogos_apostas";
        		 String consulta2 = "TRUNCATE apostas";
        		 String consulta3 = "TRUNCATE resultados";
        		 String consulta4 = "UPDATE times set vitorias = 0, derrotas = 0, empates = 0, gols_pro = 0, gols_contra = 0, saldo_de_gols = 0, pontos = 0, posicao_oitavas = null, posicao_quartas =null, posicao_semi = null, posicao_final = null, campeao = null";
        		 String consulta5 = "UPDATE usuarios set pontos = 0";
        		 rs1=st.execute(consulta1);
        		 rs1 = st.execute(consulta2);
        		 rs1 = st.execute(consulta3);
        		 rs1 = st.execute(consulta4);
        		 rs1 = st.execute(consulta5);
        	
			} catch (Exception e) {
				e.printStackTrace();
			}
    			 
    		 finally{// por �ltimo n�o importando o retorna que deu o m�todo fecha a conex�o com o banco.
    				fecharConexao(st,conn);
			
				// TODO: handle exception
		}
    }
		public void fecharConexao(Statement st, Connection conexao) {
			try {
				if(conexao!=null){
					conexao.close();
				}
				if(st != null){
					st.close();
				}
				
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    	 
  }
	
	


