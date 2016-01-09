package sga.dominio.copa;

public class ListaTimes {
	private static Time[] ListaTimes= new Time[32];
	
	public static Time[] getListaTimes() {
		return ListaTimes;
	}

	public static void setListaTimes(Time[] listaTimes) {
		ListaTimes = listaTimes;
	}
}
