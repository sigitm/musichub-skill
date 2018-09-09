package it.musichub.skill.model;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageFormatDemo {

	public static String getMessage(String key, Locale locale) {
		return getMessage(key, null, locale);
	}
	
	public static String getMessage(String key, Map<String, Object> params, Locale locale) {
	    ResourceBundle messages = ResourceBundle.getBundle("MessageBundle", locale);
		MapFormat formatter = new MapFormat(params);
		String output = formatter.format(messages.getString(key));
		return output;
	}
	
   static  void displayMessage(Locale currentLocale) {

//	   Map valuesMap = HashMap();
//	   valuesMap.put("animal", "quick brown fox");
//	   valuesMap.put("target", "lazy dog");
//	   String templateString = "The ${animal} jumped over the ${target}.";
//	   StrSubstitutor sub = new StrSubstitutor(valuesMap);
//	   String resolvedString = sub.replace(templateString);
 
	   
	   
	  System.out.println("currentLocale = " + currentLocale.toString());
      System.out.println();

      Map<String,Object> params = new HashMap<>();
      params.put("shipsNumber", new Integer(7));
      params.put("date", new Date());
      params.put("planetName", getMessage("planet", currentLocale));

      String output = getMessage("template", params, currentLocale);

      System.out.println(output);

   }

   static public void main(String[] args) {
      displayMessage(new Locale("en", "IN"));
      System.out.println();
      displayMessage(new Locale("de", "DE"));
   }
} 