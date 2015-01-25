package nz.co.powershop.linkyard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import nz.co.powershop.linkyard.model.LinkSubmission;

/**
 * Created by leandro on 23/01/15.
 */
public class ShareArticleFragment extends Fragment implements View.OnClickListener {

    private OnSaveListener mListener;
    private EditText mTagsView;
    private EditText mDescriptionView;
    private EditText mTitleView;
    private EditText mContentView;
    private LinkSubmission mLinkSubmission;

    public static ShareArticleFragment newInstance() {
        ShareArticleFragment f = new ShareArticleFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_share_article, container, false);

        mTagsView = (EditText) v.findViewById(R.id.tags);
        mDescriptionView = (EditText) v.findViewById(R.id.description);
        mTitleView = (EditText) v.findViewById(R.id.title);
        mContentView = (EditText) v.findViewById(R.id.content);

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
                // TODO Update submission.
                if (mLinkSubmission != null) {
                    mLinkSubmission.setTags(mTagsView.getText().toString());
                    mLinkSubmission.setDescription(mDescriptionView.getText().toString());
                    mLinkSubmission.setTitle(mTitleView.getText().toString());
                    mLinkSubmission.setContent(mContentView.getText().toString());
                    // TODO Set link interactions.
                    mListener.onSave(mLinkSubmission);
                }
            }
        }
    }

    public void setLinkSubmission(LinkSubmission linkSubmission) {
        mLinkSubmission = linkSubmission;
        mTagsView.setText(linkSubmission.getTags());
        mDescriptionView.setText(linkSubmission.getDescription());
        mTitleView.setText(linkSubmission.getTitle());
        mContentView.setText(linkSubmission.getContent());
        // TODO Display link interactions if any.
    }

    public interface OnSaveListener {
        public void onSave(LinkSubmission submission);
    }
}
