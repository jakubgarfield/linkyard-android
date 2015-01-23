package nz.co.powershop.linkyard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by leandro on 23/01/15.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private OnLoginListener mListener;
    private EditText mEmailView;
    private EditText mPasswordView;

    public static LoginFragment newInstance() {
        LoginFragment f = new LoginFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        v.findViewById(R.id.btn_log_in).setOnClickListener(this);
        mEmailView = (EditText) v.findViewById(R.id.email);
        mPasswordView = (EditText) v.findViewById(R.id.password);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnLoginListener");
        }
    }

    @Override
    public void onClick(View v) {
        if (R.id.btn_log_in == v.getId()) {
            if (mListener != null) {
                mListener.onLogin(
                        mEmailView.getText().toString(),
                        mPasswordView.getText().toString()
                );
            }
        }
    }

    public interface OnLoginListener {
        public void onLogin(String email, String password);
    }
}
