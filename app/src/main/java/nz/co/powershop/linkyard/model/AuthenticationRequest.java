package nz.co.powershop.linkyard.model;

/**
 * Created by leandro on 23/01/15.
 */
public class AuthenticationRequest {

    private User user;

    public AuthenticationRequest(String username, String password) {
        user = new User();
        user.setEmail(username);
        user.setPassword(password);
        user.setConfirmation(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
