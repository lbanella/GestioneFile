package gestionefile;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Banella
 * @version 31/01/2024
 */
public class User implements Serializable{
    
    private int id;
    private String name;
    private String surname;
    private String role;

    /**
     * Costruttore parametrico di User
     * @param  id
     * @param  name
     * @param  surnname
     * @param  role
     */
    public User(int id, String name, String surname, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
    
    /**
     * Costruttore di  User da un flusso di byte in un File (serializzazione)
     * @param  nomeFile
     */

    public User(String nomeFile){
       try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomeFile))){
            User user = (User) in.readObject();
            this.id = user.id;
            this.name = user.name;
            this.surname = user.surname;
            this.role = user.role;
       } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param  nomeFile
     */
    
    public void esporta(String nomeFile){
        try (ObjectOutputStream scrittore = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            scrittore.writeObject(this);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Errore in scrittura!");
        }
    }

    @Override
    public String toString() {
        return "{" + "id:" + id + ", " + "name:" + name + ", " + "surname:" + surname + ", " + "role:" + role + '}';
    }
    
    
    
    
    
    
    
    
    
}
