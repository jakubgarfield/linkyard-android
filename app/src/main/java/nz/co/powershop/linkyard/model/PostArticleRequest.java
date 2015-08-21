package nz.co.powershop.linkyard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandro on 26/01/15.
 */
public class PostArticleRequest {

    @SerializedName("auth_token")
    private String token;

    @SerializedName("link_submission")
    private LinkSubmission submission;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LinkSubmission getSubmission() {
        return submission;
    }

    public void setSubmission(LinkSubmission submission) {
        this.submission = submission;
    }
}
