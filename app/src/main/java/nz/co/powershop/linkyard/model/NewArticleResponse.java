package nz.co.powershop.linkyard.model;

/**
 * Created by leandro on 23/01/15.
 */
public class NewArticleResponse {

    private LinkSubmission linkSubmission;

    public LinkSubmission getLinkSubmission() {
        return linkSubmission;
    }

    public void setLinkSubmission(LinkSubmission linkSubmission) {
        this.linkSubmission = linkSubmission;
    }

    public class LinkSubmission {
        private String url;
        private String title;
        private String content;
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
    }

    public class LinkInteraction {
        private int id;
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
    }

}
