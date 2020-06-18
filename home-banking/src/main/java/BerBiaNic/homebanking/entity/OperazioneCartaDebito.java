package BerBiaNic.homebanking.entity;

import java.sql.Date;

public class OperazioneCartaDebito implements Comparable<OperazioneCartaDebito> {

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private CartaDiDebito carta_proprietario;
	private String carta_beneficiario;
	
	public OperazioneCartaDebito(int id, Date data, double importo, String tipologia, CartaDiDebito carta_proprietario,
			String carta_beneficiario) {
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.carta_proprietario = carta_proprietario;
		this.carta_beneficiario = carta_beneficiario;
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

	public String getTipologia() {
		return tipologia;
	}
	
	
	public CartaDiDebito getCarta_proprietario() {
		return carta_proprietario;
	}

	public String getCarta_beneficiario() {
		return carta_beneficiario;
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

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public void setCarta_proprietario(CartaDiDebito carta_proprietario) {
		this.carta_proprietario = carta_proprietario;
	}

	public void setCarta_beneficiario(String carta_beneficiario) {
		this.carta_beneficiario = carta_beneficiario;
	}

	public int hashCode() {
		int hash = 37;
		hash = hash * 37 + id;
		return hash;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OperazioneCartaDebito))
			return false;
		OperazioneCartaDebito other = (OperazioneCartaDebito) obj;
		return id==other.id;
	}
	
	@Override
	public String toString() {
		return " --------------------- OPERAZIONE ---------------------\n "
				+ "id = " + id + ", \tdata = " + data + ", \ttipologia = " + tipologia + "\n";
	}
	
	public int compareTo(OperazioneCartaDebito o) {
		return this.data.compareTo(data);
	}
	
	
	
}
