
package gestionefile;

import java.util.ArrayList;

/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */

public class Cifratore {
    private  Matrice matrice;
    private ArrayList<Vigenere> quadranti = new ArrayList<Vigenere>();
    
    /**
     * 
     * @param verme
     */
    
    public Cifratore(String verme){
    this.matrice = new Matrice(verme);
    Vigenere quadrante_1=new Vigenere(0,12,0,12,matrice);
     quadranti.add(quadrante_1);
    
    Vigenere quadrante_2=new Vigenere(0,12,12,26,matrice);
     quadranti.add(quadrante_2);
    
    Vigenere quadrante_3=new Vigenere(12,26,0,12,matrice);
     quadranti.add(quadrante_3);
    
    Vigenere quadrante_4=new Vigenere(12,26,12,26,matrice);
    quadranti.add(quadrante_4);

    for(Vigenere v:quadranti){
     Thread t= new Thread(v);
     t.start();
     try {
       t.join();
     }catch (InterruptedException ex) {
         System.err.println("Errore metodo join");
     }
    }  
    }
    
    /**
     * 
     * @param messaggio
     * @return fraseCriptata
     */
    
    public String cifra(String messaggio){
        String fraseCriptata=matrice.cifra(messaggio);
        return fraseCriptata;
    }
    
    /**
     * 
     * @param messaggio
     * @return fraseDecriptata
     */
    
    public String decifra(String messaggio){
        String fraseDecriptata=matrice.deCifra(messaggio);
        return fraseDecriptata;
    }
}
