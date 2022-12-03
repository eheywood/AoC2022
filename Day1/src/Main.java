import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        CalorieCounter cc = new CalorieCounter();

        System.out.println(cc.maxCalories());
        System.out.println(cc.topThreeCalories());
    }
}