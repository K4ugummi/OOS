package prak4serv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import prak4gemklassen.*;

public class ServerOrb {
    ServerOrb(BenutzerVerwaltungAdmin bv) throws IOException, ClassNotFoundException, BenutzerEmptyException {
        try {
            ServerSocket server = new ServerSocket(4711);
            
            dbInitialisieren(bv);
            
            while(true) {
                Socket client = server.accept();
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                
                String methode = (String) in.readObject();
                Benutzer par = (Benutzer) in.readObject();
                
                if (methode.equals("benutzerEintragen")) {
                    try {
                        bv.benutzerEintragen(par);
                        out.writeObject("Benutzer remote eintragen erfolgreich!");
                        out.flush();
                    } catch (BenutzerVergleichException f) {
                        out.writeObject("BenutzerVergleichException");
                    }
                }
                if (methode.equals("benutzerOk")) {
                    out.writeBoolean(bv.benutzerOk(par));
                    out.flush();
                }
                client.close();
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void dbInitialisieren(BenutzerVerwaltungAdmin bAdm) throws ClassNotFoundException, IOException {
        System.out.println("Soll die Datenhaltung initialisiert werden (0/1)?");
        int dbInitialisieren = -1;
        boolean check = true;
        do {
            try {
                if (check == false) {
                    System.out.println("Ungültige Eingabe!");
                }
                BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
                dbInitialisieren = Integer.parseInt(din.readLine());
                if (dbInitialisieren == 0 || dbInitialisieren == 1) {
                    check = true;
                }
                else {
                    check = false;
                }
            }catch(NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }while(dbInitialisieren != 0 && dbInitialisieren != 1);
        if (dbInitialisieren == 1) {
            bAdm.dbInitialisieren();
            System.out.println("Die Datenhaltung wurde initialisiert!");
        }
        else {
            System.out.println("Die Datenhaltung wurde NICHT initialisiert!");
        }
    }
}