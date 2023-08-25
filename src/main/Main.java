/*
 * SocialMediaAnalyzer
 *
 * Version V1.00
 * Author: @buddhima3967596
 *
 * 22/08/2023
 *
 * Creatives Commons No Rights Reserved
 */
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        SocialMediaMenu menu = new SocialMediaMenu();
        // Main method to start program
        try {
            menu.loadPostsViaCsv("src/resources/posts.csv");

        } catch(FileNotFoundException e){
            System.out.println("Failed to open \"posts.csv\" ! Check that it's located in \"src/resources/\" directory!");
        } finally {
            menu.startProgram();
        }


    }
}