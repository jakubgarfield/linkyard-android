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

    @SerializedName("link_interactions")
    private LinkInteraction[] linkInteractions;

    public LinkInteraction[] getLinkInteractions() {
        return linkInteractions;
    }

    public void setLinkInteractions(LinkInteraction[] linkInteractions) {
        this.linkInteractions = linkInteractions;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public class LinkInteraction {
        private int id;
        private String name;
        private String checked;

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
