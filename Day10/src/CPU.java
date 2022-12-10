import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * CPU class for the elves CRT screen
 */
public class CPU {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;

    private ArrayList<String> instructions;

    private int X;
    private int pc;

    private int signalStrength;

    /**
     * Constructs the CPU class. Initialises the file reader and buffered reader.
     */
    public CPU(){

        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        instructions = new ArrayList<>();
        X = 1;
        pc = 0;
        signalStrength = 0;

        readInput();

    }

    /**
     * Reads the instructions into an arraylist from the input text.
     */
    private void readInput(){
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
     * Performs one cycle or "tick" of the CPU from a given line of instructions. The Cycle No is used to keep a track of the addx instruction as it takes two cycles to complete.
     * @param instruction the instruction line
     * @param cycleNo the number of cycles this instruction has been through
     */
    private void oneCycle(String instruction, int cycleNo){
        String[] instr = instruction.split(" ");
        pc += 1;
        printPixel();
        if(instr[0].equals("addx")){
            if(cycleNo == 1){
                X += Integer.valueOf(instr[1]);
                //updateSignalStrength();
            } else {
                //updateSignalStrength();
                oneCycle(instruction,1);
            }
        }
    }

    /**
     * Updates the signal strength on the 20th, 60th, 100th, 140th... cycle. Used for pt1
     */
    private void updateSignalStrength(){
        if((pc + 20) % 40 == 0){
            System.out.println("SignalStrength += " + pc + " x " + X + " = " + (pc * X));
            signalStrength += pc * X;
        }
    }

    /**
     * Prints a singular pixel to the screen
     */
    private void printPixel(){
        if(((pc % 40)-1) == X || ((pc % 40)-1) == (X + 1) || ((pc % 40)-1) == (X -1)){
            System.out.print("#");
        }else{
            System.out.print(".");
        }
        if((pc % 40) == 0){
            System.out.print("\n");
        }
    }

    /**
     * Calculates and returns the signal strength.
     * @return the signal strength
     */
    public int getSignalStrength(){
        for(int i = 0; i < instructions.size(); i++){
            oneCycle(instructions.get(i),0);
        }

        return signalStrength;
    }

    /**
     * Calculates and prints the output to the command line
     */
    public void printCRTScreen(){
        for(int i = 0; i < instructions.size(); i++){
            oneCycle(instructions.get(i),0);
        }
    }
}
