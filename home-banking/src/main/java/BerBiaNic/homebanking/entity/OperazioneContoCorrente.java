package BerBiaNic.homebanking.entity;

import java.sql.Date;

public class OperazioneContoCorrente implements Comparable<OperazioneContoCorrente> {

	private int id;
	private Date data;
	private double importo;
	private String tipologia;
	private ContoCorrente conto_corrente_proprietario;
	private String conto_corrente_destinatario;
	
	
	public OperazioneContoCorrente(int id, Date data, double importo, String tipologia, ContoCorrente conto_corrente_proprietario,
			String conto_corrente_destinatario) {
		this.id = id;
		this.data = data;
		this.importo = importo;
		this.tipologia = tipologia;
		this.conto_corrente_proprietario = conto_corrente_proprietario;
		this.conto_corrente_destinatario = conto_corrente_destinatario;
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
	
	public ContoCorrente getConto_corrente_proprietario() {
		return conto_corrente_proprietario;
	}

	public String getConto_corrente_destinatario() {
		return conto_corrente_destinatario;
	}

	public void setConto_corrente_proprietario(ContoCorrente conto_corrente_proprietario) {
		this.conto_corrente_proprietario = conto_corrente_proprietario;
	}

	public void setConto_corrente_destinatario(String conto_corrente_destinatario) {
		this.conto_corrente_destinatario = conto_corrente_destinatario;
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
		if (!(obj instanceof OperazioneContoCorrente))
			return false;
		OperazioneContoCorrente other = (OperazioneContoCorrente) obj;
		return id==other.id;
	}
	
	@Override
	public String toString() {
		return " --------------------- OPERAZIONE ---------------------\n "
				+ "id = " + id + ", \tdata = " + data + ", \ttipologia = " + tipologia + "\n";
	}
	
	public int compareTo(OperazioneContoCorrente o) {
		return this.data.compareTo(data);
	}
	
}
