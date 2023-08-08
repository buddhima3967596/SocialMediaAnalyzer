import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
public class PostCollection {
    // final access modifier so only one hashmap can be intialized.
    final HashMap<Integer, Post> PostMap = new HashMap<Integer,Post>();
    final ArrayList<Post> sortedPostsByLikes = new ArrayList<Post>();
    Comparator<Post> compareLikes = (Post)
    //Getters
    public Post getPost(int inputID){
       return PostMap.get(inputID);
    }



}


