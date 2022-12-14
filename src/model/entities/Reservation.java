package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if(!checkOut.after(checkIn)) { //Tratando antes de uma inicializa??o no construtor. 
			throw new DomainException("Check-Out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	//Retirei o setCheckOut() e o setCheckIn() pois n?o queremos que o valor seja alterado.
	
	public long duration(){
		//O getTime() retorna o valor da data em milisegundos.
		long diff = checkOut.getTime() - checkIn.getTime(); 
		
		//Esse codigo vai converter o valor de milisegundos.
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) { //Esse m?todo pode lan?ar uma exce??o
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) { // Se o checkIn ou o checkOut for antes de AGORA (NOW).
			throw new DomainException("Reservation dates for updates must be future dates");
		}
		if(!checkOut.after(checkIn)) { //Vendo se a data de checkout n?o ? posterior que a data de checkIn. 
			throw new DomainException("Check-Out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
	
	
}
