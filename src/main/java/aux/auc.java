package aux;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public static String getAzarPj()
	{
		String[] objt = context.getBeanDefinitionNames();//carga objetos del bean
		List<String> list = new ArrayList<String>();//lista de nombres de clases que implementan Ipersonaje
      for (int i=0;i<objt.length;i++)//recorre la lista de objetos
      {
    	  
		if (context.getBean(objt[i]).getClass().getInterfaces()[0].getSimpleName().equals("IPersonaje"))//me fijo si implementa la interface IPersonaje
    	  {
    		list.add(objt[i]);
    	  }
      }
      	Random rand = new Random();
      	return list.get(rand.nextInt(list.size()));
      
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IPersonaje person = (IPersonaje) factory.getBean(getAzarPj());// crea un objeto que implemente la interfaz IPersonaje
		System.out.println(person.getClass().getSimpleName());//para ver el tipo de objeto que es person
      
	}

}
