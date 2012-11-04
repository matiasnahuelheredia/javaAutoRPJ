package org.ifes.Servicio;


import java.util.List;
import java.util.Random;

import org.ifes.dominio.IPersonaje;

public class LogicaJuego {

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
