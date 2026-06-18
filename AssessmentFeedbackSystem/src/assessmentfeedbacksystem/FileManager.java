package assessmentfeedbacksystem;

import java.util.*;
import java.io.*;

public class FileManager {
    // Read file function
    public static List<String> readFile(String file_name){
        List<String> lines = new ArrayList<>();
        File file = new File(file_name);
        try {
            if (!file.exists()) {
                System.out.println("File not found! Creating a new one...");
                file.createNewFile();
                return lines; // return empty list
            }
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    line = line.trim();
                    
                    if (!line.isEmpty()) {
                        lines.add(line);
                    }
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

        
    // Write file function
    public static void writeFile(String file_name, List<String> lines, boolean append){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file_name, append))){
            for(String line: lines){
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void writeFile(String file_name, String line, boolean append) throws IOException{
        writeFile(file_name, List.of(line), append);
    }
}
