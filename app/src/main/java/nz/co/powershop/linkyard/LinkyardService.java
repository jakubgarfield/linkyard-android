package nz.co.powershop.linkyard;

import nz.co.powershop.linkyard.model.AuthenticationRequest;
import nz.co.powershop.linkyard.model.LoginResponse;
import nz.co.powershop.linkyard.model.LogoutResponse;
import nz.co.powershop.linkyard.model.NewArticleResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by leandro on 23/01/15.
 */
public interface LinkyardService {

    @POST("/api/registrations")
    public void register(@Body AuthenticationRequest request);

    @POST("/api/sessions")
    void login(@Body AuthenticationRequest request, Callback<LoginResponse> callback);

    @DELETE("/api/sessions")
    void logout(@Query("auth_token") String token, Callback<LogoutResponse> callback);

    @GET("/api/links/new")
    void newArticle(@Query("auth_token") String token, @Query("url") String url,
                    Callback<NewArticleResponse> callback);
}
