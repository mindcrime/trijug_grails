// Place your Spring DSL code here
beans = {
	
	if( true ) {
		
		jmsConnectionFactory(org.springframework.jndi.JndiObjectFactoryBean) {
			jndiName="ConnectionFactory"
			jndiEnvironment=["java.naming.factory.initial":"org.jnp.interfaces.NamingContextFactory",
				"java.naming.provider.url":"localhost:1099"]
		}
	}
	
}
