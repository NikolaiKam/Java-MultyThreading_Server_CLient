import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Server {

    private static ServerSocket server = null;
    private static final FileMethods methods = new FileMethods();


    public Server(){
        methods.create();
    }

    public void start(){
        try {
            System.out.println("Server is listening...");
            server = new ServerSocket(1000);

            while (true){
                Socket client =  server.accept();

                Thread clientThread = new Thread(() ->{
                    System.out.println("Client accepted.");
                    Scanner sc = null;
                    PrintStream out = null;

                    try{
                        sc = new Scanner(client.getInputStream());
                        out = new PrintStream(client.getOutputStream());
                        userMenu(sc,out);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    finally {
                        if(sc!=null){
                            sc.close();
                        }
                        if(out!=null){
                            out.close();
                        }
                    }
                });
                clientThread.start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void userMenu(Scanner sc, PrintStream out){

        while (true) {
            out.println("Login Y/N");

            String login = sc.nextLine();

            if (!login.equalsIgnoreCase("Y")) {
                out.println("Goodbye!");
                return;
            }

            out.println("You want to write or read [W/R]");

            login = sc.nextLine();

            switch (login)
            {
                case "W":
                    out.println("Writer: ");
                    out.println("Enter the text you want to enter in file: ");
                    String msg = sc.nextLine();
                    methods.write(msg);
                    out.println();
                    out.println("The message is successfully written!");
                    break;
                case "R":
                    out.println("Reader: ");
                    out.println("Here is the text in the file: ");
                    out.println();

                    ArrayList<String> msg_arr = methods.read();

                    out.println(Arrays.toString(msg_arr.stream().toArray()));
                    break;

            }






        }
    }
}
