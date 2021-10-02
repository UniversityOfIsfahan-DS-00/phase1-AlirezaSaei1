package IMDB;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private HashMap<String, String> rated;
    private Set<String> watchlist;

    public User(String username, String password, String email){
        this.username = username;
        this.email = email;
        this.password = password;
        this.rated = new HashMap<>();
        this.watchlist = new HashSet<>();
    }

    public User(String username, String password, String email, HashMap<String, String> rated, Set<String> watchlist){
        this.username = username;
        this.email = email;
        this.password = password;
        this.rated = rated;
        this.watchlist = watchlist;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, String> getRated() {
        return rated;
    }

    public Set<String> getWatchlist() {
        return watchlist;
    }

    public void add_rated(String id ,String score){
        rated.put(id, score);
    }

    public void add_watchlist(String id){
        watchlist.add(id);
    }
}
