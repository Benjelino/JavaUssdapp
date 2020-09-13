package com.egima.ussd;



import javax.servlet.ServletException;

import hms.sdp.ussd.MchoiceUssdException;
import hms.sdp.ussd.MchoiceUssdMessage;
import hms.sdp.ussd.MchoiceUssdTerminateMessage;
import hms.sdp.ussd.client.MchoiceUssdReceiver;
import hms.sdp.ussd.client.MchoiceUssdSender;

public class UssdReceiver extends MchoiceUssdReceiver {

      /**
       *
       */
      private static final long serialVersionUID = 1L;
      private MchoiceUssdSender ussdSender;

      /**
       * we need to override the generic servlet init() method to create some
       * important objects
       *
       */
      @Override
      public void init() throws ServletException {

            super.init();
            try {
                String clientUrl = "http://127.0.0.1:8000/ussd";
                String appId = "appid";
                String pass = "password";
                ussdSender = new MchoiceUssdSender(clientUrl, appId, pass);
                System.out.println(clientUrl + " this");
         } catch (MchoiceUssdException e) {
                e.printStackTrace();
         }
      }

      
      /**
       * override onMessage of the parent class, we are passed MchoiceUssdMessage
       * which contains some useful data about the session and client
       */
      @Override
      public void onMessage(MchoiceUssdMessage arg0) {
          // address of requesting client(the gsm handset)
          String address = arg0.getAddress();
          // a unique identifier for the current USSD session
          String convId = arg0.getConversationId();
          // a unique identifier for the incoming message
          //String corrId = arg0.getCorrelationId();
          // the incoming USSD message string
          String message = arg0.getMessage();
          // now send a message back to the phone client to show we have received
          // a request
         
            try {
                   ussdSender.sendMessage(SessionManager.getNextUssdScreen(address, message, convId), address,
                                convId, false);
            } catch (MchoiceUssdException e) {
                   e.printStackTrace();
            }
      }

      /**
       * call back method called when the session is terminated
       */
      @Override
      public void onSessionTerminate(MchoiceUssdTerminateMessage arg0) {

      }

}
