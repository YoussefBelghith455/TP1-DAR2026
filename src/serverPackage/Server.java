
package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {

            System.out.println("Serveur en attente de connexion...");

            try (Socket socket = serverSocket.accept();
                 DataInputStream entree = new DataInputStream(socket.getInputStream());
                 DataOutputStream sortie = new DataOutputStream(socket.getOutputStream())) {

                System.out.println("Un client est connecté");

                int x;

                do {
                    // Recevoir l'entier envoyé par le client
                    x = entree.readInt();

                    if (x != 0) {
                        // Calculer le résultat
                        int resultat = x * 5;

                        System.out.println("Entier reçu : " + x);
                        System.out.println("Résultat calculé : " + resultat);

                        // Envoyer le résultat au client
                        sortie.writeInt(resultat);
                        sortie.flush();
                    }

                } while (x != 0);

                System.out.println("Le client a envoyé 0.");
                System.out.println("Fin de communication.");
            }

            System.out.println("Sockets fermées.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
