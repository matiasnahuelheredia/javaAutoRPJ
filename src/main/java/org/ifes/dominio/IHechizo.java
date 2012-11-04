package org.ifes.dominio;

public interface IHechizo {
	
	public String getNombre();
	public int getImpacto();
	public void setImpacto(int impacto);
	public int getGolpe();
	public void setGolpe(int golpe);
	public boolean getExpansible();
	public void setExpansible(boolean expansible);

}
