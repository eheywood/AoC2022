import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class TreeTopSearcher {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<ArrayList<String>> forest;

    private boolean[][] visibleForest;

    private int[][] scenicScoreForest;

    public TreeTopSearcher(){
        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
         forest = new ArrayList<>();

         readInput();
    }

    private void readInput(){
        try {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                forest.add(new ArrayList<>(Arrays.asList(line.split(""))));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void visible(int x, int y){
        if(x == 0 || x == (forest.get(0).size()-1)|| y == 0 || y == (forest.size())-1){
            visibleForest[x][y] = true;
        } else {
            visibleForest[x][y] = visibleFromUp(x,y) || visibleFromDown(x,y) || visibleFromLeft(x,y) || visibleFromRight(x,y);
        }
    }

    private Boolean visibleFromLeft(int x, int y){
        int value = Integer.parseInt(forest.get(y).get(x));
        for(int i = 0; i < x; i++){
            if(Integer.valueOf(forest.get(y).get(i)) >= value ){
                return false;
            }
        }
        return true;
    }

    private Boolean visibleFromRight(int x, int y){
        int value = Integer.valueOf(forest.get(y).get(x));
        for(int i = forest.get(0).size() -1; i > x; i--){
            if(Integer.valueOf(forest.get(y).get(i)) >= value ){
                return false;
            }
        }
        return true;
    }

    private boolean visibleFromUp(int x, int y){
        int value = Integer.valueOf(forest.get(y).get(x));
        for(int i = 0; i < y; i++){
            if(Integer.valueOf(forest.get(i).get(x)) >= value ){
                return false;
            }
        }
        return true;
    }

    private Boolean visibleFromDown(int x, int y) {
        int value = Integer.valueOf(forest.get(y).get(x));
        for (int i = forest.size() - 1; i > y; i--) {
            if (Integer.valueOf(forest.get(i).get(x)) >= value) {
                return false;
            }
        }
        return true;

    }

    public int findVisibleTrees(){
        int count = 0;
        visibleForest = new boolean[forest.size()][forest.size()];

        for( int x = 0; x < forest.get(0).size(); x ++){
            for( int y = 0; y < forest.size(); y++){
                visible(x,y);
            }
        }

        for( int x = 0; x < forest.get(0).size(); x ++){
            for( int y = 0; y < forest.size(); y++){
                if(visibleForest[x][y] == true){
                    count += 1;
                }
            }
        }
        return count;
    }

    private void scenicScoreForOneTree(int x, int y){
        scenicScoreForest[x][y] = scoreDown(x,y) * scoreUp(x,y) * scoreLeft(x,y) * scoreRight(x,y);
        System.out.println(scoreDown(x,y) + "," + (scoreUp(x,y) + "," + scoreLeft(x,y) + "," + scoreRight(x,y)));
    }

    private int scoreLeft(int x, int y){
        int height = Integer.parseInt(forest.get(y).get(x));
        int score = 0;
        for(int i = (x-1); i >= 0; i--){
            if(height > Integer.valueOf(forest.get(y).get(i))){
                score += 1;

            }else{
                score += 1;
                break;
            }
        }

        return score;
    }

    private int scoreRight(int x, int y){
        int height = Integer.valueOf(forest.get(y).get(x));
        int score = 0;
        for(int i = (x+1) ; i < forest.size(); i++){
            if(height > Integer.valueOf(forest.get(y).get(i))){
                score += 1;
            }else{
                score += 1;
                break;
            }
        }
        return score;
    }

    private int scoreUp(int x, int y){
        int height = Integer.valueOf(forest.get(y).get(x));
        int score = 0;
        for(int i = (y-1); i >= 0 ; i--){
            if(height > Integer.valueOf(forest.get(i).get(x))){
                score += 1;
            }else{
                score += 1;
                break;
            }
        }
        return score;
    }

    private int scoreDown(int x, int y) {
        int height = Integer.valueOf(forest.get(y).get(x));
        int score = 0;
        for (int i = (y+1); i < forest.size(); i++) {
            if(height > Integer.valueOf(forest.get(i).get(x))){
                score += 1;
            }else{
                score += 1;
                break;
            }
        }
        return score;

    }

    private int findMaxScore(){
        int max = 0;
        for(int x = 0; x < scenicScoreForest.length; x++){
            for(int y = 0; y < scenicScoreForest.length; y++){
                if(scenicScoreForest[x][y] > max){
                    max = scenicScoreForest[x][y];
                }
            }
        }
        return max;
    }

    public int findMaximumScenicSore(){
        scenicScoreForest = new int[forest.size()][forest.size()];

        for(int x = 0; x < forest.size(); x++){
            for(int y = 0; y < forest.size(); y++){
                scenicScoreForOneTree(x,y);
            }
        }

        return findMaxScore();
    }




}
