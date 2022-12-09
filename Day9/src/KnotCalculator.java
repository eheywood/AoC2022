import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Knot calculator for the rope bridge
 */
public class KnotCalculator {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<String> instructions;

    private Pair<Integer, Integer> head;

    //private Pair<Integer, Integer> tail;

    private ArrayList<Pair<Integer, Integer>> previousCoords;

    //For pt 2 of the challenge
    private ArrayList<Pair<Integer,Integer>> ropeCoords;


    /**
     * Constructs the KnotCalculator class, including initialising the file reader and buffered reader.
     */
    public KnotCalculator() {
        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        head = new Pair<>(0, 0);

        //Was used in pt1 of the challenge
        //tail = new Pair<>(0, 0);

        previousCoords = new ArrayList<>();
        instructions = new ArrayList<>();
        readInput();

    }

    /**
     * Reads the input file and puts the instructions into an array list
     */
    private void readInput() {
        try {
            String line;
            while ((line = br.readLine()) != null) {
                instructions.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs one line of instructions on the head and 9 long tail of rope
     * @param instr the line of instructions to follow
     */
    private void oneMove(String instr) {
        String[] instructions = instr.split(" ");

        for(int x = 0; x < Integer.valueOf(instructions[1]); x++){
            updateHead(instructions[0]);

            for(int i = 0; i < 9; i++){
                updateTail(instructions[0], ropeCoords.get(i),ropeCoords.get(i+1));
            }

            if(!alreadyVisited(ropeCoords.get(9))){
                previousCoords.add(new Pair(ropeCoords.get(9).getKey(),ropeCoords.get(9).getValue()));
            }


            System.out.println("Head is at:" + head.getKey() + "," + head.getValue());
            //System.out.println("Tail is at:" + tail.getKey() + "," + tail.getValue());
        }

    }


    /**
     * Updates the position of the tail according to the position of the head it is following
     * @param dir the direction of the movement
     * @param head the head that the tail is following
     * @param tail the tail
     */
    private void updateTail(String dir, Pair<Integer,Integer> head, Pair<Integer,Integer> tail){

            int verdicatDif = head.getValue() - tail.getValue();
            int horisontalDif = head.getKey() - tail.getKey();

            if(Math.abs(verdicatDif) < 2 && Math.abs(horisontalDif) < 2){
                return;
            }

            if (verdicatDif > 0){
                tail.updateValue(tail.getValue() +1);
            } else if (verdicatDif < 0) {
                tail.updateValue(tail.getValue() - 1);
            }

            if (horisontalDif > 0){
                tail.updateKey(tail.getKey() +1);
            }else if (horisontalDif < 0){
                tail.updateKey((tail.getKey()) -1);
            }
    }


    /**
     * Returns true if the tail has already visited the position before
     * @param tail the tail in question
     * @return true if the tail has already visited the co-ordinate,  false if not
     */
    private boolean alreadyVisited(Pair<Integer,Integer> tail){
        int x = tail.getKey();
        int y = tail.getValue();
        for(int i = 0; i < previousCoords.size(); i++){
            if(previousCoords.get(i).getKey() == x && previousCoords.get(i).getValue() == y){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the heads position according to a direction.
     * @param dir the direction that the head needs to move in
     */
    private void updateHead(String dir) {
        switch (dir) {
            case "L" -> head.updateKey(head.getKey() - 1);
            case "R" -> head.updateKey(head.getKey() + 1);
            case "D" -> head.updateValue(head.getValue() + 1);
            case "U" -> head.updateValue(head.getValue() - 1);

        }
    }

    /**
     * Gets the number of positions the tail of the rope has been in (for pt1)
     * @return the number of positions
     */
    public int getNumOfTailPositions(){
        previousCoords.add(new Pair(0,0));
        for(int x = 0; x < instructions.size();x ++){
            oneMove(instructions.get(x));
        }
        return previousCoords.size();
    }

    /**
     * Gets the number of positions the end of the rope (tail) has been in
     * @return the number of positions
     */
    public int getNumOf9thTailPositions(){
        ropeCoords = new ArrayList<>();
        ropeCoords.add(head);
        for(int i = 0; i < 9; i++){
            ropeCoords.add(new Pair(0,0));
        }

        for(int i = 0; i < instructions.size(); i++){
            oneMove(instructions.get(i));
        }

        return previousCoords.size();

    }




}
