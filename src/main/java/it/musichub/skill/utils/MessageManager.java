package it.musichub.skill.utils;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageManager {

	public static String i18n(String key, String locale){
		return i18n(key, Locale.forLanguageTag(locale));
	}
	
	public static String i18n(String key, Locale locale) {
		return i18n(key, null, locale);
	}
	
	public static String i18n(String key, Map<String, Object> params, String locale) {
		return i18n(key, params, Locale.forLanguageTag(locale));
	}
	
	public static String i18n(String key, Map<String, Object> params, Locale locale) {
	    ResourceBundle messages = ResourceBundle.getBundle("MessageBundle", locale);
		MapFormat formatter = new MapFormat(params);
		String output = formatter.format(messages.getString(key));
		return output;
	}
	
//	static  void displayMessage(Locale currentLocale) {
//
//		System.out.println("currentLocale = " + currentLocale.toString());
//		System.out.println();
//
//		Map<String,Object> params = new HashMap<>();
//		params.put("shipsNumber", new Integer(7));
//		params.put("date", new Date());
//		params.put("planetName", i18n("planet", currentLocale));
//
//		String output = i18n("template", params, currentLocale);
//
//		System.out.println(output);
//
//	}
//
//	static public void main(String[] args) {
//		displayMessage(new Locale("en", "IN"));
//		System.out.println();
//		displayMessage(new Locale("de", "DE"));
//	}
} 