package application.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class Util {
	public static java.sql.Date getConvertedDate(LocalDate classDate){
		java.sql.Date sqlDate = null;
		if(classDate != null) {
		LocalDateTime dt = classDate.atTime(LocalTime.NOON); //LocalDateTime.of(customer.getDatum().getYear(),customer.getDatum().getMonth(),1,1,1,1,1);
		sqlDate = new java.sql.Date(dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()); 
		}
		return sqlDate;
	}
	
	public static LocalDate getConvertedDate(java.sql.Date sqlDate) {
		LocalDate classDate = null;
		if(sqlDate != null) {
			classDate = sqlDate.toLocalDate();
		}
		return classDate;
	}
}
