package gestionefile;

/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */
public class Copiatore extends Thread {
    private String nomeFileDaCopiare;
    private String nomeFileFinale;
    
    /**
     * @param  nomeFileDaCopiare
     * @param  nomeFileFinale
     */
    public Copiatore(String nomeFileDaCopiare,String nomeFileFinale){
         this.nomeFileDaCopiare=nomeFileDaCopiare;
       this.nomeFileFinale=nomeFileFinale;
    }
    
    
    public void copia(){
        //1) Leggo il contenuto del file copiato
        Lettore lettore = new Lettore(nomeFileDaCopiare,false);
        String contenuto=lettore.leggi();

        //2)Scrivo il contenuto del file copiato
        Scrittore scrittore = new Scrittore(nomeFileFinale,contenuto);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
         try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        } 
    }

    public String getNomeFileDaCopiare() {
        return nomeFileDaCopiare;
    }

    public String getNomeFileFinale() {
        return nomeFileFinale;
    }
    
    

    @Override
    public void run(){
        copia();
    }
}
