package BerBiaNic.homebanking.main;

import java.sql.Date;
import java.util.concurrent.*;

import BerBiaNic.homebanking.dao.*;
import BerBiaNic.homebanking.entity.*;
import BerBiaNic.homebanking.exceptions.InputValidationException;

public class DaoCartaDiDebitoTest {
	public static void main(String[] args) {
		DaoContoCorrente daoContoCorrente = new DaoContoCorrente();
		DaoCartaDiDebito daoCartaDebito = new DaoCartaDiDebito();
		ContoCorrente cc;
		try {
			cc = daoContoCorrente.getOne("IT38483930001212").get();
			CartaDiDebito cdd = new CartaDiDebito("3644787389", "IT3747474000382", new Date(20201010), 234, 1111, cc);
			Future<CartaDiDebito> futureCartaD = daoCartaDebito.insert(cdd);
			System.out.println("Carta inserita");
			System.out.println(futureCartaD.get());
		} catch (InterruptedException | ExecutionException | InputValidationException e) {
			e.printStackTrace();
		}
		
	}
}
