package org.ifes.dominio;

import java.util.List;
import java.util.Random;

abstract class Moustruo {
	private int hv;
	private int danio;
	
	public String getNombre(){ return this.getClass().getSimpleName(); }
	public Moustruo(int hv, int danio) {
		super();
		this.hv = hv;
		this.danio = danio;
	}
	public int getVida(){ return this.hv; }
	public void setVida(int nuevaVida){ this.hv = nuevaVida; }
	public int getDanio(){ return this.danio; }
	public void setDanio(int nuevoDanio) { this.danio = nuevoDanio; }
	public void cargarVida(int vida) 
	{ 
		int operacion = this.hv+vida;
		if((operacion) < 0)
							this.hv = 0;
		else
							this.hv = this.hv+vida;
	}
	public List<IPersonaje> AtacarAUnPersonaje(List<IPersonaje> personaje)
	{
		Random rnd = new Random();
		IPersonaje persAux;//personaje auxiliar
		persAux = personaje.get(rnd.nextInt(personaje.size()));	
		if(rnd.nextInt(2)==1)
		{
		persAux.cargarVida(this.getDanio());
		
		System.out.println("El monstruo "+this.getNombre()+" a atacado al "+persAux.getNombre()+" haciendole "+this.getDanio()+" de daño!");
		}
		else
			{System.out.println("El monstruo "+this.getNombre()+" a fallado su ataque!");}
		return personaje;
	}
	

}
