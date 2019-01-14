package prak4client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import prak4gemklassen.*;

public class ClientOrb implements BenutzerVerwaltung {
    
    private String address;
    
    private int port;
    
    private Client refMA;
    
    public ClientOrb(){
        this.address = "localhost";
        this.port = 4711;
    }

    @Override
    public void benutzerEintragen(Benutzer benutzer)
            throws BenutzerEmptyException, BenutzerVergleichException, IOException, ClassNotFoundException {
        System.out.println(address + ", " + port);
        Socket server = new Socket(this.address, this.port);
        ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(server.getInputStream());
        
        out.writeObject("benutzerEintragen");
        out.writeObject(benutzer);
        out.flush();
        
        String result = in.readObject().toString();
        server.close();
        if (result.equals("BenutzerVergleichException")) {
            throw new BenutzerVergleichException("Dieser Benutzer ist bereits vorhanden!");
        }
        
    }

    @Override
    public boolean benutzerOk(Benutzer benutzer) throws ClassNotFoundException, IOException {
        Socket server = new Socket(this.address, this.port);
        ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(server.getInputStream());
        
        out.writeObject("benutzerOk");
        out.writeObject(benutzer);
        out.flush();
        
        Boolean result = in.readBoolean();
        server.close();
        return result;
    }
    
    public void setORB(Client mA) { this.refMA = mA; }
    
}