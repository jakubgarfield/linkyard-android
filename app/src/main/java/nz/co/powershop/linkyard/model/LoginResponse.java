package nz.co.powershop.linkyard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandro on 23/01/15.
 */
public class LoginResponse {

    private boolean success;
    private String info;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("auth_token")
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
