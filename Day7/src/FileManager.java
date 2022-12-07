import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.util.*;

import static java.lang.Math.min;

/**
 * File manager that given an output from a command line can figure out the directory tree for a given filesystem.
 */
public class FileManager {

    private String fileName = "input.txt";
    private FileReader fileReader;
    private BufferedReader br;
    private DirTreeNode<String> rootDir;
    private ArrayList<Pair<String,Integer>> sizesOfDirs;

    /**
     * Constructs the file manager class, initialising the file reader, buffered reader and array lists.
     */
    public FileManager(){
        try {
            File caloriesFile = new File(fileName);
            fileReader = new FileReader(caloriesFile);
            br = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rootDir = new DirTreeNode<>("root");
        sizesOfDirs = new ArrayList<>();
        readInput();

        findSizesOfAllDirs(rootDir.getChildren().get(0));
    }

    /**
     * Reads the input file and creates a multiway tree from it
     */
    private void readInput(){
        try {
            String line;
            DirTreeNode<String> curNode = rootDir;
            while ((line = br.readLine()) != null) {
                //do what want with input here
                String[] cmd = line.split(" ");

                if(cmd[0].equals("$")){
                    if(cmd[1].equals("ls")){
                        continue;
                    }else if(cmd[1].equals("cd")){
                        if(cmd[2].equals("..")){
                            curNode = curNode.getParent();
                        } else {
                            curNode = curNode.addChild(cmd[2]);
                        }
                    }
                } else if(cmd[0].equals("dir")){
                    continue;
                } else {
                    curNode = curNode.addChild(cmd[0] + "," + cmd[1]).getParent();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out some nodes from the directory tree - used for testing.
     */
    public void printTree(){
        DirTreeNode<String> curNode = rootDir;
        System.out.println(rootDir.getChildren().get(0).getData());
        curNode = curNode.getChildren().get(0);

        for(int i = 0; i < curNode.getChildren().size();i ++){
            System.out.println(curNode.getChildren().get(i).getData());
        }

    }

    /**
     * Gets the total size of a directory from a given directory
     * @param rootNode the node of the directory in question
     * @return the size of the directory
     */
    public Integer getTotalSizeOfDir(DirTreeNode<String> rootNode){
        if(rootNode.getChildren().isEmpty()){
            String[] data = rootNode.getData().toString().split(",");
            return Integer.valueOf(data[0]);
        }else{
            int total = 0 ;
            List<DirTreeNode<String>> lst = rootNode.getChildren();
            for(int i = 0; i < lst.size(); i++){
                total += getTotalSizeOfDir(lst.get(i));
            }
            return total;
        }

    }

    /**
     * Gets the root node of the directory tree
     * @return the root node
     */
    public DirTreeNode<String> getRootNode(){
        return rootDir;
    }


    /**
     * Finds the sizes of all the directories in the directory tree and adds this information to an array list as a pair of Directory name and size.
     * @param curNode the current node being calculated
     */
    private void findSizesOfAllDirs(DirTreeNode<String> curNode){
        if(!curNode.getChildren().isEmpty()){
            sizesOfDirs.add(new Pair(curNode.getData(),getTotalSizeOfDir(curNode)));
            for(int i = 0;i < curNode.getChildren().size();i++){
                findSizesOfAllDirs(curNode.getChildren().get(i));
            }
        }
    }

    /**
     * Prints out all the directory sizes - used in testing.
     */
    public void printDirSizes(){
        for(int i = 0;i < sizesOfDirs.size();i++){
            sizesOfDirs.get(i).printPair();
        }
    }

    /**
     * Returns the total size of directories that are under 1000 in size
     * @return the total size of directories under 1000
     */
    public int findTotalSizeOfDirUnder1000(){
        Set<Pair<String,Integer>> s = new LinkedHashSet<>(sizesOfDirs);
        sizesOfDirs.clear();;
        sizesOfDirs.addAll(s);
        int total = 0;
        for (int i = 0; i < s.size();i++){
            if(Integer.valueOf(sizesOfDirs.get(i).getValue()) <= 100000){
                total += Integer.valueOf(sizesOfDirs.get(i).getValue());
            }
        }
        return total;
    }

    /**
     * Finds the smallest size of a file to delete in order to free up enough space for the elves.
     * @return the smallest size of a file to delete.
     */
    public Integer findSizeOfFileToDelete(){
        Set<Pair<String,Integer>> s = new LinkedHashSet<>(sizesOfDirs);
        sizesOfDirs.clear();;
        sizesOfDirs.addAll(s);

        Pair<String,Integer> rootPair = sizesOfDirs.get(0);
        Integer freeSpace = 70000000 - Integer.valueOf(rootPair.getValue());
        Integer spaceNeeded = 30000000 - freeSpace;

        System.out.println("FreeSpace:" + freeSpace);
        System.out.println("SpaceNeeded:" + spaceNeeded);

        ArrayList<Integer> contenders = new ArrayList<>();

        for(int i = 0; i < sizesOfDirs.size(); i++ ){
            if(Integer.valueOf(sizesOfDirs.get(i).getValue()) >= spaceNeeded){
                contenders.add(Integer.valueOf(sizesOfDirs.get(i).getValue()));
            }
        }

        return Collections.min(contenders);
    }







}
