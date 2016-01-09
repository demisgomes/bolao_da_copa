package sga.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sga.dominio.copa.*;


public class JogosDAO {
	
	/**
	 * Insere na tabela resultados as informações de um jogo informado como parâmetro e atribui a ele o id informado.
	 * @param jogo (Jogo)
	 * @param id_jogo (int)
	 */
	public void inserirResultado(Jogo jogo, int id_jogo){
		Connection conn=null;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			String comando="Insert into resultados (time_1, time_2, gols_time_1, gols_time_2,id_jogo) VALUES(?,?,?,?,?)";
			
			try{
				PreparedStatement stmt = conn.prepareStatement(comando);      
				  
                stmt.setString(1, jogo.getTime1().getNome());      // manda colocar o nome do time 1 capturado do objeto jogo informado no parâmetro na 1 coluna informada no comando(coluna time_1)   
                
                stmt.setString(2, jogo.getTime2().getNome());      
                stmt.setInt(3, jogo.getGolsTime1());
                stmt.setInt(4, jogo.getGolsTime2());
                stmt.setInt(5, id_jogo);         

                stmt.execute(); //executa comando     
                stmt.close();  
                conn.close();
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
			ConectaBanco.fecharConexao(null, conn);
		}
	}
	
	/**
	 * Transforma as informações dos jogos em objetos do tipo Jogo e os carrega na lista de jogos do sistema.
	 */
	public void addInResultados(){
		Connection conn=null;
		Statement st=null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conn.createStatement();
			String comando="Select * from resultados";// faz a pesquisa na tabela resultados
			ResultSet rs=st.executeQuery(comando);
			while (rs.next()){// enquanto achar resultados na tabela resultados.
    			int idJogo=rs.getInt("id_jogo"); // atribui o resultado da tabela id_jogo a uma variavel
    			int golsTime1 =rs.getInt("gols_time_1");
    			int golsTime2=rs.getInt("gols_time_2");
    			Jogo jogo=new Jogo();// cria objeto jogo e os carrega com as informações das variaveis.
    			jogo.setGolsTime1(golsTime1);
    			jogo.setGolsTime2(golsTime2);
    			ListaJogos.getListaDeJogos()[idJogo]=(jogo);// coloca esse objeto na lista de jogos, para ser usado no sistema.
    		}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			
				ConectaBanco.fecharConexao(st, conn);
			
		}
	
	}
	
		
}
