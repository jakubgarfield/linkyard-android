package nz.co.powershop.linkyard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by leandro on 23/01/15.
 */
public class ShareArticleFragment extends Fragment implements View.OnClickListener {

    private OnSaveListener mListener;

    public static ShareArticleFragment newInstance() {
        ShareArticleFragment f = new ShareArticleFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_share_article, container, false);
        v.findViewById(R.id.btn_add).setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSaveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnLoginListener");
        }
    }

    @Override
    public void onClick(View v) {
        if (R.id.btn_add == v.getId()) {
            if (mListener != null) {
                // TODO Pass article to onSave method.
                mListener.onSave();
            }
        }
    }

    public interface OnSaveListener {
        public void onSave();
    }
}
