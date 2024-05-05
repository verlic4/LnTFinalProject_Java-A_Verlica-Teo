package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pudding {
//	protected String kodeMenu;
	protected String namaMenu;
	protected int hargaMenu;
	protected int stokMenu;
	
	//constructor
	public Pudding(String namaMenu, int hargaMenu, int stokMenu){
		super();
//		this.kodeMenu = kodeRand();
		this.namaMenu = namaMenu;
		this.hargaMenu = hargaMenu;
		this.stokMenu = stokMenu;
	}
	
//	//generator random code
//	private String kodeRand() {
//		// TODO Auto-generated method stub
//		int rand = (int)(Math.random()*1000);
//		return "PD-" + rand;
//	}
//
//	//getter setter
//	public String getKodeMenu() {
//		return kodeMenu;
//	}
//
//	public void setKodeMenu(String kodeMenu) {
//		this.kodeMenu = kodeMenu;
//	}

	public String getNamaMenu() {
		return namaMenu;
	}

	public void setNamaMenu(String namaMenu) {
		this.namaMenu = namaMenu;
	}

	public int getHargaMenu() {
		return hargaMenu;
	}

	public void setHargaMenu(int hargaMenu) {
		this.hargaMenu = hargaMenu;
	}

	public int getStokMenu() {
		return stokMenu;
	}

	public void setStokMenu(int stokMenu) {
		this.stokMenu = stokMenu;
	}
	
	public StringProperty namaPuddingProperty() {
		return new SimpleStringProperty(namaMenu);
	}
	
	public StringProperty hargaPuddingProperty() {
		return new SimpleStringProperty(Integer.toString(hargaMenu));
	}
	
	public StringProperty stokPuddingProperty() {
		return new SimpleStringProperty(Integer.toString(stokMenu));
	}

}
