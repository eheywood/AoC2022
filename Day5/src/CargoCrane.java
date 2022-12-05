import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * The Cargo Crane class, simulates the cargo crane moving crates around
 */
public class CargoCrane {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private ArrayList<ArrayList<String>> cargoList;

    private ArrayList<String> instructions;

    public CargoCrane(){

        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cargoList = new ArrayList<>();
        for(int i = 0; i < 9; i ++){
            cargoList.add(new ArrayList<>());
        }

        instructions = new ArrayList<>();
        toArrays();

    }

    /**
     * Converts the input file into an array of instructions and an array of stacks of cargo.
     */
    private void toArrays() {
        try {
            String line;
            while (!(line = br.readLine()).isBlank()) {
                String[] cargo = line.split("");
                int columnCount = 1;
                for(int i = 0; i < ((cargo.length+1) / 4); i ++){
                    if(!cargo[columnCount].equals(" ")){
                        cargoList.get(i).add(cargo[columnCount]);
                    }
                    columnCount = columnCount + 4;
                }
            }
            while ((line = br.readLine()) != null ) {
                instructions.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the arrays to the command line - used for debugging
     */
    public void printArrays(){
        for(int i = 0; i < cargoList.size(); i ++){
            System.out.println("Stack " + i );
            for(int j = 0; j < cargoList.get(i).size(); j ++){
                System.out.println(cargoList.get(i).get(j));
            }
        }

        for(int i = 0; i < instructions.size(); i ++){
            System.out.println(instructions.get(i));
        }
    }

    /**
     * Pops the first cargo box from the top of a given stack
     * @param stackNo number of the stack to pop from
     * @return the popped item
     */
    private String popFromCargoStack(int stackNo){
        String popped = cargoList.get(stackNo).get(0);
        cargoList.get(stackNo).remove(0);
        return popped;
    }

    /**
     * Pushes an item onto the top of a cargo stack.
     * @param stackNo the number of the stack to push onto
     * @param toPush the value to push onto the top of the stack with
     */
    private void pushOntoCargoStack(int stackNo,String toPush){
        cargoList.get(stackNo).add(0,toPush);
    }

    /**
     * Parses one set of instructions and performs the actions on the cargo stacks for the cargo cran2 9000.
     * @param instr One line of instructions
     */
    private void oneMove9000(String instr){
        String[] split = instr.split(" ");
        int numToMove = Integer.valueOf(split[1]);
        int origStack = (Integer.valueOf(split[3])) - 1;
        int newStack = (Integer.valueOf(split[5])) - 1;

        for(int i = 0; i < numToMove; i++ ){
            String popped = popFromCargoStack(origStack);
            pushOntoCargoStack(newStack,popped);
        }
    }

    /**
     * Performs all moves on the cargo stacks for the Cargo Crane 9000
     */
    public void allMoves9000(){
        for(int i = 0; i < instructions.size(); i++){
            oneMove9000(instructions.get(i));
        }
    }

    /**
     * Parses one set of instructions and performs the actions on the cargo stacks.
     * @param instr One line of instructions
     */
    private void oneMove9001(String instr){
        String[] split = instr.split(" ");
        int numToMove = Integer.valueOf(split[1]);
        int origStack = (Integer.valueOf(split[3])) - 1;
        int newStack = (Integer.valueOf(split[5])) - 1;

        String[] toMove = new String[numToMove];
        for(int i = 0; i < numToMove; i++ ){
            toMove[i] = popFromCargoStack(origStack);
        }

        for(int i = 0; i < numToMove; i++ ){
            pushOntoCargoStack(newStack,(toMove[(numToMove-1)-i]));
        }
    }

    /**
     * Performs all moves on the cargo stacks for the Cargo Crane 9001
     */
    public void allMoves9001(){
        for(int i = 0; i < instructions.size(); i++){
            oneMove9001(instructions.get(i));
        }
    }


    /**
     * Prints the names of the boxes at the top of each of the stacks
     */
    public void printTopOfStack(){
        for(int i = 0; i < cargoList.size(); i ++){
            System.out.println(cargoList.get(i).get(0));
        }
    }

}
