package BerBiaNic.homebanking.entity;

import java.sql.Date;

public class OperazionePrepagata implements Comparable<OperazionePrepagata>{

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private Account destinatario;
	private CartaPrepagata cartaPrepagata;
	
	public OperazionePrepagata(int id, Date data, double importo,String tipologia, Account destinatario, CartaPrepagata cartaPrepagata) {
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.destinatario = destinatario;
		this.cartaPrepagata = cartaPrepagata;
	}
	
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public void setDestinatario(Account destinatario) {
		this.destinatario = destinatario;
	}
	public void setCartaPrepagata(CartaPrepagata cartaPrepagata) {
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
	
	public String getTipologia() {
		return tipologia;
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
