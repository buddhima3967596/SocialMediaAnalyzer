import java.util.*;

public class PostCollection {
    // final access modifier so only one hashmap can be initialized.
    // Better option might be LinkedHashMap for maintaining the order of posts
    final HashMap<Integer, Post> PostMap = new HashMap<>();

    //Comparators to sort object
    Comparator<Post> compareLikes  = Comparator.comparingInt(Post::getLikes);
    Comparator<Post> compareShares = Comparator.comparingInt(Post::getShares);

    // Variables to store sorted posts when the method is called
    public ArrayList<Post> sortedPostsByLikes;
    public ArrayList<Post> sortedPostsByShares;

    //Getters
    public Post getPost(int inputID){
       return PostMap.get(inputID);
    }

    public ArrayList<Post> getSortedPostsByLikes(){
       sortedPostsByLikes = new ArrayList<>(PostMap.values());
       Collections.sort(sortedPostsByLikes,compareLikes.reversed());
       return sortedPostsByLikes;
    }

    public ArrayList<Post> getSortedPostByShares(){
        sortedPostsByShares= new ArrayList<>(PostMap.values());
        Collections.sort(sortedPostsByShares,compareShares.reversed());
        return sortedPostsByShares;
    }
    public int getNumberOfPosts(){
        return PostMap.size();
    }

    // Main Methods
    public void addPost(Post inputPost){
        PostMap.put(inputPost.getPostID(),inputPost);
    }
    public boolean deletePost(int inputPostID){
        if (PostMap.remove(inputPostID) == null){
            return false;
        }
        return true;
    }


}


