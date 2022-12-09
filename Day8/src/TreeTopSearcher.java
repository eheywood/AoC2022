import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tree top searcher class to help the elves find a perfect treetop hideout!
 */
public class TreeTopSearcher {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<ArrayList<String>> forest;

    private boolean[][] visibleForest;

    private int[][] scenicScoreForest;

    /**
     * Constructs the tree top searcher class by initialising the file reader, buffered reader and the arrays and arraylists that are needed for the class.
     */
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

    /**
     * Reads the input from the input file into an arraylist
     */
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

    /**
     * Calculates if a given tree is visible or not from the outside and updates the visibleForest array.
     * @param x the x coordinate of the tree in question
     * @param y the y coordinate of the tree in question
     */
    private void visible(int x, int y){
        if(x == 0 || x == (forest.get(0).size()-1)|| y == 0 || y == (forest.size())-1){
            visibleForest[x][y] = true;
        } else {
            visibleForest[x][y] = visibleFromUp(x,y) || visibleFromDown(x,y) || visibleFromLeft(x,y) || visibleFromRight(x,y);
        }
    }

    /**
     * Calculates if the tree is visible from the left of the grid and returns true if so
     * @param x the x coordinate of the tree in question
     * @param y the y coordinate of the tree in question
     * @return true if the tree is visible from the left, false if not
     */
    private Boolean visibleFromLeft(int x, int y){
        int value = Integer.parseInt(forest.get(y).get(x));
        for(int i = 0; i < x; i++){
            if(Integer.valueOf(forest.get(y).get(i)) >= value ){
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates if the tree is visible from the right of the grid and returns true if so
     * @param x the x coordinate of the tree in question
     * @param y the y coordinate of the tree in question
     * @return true if the tree is visible from the right, false if not
     */
    private Boolean visibleFromRight(int x, int y){
        int value = Integer.valueOf(forest.get(y).get(x));
        for(int i = forest.get(0).size() -1; i > x; i--){
            if(Integer.valueOf(forest.get(y).get(i)) >= value ){
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates if the tree is visible from the top of the grid and returns true if so
     * @param x the x coordinate of the tree in question
     * @param y the y coordinate of the tree in question
     * @return true if the tree is visible from the top, false if not
     */
    private boolean visibleFromUp(int x, int y){
        int value = Integer.valueOf(forest.get(y).get(x));
        for(int i = 0; i < y; i++){
            if(Integer.valueOf(forest.get(i).get(x)) >= value ){
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates if the tree is visible from the bottom of the grid and returns true if so
     * @param x the x coordinate of the tree in question
     * @param y the y coordinate of the tree in question
     * @return true if the tree is visible from the bottom, false if not
     */
    private Boolean visibleFromDown(int x, int y) {
        int value = Integer.valueOf(forest.get(y).get(x));
        for (int i = forest.size() - 1; i > y; i--) {
            if (Integer.valueOf(forest.get(i).get(x)) >= value) {
                return false;
            }
        }
        return true;

    }

    /**
     * Calculates the number of trees that are visible from the outside of the grid
     * @return the number of trees
     */
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

    /**
     * Calculates the scenic score of a single tree
     * @param x the x coordinate of the tree
     * @param y the y coordinate of the tree
     */
    private void scenicScoreForOneTree(int x, int y){
        scenicScoreForest[x][y] = scoreDown(x,y) * scoreUp(x,y) * scoreLeft(x,y) * scoreRight(x,y);
        System.out.println(scoreDown(x,y) + "," + (scoreUp(x,y) + "," + scoreLeft(x,y) + "," + scoreRight(x,y)));
    }

    /**
     * Calculates the score of the tree for the left, for a given tree
     * @param x the x coordinate of the tree
     * @param y the y coordinate of the tree
     * @return the scenic score of the given tree
     */
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

    /**
     * Calculates the score of the tree for the right, for a given tree
     * @param x the x coordinate of the tree
     * @param y the y coordinate of the tree
     * @return the scenic score of the given tree
     */
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

    /**
     * Calculates the score of the tree for above, for a given tree
     * @param x the x coordinate of the tree
     * @param y the y coordinate of the tree
     * @return the scenic score of the given tree
     */
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

    /**
     * Calculates the score of the tree for the bottom, for a given tree
     * @param x the x coordinate of the tree
     * @param y the y coordinate of the tree
     * @return the scenic score of the given tree
     */
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

    /**
     * Finds the highest scoring tree from the scenicScoreForest
     * @return the highest storing tree
     */
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

    /**
     * Calculates the highest scenic scoring tree from the forest
     * @return the score of the highest scoring tree
     */
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
