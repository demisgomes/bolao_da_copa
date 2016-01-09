package sga.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConectaBanco {

    private String url;
    private String login;
    private String senha;
    public String q="";
    private Connection conn=null;
    
    public ConectaBanco(String url, String login, String senha) {
        setUrl(url);
        setLogin(login);
        setSenha(senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    /**Cria a conexão com o banco de dados.
     * 
     * @return um statement resultado dessa conexão ou null se acontecer algum erro.
     */
    public Statement conectar(){
    	Connection conn;
    	try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(getUrl(), getLogin(), getSenha());
			Statement st= conn.createStatement();
			return st;
		} catch (Exception e) {
		return null;	
		} 
    	
    }
    	
    	
    	/**
    	 * Verifica se o login e senha informados existe na tabela de administrador.
    	 * @param login (String)
    	 * @param senha (String)
    	 * @return true se ambos estiverem na tabela, false se pelo menos um não estiver ou se acontecer algum erro.
    	 */
    	public boolean verifyAdmin(String login, String senha){
    		//tenta realizar a conexão
    		try{
    			
    			//seleciona da tabela admin o login cadastrado
        		String comando = "Select * From admin where login='"+login+"'"; 
        		ResultSet resultSet = this.conectar().executeQuery(comando);
        		
        		// se o login estiver cadastrado, ele entra neste if
        		if (resultSet.next()){
        			String senha1=resultSet.getString("senha");
        			if (senha.equals(senha1)){
        				this.conectar().close();
        				return true;
        			}
        			else{
        				this.conectar().close();
        				return false;
        			}
        		}
        		
        	
    		}
    		catch(Exception exc){
    				//conn.close();
        			exc.printStackTrace();
        			return false;
    			
    		}
    		finally{
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
    	
 
    	
    	/**
    	 * retorna as informações de uma coluna do banco.
    	 * @param coluna (String)
    	 * @return uma lista de Strings com todas as informações da coluna informada no parâmetro.
    	 *
    	 */
    	 public ArrayList<String> getNomeColuna(String coluna){
    		 ArrayList<String> lista=new ArrayList<String>();
    		 Connection conn=null;
    	    	try{
    	    		
    	    		q="Select * from usuarios";//cria a pesquisa na tabela usuarios.
    	    		ResultSet rs=this.conectar().executeQuery(q);
    	    		while (rs.next()){// enquanto achar informações
    	    			String nome=rs.getString(coluna);// atribui essa informação a uma variável
    	    			
    	    			lista.add(nome);// adiciona a informação à lista.
    	    		}
    	    		
    	    	}
    	    	
    	    	catch (Exception e){
    	    			e.printStackTrace();
    	    		}
    	    	finally{// fecha a conexão.
    				try {
    					if(conn!=null){
    					conn.close();
    					}
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			
    	    	return lista; // retorna a lista.
        	    
    	    	
    	   }
    	 
    	 public static void fecharConexao(Statement st, Connection conexao) {
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


  