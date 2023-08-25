import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static junit.framework.Assert.*;

public class PostCollectionTest {

    private PostCollection testCollection;
    private ArrayList<Post> likesSorted;
    private ArrayList<Post> sharesSorted;

    private int j;
    @Before
    public void setUp() {
        testCollection = new PostCollection();
        testCollection.addPost(new Post(-1,"Post 1!","Buddhima1",8,25, LocalDateTime.now()));
        testCollection.addPost(new Post(0,"Post 2!","Buddhima2",5,50, LocalDateTime.now()));
        testCollection.addPost(new Post(50,"Post 3!","Buddhima3",1353,100, LocalDateTime.now()));
        testCollection.addPost(new Post(13,"Post 4!","Buddhima4",0,200, LocalDateTime.now()));
    }

    @Test
    public void testAdd() {
        testCollection.addPost(new Post(25, "Post 5!", "Buddhima5", 125, 235, LocalDateTime.now()));
        assertTrue("Post not added to collection!",testCollection.getNumberOfPosts()==5);
    }


    // Testing the post was actually removed from the list
    @Test
    public void testDelete(){
        testCollection.deletePost(13);
        assertEquals(3,testCollection.getNumberOfPosts());
    }

    // Testing delete returns true on successful deletion
    @Test
    public void testBooleanDelete(){
        assertEquals(true,  testCollection.deletePost(13));
    }

    // Testing delete returns false when attempting to delete post which doesn't exist
    @Test
    public void testFalseDelete(){
        assertFalse("Deleted a post that does not exist!", testCollection.deletePost(14));
    }


    @Test
    public void testLikeSortDescending(){
        likesSorted=testCollection.getSortedPostsByLikes();


        assertEquals(1353,likesSorted.get(0).getLikes());
    }

    @Test
    public void testLikeSortAscending(){
        likesSorted=testCollection.getSortedPostsByLikes();

        assertEquals(0,likesSorted.get(3).getLikes());
    }

    @Test
    public void testShareSortDescending(){
        sharesSorted=testCollection.getSortedPostsByShares();
        assertEquals(200,sharesSorted.get(0).getShares());
    }

    @Test
    public void testShareSortAscending(){
        sharesSorted=testCollection.getSortedPostsByShares();
        assertEquals(25,sharesSorted.get(3).getShares());
    }

}
