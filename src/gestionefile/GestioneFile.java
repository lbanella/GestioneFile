package gestionefile;

import java.io.*;

/**
 *
 * @author Lorenzo
 * @version 18/01/24
 */
public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        try {
            lettore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        } 
       

        //2)ELABORAZIONE
        String username = null;
        String password = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Inserisci l'username");
            username = br.readLine().toUpperCase();  // Legge una riga dall'input
            System.out.println("Inserisci la password");
             password = br.readLine().toUpperCase(); // Legge una riga dall'input
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'input: " + e.getMessage());
        }
        
        
        Cifratore cifratore=new Cifratore("TPSIT");
        String passwordCifrata = cifratore.cifra(password);
      

        

  
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv", username + ";" + passwordCifrata);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        
        //4) COPIA
        
        
        
        
    }
    
}

