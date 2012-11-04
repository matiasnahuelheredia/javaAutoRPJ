package org.ifes.Servicio;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.ifes.dominio.IHechizo;
import org.ifes.dominio.IMonstruo;
import org.ifes.dominio.IPersonaje;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LogicaJuego {

	public static ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"org/ifes/rpg/juegoRol.xml"});
    public static BeanFactory factory = context;
    public static IHechizo hechizo;
	/**
	 * transforma el objeto Random en estatico
	 * @param limite limite maximo de numero pseudoaleatorio empezando desde 0
	 * @return numero pseudoaletorio
	 */
	public static int tirameRandom(int limite)
	{
		Random rnd = new Random();
		return rnd.nextInt(limite);
	}
	/**
	 * 
	 * @param personaje lista de personajes
	 * @return regresa un personaje de la lista al azar
	 */
	public static IPersonaje damePersonaje(List<IPersonaje> personaje)
	{	
		return personaje.get(tirameRandom(personaje.size()));
	}
	/**
	 * retorna un objeto al azar que implemente la interfaz IMoustruo
	 * @return objeto de tipo IMoustruo Al azar
	 */
	public static IMonstruo tipoBichoAzar()
	{
		String[] objt = context.getBeanDefinitionNames();//carga objetos del bean
		List<String> list = new ArrayList<String>();//lista de nombres de clases que implementan Ipersonaje
      for (int i=0;i<objt.length;i++)//recorre la lista de objetos
      {
    	  
		if (context.getBean(objt[i]).getClass().getInterfaces()[0].getSimpleName().equals("IMonstruo"))//me fijo si implementa la interface IPersonaje
    	  {
    		list.add(objt[i]);
    	  }
      }
      	Random rand = new Random();
      	
      	return (IMonstruo) factory.getBean(list.get(rand.nextInt(list.size())));
		
	}
	
    /**
     * metodo aleatoreo para indicar si el golpe fue exitoso
     * @return true si fue exitoso false si erro el golpe
     */
	public static boolean isGolpe()
	{	
		if(tirameRandom(2) == 1)
			return true;
		else
			return false;
	}
	public static boolean isAlivepjs(List<IPersonaje> listaPersonaje)
	{
		boolean estaVivo = false;
		for (int i=0;i<listaPersonaje.size();i++)
		{
			estaVivo = estaVivo || (listaPersonaje.get(i).getVida()>0);
		}
		
		return (estaVivo);
	}
}
