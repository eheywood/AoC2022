import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Helps organise the elves rucksacks
 */
public class RucksackOrganiser {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<String> rucksacks;

    /**
     * Constructs the Rucksack Organiser. Initialises the file reader and buffered reader, and reads the input file into an array.
     */
    public RucksackOrganiser(){
        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rucksacks = new ArrayList<>();
        toArray();
    }

    /**
     * Converts the input file into an array of instructions and an array of stacks of cargo.
     */
    private void toArray() {
        try {
            String line;
            while ((line = br.readLine()) != null) {
                rucksacks.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the priority of an item in a rucksack from a given character
     * @param c the character being calculated
     * @return the priority of the item
     */
    private int getPriority(char c){
        int cInt = c;
        if(cInt >= 60 && cInt <= 90){
            return (cInt - 38);
        } else{
            return (cInt - 96);
        }
    }

    /**
     * Finds if there is a duplicate item in the compartments of a rucksack.
     * @param rucksack the rucksack in question
     * @return the duplicate character
     */
    private char duplicateItemsInCompartments(String rucksack){
        String[] compartment1 = rucksack.substring(0,(rucksack.length()/2)).split("");
        String[] compartment2 = rucksack.substring((rucksack.length()/2),rucksack.length()).split("");

        List<String> compList1 = Arrays.asList(compartment1);
        List<String> compList2 = Arrays.asList(compartment2);

        for(int i = 0; i < (rucksack.length() / 2); i++){
            if(compList2.contains(compList1.get(i))){
                return compList1.get(i).charAt(0);
            }
        }
        return '?';
    }

    /**
     * Calculates the priority of all the duplicate items in rucksacks
     * @return the total  priority
     */
    public int findAllDuplicatesPriority(){
        int total = 0;
        for (int i = 0; i < rucksacks.size() ; i ++ ){
            char c = duplicateItemsInCompartments(rucksacks.get(i));
            total += getPriority(c);
        }

        return total;
    }

    /**
     * Finds the duplicate item in three different rucksacks
     *
     * @param rucksack1 the first rucksack
     * @param rucksack2 the second rucksack
     * @param rucksack3 the third rucksack
     * @return the common item
     */
    private char duplicateItemsRucksacks(String rucksack1,String rucksack2,String rucksack3){
        String[] rucksack1Arr = rucksack1.split("");
        String[] rucksack2Arr = rucksack2.split("");
        String[] rucksack3Arr = rucksack3.split("");

        List<String> ruckList1 = Arrays.asList(rucksack1Arr);
        List<String> ruckList2 = Arrays.asList(rucksack2Arr);
        List<String> ruckList3 = Arrays.asList(rucksack3Arr);

        for(int i = 0; i < (ruckList1.size()); i++){
            if(ruckList3.contains(ruckList1.get(i)) && ruckList2.contains(ruckList1.get(i))){
                return ruckList1.get(i).charAt(0);
            }
        }
        return '?';
    }

    /**
     * Finds the common items from three different rucksacks and returns the total priority.
     * @return Total priority
     */
    public int findAllCommonItemsPriority(){
        int total = 0;
        for (int i = 0; i < rucksacks.size(); i += 3){
            char c = duplicateItemsRucksacks(rucksacks.get(i),rucksacks.get(i+1),rucksacks.get(i+2));
            total += getPriority(c);
        }
        return total;
    }

}
