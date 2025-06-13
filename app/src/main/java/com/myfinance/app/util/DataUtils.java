package com.myfinance.app.util;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	public static Date colocarNoFimDoDia(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		data = cal.getTime();
		return data;
	}

}
