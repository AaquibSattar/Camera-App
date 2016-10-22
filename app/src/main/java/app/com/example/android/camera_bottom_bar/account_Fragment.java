package app.com.example.android.camera_bottom_bar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aaquib on 20-Oct-16.
 */

public class account_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView =inflater.inflate(R.layout.account_fragment,container,false);
        return rootView;
    }
}
