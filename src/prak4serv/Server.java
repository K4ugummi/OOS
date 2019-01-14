package prak4serv;

import java.io.IOException;
import prak4gemklassen.*;

public class Server {
    public Server() throws IOException, ClassNotFoundException, BenutzerEmptyException {
        BenutzerVerwaltungAdmin bv = new BenutzerVerwaltungAdmin();
        ServerOrb so = new ServerOrb(bv);
    }
    
    public static void main(String[] args) throws BenutzerEmptyException {
        try {
            Server server = new Server();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}