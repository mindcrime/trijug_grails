package org.trijug

class JmsListenerService {

	static expose = ['jms']
	static destination = "trijugQueue"
	
	List<String> messages = new ArrayList<String>();
	
	def onMessage(msg)
	{
		println "Received message from JMS: ${msg}";
		
		messages.add( msg.msgBody );
		
		return null;
	}
	
	public List<String> getMessages() 
	{
		return messages;	
	}
}
