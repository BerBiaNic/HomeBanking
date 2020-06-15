package BerBiaNic.homebanking.logica;

import java.sql.Date;

public class OperazionePrepagata implements Comparable<OperazionePrepagata>{

	private final int id;
	private final Date data;
	private final double importo;
	private final String tipologia;
	private final Account destinatario;
	private final CartaPrepagata cartaPrepagata;
	
	public OperazionePrepagata() {}
	public OperazionePrepagata(int id, Date data, double importo,String tipologia, Account destinatario, CartaPrepagata cartaPrepagata) {
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.destinatario = destinatario;
		this.cartaPrepagata = cartaPrepagata;
	}
	public int getId() {
		return id;
	}
	public Date getData() {
		return data;
	}
	public double getImporto() {
		return importo;
	}
	public Account getDestinatario() {
		return destinatario;
	}
	public CartaPrepagata getCartaPrepagata() {
		return cartaPrepagata;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OperazionePrepagata))
			return false;
		OperazionePrepagata other = (OperazionePrepagata) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return tipologia + ", effettuato da " + cartaPrepagata.getAccount().getNomeCognome() + 
				"\nid: " + id + ", data: " + data + ", importo: " + importo + ", a " + destinatario.getNomeCognome();
	}
	@Override
	public int compareTo(OperazionePrepagata o) {
		return o.getData().compareTo(data);
	}
	
}
