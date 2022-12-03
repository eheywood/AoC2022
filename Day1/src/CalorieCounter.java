import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Counts the calories from the elves from a given input file.
 */
public class CalorieCounter {

    private String fileName = "calories.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<Integer> calorieArray;

    /**
     * Constructs the calorie counter, initialising the file reader and buffered reader.
     */
    public CalorieCounter() {
        try{
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        calorieArray = new ArrayList<>();
        toArray();
    }

    /**
     * Converts the input file to an ArrayList of the total calories for each elf.
     */
    private void toArray(){
        try{
            String line;
            Integer calories = 0;
            while((line = br.readLine()) != null){
                if (line.isBlank()){
                    calorieArray.add(calories);
                    calories = 0;
                }else{
                    calories = calories + Integer.valueOf(line);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the largest number of calories that an elf is carrying.
     * @return the largest number of calories
     */
    public Integer maxCalories(){
        return Collections.max(calorieArray);
    }

    /**
     * Gets the sum of the largest three amounts of calories that the elves are carrying.
     * @return sum of the largest three amounts of calories
     */
    public Integer topThreeCalories(){
        Collections.sort(calorieArray);
        Integer topThree = 0;
        for(int i = 0; i < 3; i++) {
              topThree += calorieArray.get(calorieArray.size() - (i+1));
        }
        return topThree;
    }
}
