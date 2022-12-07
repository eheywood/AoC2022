public class Main {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        //fm.printTree();
        //System.out.println(fm.getTotalSizeOfDir(fm.getRootNode()));

        //System.out.println(fm.getTotalSizeOfDir(fm.getRootNode().getChildren().get(0).getChildren().get(3)));

        System.out.println(fm.findTotalSizeOfDirUnder1000());

        System.out.println(fm.findSizeOfFileToDelete());
    }
}