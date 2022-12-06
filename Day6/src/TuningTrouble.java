import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TuningTrouble {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;

    private String packet;

    public TuningTrouble(){
        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        readInput();
    }

    /**
     * Reads the input file and saves the string
     */
    private void readInput(){
        try {
            String line;
            while ((line = br.readLine()) != null) {
                packet = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if all the characters in the string are unique
     * @param substring the substring in question
     * @return true if all characters are unique, false if not.
     */
    private boolean uniqueChars(String substring){
        String[] chars = substring.split("");

        for(int x = 0; x < (chars.length-1);x++){
            for(int y = x+1; y < (chars.length);y++){
                if(chars[x].equals(chars[y])){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Finds where the start of packet marker is in the input string
     */
    public int findWhereMarkerIsFor4(){
        for (int i = 0; i < packet.length()-3; i++){
            System.out.println(packet.substring(i,i+4));
            if(uniqueChars(packet.substring(i,i+4))){

                return (i+4);
            }
        }
        return 0;
    }

    /**
     * Finds where the start of packet marker is in the input string
     */
    public int findWhereMarkerIsFor14(){
        for (int i = 0; i < packet.length()-14; i++){
            System.out.println(packet.substring(i,i+14));
            if(uniqueChars(packet.substring(i,i+14))){

                return (i+14);
            }
        }
        return 0;
    }



}
