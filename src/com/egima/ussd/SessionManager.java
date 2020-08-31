package com.egima.ussd;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
	
	private static final Map<String, String[]> routesnrates = new HashMap<String, String[]>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		
		put("Cocoa", new String[] {"per bag", "Tarkwa to Takoradi for Gh¢5.91 ", "Enchi to Takoradi for Gh¢12.3","New Edubiase to Tema for Gh¢14.13", "Asawinso to Kaase for Gh¢11.75", "Elluokrom to Kaase for Gh¢11.34", "Debiso to Kaase for Gh¢13.06", "Akim Oda to Tema for Gh¢11.17"});
		put("Cashew", new String[] {"per ton", "Techiman to Tema - Gh¢140", "Wenchi to Tema - Gh¢150", "Sampa to Tema - Gh¢185", "Nkrankwanta to Tema - Gh¢175", "Banda to Tema - Gh¢185", "Japekrom to Tema - Gh¢175"});
		put("Rice", new String[] {"per bag", "Harbour to Warehouse for Gh¢1.10", "Tema to Kumasi for Gh¢4.10", "Tema to Obuasi - Gh¢5", "Tema to Takoradi - Gh¢4", "Tema to Sunyani - Gh¢5", "Tema to Nkawkaw - Gh¢3.5"});
		put("Tiles", new String[] {"42tons per truck", "Takoradi to Kumasi for Gh¢3,000. "});
		put("Fertilizer", new String[] {"per bag", "Kumasi to Estern for Gh¢2.8", "Tema to Kumasi for Gh¢4", "Tema to Tema area for Gh¢1.8", "Kumasi to Ayinam for Gh¢2.5", "Tema - Dormaa for Gh¢6.5", "Kumasi to Twifo Praso for Gh¢3.6", "Kumasi to Wasa Akropong for Gh¢4.4"});
		put("Cement", new String[] {"per bag", "Tema to Kumasi for Gh¢3", "Takoradi to Offinso for Gh¢3.3", "Takoradi to Accra for Gh¢2.3", "Takoradi to Kumasi for Gh¢3"});
		put("Petroleum", new String[] {"per 50,000 liters", "Spintex to Aska for 2,300", "Tema to Spintex for Gh¢1,800"});
	}};
	
	
	private static final Map<String, String[]> commodities = new HashMap<String, String[]>(){/** 
	
	this saves all the commodity entries
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("1", new String[] {"Cocoa", "Choose the route and rate per bag. \n 1. Tarkwa to Takoradi - 5.91 \n 2. Enchi to Takoradi - 12.3 \n 3. New Edubiase to Tema - 14.13 \n 4. Asawinso to Kaase - 11.75 \n 5. Elluokrom to Kaase - 11.34 \n 6. Debiso to Kaase - 13.06 \n 7. Akim Oda to Tema - 11.17"})
	;put("2", new String[] {"Cashew", "Choose the route rate per ton \n 1. Techiman to Tema - 140 \n 2. Wenchi to Tema - 150 \n 3. Sampa to Tema - 185 \n 4. Nkrankwanta to Tema - 175 \n 5. Banda to Tema - 185 \n 6. Japekrom to Tema - 175"});
	put("3", new String[] {"Rice", "Choose the route and rate per bag. \n 1. Harbour to Warehouse - 1.10 \n 2. Tema to Kumasi - 4.10 \n 3. Tema to Obuasi - 5 \n 4. Tema to Takoradi - 4 \n 5. Tema to Sunyani - 5 \n 6. Tema to Nkawkaw - 3.5 \n 7. other"});
	put("4", new String[] {"Tiles", "Choose the route. 42tons per truck. \n 1. Takoradi to Kumasi - 3,000."});
	put("5", new String[] {"Fertilizer", "Choose the route and rate per bag. \n 1. Kumasi to Estern - 2.8 \n 2. Tema to Kumasi - 4 \n 3. Tema to Tema area - 1.8 \n 4. Kumasi to Ayinam - 2.5 \n 5. Tema - Dormaa - 6.5 \n 6. Kumasi to Twifo Praso - 3.6 \n 7. Kumasi to Wasa Akropong - 4.4"});
	put("6", new String[] {"Cement", "Choose the route and rate per bag. \n 1. Tema to Kumasi - 3 \n 2. Takoradi to Offinso - 3.3 \n 3. Takoradi to Accra - 2.3 \n 4. Takoradi to Kumasi - 3"});
	put("7", new String[] {"Petroleum", "Choose the route and rate per 50,000 liters. \n 1. Spintex to Aska - 2,300 \n 2. Tema to Spintex - 1,800"});}};
	private static final ConcurrentHashMap<String, String> session = new ConcurrentHashMap<String, String>();
	
	public static String getNextUssdScreen(String address, String message, String id) {
		if (!session.containsKey(address)) {
			session.put(address, "Welcome");
			return "Welcome to Kiteko Ghana. \n Choose an action. \n 1. Request for a truck \n 2. Register \n 3. Contact Us ";
		}else if ("Welcome".equals(session.get(address))) {
			if("1".equals(message)) {
				session.replace(address, "Commodity");
				return "Menu. \n Choose the commodity you want to move. \n 1. Cocoa \n 2. Cashew \n 3. Rice \n 4. Tiles \n 5. Fertilizer \n 6. Cement \n 7. Petroleum";
			}else if("2".equals(message)) {
				return "You have successfully registered";
			}else if("3".equals(message)) {
				return "Please contact Us on \n WhatsApp: 0249551119 or \n Website: www.kiteko.com";
			}else {
				return "Your choice was incorrect. \n Please try again.";
			}
		}else if("Commodity".equals(session.get(address))) {
			if(commodities.containsKey(message)) {
			String[] mcResponse = commodities.get(message);
			session.replace(address, mcResponse[0]);
			session.put("mCommodity", mcResponse[0]);
			return mcResponse[1];
			}else {
				return "Wrong choice, try again. \n 1. Cocoa \n 2. Cashew \n 3. Rice \n 4. Tiles \n 5. Fertilizer \n 6. Cement \n 7. Petroleum";
			}
		}else if (session.get("mCommodity").equals(session.get(address))) {
			String[] selectedcommodity = routesnrates.get(session.get("mCommodity"));
			int routeNo = Integer.parseInt(message);
			session.put("selectedroute",selectedcommodity[routeNo]);
			session.replace(address, "Telephone");
			return "Enter your telephone Number";
		}else if("Telephone".equals(session.get(address))) {
			if(SessionManager.validate(message)) {
				session.replace(address, "Confirm");
				return "You want to transport " + session.get("mCommodity") + " from " + session.get("selectedroute") + routesnrates.get(session.get("mCommodity"))[0]+ ". \n Confirm your order. \n 1. Yes \n 2. No";
			}else {
				return "Incorrect Number please try again";
			}
		}else if ("Confirm".equals(session.get(address))) {
			if("1".equals(message)) {
				return "Truck order successful. You will receive a call from our representative in no time.";
			}else if("2".equals(message)) {
				return "Transaction cancled. Thank you";
			}
		}
		return "Error occured";
		
	}
	public static boolean validate(String phoneNumber) {
		if (phoneNumber.length() < 10) {
		return false;
		}
		if (!phoneNumber.startsWith("0")) {
		return false;
		}
		return true;
		}
}
