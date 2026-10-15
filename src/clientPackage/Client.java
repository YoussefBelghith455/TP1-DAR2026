
package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Donner l'adresse IP du serveur : ");
            String ipServeur = scanner.nextLine();

            InetAddress adresseServeur = InetAddress.getByName(ipServeur);
            InetSocketAddress adresseSocket =
                    new InetSocketAddress(adresseServeur, 1234);

            System.out.println("Je suis un client pas encore connecté...");

            try (Socket socket = new Socket()) {

                socket.connect(adresseSocket);

                try (DataOutputStream sortie =
                             new DataOutputStream(socket.getOutputStream());
                     DataInputStream entree =
                             new DataInputStream(socket.getInputStream())) {

                    System.out.println("Je suis un client connecté");

                    int choix;

                    do {
                        System.out.println("\n===== MENU CALCULATRICE =====");
                        System.out.println("1. Addition");
                        System.out.println("2. Soustraction");
                        System.out.println("3. Multiplication");
                        System.out.println("4. Division");
                        System.out.println("0. Quitter");
                        System.out.println("============================");
                        System.out.print("Votre choix : ");

                        choix = scanner.nextInt();

                        if (choix < 0 || choix > 4) {
                            System.out.println("Choix invalide !");
                            continue;
                        }

                        sortie.writeInt(choix);
                        sortie.flush();

                        if (choix != 0) {
                            System.out.print("Donner un entier x : ");
                            int x = scanner.nextInt();

                            sortie.writeInt(x);
                            sortie.flush();

                            double resultat = entree.readDouble();

                            System.out.println("Résultat reçu : " + resultat);
                        }

                    } while (choix != 0);

                    System.out.println("Fin de communication.");
                }

                System.out.println("Connexion fermée.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
