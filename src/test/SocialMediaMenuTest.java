import org.junit.Assert;
import org.junit.*;
import org.junit.contrib.java.lang.system.*;
import java.io.FileNotFoundException;


public class SocialMediaMenuTest {
    // References for mocking user input
    //https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner
    // https://stefanbirkner.github.io/system-rules/index.html
    private SocialMediaMenu testMenu;

    private FileNotFoundException testFIleException;

    @Rule
    public final TextFromStandardInputStream userInputMock= TextFromStandardInputStream.emptyStandardInputStream();

    @Before
    public void setUp() {
        testMenu = new SocialMediaMenu();
    }

    //Tet for non existent file
    @Test(expected = FileNotFoundException.class)
    public void testLoadPostsViaInvalidCsv() throws FileNotFoundException {
        try {
            testMenu.loadPostsViaCsv("src/test_resources/post.csv");
            Assert.fail("This file doesn't exist!");
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    @Test
    public void testLoadPostsViaCleanCsv()  {
        try{
        testMenu.loadPostsViaCsv("src/test_resources/posts.csv");

        Assert.assertEquals(5,testMenu.postCollection.getNumberOfPosts());
    } catch (FileNotFoundException e) {
            Assert.fail("This file does exist!");
        }
    }

    @Test
    // Test CSV with only 3 valid records, therefore only 3 posts should be created
    public void testLoadPostsViaErrorCsv(){
        try{
            testMenu.loadPostsViaCsv("src/test_resources/testErrorPosts.csv");

            Assert.assertEquals(3,testMenu.postCollection.getNumberOfPosts());
        } catch(FileNotFoundException e) {
            Assert.fail("This file does exist!");
        }
    }


    @Test
    // Tests that a post was actually added to the collection
    public void testPostCleanAdd(){
            userInputMock.provideLines("1","23","This is a test post!","s3967596","235","70","13/08/2023 09:00");
            try{
                testMenu.startProgram();
            } catch (Exception e){
                Assert.assertEquals(1,testMenu.postCollection.getNumberOfPosts());
            }

    }


@Test
// Tests result of invalid fields being inputted
public void testPostErrorAdd(){
    userInputMock.provideLines("1","23x","233","This is a test post!","s3967596","235","70","13/08/2023 09:00");
    try{
        testMenu.startProgram();
    } catch (Exception e){
        Assert.assertFalse("This post should have a ID of 233",testMenu.postCollection.getPost(233)==null);
    }

}

@Test
public void testInvalidMenuOption(){
        userInputMock.provideLines("x","-1","1","23x","233","This is a test post!","s3967596","235","70","13/08/2023 09:00");
        try{
            testMenu.startProgram();
        } catch (Exception e){
            Assert.assertFalse("This post should have a ID of 233",testMenu.postCollection.getPost(233)==null);
        }

    }


    // Test Deleting Post With Valid Options
    @Test
    public void testDeleteCleanPost(){
        userInputMock.provideLines("1","233","This is a test post!","s3967596","235","70","13/08/2023 09:00","\n2","233");
        try{
            testMenu.startProgram();
        } catch (Exception e){
            Assert.assertTrue("This post should have been deleted!",testMenu.postCollection.getPost(233)==null);
        }

    }

    //Test Deleting Non-Existent Post
    @Test
    public void testDeleteInvalidPost(){
        userInputMock.provideLines("1","233","This is a test post!","s3967596","235","70","13/08/2023 09:00","\n2","23");
        try{
            testMenu.startProgram();
        } catch (Exception e){
            Assert.assertFalse("This post shouldn't have been deleted!",testMenu.postCollection.getPost(233)==null);
        }

    }

    @Test
    //Test Delete posts with invalid input
    public void testDeleteErrorPost(){
        userInputMock.provideLines("1","233","This is a test post!","s3967596","235","70","13/08/2023 09:00","\n2","xasd","233");
        try{
            testMenu.startProgram();
        } catch (Exception e){
            Assert.assertTrue("This post should have been deleted!",testMenu.postCollection.getPost(233)==null);
        }

    }

    @Test
    // Test Retrieve Post with valid input
    public void testRetrieveCleanPost(){
        userInputMock.provideLines("1","233","This is a test post!","s3967596","235","70","13/08/2023 09:00","\n3","233");
        try{
            testMenu.startProgram();
        } catch (Exception e){
            Assert.assertTrue(testMenu.postCollection.getPost(233).toString()!=null);
        }

    }

    @Test
    // Test Retrieve Post with invalid input
    public void testRetrieveErrorPost(){
        userInputMock.provideLines("1","233","This is a test post!","s3967596","235","70","13/08/2023 09:00","\n3","x","233");
        try{
            testMenu.startProgram();
        } catch (Exception e){
            Assert.assertTrue(testMenu.postCollection.getPost(233).toString()!=null);
        }
    }

    //Sorting tested in PostCollectionTest.java


    }

