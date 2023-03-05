import java.io.IOException;
import java.io.PrintStream;
import java.net.SecureCacheResponse;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SplittableRandom;

public class ClientMain {
    public static void main(String[] args) {

        try {
            Socket server = new Socket("localhost", 1000);
            Scanner console = new Scanner(System.in);
            Scanner sc = new Scanner(server.getInputStream());
            PrintStream out = new PrintStream(server.getOutputStream());

            userMenu(console,sc,out);

        }catch (IOException e ){
            e.printStackTrace();
        }


    }

    public static void userMenu(Scanner console, Scanner sc, PrintStream out){

        while (true) {
            //Login msg
            System.out.println(sc.nextLine());

            //Transport the answer to the server - out is server output stream
            //console.nextLine take the next line

            out.println(console.nextLine());


            //Takes the msg that the server give
            String next = sc.nextLine();
            System.out.println(next);

            if(next.equalsIgnoreCase("goodbye!")){
                return;
            }

            //Export the answer to the server
            out.println(console.nextLine());

            next = sc.nextLine();
            System.out.println(next);

            switch (next){
                case "Writer: ":
                    System.out.println(next = sc.nextLine());
                    out.println(console.nextLine());
                    System.out.println(next = sc.nextLine());
                    System.out.println(next = sc.nextLine());
                    break;
                case "Reader: ":
                    System.out.println(next = sc.nextLine());
                    System.out.println(next = sc.nextLine());

                    System.out.println(sc.nextLine());

                    break;
            }

        }
    }

}
