import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Actions {

    private String filePath;
    private String newFilePath;

    public void create(String filePath){

        File file = new File(filePath);

        try{
            if (file.createNewFile()) {
                System.out.println( filePath + " created");
            }else{
                System.out.println( filePath+ " file already exists.");
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append(String filePath, String [] toAppend){
        String line = "";
        for (int i = 2; i < toAppend.length; i++){
            lineToAdd += toAppend[i];
        }

        try (FileWriter fileWriter = new FileWriter("colors.txt", true);
             PrintWriter writer = new PrintWriter(fileWriter)) {

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(){
        File file = new File(filePath);
        boolean success = file.delete();

        if(success) {
            System.out.println(filePath + " was deleted");
        } else{
            System.out.println(filePath + " was not deleted");
        }
    }

    public void copy(String String[] parts) throws IOException{

        Path sourceFilePath = Paths.get("./bar.txt");
        Path targetFilePath = Paths.get(System.getProperty("user.home");

        Files.copy(sourceFilePath, targetFilePath.REPLACE_EXISTING);


    }
}

