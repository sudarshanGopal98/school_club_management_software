package protection;

import datatypes.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sudarshan on 5/19/2016.
 */
public class DataIO {
    public static Object[] loadData(String path){
        try {
            Scanner scan = new Scanner(new File(path));
            ArrayList<String> loadedLines = new ArrayList<>();

            while(scan.hasNextLine()) {
                String lineLoaded = scan.nextLine();
                String decryptedLine = lineLoaded; //Encryptor.decrypt(lineLoaded);
                loadedLines.add(decryptedLine);
            }

            return loadedLines.toArray();
        }catch (FileNotFoundException e){
            return null;
        }
    }


    public static boolean writeData(String path, ArrayList<Student> data) throws IOException{
        BufferedWriter bufferedWriter = null;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(new File(path)));
            for(Student line: data){
                String encryptedLine = line.getSaveString(); //Encryptor.encrypt(line);
                bufferedWriter.write(encryptedLine);
                bufferedWriter.flush();
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

            return true;
        }catch (IOException e){
            if(bufferedWriter != null)
                bufferedWriter.close();

            return false;
        }catch (Exception e){
            if(bufferedWriter != null)
                bufferedWriter.close();

            return false;
        }
    }

}
