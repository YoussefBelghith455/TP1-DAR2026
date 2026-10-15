
package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        System.out.println("Je suis un client pas encore connecté...");

        try (Socket socket = new Socket("127.0.0.1", 1234);
             DataOutputStream sortie = new DataOutputStream(socket.getOutputStream());
             DataInputStream entree = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Je suis un client connecté");

            int x;

            do {
                System.out.print("Donner un entier x (0 pour quitter) : ");
                x = scanner.nextInt();

                // Envoyer l'entier au serveur
                sortie.writeInt(x);
                sortie.flush();

                if (x != 0) {
                    // Recevoir le résultat du serveur
                    int resultat = entree.readInt();
                    System.out.println("Résultat reçu : " + resultat);
                }

            } while (x != 0);

            System.out.println("Fin de communication.");
            System.out.println("Connexion fermée.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
