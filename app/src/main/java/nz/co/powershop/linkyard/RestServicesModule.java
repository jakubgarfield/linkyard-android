package nz.co.powershop.linkyard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static final String ENDPOINT = "http://10.42.255.28:3000";
    public static final String DATE_FORMAT = "yyyy-MM-ddTHH:mm:ss.SSSZ";

    @Provides
    @Named("linkyardService")
    LinkyardService provideLinkyardService() {

        Gson gson = new GsonBuilder()
//                .setDateFormat(DATE_FORMAT)
                .create();

        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(this)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(LinkyardService.class);
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("format", "json");
    }
}
