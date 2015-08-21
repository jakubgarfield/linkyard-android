package nz.co.powershop.linkyard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import nz.co.powershop.linkyard.model.LinkInteraction;
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
    private LinearLayout mInteractionsView;
    private SparseArray<CheckBox> mInteractionViews;
    private Spinner mDigestsView;
    private String mUrl;

    public static ShareArticleFragment newInstance() {
        return new ShareArticleFragment();
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
        mDigestsView = (Spinner) v.findViewById(R.id.digest);

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

                LinkSubmission linkSubmission = new LinkSubmission();
                linkSubmission.setUrl(mUrl);
                linkSubmission.setTags(mTagsView.getText().toString());
                linkSubmission.setDigest((String) mDigestsView.getSelectedItem());
                linkSubmission.setDescription(mDescriptionView.getText().toString());
                linkSubmission.setTitle(mTitleView.getText().toString());
                linkSubmission.setContent(mContentView.getText().toString());

                final int count = mInteractionViews.size();
                LinkInteraction[] interactions = new LinkInteraction[count];
                for (int i = 0; i < count; i++) {
                    CheckBox checkBox = mInteractionViews.valueAt(i);
                    interactions[i] = (LinkInteraction) checkBox.getTag();
                    interactions[i].setChecked(checkBox.isChecked() ? "1" : "0");
                }

                linkSubmission.setLinkInteractions(interactions);

                mListener.onSave(linkSubmission);
            }
        }
    }

    public void setLinkSubmission(LinkSubmission linkSubmission) {

        mUrl = linkSubmission.getUrl();
        mTagsView.setText(linkSubmission.getTags());
        mDescriptionView.setText(linkSubmission.getDescription());
        mTitleView.setText(linkSubmission.getTitle());
        mContentView.setText(linkSubmission.getContent());
        mDigestsView.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, linkSubmission.getOptions()));

        mInteractionViews = new SparseArray<>();
        for (LinkInteraction interaction : linkSubmission.getLinkInteractions()) {

            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setTag(interaction);
            checkBox.setText(interaction.getName());
            checkBox.setChecked(!interaction.getChecked().equals("0"));

            mInteractionsView.addView(checkBox);
            mInteractionViews.put(interaction.getId(), checkBox);
        }

    }

    public interface OnSaveListener {
        void onSave(LinkSubmission submission);
    }
}
