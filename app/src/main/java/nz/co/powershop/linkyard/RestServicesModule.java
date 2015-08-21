package nz.co.powershop.linkyard;

import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by leandro on 23/01/15.
 */
@Module(
        injects = {
                LinkListActivity.class,
                LoginActivity.class,
                ShareArticleActivity.class
        },
        library = true,
        complete = false
)
public class RestServicesModule implements RequestInterceptor {

    public static final String ENDPOINT = "http://linkyard.cloudapp.net";

    @Provides
    @Named("linkyardService")
    LinkyardService provideLinkyardService() {

        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(this)
                .build()
                .create(LinkyardService.class);
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("format", "json");
    }
}
