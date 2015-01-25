package nz.co.powershop.linkyard;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import nz.co.powershop.linkyard.model.GetArticlesResponse;

/**
 * Created by leandro on 26/01/15.
 */
public class LinkListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static LinkListFragment newInstance() {
        return new LinkListFragment();
    }

    public void setLinks(GetArticlesResponse.Link[] links) {
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, links));
    }
}
