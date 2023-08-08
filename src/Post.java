import java.time.LocalDateTime;
public class Post {
    private int postID;
    private int likes;
    private int shares;
    private String content;
    private String author;
    private LocalDateTime datePosted;

//    Getters
    public int getPostID(){
     return this.postID;
}
    public int getLikes(){
        return this.likes;
    }

    public int getShares(){
        return this.shares;
    }

    public String getContent(){
        return this.content;
    }

    public String getAuthor(){
        return this.author;
    }

    public LocalDateTime getDatePosted(){
        return this.datePosted;
    }

    public String getShareString(){
        return String.format("%d | %s | %d",this.getPostID(),this.getContent(),this.getShares());
    }

    public String getLikestring(){
        return String.format("%d | %s | %d",this.getPostID(),this.getContent(),this.getLikes());
    }


//Setters
    public void setPostID(int inputID){
        this.postID=inputID;
    }
    public void setLikes(int inputLikes){
        this.likes=inputLikes;
    }

    public void setShares(int inputShares){
        this.shares=inputShares;
    }

    public void setContent(String inputContent){
        this.content=inputContent;
    }

    public void setAuthor(String inputAuthor){
        this.author=inputAuthor;
    }

    //TODO: Complete Date Time Implementation
    public void setDatePosted(LocalDateTime inputDate){
        this.datePosted=inputDate;
    }
}
