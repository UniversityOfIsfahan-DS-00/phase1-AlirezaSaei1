package IMDB;

import java.util.HashSet;
import java.util.Set;

public class User {
    String username;
    String email;
    String password;
    Set<String> rated;
    Set<String> watchlist;

    public User(String username, String password, String email){
        this.username = username;
        this.email = email;
        this.password = password;
        this.rated = new HashSet<>();
        this.watchlist = new HashSet<>();
    }

    public User(String username, String password, String email,Set<String> rated, Set<String> watchlist){
        this.username = username;
        this.email = email;
        this.password = password;
        this.rated = rated;
        this.watchlist = watchlist;
    }
}
