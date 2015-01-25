package nz.co.powershop.linkyard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandro on 26/01/15.
 */
public class GetArticlesResponse {

    private Link[] links;

    public Link[] getLinks() {
        return links;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }

    public class Link {
        private int id;

        @SerializedName("user_id")
        private int userId;

        private String title;
        private String url;

//        @SerializedName("created_at")
//        private Date created;
//
//        @SerializedName("updated_at")
//        private Date updated;

        private String description;

        private String content;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

//        public Date getCreated() {
//            return created;
//        }
//
//        public void setCreated(Date created) {
//            this.created = created;
//        }
//
//        public Date getUpdated() {
//            return updated;
//        }
//
//        public void setUpdated(Date updated) {
//            this.updated = updated;
//        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return getTitle() + " (" + getUrl() + ")";
        }
    }
}
