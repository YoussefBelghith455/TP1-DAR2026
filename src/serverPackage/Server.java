
package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {
            InetAddress adresseServeur = InetAddress.getLocalHost();
            InetSocketAddress adresseSocket =
                    new InetSocketAddress(adresseServeur, 1234);

            try (ServerSocket serverSocket = new ServerSocket()) {

                serverSocket.bind(adresseSocket);

                System.out.println("Serveur en attente de connexion...");
                System.out.println("Port : " + serverSocket.getLocalPort());

                try (Socket socket = serverSocket.accept();
                     DataInputStream entree =
                             new DataInputStream(socket.getInputStream());
                     DataOutputStream sortie =
                             new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("Un client est connecté");

                    int choix;

                    do {
                        choix = entree.readInt();

                        if (choix != 0) {
                            int x = entree.readInt();
                            double resultat;

                            switch (choix) {
                                case 1:
                                    resultat = x + 5;
                                    break;

                                case 2:
                                    resultat = x - 5;
                                    break;

                                case 3:
                                    resultat = x * 5;
                                    break;

                                case 4:
                                    resultat = (double) x / 5;
                                    break;

                                default:
                                    resultat = Double.NaN;
                                    System.out.println("Opération invalide");
                            }

                            System.out.println("Choix reçu : " + choix);
                            System.out.println("Valeur reçue : " + x);
                            System.out.println("Résultat calculé : " + resultat);

                            sortie.writeDouble(resultat);
                            sortie.flush();
                        }

                    } while (choix != 0);

                    System.out.println("Le client a choisi de quitter.");
                    System.out.println("Fin de communication.");
                }

                System.out.println("Sockets fermées.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
