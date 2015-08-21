package nz.co.powershop.linkyard.model;

/**
 * Created by leandro on 21/08/15.
 */
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