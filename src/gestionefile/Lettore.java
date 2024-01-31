package gestionefile;

import java.io.*;


/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */

public class Lettore extends Thread{
    private String nomeFile;
    private boolean console;
    /*
    if (console == false)userà il metodo leggi
     if (console == true) userà il metodo leggiCSV
    */
    
    /**
     * @param  nomeFile
     * @param  console
     */
    public Lettore(String nomeFile,boolean console){
        this.nomeFile = nomeFile;
        this.console=console;
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * ed ogni carattere
     *  lo salvo su uno StringBuilder
     * @return sb.toString()
     */
    public String leggi(){
        StringBuilder sb= new StringBuilder();
        int i; 
         //1) apro il file
        try( FileReader  fr = new FileReader(nomeFile)) { 
            //2) leggo carattere per carattere 
            while ((i=fr.read()) != -1)
                sb.append((char) i);
            sb.append("\n\r");
            //3) chiudo il file
            fr.close();
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
        return sb.toString();
    }
    
    /**
     * utilizzando la classe DataInputStream leggendo ogni riga come stringa UTF 
     */
      public void  leggiCSV () {
       
        try (DataInputStream lettore = new DataInputStream(new FileInputStream(nomeFile))) {
            String line;
            while (true) {
                line = lettore.readUTF();
                  System.out.print(line);
            }
        } catch (EOFException ignored) {
            /* non c'è più niente da leggere */
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Errore in lettura!");
        }
     
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public boolean isConsole() {
        return console;
    }
     

    
    
    

    @Override
    public void run(){
        if(!console){
           System.out.println(leggi());
        }else{
            leggiCSV();
        }
        
    }
}
