package nz.co.powershop.linkyard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandro on 26/01/15.
 */
public class LinkSubmission {

    private String tags;
    private String description;
    private String url;
    private String title;
    private String content;
    private String digest;

    @SerializedName("digests")
    private String[] options;

    @SerializedName("link_interactions")
    private LinkInteraction[] linkInteractions;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public LinkInteraction[] getLinkInteractions() {
        return linkInteractions;
    }

    public void setLinkInteractions(LinkInteraction[] linkInteractions) {
        this.linkInteractions = linkInteractions;
    }
}
