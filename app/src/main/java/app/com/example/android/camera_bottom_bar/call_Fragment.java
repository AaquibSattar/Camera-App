package app.com.example.android.camera_bottom_bar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.button;
import static app.com.example.android.camera_bottom_bar.R.id.yourName;

/**
 * Created by aaquib on 20-Oct-16.
 */

public class call_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView =inflater.inflate(R.layout.call_fragment,container,false);
        final EditText telNo_Text = (EditText)rootView.findViewById(R.id.tel_Text);
        Button callButton = (Button)(rootView.findViewById(R.id.call_Button_fragment));
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactNo =telNo_Text.getText().toString();
                if(contactNo.equals("")){
                    Toast.makeText(getContext(), "enter your telephone no", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_SUBJECT, "call request");
                    intent.putExtra(Intent.EXTRA_TEXT,"call request from:\n"+ contactNo);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });

                return rootView;

    }
}
