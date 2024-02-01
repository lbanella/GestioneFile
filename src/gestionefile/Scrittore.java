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
    public Scrittore(String nomeFile) {
        this.nomeFile = nomeFile;
        this.consoleCSV = true;
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
         ArrayList<String> elementi= new ArrayList<>();
        /*
         Utilizzo BufferedReader
        String id = null;
        String name = null;
        String surname = null;
        String role = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Inserisci l'id");
            id = br.readLine();  // Legge una riga dall'input
            elementi.add(id);
            System.out.println("Inserisci il name");
            name = br.readLine(); // Legge una riga dall'input
            elementi.add(name);
            System.out.println("Inserisci il name");
            surname = br.readLine(); // Legge una riga dall'input
            elementi.add(surname);
            System.out.println("Inserisci il name");
            role = br.readLine(); // Legge una riga dall'input
            elementi.add(role);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'input: " + e.getMessage());
        }
        */
        
        
        //Utilizzo Scanner
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter id");
        String s=myObj.nextLine();
        elementi.add(  s);
        
        System.out.println("Enter name");
        String name=myObj.nextLine();
        elementi.add(name);
        
        System.out.println("Enter surname");
        String surname=myObj.nextLine();
        elementi.add(surname);
       
        System.out.println("Enter role");
        String role=myObj.nextLine();
        elementi.add(role);
        
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