import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Rock Paper Scissors scorer
 */
public class RPSScorer {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<String> strategyArray;

    /**
     * Constructs the RPS Scorer and initialises the file reader and buffered reader
     */
    public RPSScorer(){

        try{
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        strategyArray = new ArrayList<>();
        toArray();
    }

    /**
     * Converts the input file into an array list of all of the rounds of RPS
     */
    private void toArray(){
        try{
            String line;
            while((line = br.readLine()) != null){
                strategyArray.add(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Calculates the score of single round of RPS with the new strategy from part two
     * @param round The single round as a String
     * @return the score from the round
     */
    private Integer oneRoundStrategyScore(String round){
        Integer score = 0;
        String[] split = round.split(" ");

        if(split[1].equals("X")){
            if(split[0].equals("A")){
                score += 3;
            }else if(split[0].equals("B")){
                score += 1;
            }else if(split[0].equals("C")){
                score += 2;
            }
        }else if (split[1].equals("Y")){
            score += 3;
            if(split[0].equals("A")){
                score += 1;
            }else if(split[0].equals("B")){
                score += 2;
            }else if(split[0].equals("C")){
                score += 3;
            }
        }else if (split[1].equals("Z")) {
            score += 6;
            if(split[0].equals("A")){
                score += 2;
            }else if(split[0].equals("B")){
                score += 3;
            }else if(split[0].equals("C")){
                score += 1;
            }
        }
        return score;
    }

    /**
     * Calculates the total score of the entire game with the new strategy in part 2
     * @return the total score
     */
    public Integer totalStrategyScore(){
        int totalScore = 0;
        for(int i = 0; i < strategyArray.size(); i++ ){
            totalScore += oneRoundStrategyScore(strategyArray.get(i));
        }
        return totalScore;
    }

    /**
     * Calculates the score of the entire game
     * @return the total score
     */
    public Integer totalScore(){
        int totalScore = 0;
        for(int i = 0; i < strategyArray.size(); i++ ){
            totalScore += oneRoundScore(strategyArray.get(i));
        }
        return totalScore;
    }

    /**
     * Calculates the score of a single round of RPS
     * @param round The single round as a String
     * @return the score from the round
     */
    private Integer oneRoundScore(String round){
        Integer score = 0;
        String[] split = round.split(" ");

        if(split[1].equals("X")){
            score += 1;
            if(split[0].equals("C")){
                score += 6;
            }else if(split[0].equals("A")){
                score += 3;
            }
        }else if (split[1].equals("Y")){
            score += 2;
            if(split[0].equals("A")){
                score += 6;
            }else if(split[0].equals("B")){
                score += 3;
            }
        }else if (split[1].equals("Z")) {
            score += 3;
            if(split[0].equals("B")){
                score += 6;
            }else if(split[0].equals("C")){
                score += 3;
            }
        }
        return score;
    }


}
