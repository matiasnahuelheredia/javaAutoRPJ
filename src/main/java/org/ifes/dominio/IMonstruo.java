package org.ifes.dominio;

import java.util.List;

public interface IMonstruo {
	
	public String getNombre();
	public int getVida();
	public void setVida(int vida);
	public int getDanio();
	public void setDanio(int danio);
	public void cargarVida(int vida);
	public List<IPersonaje> AtacarAUnPersonaje(IMonstruo moustruo, List<IPersonaje> personaje);
}
