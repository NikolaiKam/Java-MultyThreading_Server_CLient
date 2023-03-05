import java.io.*;
import java.util.ArrayList;

public class FileMethods {

    private static final String FILE_NAME = "text.txt";

    public void create(){

        File file = new File(FILE_NAME);

        if(file.exists()){
            System.out.println("File is already created");
        }
        else {
            try{
                file.createNewFile();
                System.out.println("File created");

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void write(String msg){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));

            bufferedWriter.write(msg);
            bufferedWriter.flush();
            System.out.println("Successful written in file");

            bufferedWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


        public ArrayList<String> read(){

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));

                ArrayList<String> fullFile = new ArrayList<>();
                String line = bufferedReader.readLine();
                while (line!=null){

                    fullFile.add(line);
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();
                return fullFile;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
    }


}
