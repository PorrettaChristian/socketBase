/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketbase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author porretta.christian
 */
public class ServerBase {
    private int port;
    private ServerSocket ss;
             
    public ServerBase(int port) {
        this.port = port;
        if(!startServer())
            System.out.println("errore in fase di creazione");
    }
    
    private boolean startServer(){
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        System.out.println("server creato con successo");
        return true;
    }
    
    public void runServer(){
        try {
            while(true){
                System.out.println("server in attesa di richiesta...");
                
                Socket client = ss.accept();
                System.out.println("un client connesso!!!!!!!!!!");
                
                OutputStream versoIlClient = client.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(versoIlClient));
                bw.write("ciao bello sono il sever!");
                bw.close();
                client.close();
                System.out.println("chiusura connessione effettuata.");
            }
        }
        catch (IOException ex) {
                Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        ServerBase server = new ServerBase(6666);
        server.runServer();
    }

    
    
}
