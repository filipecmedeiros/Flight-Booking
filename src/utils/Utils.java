package utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	public static final Pattern VALID_EMAIL_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PHONE_REGEX = Pattern.compile("^[0-9]{10,16}$");
	public static final Pattern VALID_NAME_REGEX = 
			Pattern.compile("^[A-Z ]+$", Pattern.CASE_INSENSITIVE);
	public final static String DATE_FORMAT = "dd/MM/yyyy";
	
	
	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String cpf) {
		if ((cpf==null) || (cpf.length()!=11)) return false;

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	}
	   
	public static boolean isValidEmail(String email) {
		Matcher matcher = VALID_EMAIL_REGEX .matcher(email);
		return matcher.find();
	}
	
	public static boolean isValidPhone(String phone) {
		Matcher matcher = VALID_PHONE_REGEX .matcher(phone);
		return matcher.find();
	}
	
	public static boolean isValidName(String name) {
		Matcher matcher = VALID_NAME_REGEX .matcher(name);
		return matcher.find();
	}
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	
	public static boolean isValidDate(String date){
		boolean result = false;
		Date today = Calendar.getInstance().getTime();
		Date df = parseDate(date);
        
		if (today.before(df)) {
        	result = true;
        }
		else {
        	result = false;
        }
		
		return result;
	}	
}
