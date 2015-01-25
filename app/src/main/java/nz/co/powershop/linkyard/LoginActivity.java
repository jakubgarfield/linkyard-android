package nz.co.powershop.linkyard;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import nz.co.powershop.linkyard.model.AuthenticationRequest;
import nz.co.powershop.linkyard.model.LoginResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends ActionBarActivity implements LoginFragment.OnLoginListener,
        Callback<LoginResponse> {

    private static final String TAG = LoginActivity.class.getSimpleName();

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
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commit();
        }

    }


    @Override
    public void onLogin(String username, String password) {
        AuthenticationRequest request = new AuthenticationRequest(username, password);
        linkyardService.login(request, this);
    }


    @Override
    public void success(LoginResponse loginResponse, Response response) {

        Log.d(TAG, loginResponse.getInfo());
        if (loginResponse.isSuccess()) {

            // Save token.
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putString("token", loginResponse.getData().getToken())
                    .commit();

            setResult(RESULT_OK);

            finish();
        } else {
            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this, "Could not connect to server.", Toast.LENGTH_SHORT).show();
    }

}
