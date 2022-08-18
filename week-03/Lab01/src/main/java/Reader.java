import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {
    public static void main(String[] args) {

        try(FileReader fileReader = new FileReader("./data/README.txt");
        BufferedReader reader = new BufferedReader(fileReader)){
            for (String line = reader.readline());
            line != null;
            line = reader.readLine(){
                String [] parts = line.split(" ");

                if (parts[0].equalsIgnoreCase("CREATE"){
                    new FileManager(parts[1]).creates();
                }
                 else if (parts[0].equalsIgnoreCase("APPEND"){
                    new FileManager(parts[1]).append(parts);
                }
                else if (parts[0].equalsIgnoreCase("DELETE"){
                    new FileManager(parts[1]).deletes();
                } else if(parts[0].equalsIgnoreCase("COPY")){
                    new FileManager(parts[2].create());
                }



            }
        }

    }
}
