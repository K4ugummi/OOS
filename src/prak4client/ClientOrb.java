package prak4client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import prak4gemklassen.*;

public class ClientOrb implements BenutzerVerwaltung {

    private String address;

    private int port;

    private Client client;

    public ClientOrb() {
        this.address = "localhost";
        this.port = 4711;
    }

    @Override
    public void benutzerEintragen(Benutzer benutzer)
            throws BenutzerVerwaltungsSystem, BenutzerVorhanden,
            BenutzerRichtlinie {
        System.out.println(address + ", " + port);
        Socket server;
        try {
            server = new Socket(this.address, this.port);
            ObjectOutputStream out = new ObjectOutputStream(
                    server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
    
            out.writeObject("benutzerEintragen");
            out.writeObject(benutzer);
            out.flush();
    
            String result = in.readObject().toString();
    
            server.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean benutzerOk(Benutzer benutzer) {
        Boolean result = false;
        try {
        Socket server = new Socket(this.address, this.port);
        ObjectOutputStream out = new ObjectOutputStream(
                server.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(server.getInputStream());

        out.writeObject("benutzerOk");
        out.writeObject(benutzer);
        out.flush();

        result = in.readBoolean();
        server.close();
    
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}