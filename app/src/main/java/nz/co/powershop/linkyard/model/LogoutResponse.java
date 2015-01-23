package nz.co.powershop.linkyard.model;

/**
 * Created by leandro on 23/01/15.
 */
public class LogoutResponse {

    private boolean success;
    private String info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
