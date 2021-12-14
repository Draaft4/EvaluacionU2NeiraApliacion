package ec.edu.ups.pw59.AutoApp.view;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ec.edu.ups.pw59.EvaluacionU2Neira.bussines.AutoONRemote;

public class Principal {
	
	private static AutoONRemote remoto;
	
	public void conectar() throws Exception {
    	try {  
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "demop59");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "demop59");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            final String lookupName = "ejb:/EvaluacionU2Neira/AutoON!ec.edu.ups.pw59.EvaluacionU2Neira.bussines.AutoONRemote";  
              
            Principal.remoto = (AutoONRemote) context.lookup(lookupName);  
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
    }

	public static void main(String[] args) {
		Principal p = new Principal();
		try {
			p.conectar();
			
			System.out.println("Ingresando auto");
			remoto.crearAuto("ABC123", "Camaro", "Chevrolet");
			remoto.crearAuto("BCD", "Camaro", "Chevrolet");
			System.out.println("Modificando auto");
			remoto.actualizarAuto(1, "DEF456", "4x4", "Toyota");
			
		} catch (Exception e) {
		}
	}

}
