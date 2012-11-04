package aux;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.ifes.Servicio.LogicaJuego;
import org.ifes.dominio.IMonstruo;
import org.ifes.dominio.IPersonaje;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * este main no forma parte del juego
 * @author mati89
 *
 */


public class auc {

	public static ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"org/ifes/rpg/juegoRol.xml"});
	public static BeanFactory factory = context;
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// crea un objeto que implemente la interfaz IPersonaje
		
		System.out.println(LogicaJuego.tipoBichoAzar());
      
	}

}
