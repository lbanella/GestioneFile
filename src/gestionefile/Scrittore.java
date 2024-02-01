package gestionefile;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;  // Import the Scanner class

/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */

public class Scrittore implements Runnable {

    private String nomeFile;
    private String messaggio;
    private boolean consoleCSV;
    private ArrayList<String> elementi;
    
     //
    /**
     * Costruttore parametrico di Scrittore
     * @param  nomeFile
     * @param  messaggio
     */
    public Scrittore(String nomeFile, String messaggio) {
        this.nomeFile = nomeFile;
        this.consoleCSV = false;
        this.messaggio = messaggio;
    }
    
    //SCRITTORE DI CSV
    /**
     * Costruttore parametrico di Scrittore
     * serve a scrivere un array di valori 
     * separati da un ';'
     * @param  nomeFile
     * 
     */
    public Scrittore(String nomeFile){
        this.nomeFile = nomeFile;
        this.consoleCSV = true;
        this.elementi=new ArrayList<String>();
    }
    /**
     * Aggiunge all'ArrayList Elementi
     * un campo del csv 
     * @param  elemento
     * 
     */
    
    public void addElemento(String elemento){
        this.elementi.add(elemento);
    }
    
    public int getSizeOfArrayElementi(){
        return this.elementi.size();
    }

    @Override
    public void run() {
        if (!consoleCSV) {
            scrivi();
        } else {
            scriviCSV();
        }
    }

    /**
     * Scrive su un file di testo,un semplice messaggio,usando la classe BufferedWriter
     */
   public void scrivi(){ 
        //1) apro il file
        try (BufferedWriter br = new BufferedWriter(new FileWriter(nomeFile))) {
            //2) scrivo nel buffer
            br.write(messaggio);
            br.newLine(); // Utilizza newLine() per la nuova riga, più compatibile
            //3) svuoto il buffer e salvo nel file i dati
            br.flush();
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Scrive su un file di testo usando la classe DataOutputStream 
     * separando ogni indice dell'array con un ;
     */
    public void scriviCSV(){
        /* utilizzando la classe DataOutputStream */
        try (DataOutputStream scrittore = new DataOutputStream(new FileOutputStream(nomeFile, true))) {
            for (String elemento : this.elementi) {
                scrittore.writeUTF(elemento);
                scrittore.writeUTF(";");
            }
            scrittore.writeUTF("\n"); // Modificato per la nuova riga
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}