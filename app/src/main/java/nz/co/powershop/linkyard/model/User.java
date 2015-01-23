package nz.co.powershop.linkyard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandro on 23/01/15.
 */
public class User {

    @SerializedName("password_confirmation")
    private String confirmation;
    private String email;
    private String password;

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
