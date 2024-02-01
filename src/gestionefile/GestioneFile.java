package gestionefile;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */
public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
       //Istanzio un BufferedReader inizialmente null che mi servirà per richiedere i dati in Input
        BufferedReader br =null;
        //1)LETTURA
        Lettore lettore = new Lettore("user.json",false);
        lettore.start();
        try {
            lettore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        } 
       

        //2)ELABORAZIONE
        String username = null;
        String password = null;
        String verme =null;
        
        
        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci l'username");
            username = br.readLine();  // metodo di BufferedReader che serve per leggere una Stringa dall' input
            System.out.println("Inserisci la password");
            password = br.readLine().toUpperCase(); 
            System.out.println("Inserisci il verme");
             verme = br.readLine().toUpperCase();
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'input: " + e.getMessage());
        }
        
        
        
  
   
        Cifratore cifratore=new Cifratore(verme);
        String passwordCifrata = cifratore.cifra(password);
      
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv", username + ";" + passwordCifrata);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
         try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        } 
        
        
        //4) COPIA
          Copiatore copiatore = new Copiatore("output.csv","copia.csv");
        copiatore.start();
        try {
            copiatore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        }
        System.out.println("Copia nel file "+copiatore.getNomeFileFinale()+" effettuata !");
        

        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // ISSUE 3
         System.out.println("ISSUE 3");
         try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        Scrittore scrittoreCSV = new Scrittore("user.csv");
        String id = null;
        String name = null;
        String surname = null;
        String role = null;
        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci l'id");
            id = br.readLine();
            scrittoreCSV.addElemento(id);
            System.out.println("Inserisci il name");
            name = br.readLine(); 
            scrittoreCSV.addElemento(name);
            System.out.println("Inserisci il surname");
            surname = br.readLine(); 
            scrittoreCSV.addElemento(surname);
            System.out.println("Inserisci il role");
            role = br.readLine(); 
            scrittoreCSV.addElemento(role);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'input: " + e.getMessage());
        }
        
        /* il threadScrittore viene istanziato e fatto partire
        *  quando la dimensione del suo array  è uguale a 4 ovvero con i campi
        *  id,name,surname e role inseriti
        */
        
        
        if(scrittoreCSV.getSizeOfArrayElementi()==4){
            Thread threadScrittoreCSV = new Thread(scrittoreCSV);
            threadScrittoreCSV.start();
            try {
                threadScrittoreCSV.join();
            } catch (InterruptedException ex) {
                System.err.println("Errore nel metodo join()");
            } 
        }
        
       
         
        
        
        Lettore lettoreCSV=new Lettore("user.csv",true);
        System.out.println("LETTURA DEL FILE "+lettoreCSV.getNomeFile());
        lettoreCSV.start();
        try{
            lettoreCSV.join();
        }catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        }
         
        try {
            Thread.sleep(3000);
        }catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //ISSUE 4
        
        
        System.out.println("ISSUE 4");
        try {
            Thread.sleep(1500);
        }catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //Creazione di un User con costruttore parametrico
        /*
        User user=new User(1,"Lorenzo","Banella","studente");
        esportaUser("user.txt",user);
        */
        importaUser("user.txt");
    }
    

    /**
     * Metodo statico di gestione File 
     * si appoggia al metodo esporta di User 
     * esporta sotto forma di un flusso di Byte l'User ,passato come secondo parametro,
     * nel file passato come primo parametro del metodo
     * 
     * @param  nomeFile
     * @param  user
     */
    
    public static void esportaUser(String nomeFile,User user){
       user.esporta(nomeFile);
       System.out.println("Esportazione Completata!!");
    }
    
    /**
     * Metodo statico di gestione File 
     * si appoggia al metodo Costruttore di User 
     * per istanziarne uno nuovo da un flusso di Byte
     * che si trovano nel file passato come parametro del metodo
     * 
     * @param  nomeFile
     */
    public static void importaUser(String nomeFile){
          User user =new User(nomeFile);
           System.out.println("Importazione Completata!!");
          System.out.println(user.toString()); 
     }
    
		
    }   


