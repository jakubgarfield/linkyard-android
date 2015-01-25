package nz.co.powershop.linkyard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import nz.co.powershop.linkyard.model.GetNewArticleResponse;
import nz.co.powershop.linkyard.model.LinkSubmission;
import nz.co.powershop.linkyard.model.LogoutResponse;
import nz.co.powershop.linkyard.model.PostArticleRequest;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by leandro on 23/01/15.
 */
public class ShareArticleActivity extends ActionBarActivity
        implements ShareArticleFragment.OnSaveListener, Callback<LogoutResponse> {

    private static final String TAG = ShareArticleActivity.class.getSimpleName();
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
                    .replace(R.id.container, ShareArticleFragment.newInstance())
                    .commit();
        }

        if (PreferenceManager.getDefaultSharedPreferences(this).contains("token")) {
            loadArticle();
        } else {
            startActivityForResult(new Intent(this, LoginActivity.class), 0);
        }
    }

    private void loadArticle() {
        final String token = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("token", null);
        final String url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        linkyardService.newArticle(token, url, new Callback<GetNewArticleResponse>() {

            @Override
            public void success(GetNewArticleResponse newArticleResponse, Response response) {
                setTitle(newArticleResponse.getLinkSubmission().getUrl());
                ShareArticleFragment fragment = (ShareArticleFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.container);
                if (fragment != null) {
                    fragment.setLinkSubmission(newArticleResponse.getLinkSubmission());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.w(TAG, error.getLocalizedMessage(), error);
                Toast.makeText(ShareArticleActivity.this, "Could not load url.",
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
            loadArticle();
        }
    }

    @Override
    public void onSave(LinkSubmission submission) {

        Log.d(TAG, "Saving article.");

        final String token = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("token", null);

        PostArticleRequest request = new PostArticleRequest();
        request.setToken(token);
        request.setSubmission(submission);

        linkyardService.newArticle(token, request, new Callback<Void>() {

            @Override
            public void success(Void aVoid, Response response) {
                Log.d(TAG, "Article saved");
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ShareArticleActivity.this, "Failed to save article.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final int id = item.getItemId();
        if (id == R.id.action_logout) {

            final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            final String token = defaultSharedPreferences.getString("token", null);

            defaultSharedPreferences.edit().remove("token").commit();

            linkyardService.logout(token, this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(LogoutResponse logoutResponse, Response response) {
        Log.d(TAG, logoutResponse.getInfo());
        finish();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.w(TAG, error.getLocalizedMessage(), error);
        finish();
    }
}
