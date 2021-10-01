package IMDB;

import java.util.ArrayList;

public class Movie implements Comparable<Movie> {
    private String title;
    private String titleId;
    private int ordering;
    private double avgRating;
    private int votes;
    private String region;
    private String language;
    private String types;
    private ArrayList<String> attributes;
    private String isOriginalTitle;

    public Movie(String title, String titleId, int ordering, double avgRating, int votes, String region,
                 String language, String types, ArrayList<String> attributes, boolean isOriginalTitle) {
        this.title = title;
        this.titleId = titleId;
        this.ordering = ordering;
        this.avgRating = avgRating;
        this.votes = votes;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        if(isOriginalTitle) {
            this.isOriginalTitle = "Yes";
        }else{
            this.isOriginalTitle = "No";
        }
    }

    public Movie(String title, String titleId, int ordering, String region,
                 String language, String types, ArrayList<String> attributes, boolean isOriginalTitle) {
        this.title = title;
        this.titleId = titleId;
        this.ordering = ordering;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        if(isOriginalTitle) {
            this.isOriginalTitle = "Yes";
        }else{
            this.isOriginalTitle = "No";
        }
    }

    public void voted(double score){
        this.avgRating = ((this.avgRating * this.votes) + score)/(this.votes + 1);
        this.votes++;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleId() {
        return titleId;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public int getVotes() {
        return votes;
    }

    public String getRegion() {
        return region;
    }

    public String getLanguage() {
        return language;
    }

    public String getTypes() {
        return types;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public String getIsOriginalTitle() {
        return isOriginalTitle;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public int compareTo(Movie o1) {
        if(o1.getAvgRating() > this.getAvgRating()){
            return 1;
        }else if(o1.getAvgRating() < this.getAvgRating()){
            return -1;
        }else{
            if(o1.getVotes() > this.getVotes()){
                return 1;
            }else{
                return -1;
            }
        }
    }
}
