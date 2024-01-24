/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionefile;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Utente
 */
public class Copiatore extends Thread {
    String nomeFileDaCopiare;
    String nomeFileFinale;
   
    
    public Copiatore(String nomeFileDaCopiare,String nomeFileFinale){
         this.nomeFileDaCopiare=nomeFileDaCopiare;
       this.nomeFileFinale=nomeFileFinale;
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * e lo mostra in output
     */
    public void copia(){
        Lettore lettore = new Lettore(nomeFileDaCopiare);
        Scrittore scrittore = new Scrittore(nomeFileFinale, lettore.leggi());
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
         try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        } 
    }
    

    @Override
    public void run(){
        copia();
    }
}
