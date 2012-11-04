package org.ifes.dominio;


public interface IPersonaje {
	
	public abstract String getNombre();
	public abstract int getVida();
	public abstract void setVida(int nuevaVida);
	public abstract IArma getArma();
	public abstract void setArma(IArma nuevaArma);
	public boolean isMagia();
	public boolean ultimoGolpe();
	public void  setUltimoGolpe(boolean ultimo);
	public void cargarVida(int vida);
	public int cantGolpe();
	public void setCantGolpe(int numGolpes);
	public IMonstruo ataca(IMonstruo mous);

}
