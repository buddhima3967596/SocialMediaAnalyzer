import java.util.*;

public class PostCollection {
    // final access modifier so only one hashmap can be initialized.
    // Better option might be LinkedHashMap for maintaining the order of posts
    final HashMap<Integer, Post> PostMap = new HashMap<>();

    //Anonymous Comparators to sort Post object based on likes or shares
    Comparator<Post> compareLikes  = Comparator.comparingInt(Post::getLikes);
    Comparator<Post> compareShares = Comparator.comparingInt(Post::getShares);


    // Variables to store sorted posts when the method is called

    private ArrayList<Post> sortedPostsByShares;
    private ArrayList<Post> sortedPostsByLikes;
    private boolean sorted=false;

    private boolean changedSinceSorted;

    //Getters
    public Post getPost(int inputID){
       return PostMap.get(inputID);
    }


    // Check if a sorted array list exists , if not sort the arraylist, then return sorted array list
    public ArrayList<Post> getSortedPostsByShares(){
        if (sorted){
            return sortedPostsByShares;
        }
        this.intialSortByShares();
        return sortedPostsByShares;
    }

    public ArrayList<Post> getSortedPostsByLikes(){
        if (sorted){
            return sortedPostsByLikes;
        }
        this.intialSortByLikes();
        return sortedPostsByLikes;
    }

    public int getNumberOfPosts(){
        return PostMap.size();
    }

    // Main Methods
    public void addPost(Post inputPost){
        PostMap.put(inputPost.getPostID(),inputPost);
        if (sorted){
            this.changedSinceSorted=true;
        }
    }
    public boolean deletePost(int inputPostID){
        if (PostMap.remove(inputPostID) == null){
            return false;
        }
        return true;
    }

    // Sorting Methods
    private void intialSortByShares(){
        sortedPostsByShares= new ArrayList<>(PostMap.values());
        Collections.sort(sortedPostsByShares,compareShares.reversed());
    }

    private void intialSortByLikes(){
        sortedPostsByLikes = new ArrayList<>(PostMap.values());
        Collections.sort(sortedPostsByLikes,compareLikes.reversed());
    }


}


