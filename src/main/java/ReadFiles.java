import java.io.*;
import java.util.*;

public class ReadFiles {
    public List<String> readFile() {
        List<String> result = new ArrayList<String>();

        try {
			InputStream nameData = getClass().getResourceAsStream("lastName.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(nameData));
            String lineData;
            while ((lineData = br.readLine()) != null) {result.add(lineData);}
			
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return result;
    }
}