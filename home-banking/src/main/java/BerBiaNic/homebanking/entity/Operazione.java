package BerBiaNic.homebanking.entity;

import java.sql.Date;

public class Operazione implements Comparable<Operazione> {

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private ContoCorrente conto_corrente;
	private CartaDiDebito carta_di_debito;
	
	public Operazione(int id, Date data, double importo, String tipologia, ContoCorrente conto_corrente,
			CartaDiDebito carta_di_debito) {
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.conto_corrente = conto_corrente;
		this.carta_di_debito = carta_di_debito;
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

	public ContoCorrente getConto_corrente() {
		return conto_corrente;
	}

	public CartaDiDebito getCarta_di_debito() {
		return carta_di_debito;
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

	public void setConto_corrente(ContoCorrente conto_corrente) {
		this.conto_corrente = conto_corrente;
	}

	public void setCarta_di_debito(CartaDiDebito carta_di_debito) {
		this.carta_di_debito = carta_di_debito;
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
		if (!(obj instanceof Operazione))
			return false;
		Operazione other = (Operazione) obj;
		return id==other.id;
	}
	
	@Override
	public String toString() {
		return " --------------------- OPERAZIONE ---------------------\n "
				+ "id = " + id + ", \tdata = " + data + ", \ttipologia = " + tipologia + "\n";
	}
	
	public int compareTo(Operazione o) {
		return this.data.compareTo(data);
	}
	
	
	
}
