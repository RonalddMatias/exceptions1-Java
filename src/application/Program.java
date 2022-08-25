package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			System.out.print("Check-In date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next()); //Essa � a forma correta de inputar DATE.
			System.out.print("Check-Out date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());
			
		
			
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-In date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next()); //Essa � a forma correta de inputar DATE.
			System.out.print("Check-Out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			reservation.updateDates(checkIn, checkOut);
			System.out.println("Error in reservation: " + reservation);
		}
		catch(ParseException e) {
			System.out.println("in");
		}
		catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage()); //getMessage para pegar a mensagem da exce��o
		}
		//RuntimeException � gen�rico, ent�o, vai servir para qualquer exce��o.
		catch (RuntimeException e) { //Para qualquer outra EXCEC�O inesperada
			System.out.println("Unexpectd error");
		}
			
		
		sc.close();
	}

}
