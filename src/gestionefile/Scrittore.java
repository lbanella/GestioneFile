package gestionefile;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */

public class Scrittore implements Runnable {

    private String nomeFile;
    private String messaggio;
    private boolean consoleCSV;
    private String[] elementi ;
    
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
     * @param  elementi
     */
    public Scrittore(String nomeFile,String[]elementi) {
        this.nomeFile = nomeFile;
        this.consoleCSV = true;
        this.elementi=elementi;
     
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
     * Scrive su un file di testo usando la classe BufferedWriter
     */
    public void scrivi() {
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
    public void scriviCSV() {
        /* utilizzando la classe DataOutputStream */
        try (DataOutputStream scrittore = new DataOutputStream(new FileOutputStream(nomeFile, true))) {
            for (String elemento : elementi) {
                scrittore.writeUTF(elemento);
                scrittore.writeUTF(";");
            }
            scrittore.writeUTF("\n"); // Modificato per la nuova riga
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}