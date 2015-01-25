package nz.co.powershop.linkyard;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import nz.co.powershop.linkyard.model.GetArticlesResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by leandro on 26/01/15.
 */
public class LinkListActivity extends ActionBarActivity {

    private static final String TAG = LinkListActivity.class.getSimpleName();

    @Inject
    @Named("linkyardService")
    LinkyardService linkyardService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((LinkyardApplication) getApplication()).getObjectGraph().inject(this);

        setContentView(R.layout.activity_base);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, LinkListFragment.newInstance())
                    .commit();
        }

        if (PreferenceManager.getDefaultSharedPreferences(this).contains("token")) {
            loadArticles();
        } else {
            startActivityForResult(new Intent(this, LoginActivity.class), 0);
        }
    }

    private void loadArticles() {

        final String token = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("token", null);

        linkyardService.getArticles(token, new Callback<GetArticlesResponse>() {

            @Override
            public void success(GetArticlesResponse getArticlesResponse, Response response) {
                LinkListFragment fragment = (LinkListFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.container);
                if (fragment != null) {
                    fragment.setLinks(getArticlesResponse.getLinks());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.w(TAG, error.getLocalizedMessage(), error);
                Toast.makeText(LinkListActivity.this, "Could not load articles.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            finish();
        } else {
            loadArticles();
        }
    }
}
