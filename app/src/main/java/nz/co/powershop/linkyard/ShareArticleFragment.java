package nz.co.powershop.linkyard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

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
    private LinearLayout mInteractionsView;
    private SparseArray<CheckBox> mInteractionViews;

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
        mInteractionsView = (LinearLayout) v.findViewById(R.id.interactions);

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
                if (mLinkSubmission != null) {
                    mLinkSubmission.setTags(mTagsView.getText().toString());
                    mLinkSubmission.setDescription(mDescriptionView.getText().toString());
                    mLinkSubmission.setTitle(mTitleView.getText().toString());
                    mLinkSubmission.setContent(mContentView.getText().toString());

                    for (LinkSubmission.LinkInteraction interaction : mLinkSubmission
                            .getLinkInteractions()) {
                        CheckBox checkBox = mInteractionViews.get(interaction.getId());
                        interaction.setChecked(checkBox.isChecked() ? "1" : "0");
                    }

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
        mInteractionViews = new SparseArray<>();
        for (LinkSubmission.LinkInteraction interaction : linkSubmission.getLinkInteractions()) {

            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(interaction.getName());
            checkBox.setChecked(!interaction.getChecked().equals("0"));

            mInteractionsView.addView(checkBox);

            mInteractionViews.put(interaction.getId(), checkBox);
        }

    }

    public interface OnSaveListener {
        public void onSave(LinkSubmission submission);
    }
}
