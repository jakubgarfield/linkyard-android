package nz.co.powershop.linkyard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandro on 23/01/15.
 */
public class GetNewArticleResponse {

    @SerializedName("link_submission")
    private LinkSubmission linkSubmission;

    public LinkSubmission getLinkSubmission() {
        return linkSubmission;
    }

    public void setLinkSubmission(LinkSubmission linkSubmission) {
        this.linkSubmission = linkSubmission;
    }

}
