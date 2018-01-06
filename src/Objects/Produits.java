package Objects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;

public class Produits implements Serializable{
	
	private List<String> prduit ;
	private List<Produits> listeprduit ;// = new ArrayList<>(); 
	private String Nom ;
	private int stock;
	private int fornisseur;
	planches planche ;
	
	public Produits(String n, int s, int f, int np) {
		this.Nom = n ;
		this.fornisseur = f ;
		this.stock = s ;
		planche = new planches(np)	;
	}

	public List<Produits> getListeprduit() {
		return listeprduit;
	}
	public void setListeprduit(List<Produits> listeprduit) {
		this.listeprduit = listeprduit;
	}

    
   
    
	public Produits() {
		listeprduit = new ArrayList<>(); 

	}
	/*
	public Produits() {
	prduit = new ArrayList<>();
	prduit.add("Table");
	prduit.add("Chaise");
	prduit.add("Buffet");
	prduit.add("Lit");
	prduit.add("Chevet");
	prduit.add("Armoire");
	prduit.add("Banquette");
	prduit.add("Fauteuil");
	prduit.add("Etagère");
	this.stock =(int) (Math.random() * 50);
	this.fornisseur =(int) (Math.random() * 50);	
	}
	*/

	public List<String> getListProduit() {
        return prduit;
    }

    public void setprduit(String p) {
    	prduit.add(p);
    }

	public int getstock() {
        return stock;
    }
	public void setFornisseur(int fornisseur) {
		this.fornisseur = fornisseur;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	
	public int getfornisseur() {
        return fornisseur;
    }
	public String getNom() {
		return Nom;
	}
	
}