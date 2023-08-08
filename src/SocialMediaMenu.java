import java.util.Scanner;

public class SocialMediaMenu {
    //Reference: COSC-1295-Assignment 1 SEM 2 2023: Social Media Analyzer Spec
    //https://sharkysoft.com/archive/printf/docs/javadocs/lava/clib/stdio/doc-files/specification.htm\


    //Final keyword to ensure each field can only be initialized once
    private final PostCollection postCollection;
    private final Scanner scanner;

    public SocialMediaMenu(){
        this.postCollection= new PostCollection();
        this.scanner= new Scanner(System.in);
    }

    public void generateMenu(){
        System.out.println("Welcome to Social Media Analyzer!");
        final String seperator ="--------------------------------------------------------------------------------";
        int input;
        do {
            System.out.println(seperator);
            System.out.println(">Select from main menu");
            System.out.println(seperator);
            System.out.println("   1) Add a social media post");
            System.out.println("   2) Delete an existing social media post");
            System.out.println("   3) Retrieve a social media post");
            System.out.println("   4) Retrieve the top N posts with most likes");
            System.out.println("   5) Retrieve the top N posts with most shares");
            System.out.println("   6) Exit");
            input =scanner.nextInt();
        } while (input !=6);{
            System.out.println("Thanks for using Social Media Analyzer!");
            System.exit(0);
        }

    }
}
