package nz.co.powershop.linkyard;

import android.app.Application;

import java.util.Arrays;
import java.util.Collection;

import dagger.ObjectGraph;

/**
 * Created by leandro on 23/01/15.
 */
public class LinkyardApplication extends Application {

    private ObjectGraph mObjectGraph;

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    private Collection getModules() {
        return Arrays.<Object>asList(new RestServicesModule());
    }
}
