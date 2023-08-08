import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SocialMediaMenu {
    //References
    // COSC-1295-Assignment 1 SEM 2 2023: Social Media Analyzer Spec
    //https://sharkysoft.com/archive/printf/docs/javadocs/lava/clib/stdio/doc-files/specification.htm
    //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html


    //Final keyword to ensure each field can only be initialized once
    private final PostCollection postCollection;
    private final Scanner scanner;

    private final DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");

    public SocialMediaMenu(){
        this.postCollection= new PostCollection();
        this.scanner= new Scanner(System.in);
    }

    static private void printMainMenu(){
        //Block String --> Only need to call println once
        final String menu="""
               --------------------------------------------------------------------------------
               >Select from main menu
               --------------------------------------------------------------------------------
                  1) Add a social media post
                  2) Delete an existing social media post
                  3) Retrieve a social media post
                  4) Retrieve the top N posts with most likes
                  5) Retrieve the top N posts with most shares
                  6) Exit
                Please select:
                """;
        System.out.println(menu);

    }
    public void startProgram(){
        //Fields to be filled by user input
        int menuSelection;
        int inputPostID;
        String inputContent;
        String inputAuthor;
        int inputLikes;
        int inputShares;
        String inputDate;
        int numPosts;


        System.out.println("Welcome to Social Media Analyzer!");

        do {
            printMainMenu();
            menuSelection=scanner.nextInt();
            // Skip the leftover /n char left by nextInt()
            scanner.nextLine();

            switch(menuSelection){
                case (1):
                    System.out.println("Please provide the post ID:");

                        inputPostID=scanner.nextInt();
                        scanner.nextLine();
                        while (postCollection.getPost(inputPostID)!=null) {
                            System.out.println("Duplicate post ID ,please select a different post ID");
                            System.out.println("Please provide the post ID:");
                            inputPostID=scanner.nextInt();
                            scanner.nextLine();
                        }

                    System.out.println("Please provide the post content:");
                        inputContent=scanner.nextLine();
                    System.out.println("Please provide the post author:");
                        inputAuthor=scanner.nextLine();
                    System.out.println("Please provide the number of likes of the :");
                        inputLikes=scanner.nextInt();
                        scanner.nextLine();
                    System.out.println("Please provide the number of shares of the post:");
                        inputShares=scanner.nextInt();
                        scanner.nextLine();
                    System.out.println("Please provide the date and time of the post in the format of DD/MM/YYYY HH:MM:");
                        inputDate=scanner.nextLine();
                        LocalDateTime inputDateObject = LocalDateTime.from(timeFormatter.parse(inputDate));

                    postCollection.addPost(new Post(inputPostID,inputContent,inputAuthor,inputLikes,inputShares,inputDateObject));

                    System.out.println("The post has been added to the collection!");
                break;


                case(2):
                    System.out.println("Please provide the post ID:");
                    inputPostID=scanner.nextInt();
                    scanner.nextLine();
                    if (postCollection.deletePost(inputPostID)==false){
                        System.out.println("Sorry the post does not exist in the collection!");
                        break;
                    }
                    System.out.println("The post has been deleted from the collection!");
                break;

                case(3):
                    System.out.println("Please provide the post ID:");
                    inputPostID=scanner.nextInt();
                    scanner.nextLine();
                    if (postCollection.getPost(inputPostID)!=null){
                        System.out.println(postCollection.getPost(inputPostID).toString());
                        break;
                    }
                    System.out.println("Sorry the post does not exist in the collection!");
                break;

                case(4):
                    System.out.println("Please specify the number of posts to retrieve");
                    numPosts=scanner.nextInt();
                    scanner.nextLine();

                    if (numPosts > postCollection.getNumberOfPosts()) {
                        System.out.printf("\nOnly %d exist in the collection. Showing all of them.",postCollection.getNumberOfPosts());
                        numPosts=postCollection.getNumberOfPosts();
                    } else{
                        System.out.printf("\nThe top-%d posts with the most likes are:",numPosts);
                    }
                    ArrayList<Post> postsSortedByLikes=postCollection.getSortedPostsByLikes();

                    for (int i=0;i<numPosts;i++){
                        System.out.printf("\n  %d) %s",i+1,postsSortedByLikes.get(i).getLikestring());
                    }
                    //Print newline char for formatting
                    System.out.println();

                break;

                case(5):
                    System.out.println("Please specify the number of posts to retrieve");
                    numPosts=scanner.nextInt();
                    scanner.nextLine();

                    if (numPosts > postCollection.getNumberOfPosts()) {
                        System.out.printf("\nOnly %d exist in the collection. Showing all of them.",postCollection.getNumberOfPosts());
                        numPosts=postCollection.getNumberOfPosts();
                    } else{
                        System.out.printf("\nThe top-%d posts with the most likes are:",numPosts);
                    }
                    ArrayList<Post> postsSortedByShares=postCollection.getSortedPostsByShares();

                    for (int i=0;i<numPosts;i++){
                        System.out.printf("\n  %d) %s",i+1,postsSortedByShares.get(i).getShareString());
                    }
                    //Print newline char for formatting
                    System.out.println();

                break;

                case(6):
                    break;
                default :
                    System.out.println("Invalid selection, please enter a number from 1 to 6!");
            }

                if ( (menuSelection<6) && (menuSelection>=1) )  {
                    System.out.println("""
                    --------------------------------------------------------------------------------
                    Please press the enter key to return to the main menu!
                    --------------------------------------------------------------------------------""");
                    scanner.nextLine();
                    }
        } while (menuSelection !=6);{
            System.out.println("Thanks for using Social Media Analyzer!");
            System.exit(0);
        }

    }
}
