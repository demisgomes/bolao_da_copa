package sga.dominio.copa;


public class Time {
	private int habilidade;
	private int posGrupo;
	private String nome;
	private InfoTime infoTime;
	private int auxPosGrupo;
	private String grupo;
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public int getAuxPosGrupo() {
		return auxPosGrupo;
	}
	public void setAuxPosGrupo(int auxPosGrupo) {
		this.auxPosGrupo = auxPosGrupo;
	}
	public int getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(int habilidade) {
		this.habilidade = habilidade;
	}
	public int getPosGrupo() {
		return posGrupo;
	}
	public void setPosGrupo(int posGrupo) {
		this.posGrupo = posGrupo;
	}
	public InfoTime getInfoTime() {
		return infoTime;
	}
	public void setInfoTime(InfoTime infoTime) {
		this.infoTime = infoTime;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome){
		this.nome=nome;
	}

	
	public Time(){}
	public Time(String nome){
		this.setNome(nome);
		this.infoTime = new InfoTime();
	}
}
