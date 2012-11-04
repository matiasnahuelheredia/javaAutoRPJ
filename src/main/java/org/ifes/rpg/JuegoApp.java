package org.ifes.rpg;


import org.ifes.Servicio.LogicaJuego;
import org.ifes.dominio.IArma;
import org.ifes.dominio.BolaDeFuego;
import org.ifes.dominio.Cuchillo;
import org.ifes.dominio.Curar;
import org.ifes.dominio.Elfo;
import org.ifes.dominio.Espada;
import org.ifes.dominio.Gargola;
import org.ifes.dominio.Guerrero;
import org.ifes.dominio.IHechizo;
import org.ifes.dominio.Mago;
import org.ifes.dominio.IMonstruo;
import org.ifes.dominio.MuertoViviente;
import org.ifes.dominio.Orco;
import org.ifes.dominio.IPersonaje;
import org.ifes.dominio.Vara;
import org.ifes.dominio.Velocidad;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class JuegoApp extends Thread {
	
	public static ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"org/ifes/rpg/juegoRol.xml"});
    public static BeanFactory factory = context;
    public static IHechizo hechizo;
	
	public static int tirameRandom(int limite)
	{
		Random rnd = new Random();
		return rnd.nextInt(limite);
	}
	
	public static boolean isGolpe()
	{	
		if(tirameRandom(2) == 1)
			return true;
		else
			return false;
	}
	
		
	
	public static IPersonaje damePersonaje(IPersonaje p1, IPersonaje p2, IPersonaje p3)
	{	
		IPersonaje aux = null;
		do
		{
			switch(tirameRandom(3))
			{
			case 0 : aux = p1; break;
			case 1 : aux = p2; break;
			case 2 : aux = p3; break;
			}
		}
		while(aux.getVida() == 0);
		
		return aux;
	}
	
	public static void isBichoDead(IMonstruo b) throws Exception
	{
		if(b.getVida() == 0)
			System.out.println("El monstruo: "+b.getNombre()+" a muerto!");		
			
			sleep(1000);
	}
	
	public static void isDead(IPersonaje p) throws Exception
	{
		if(p.getVida() == 0)
			System.out.println("El "+p.getNombre()+" a muerto!");
		
			sleep(1000);
	}
	
	public static void lanzoHechizo(IPersonaje lanzador, IPersonaje p2, IPersonaje p3, IMonstruo m) throws Exception
	{
		IPersonaje random = null;
		
		switch(tirameRandom(3))
		{
		case 0 : 
		hechizo = (Curar) factory.getBean("Curar"); 	
		random = damePersonaje(lanzador,p2,p3);
		random.cargarVida(hechizo.getImpacto());
		System.out.println("El "+ lanzador.getNombre() +" lanza "+hechizo.getNombre()+" sobre el "+random.getNombre());
		sleep(1000);
		break;
		case 1 : //la bola de fuego
			hechizo = (BolaDeFuego) factory.getBean("Fireball"); 
			m.cargarVida(hechizo.getImpacto());
			System.out.println("El "+ lanzador.getNombre() +" ha lanzado "+hechizo.getNombre()+" y hace "+hechizo.getImpacto()+" de daño al mounstruo "+m.getNombre());
			sleep(1000);
			//ataque en area resta 2 de vida a los restantes personajes
			
				p2.cargarVida(-2);//lo daño
				System.out.println("El "+ lanzador.getNombre() +" inflinge 2 de daño al "+p2.getNombre());
				sleep(1000);
				isDead(p2);//y pregunto si está muerto
			
		
				p3.cargarVida(-2);
				System.out.println("El "+ lanzador.getNombre() +" inflinge 2 de daño al "+p3.getNombre());
				sleep(1000);
				isDead(p3);
			
			break;
				
		case 2 : 
		hechizo = (Velocidad) factory.getBean("Velocidad"); 
		/*
		random = damePersonaje(lanzador,p2,p3);
		random.setCantGolpe(hechizo.getGolpe()); */
		System.out.println("El "+ lanzador.getNombre() +" lanza "+hechizo.getNombre());
		ataque(lanzador,m);
		ataque(lanzador,m);
		sleep(1000);
		break;
		}
		
		lanzador.setUltimoGolpe(true);//ultimo juego fue un hechizo
	}
	
	public static void ataque(IPersonaje p, IMonstruo bicho) throws Exception
	{			
			if(isGolpe())
			{
				bicho.cargarVida(p.getArma().getDanio());
				System.out.println("El "+p.getNombre() +" a atacado a "+bicho.getNombre()+ " con el arma "+p.getArma().getNombre() +" haciendole "+p.getArma().getDanio()+" de daño");
			}
			else
				System.out.println("El "+p.getNombre() +" erró un ataque!");
			
			p.setUltimoGolpe(false);//ultimo juego fue con arma		
			sleep(1000);
	}
	
	public static void tirameHechizoAtaque(IPersonaje lanzador, IPersonaje p2, IPersonaje p3, IMonstruo bicho) throws Exception
	{
		switch(tirameRandom(2))
		{
		case 0: ataque(lanzador,bicho); break;
		case 1: lanzoHechizo(lanzador, p2, p3, bicho); break;
		}
	}
	
	public static void secuencia(IPersonaje jugador, IPersonaje p2, IPersonaje p3, IMonstruo bicho) throws Exception
	{
		if(bicho.getVida() > 0)
		{
			if(jugador.isMagia())//para los que pueden usar hechizos
			{
				if(jugador.getNombre() == "Elfo")
					//cuando le toca al elfo pregunto por el ultimo ataque que hizo
				{
					if(jugador.ultimoGolpe())
					{//si es verdadero fue un hechizo asi que ahora ataca con el arma
									ataque(jugador,bicho);									
					}
					else
					{//si no, puede tirar un hechizo u otro ataque
				   					tirameHechizoAtaque(jugador, p2, p3, bicho);
					}
				}
				else
				{//el mago puede tirar hechizos o ataques seguidamente por turnos
					tirameHechizoAtaque(jugador, p2, p3, bicho);
				}
			}
			else
			{
				//para los personajes que no son magicos y solo pueden atacar
				ataque(jugador,bicho);
			}
			isBichoDead(bicho);//si murió el bicho lo informamos
		}
	}
	
	public static IMonstruo dameBicho()
	{
		IMonstruo bicho = null;
		
		switch(tirameRandom(3))
   		{
		
   			case 0 : bicho = (Orco) factory.getBean("Orco"); break;
   			case 1 : bicho = (MuertoViviente) factory.getBean("Muertoviviente"); break;
   			case 2 : bicho = (Gargola) factory.getBean("Gargola"); break;
   		}
		return bicho;
	}
	
	public static IArma dameArma()
	{
		IArma aux = null;
	
			switch(tirameRandom(3))
			{
			case 0 : aux = (Cuchillo) factory.getBean("Cuchillo"); break;
			case 1 : aux = (Vara) factory.getBean("Vara"); break;
			case 2 : aux = (Espada) factory.getBean("Espada"); break;
			}
			
		return aux;
	}

	public static void main(String[] args) throws Exception {		
			    
	   	IPersonaje mago = (Mago) factory.getBean("Mago");	
	   	IPersonaje guerrero = (Guerrero) factory.getBean("Guerrero");	
	   	IPersonaje elfo = (Elfo) factory.getBean("Elfo");
	   	List<IPersonaje> listaPersonaje = new ArrayList<IPersonaje>();
	   	/*List<IMonstruo> listaIMonstruos = null;*/
	   	System.out.println("Ha comenzado el juego!");
	   	listaPersonaje.add(guerrero);
	   	listaPersonaje.add(elfo);
	   	listaPersonaje.add(mago);
	   	
	   	
	    do
	   	{	
	    	//sale un bicho al azar
	    	IMonstruo bicho = dameBicho();
	    	System.out.println("Ha surgido un nuevo monstruo en la batalla : "+bicho.getNombre());
	   		
	   		do
	   		{
	   		//cuando le toca a el guerrero	   			
	   			if(guerrero.getVida() > 0)
	   			secuencia(guerrero,elfo,mago,bicho);//solo interesa el orden del primer valor por referencia que es el jugador actual
	   			if(bicho.getVida() == 0) break;
	   			//cuando le toca a el elfo
	   			if(elfo.getVida() > 0)
	   			secuencia(elfo,guerrero,mago,bicho);
	   			if(bicho.getVida() == 0) break;
	   			//cuando le toca al mago
	   			if(mago.getVida() > 0)
	   			secuencia(mago,guerrero,elfo,bicho);
	   			if(bicho.getVida() == 0) break;
	   			listaPersonaje = bicho.AtacarAUnPersonaje(listaPersonaje);
	   		}
	   		while(LogicaJuego.isAlivepjs(listaPersonaje)&&bicho.getVida() > 0);
	   			   		
	   		//cuando muere un bicho se les concede un arma al azar a los personajes vivos
	   		//cambiar esta secuencia, usar isAlive en la interface
	   		for (int i=0;i<listaPersonaje.size();i++)
	   		{
	   		if(listaPersonaje.get(i).getVida() > 0)
	   			listaPersonaje.get(i).setArma(dameArma());
	   		}
	   		/*if(elfo.getVida() > 0)
	   			elfo.setArma(dameArma());
	   		if(guerrero.getVida() > 0)
	   			guerrero.setArma(dameArma());*/
	   		
	   	}	
	   	while(LogicaJuego.isAlivepjs(listaPersonaje));
	    
	    System.out.println("El juego terminó!");
	}

}
