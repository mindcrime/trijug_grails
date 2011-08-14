/**
 * 
 */
package org.trijug

/**
 * @author prhodes
 *
 */
class MessageController {

	def jmsListenerService;
	
	def index = {
		// println "MessageController.index";
	}
	
	def submitMessage = {
		// println "MessageController.submitMessage";
		
		def messageBody = params.messageBody;
		
		def message = [msgBody: messageBody ];
		
		// send a JMS message to our testQueue
		sendJMSMessage("trijugQueue", message );
		
		redirect(controller:"message", action:"index");
	}
	
	def list = {
		
		List<String> messages = jmsListenerService.getMessages();
		[messages: messages];	
	}
}
