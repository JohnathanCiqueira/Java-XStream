package br.com.caelum.xstream;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Main {

		
		Locale brasil = new Locale("pt", "br");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(brasil);
		String formatado = formatter.format(1000.0);
		//System.out.println(formatado);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy=HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String dataHora = dateFormat.format(calendar.getTime());
		
		//System.out.println(dataHora);

}
