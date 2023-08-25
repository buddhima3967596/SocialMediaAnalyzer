/*
 * Post
 *
 * Version V1.00
 * Author: @buddhima3967596
 *
 * 22/08/2023
 *
 * Creatives Commons No Rights Reserved
 */
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class Post {



    private int postID;
    private int likes;
    private int shares;
    private String content;
    private String author;
    private LocalDateTime datePosted;

    //Date Time Formatter for all instances of the class
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Post(int postID,String content,String author,int likes,int shares,LocalDateTime datePosted) {
        this.postID=postID;
        this.content=content;
        this.author=author;
        this.likes=likes;
        this.shares=shares;
        this.datePosted=datePosted;
    }

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

    @Override
    public String toString(){
        return String.format("%d | %s | %s | %d | %d | %s",this.getPostID(),this.getContent(),this.getAuthor(),this.getLikes(),this.getShares(), this.getDatePosted().format(dateTimeFormat));
    }

//Setters
    private void setPostID(int inputID){
        this.postID=inputID;
    }
    private void setLikes(int inputLikes){
        this.likes=inputLikes;
    }

    private void setShares(int inputShares){
        this.shares=inputShares;
    }

    private void setContent(String inputContent){
        this.content=inputContent;
    }

    private void setAuthor(String inputAuthor){
        this.author=inputAuthor;
    }


    private void setDatePosted(LocalDateTime inputDate){
        this.datePosted=inputDate;
    }


}
