package IMDB;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String email;
    private String password;
    private Set<String> rated;
    private Set<String> watchlist;

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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRated() {
        return rated;
    }

    public Set<String> getWatchlist() {
        return watchlist;
    }
}
