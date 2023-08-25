import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertSame;
public class PostTest {

    Post testPost;
    LocalDateTime testDate;

    //@BeforeClass Executed just once
    //@Before before every test

    @BeforeClass
    public static void setUpClass() throws Exception{

    }
    @Before
    public void setUp() throws Exception{
        testDate =  LocalDateTime.now();

    }

    @Test
    public void testConstructor(){
        Post testPost=new Post(12,"This is a unit test!","Buddhima",2,1,testDate);
        assertSame(testPost.getClass(), Post.class);
    }

}
