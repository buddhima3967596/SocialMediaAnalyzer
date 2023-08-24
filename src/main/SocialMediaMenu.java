import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocialMediaMenu {
    //References
    // COSC-1295-Assignment 1 SEM 2 2023: Social Media Analyzer Spec
    //https://sharkysoft.com/archive/printf/docs/javadocs/lava/clib/stdio/doc-files/specification.htm
    //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    // https://stackify.com/java-custom-exceptions/
    // https://www.baeldung.com/java-csv-file-array


    //Final keyword to ensure each field can only be initialized once
    private final PostCollection postCollection;
    private final Scanner scanner;

    private final DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");

    public SocialMediaMenu(){
        this.postCollection= new PostCollection();
        this.scanner= new Scanner(System.in);
        String scannerInput;
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

    public void loadPostsViaCsv(String filepath) throws FileNotFoundException {
        // Index values for post attributes
        int POST_ID=0;
        int POST_CONTENT=1;
        int POST_AUTHOR=2;
        int POST_LIKES=3;
        int POST_SHARES=4;
        int POST_DATE_TIME=5;

        List<List<String>> csvRows = new ArrayList<>();
            try (Scanner csvScanner = new Scanner(new File(filepath));){
                while (csvScanner.hasNextLine()){
                csvRows.add(convertRowToList(csvScanner.nextLine()));
            }
        }
            System.out.println("Importing Posts From CSV!");
        for (int i=1;i<csvRows.size();i++) {
                List<String> currentRecord=csvRows.get(i);
                try {
                    int inputID = Integer.parseInt(currentRecord.get(POST_ID));
                    int inputLikes = Integer.parseInt(currentRecord.get(POST_LIKES));
                    int inputShares = Integer.parseInt(currentRecord.get(POST_SHARES));
                    String inputDateTime = currentRecord.get(POST_DATE_TIME);
                    // Fix for Missing Leading 0
                    if (inputDateTime.length()<16){
                        inputDateTime="0".concat(inputDateTime);
                    }


                    LocalDateTime dateTimeParsed = LocalDateTime.from(timeFormatter.parse(inputDateTime));
                    postCollection.addPost(new Post(inputID,currentRecord.get(POST_CONTENT),currentRecord.get(POST_AUTHOR), inputLikes,inputShares,dateTimeParsed));
                } catch (Exception e){
                    System.out.println(e.toString());
                    System.out.printf("\nError in row %d skipping record!",i);

            }

        }
        System.out.printf("%d Rows read from CSV",csvRows.size()-1);
        System.out.printf("\n%d Posts successfully added to collection",postCollection.getNumberOfPosts());
    }

        private List<String> convertRowToList(String inputRow){
            List<String> commaDelimitedValues = new ArrayList<String>();
                try(Scanner rowScanner= new Scanner(inputRow)){
                    rowScanner.useDelimiter(",") ;
                    while (rowScanner.hasNext()){
                    commaDelimitedValues.add(rowScanner.next());
                    }
                }
                return commaDelimitedValues;
        }

    public void startProgram(){

        //Fields to be filled by user input
        int menuSelection;
        int numPosts;
        System.out.println("\n\nWelcome to Social Media Analyzer!");

        do {
            printMainMenu();

            menuSelection=getMenuSelection();

            switch (menuSelection) {
                case (1) -> {
                    addPost();
                }
                case (2) -> {
                    deletePost();
                }
                case (3) -> {
                    retrievePost();
                }
                case (4) -> {
                    getTopPostsViaLikes();

                }
                case (5) -> {
                    getTopPostsViaShares();
                }
                case (6) -> {
                    break;
                }
                default -> System.out.println("Invalid selection, please enter a number from 1 to 6!");
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

    // Method 1

    private void addPost() {

        int inputPostID;
        String inputContent;
        String inputAuthor;
        int inputLikes;
        int inputShares;

        inputPostID=validatePostID();

        // Check for pre-existing post with duplicated ID
        while (postCollection.getPost(inputPostID) != null) {
            System.out.println("Duplicate post ID ,please select a different post ID!");
            System.out.println("Please provide the post ID:");
            inputPostID = validatePostID();
        }

        inputContent = validateContent();
        inputAuthor=validateAuthor();
        inputLikes = validateLikes();
        inputShares = validateShares();
        LocalDateTime inputDateObject = validateDate();
        postCollection.addPost(new Post(inputPostID, inputContent, inputAuthor, inputLikes, inputShares, inputDateObject));
        System.out.println("The post has been added to the collection!");
    }

    private void deletePost() {
        if (postCollection.deletePost(validatePostID()) == false) {
            System.out.println("Sorry the post does not exist in the collection!");
            return;
        }
        System.out.println("The post has been deleted from the collection!");
    }

    private void retrievePost(){
        int postID=validatePostID();
        if (postCollection.getPost(postID) != null) {
            System.out.println(postCollection.getPost(postID).toString());
        }
        System.out.println("Sorry the post does not exist in the collection!");
    }

    private void getTopPostsViaLikes(){

                    int numPosts = validateNumPostsChosen();
                    if (postCollection.getNumberOfPosts()==0){
                        System.out.println("Sorry no posts exist in this collection! Returning to main menu");
                    }
                    if (numPosts > postCollection.getNumberOfPosts()) {
                        System.out.printf("\nOnly %d exist in the collection. Showing all of them.", postCollection.getNumberOfPosts());
                        numPosts = postCollection.getNumberOfPosts();
                    } else {
                        System.out.printf("\nThe top-%d posts with the most likes are:", numPosts);
                    }
                    ArrayList<Post> postsSortedByLikes = postCollection.getSortedPostsByLikes();
                    for (int i = 0; i < numPosts; i++) {
                        System.out.printf("\n  %d) %s", i + 1, postsSortedByLikes.get(i).getLikestring());
                    }
                    //Print newline char for formatting
                    System.out.println();
    }

    private void getTopPostsViaShares(){
                    int numPosts = validateNumPostsChosen();
                    if (numPosts > postCollection.getNumberOfPosts()) {
                        System.out.printf("\nOnly %d exist in the collection. Showing all of them.", postCollection.getNumberOfPosts());
                        numPosts = postCollection.getNumberOfPosts();
                    } else {
                        System.out.printf("\nThe top-%d posts with the most likes are:", numPosts);
                    }
                    ArrayList<Post> postsSortedByShares = postCollection.getSortedPostsByShares();
                    for (int i = 0; i < numPosts; i++) {
                        System.out.printf("\n  %d) %s", i + 1, postsSortedByShares.get(i).getShareString());
                    }
                    //Print newline char for formatting
                    System.out.println();
    }

    //
    private boolean checkDuplicateID(){
            return false;
    }

    //Exceptions needed
    // Empty String
    // Integer 0/Negative
    // Can't contain Comma
    // Duplicate ID
    // Invalid Date Format

    // Input Validation Methods

    private static String cleanInput(String userInput) throws EmptyUserInputException {
        String userInputCleaned=userInput.strip();
        if (userInputCleaned.isEmpty()) {
            throw new EmptyUserInputException("User Input Can't Be Empty!");
        } else {
            return userInputCleaned;
        }
    }

    private Integer getMenuSelection (){

        try {
            String cleanedMenuSelection = cleanInput(scanner.nextLine());
            int menuSelection = Integer.parseInt(cleanedMenuSelection);
            if (menuSelection<=0 || menuSelection>6) {
                throw new InvalidMenuSelectionException("Menu Selection must be between 1 and 6 inclusive!");
            }
            return  menuSelection;
        } catch (EmptyUserInputException e) {
            System.out.println("Menu Selection can't be empty!, Please Try Again");
            return getMenuSelection();
        } catch (NumberFormatException | InvalidMenuSelectionException e) {
            System.out.println("Invalid Menu Option! , Please input a positive integer between 1 and 6");
            return getMenuSelection();
        }
    }
    private Integer validatePostID()  {
        //Must Be Integer , Can't be negative , can't be empty
        try{
            System.out.println("Please provide the post ID:");

            String cleanedInput=cleanInput(scanner.nextLine());

            return Integer.parseInt(cleanedInput);

        } catch(EmptyUserInputException e){
            System.out.println("Post ID can't be empty!, Please try again");
            return validatePostID();
        } catch (IllegalArgumentException e){
            System.out.println("Input must be a integer!, Please try again");
            return validatePostID();
        }

    }

    private String validateContent(){
        try {
            System.out.println("Please provide the post content:");
            String cleanedInput=cleanInput(scanner.nextLine());

            if (cleanedInput.contains(",")){
                throw new InvalidCharacterInputException("Content can't contain any commas!");
            }
            return cleanedInput;

        } catch (EmptyUserInputException e) {
            System.out.println("Content field can't be empty!, Please try again.");
            return validateContent();
        } catch (InvalidCharacterInputException e) {
            System.out.println("Content field can't contain any commas!, Please try again");
            return validateContent();
        }

    }

    private String validateAuthor(){
        try {
            System.out.println("Please provide the post author:");
            String cleanedInput=cleanInput(scanner.nextLine());

            if (cleanedInput.contains(",")){
                throw new InvalidCharacterInputException("Author name can't contain commas!");
            }
            return cleanedInput;

        } catch (EmptyUserInputException e) {
            System.out.println("Author field can't be empty!, Please try again.");
            return validateContent();
        } catch (InvalidCharacterInputException e) {
            System.out.println("Author field can't contain any commas!, Please try again");
            return validateContent();
        }

    }

    private Integer validateLikes() {

        try {
            System.out.println("Please provide the number of likes of the post:");
            String cleanedInput = cleanInput(scanner.nextLine());

            int likes = Integer.parseInt(cleanedInput);

            if (likes < 0) {
                throw new NegativeLikesException("Number of likes must be a positive Integer!");
            }
            return likes;
        } catch (EmptyUserInputException e) {
            System.out.println("Number of Likes Can't be empty!, Please try again");
            return validateLikes();
        } catch (NumberFormatException e) {
            System.out.println("Input must be an integer!, Please try again");
            return validateLikes();
        } catch (NegativeLikesException e) {
            System.out.println("Input must be non-negative!, Please try again");
            return validateLikes();
        }
    }


    private Integer validateShares() {

        try {
            System.out.println("Please provide the number of shares of the post:");
            String cleanedInput = cleanInput(scanner.nextLine());

            int shares = Integer.parseInt(cleanedInput);

            if (shares< 0) {
                throw new NegativeSharesException("Number of Shares must be a positive integer!");
            }
            return shares;
        } catch (EmptyUserInputException e) {
            System.out.println("Number of Shares can't be empty!, Please try again");
            return validateShares();
        } catch (NumberFormatException e) {
            System.out.println("Input must be an integer!, Please try again");
            return validateShares();
        } catch (NegativeSharesException e) {
            System.out.println("Input must be non-negative!, Please try again");
            return validateShares();
        }
    }

    private LocalDateTime validateDate(){
        try {
            System.out.println("Please provide the date and time of the post in the format of DD/MM/YYYY HH:MM:");
            String cleanedInput=cleanInput(scanner.nextLine());
            return LocalDateTime.from(timeFormatter.parse(cleanedInput));
        } catch (EmptyUserInputException e) {
            System.out.print("Input can't be empty!, Please try again");
            return validateDate();
        } catch (DateTimeParseException | IllegalArgumentException e) {
            System.out.print("Invalid Date Format!, Please try again");
            return validateDate();
        }
    }



    private Integer validateNumPostsChosen(){
        //Must be Integer // Greater than 0

        try {
            System.out.println("Please specify the number of posts to retrieve");

            String cleanedNumPosts = cleanInput(scanner.nextLine());

            int numPosts=Integer.parseInt(cleanedNumPosts);

            if (numPosts<=0){
                throw new InvalidMenuSelectionException("Must be greater than 0!");
            }
            return numPosts;
        } catch (InvalidMenuSelectionException | NumberFormatException e) {
            System.out.println("Please input a positive integer! , Please try again!");
            return validateNumPostsChosen();
        } catch (EmptyUserInputException e) {
            System.out.println("User Input can't be empty!, Please try again");
            return validateNumPostsChosen();
        }

    }





}


