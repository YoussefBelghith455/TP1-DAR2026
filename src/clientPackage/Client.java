
package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        System.out.println("Je suis un client pas encore connecté...");

        try {
            // Première étape : connexion au serveur
            Socket socket = new Socket("127.0.0.1", 1234);

            // Deuxième étape : connexion établie
            System.out.println("je suis un client connecté");

            // Dernière étape : fermer la socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
