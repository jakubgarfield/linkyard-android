package nz.co.powershop.linkyard;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by leandro on 23/01/15.
 */
@Module(
        injects = {
                MainActivity.class,
                ShareArticleActivity.class
        },
        library = true,
        complete = false
)
public class RestServicesModule {

    public static final String ENDPOINT = "http://10.42.255.28:3000";

    @Provides
    @Named("linkyardService")
    LinkyardService provideLinkyardService() {
        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(LinkyardService.class);
    }
}
