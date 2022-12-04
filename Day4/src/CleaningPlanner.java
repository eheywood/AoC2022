import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Cleaning Planner class, helps the elves organise their cleaning rota.
 */
public class CleaningPlanner {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<ElfPair> cleaningPlan;

    /**
     * Constructs the cleaning planner, initialising the file reader and buffered reader.
     */
    public CleaningPlanner() {
        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cleaningPlan = new ArrayList<>();

        //Converts the input file to an arrayList.
        toArray();
    }

    /**
     * Converts the input file into an array list of all the pairs of assignments for elves
     */
    private void toArray() {
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                cleaningPlan.add(new ElfPair(splitLine[0], splitLine[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a pair of assignments fully contain one another given a pair of elves
     *
     * @param pair A pair of elves in the form of an ElfPair
     * @return True if fully contains, False if they don't fully contain one another.
     */
    private boolean pairFullyContains(ElfPair pair) {
        String[] elf1 = pair.getElf1().split("-");
        int min1 = Integer.valueOf(elf1[0]);
        int max1 = Integer.valueOf(elf1[1]);

        String[] elf2 = pair.getElf2().split("-");
        int min2 = Integer.valueOf(elf2[0]);
        int max2 = Integer.valueOf(elf2[1]);

        if (min1 <= min2 && max2 <= max1) {
            return true;
        } else if (min1 >= min2 && max2 >= max1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the number of pairs that the assignments fully contain each other
     * @return the number of pairs that fully contain each other
     */
    public Integer numberOfFullyContains() {
        Integer fullyContains = 0;
        for (int i = 0; i < cleaningPlan.size(); i++) {
            if(pairFullyContains(cleaningPlan.get(i))){
                fullyContains += 1;
            }
        }
        return fullyContains;
    }

    /**
     * Given a pair of assignments to elves, will calculate if their assignments overlap
     * @param pair The pair of elves
     * @return True if there is an overlap,  false if there is not
     */
    private boolean pairOverlaps(ElfPair pair){
        String[] elf1 = pair.getElf1().split("-");
        int min1 = Integer.valueOf(elf1[0]);
        int max1 = Integer.valueOf(elf1[1]);

        String[] elf2 = pair.getElf2().split("-");
        int min2 = Integer.valueOf(elf2[0]);
        int max2 = Integer.valueOf(elf2[1]);

        if(min1 <= min2 && min2 <= max1){
            return true;
        }else if(min2 <= min1 && min1 <= max2){

            return true;
        }else{
            return false;
        }
    }

    /**
     * Gets the number of pairs that the assignments that overlap
     * @return the number of pairs that overlap each other
     */
    public Integer numberOfOverlaps() {
        Integer overlaps = 0;
        for (int i = 0; i < cleaningPlan.size(); i++) {
            if(pairOverlaps(cleaningPlan.get(i))){
                overlaps += 1;
            }
        }
        return overlaps;
    }
}
