package sga.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sga.dominio.copa.*;

public class TimesDAO {
	
	
	public TimesDAO(){
		Connection conexaoexao=null;
		Statement st=null;
		String pesquisa= "Select * from times";// cria a pesquisa na tabela times.
		try {
			conexaoexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st= conexaoexao.createStatement();
			ResultSet rs= st.executeQuery(pesquisa);
			while (rs.next()){// enquanto encontrar resultados na tabela times.
				String nomeTime=rs.getString("nome");// atribui os valores das colunas a variaveis
				int ID=rs.getInt("idtimes");
				String grupo=rs.getString("grupo");	
				int golsPro = rs.getInt("gols_pro");
				int GolsContra = rs.getInt("gols_contra");
				int saldo = rs.getInt("saldo_de_gols");
				int pontos = rs.getInt("pontos");
				int vitorias = rs.getInt("vitorias");
				int empates = rs.getInt("empates");
				int derrotas = rs.getInt("derrotas");
				Time time=new Time(nomeTime);// cria objeto time.
				time.setGrupo(grupo);// adiciona as informações das variáveis nele.
				time.getInfoTime().setDerrotas(derrotas);
				time.getInfoTime().setVitorias(vitorias);
				time.getInfoTime().setEmpates(empates);
				time.getInfoTime().setPontos(pontos);
				time.getInfoTime().setSaldo(saldo);
				time.getInfoTime().setGolsPro(golsPro);
				time.getInfoTime().setGolsContra(GolsContra);
				ListaTimes.getListaTimes()[ID-1]=time;// adiciona o objeto na lista de times para ser usado no sistema, na posicao do id dele -1.
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{// fecha a conexaoexão

				ConectaBanco.fecharConexao(st, conexaoexao);
			
		}
		
		
		
	}

	
	/***
	 * Atualiza as informações de um time na tabela times do banco.
	 * @param time (Time)
	 */
	public void inserirTimes(Time time){
		Connection conexaoexao=null;
		Statement st=null;
		try {
			conexaoexao= DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st=conexaoexao.createStatement();
			String pesquisa = "Select * from times where nome ='"+time.getNome()+"'";// cria a pesquisa
			ResultSet rs = st.executeQuery(pesquisa);
			if (rs.next()) {// se achar na coluna nomes o nome do objeto informado no parâmetro
				// dar um update em todas as colunas com os atributos do objeto correspondente.
				String comando = "UPDATE times set vitorias='"+time.getInfoTime().getVitorias()+"', empates='"+time.getInfoTime().getEmpates()+"', derrotas='"+time.getInfoTime().getDerrotas()+"', gols_pro='"+time.getInfoTime().getGolsPro()+"', gols_contra='"+time.getInfoTime().getGolsContra()+"', pontos='"+time.getInfoTime().getPontos()+"', saldo_de_gols='"+time.getInfoTime().getSaldoDeGols()+"' where nome ='"+time.getNome()+"'";
				PreparedStatement stmt = conexaoexao.prepareStatement(comando);
    			stmt.execute();
    			stmt.close();
    			
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{// fecha a conexaoexão

			ConectaBanco.fecharConexao(st, conexaoexao);
		
	}
		
	}
	
	/**
	 * Verifica se os times já estão em suas respectivas posições para as oitavas de final
	 * <br>
	 * Se pleo menos um time estiver posicionado, quer dizer que os outros também sestão, já que iremos inserir todos os times de uma s´po vez.
	 * @return
	 */
	public boolean verificarOitavas(){
		boolean variavel = false;
		Connection conexaoexao=null;
		Statement st=null;
		try {
			conexaoexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st=conexaoexao.createStatement();
			String pesquisa = "Select * from times where posicao_oitavas ='A1'";
			ResultSet rs = st.executeQuery(pesquisa);
			if(rs.next()){
				variavel=true;
			}
			else{ variavel=false;}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			ConectaBanco.fecharConexao(st, conexaoexao);
		}
		return variavel;
	}
	
	/**
	 * Pega os times do banco para as oitavas de final
	 * @return Uma lista com todos os times que compõem as oitavas de final
	 */
	public Time[] getListaTimesOitavas(){
		Connection conexaoexao=null;
		Statement st=null;
		Time[] timesOitavas=new Time[16];
		try {
			if(verificarOitavas()){
				conexaoexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
				st=conexaoexao.createStatement();
				String pesquisa = "Select * from times where posicao_oitavas !='"+null+"'";
				ResultSet rs = st.executeQuery(pesquisa);
				while(rs.next()){
					String posicao=rs.getString("posicao_oitavas");
					Time time=new Time(rs.getString("nome"));
					int golsPro = rs.getInt("gols_pro");
					int GolsContra = rs.getInt("gols_contra");
					int saldo = rs.getInt("saldo_de_gols");
					int pontos = rs.getInt("pontos");
					int vitorias = rs.getInt("vitorias");
					int empates = rs.getInt("empates");
					int derrotas = rs.getInt("derrotas");
					time.getInfoTime().setDerrotas(derrotas);
					time.getInfoTime().setVitorias(vitorias);
					time.getInfoTime().setEmpates(empates);
					time.getInfoTime().setPontos(pontos);
					time.getInfoTime().setSaldo(saldo);
					time.getInfoTime().setGolsPro(golsPro);
					time.getInfoTime().setGolsContra(GolsContra);
					/*
					Para cada posição da equipe, ele põe no lugar correto
					*/
					if(posicao.equals("A1")){
						timesOitavas[0]=time;
					}
					if(posicao.equals("A2")){
						timesOitavas[1]=time;
					}

					if(posicao.equals("B1")){
						timesOitavas[2]=time;
					}

					if(posicao.equals("B2")){
						timesOitavas[3]=time;
					}

					if(posicao.equals("C1")){
						timesOitavas[4]=time;
					}

					if(posicao.equals("C2")){
						timesOitavas[5]=time;
					}

					if(posicao.equals("D1")){
						timesOitavas[6]=time;
					}

					if(posicao.equals("D2")){
						timesOitavas[7]=time;
					}

					if(posicao.equals("E1")){
						timesOitavas[8]=time;
					}

					if(posicao.equals("E2")){
						timesOitavas[9]=time;
					}

					if(posicao.equals("F1")){
						timesOitavas[10]=time;
					}

					if(posicao.equals("F2")){
						timesOitavas[11]=time;
					}

					if(posicao.equals("G1")){
						timesOitavas[12]=time;
					}

					if(posicao.equals("G2")){
						timesOitavas[13]=time;
					}

					if(posicao.equals("H1")){
						timesOitavas[14]=time;
					}

					if(posicao.equals("H2")){
						timesOitavas[15]=time;
					}
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			ConectaBanco.fecharConexao(st, conexaoexao);
		}
		return timesOitavas;
	
	}
	
	/**
	 * Insere a posição das oitavas de final
	 * Se o time na classificação de seu grupo, for o primeiro ou segundo, terá uma string inserida.
	 * <br>
	 * Exemplo: o primeiro do grupo A recebe A1. O segundo do grupo C recebe C2.
	 * @param time
	 * @param posicao
	 */
	public void insiraPosicao(Time time, String posicao,String coluna){
		Connection conexao=null;
		Statement st=null;
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st=conexao.createStatement();
			String pesquisa = "Select * from times where nome='"+time.getNome()+"'";
			ResultSet rs = st.executeQuery(pesquisa);
			if(rs.next()){
				String updatePosicao="Update times set "+coluna+" ='"+posicao+"' where nome='"+time.getNome()+"'";
				PreparedStatement stmt = conexao.prepareStatement(updatePosicao);
    			stmt.execute();
    			stmt.close();
    			conexao.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			ConectaBanco.fecharConexao(st, conexao);
		}
	}
	

	public Time [] getListaQuartas(){
		Connection conexao =null;
		Statement st=null;
		Time[] listaTimes = new Time[8];
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st=conexao.createStatement();
			String pesquisa = "select * from times where posicao_quartas != '"+null+"'";
			ResultSet rs = st.executeQuery(pesquisa);
			
			while(rs.next()){
				String posicao=rs.getString("posicao_quartas");
				Time time=new Time(rs.getString("nome"));
				int golsPro = rs.getInt("gols_pro");
				int GolsContra = rs.getInt("gols_contra");
				int saldo = rs.getInt("saldo_de_gols");
				int pontos = rs.getInt("pontos");
				int vitorias = rs.getInt("vitorias");
				int empates = rs.getInt("empates");
				int derrotas = rs.getInt("derrotas");
				time.getInfoTime().setDerrotas(derrotas);
				time.getInfoTime().setVitorias(vitorias);
				time.getInfoTime().setEmpates(empates);
				time.getInfoTime().setPontos(pontos);
				time.getInfoTime().setSaldo(saldo);
				time.getInfoTime().setGolsPro(golsPro);
				time.getInfoTime().setGolsContra(GolsContra);
				
				if(posicao.equals("V48")){
					listaTimes[0] = time;
				}
				if(posicao.equals("V49")){
					listaTimes[1] = time;
				}
				if(posicao.equals("V50")){
					listaTimes[2] = time;
				}
				if(posicao.equals("V51")){
					listaTimes[3] = time;
				}
				if(posicao.equals("V52")){
					listaTimes[4] = time;
				}
				if(posicao.equals("V53")){
					listaTimes[5] = time;
				}
				if(posicao.equals("V54")){
					listaTimes[6] = time;
				}
				if(posicao.equals("V55")){
					listaTimes[7] = time;
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		finally{
			ConectaBanco.fecharConexao(st, conexao);
		}
		
		return listaTimes;
	}
	
	public Time [] getListaSemi(){
		Connection conexao =null;
		Statement st=null;
		Time[] listaTimes = new Time[4];
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st=conexao.createStatement();
			String pesquisa = "select * from times where posicao_semi != '"+null+"'";
			ResultSet rs = st.executeQuery(pesquisa);
			
			while(rs.next()){
				String posicao=rs.getString("posicao_semi");
				Time time=new Time(rs.getString("nome"));
				int ID=rs.getInt("idtimes");
				String grupo=rs.getString("grupo");	
				int golsPro = rs.getInt("gols_pro");
				int GolsContra = rs.getInt("gols_contra");
				int saldo = rs.getInt("saldo_de_gols");
				int pontos = rs.getInt("pontos");
				int vitorias = rs.getInt("vitorias");
				int empates = rs.getInt("empates");
				int derrotas = rs.getInt("derrotas");
				time.getInfoTime().setDerrotas(derrotas);
				time.getInfoTime().setVitorias(vitorias);
				time.getInfoTime().setEmpates(empates);
				time.getInfoTime().setPontos(pontos);
				time.getInfoTime().setSaldo(saldo);
				time.getInfoTime().setGolsPro(golsPro);
				time.getInfoTime().setGolsContra(GolsContra);
				
				if(posicao.equals("V56")){
					listaTimes[0] = time;
				}
				if(posicao.equals("V57")){
					listaTimes[1] = time;
				}
				if(posicao.equals("V58")){
					listaTimes[2] = time;
				}
				if(posicao.equals("V59")){
					listaTimes[3] = time;
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		finally{
			ConectaBanco.fecharConexao(st, conexao);
		}
		
		return listaTimes;
	}
	
	public Time [] getListaFinais(){
		Connection conexao =null;
		Statement st=null;
		Time[] listaTimes = new Time[4];
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/sga", "root", "1234");
			st=conexao.createStatement();
			String pesquisa = "select * from times where posicao_final != '"+null+"'";
			ResultSet rs = st.executeQuery(pesquisa);
			
			while(rs.next()){
				String posicao=rs.getString("posicao_final");
				Time time=new Time(rs.getString("nome"));
				int golsPro = rs.getInt("gols_pro");
				int golsContra = rs.getInt("gols_contra");
				int saldo = rs.getInt("saldo_de_gols");
				int pontos = rs.getInt("pontos");
				int vitorias = rs.getInt("vitorias");
				int empates = rs.getInt("empates");
				int derrotas = rs.getInt("derrotas");
				time.getInfoTime().setDerrotas(derrotas);
				time.getInfoTime().setVitorias(vitorias);
				time.getInfoTime().setEmpates(empates);
				time.getInfoTime().setPontos(pontos);
				time.getInfoTime().setSaldo(saldo);
				time.getInfoTime().setGolsPro(golsPro);
				time.getInfoTime().setGolsContra(golsContra);
				
				if(posicao.equals("V60")){
					listaTimes[0] = time;
				}
				if(posicao.equals("V61")){
					listaTimes[1] = time;
				}
				if(posicao.equals("P60")){
					listaTimes[2] = time;
				}
				if(posicao.equals("P61")){
					listaTimes[3] = time;
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		finally{
			ConectaBanco.fecharConexao(st, conexao);
		}
		
		return listaTimes;
	}
}
