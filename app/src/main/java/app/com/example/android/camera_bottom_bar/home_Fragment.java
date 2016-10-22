package app.com.example.android.camera_bottom_bar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.R.attr.fragment;
import static android.app.Activity.RESULT_OK;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static app.com.example.android.camera_bottom_bar.R.id.yourName;

/**
 * Created by aaquib on 20-Oct-16.
 */

public class home_Fragment extends Fragment {

    private Bitmap bitmap;
    private Button button;
    private ImageView imageView;
    private File imageFile;
    private String name;
    private String phoneNo;
    private EditText editTextName;
    private EditText editTelephone_No;
    private EditText text_Comment;
    private ImageView sendButton;
    private Uri tempUri;
    String picturePath;
    boolean checkValue;
    Button image_Gallery;

    private static final String KEY = "MY_KEY";
    private static final String TAG = "MAIN_ACTIVITY_TAG";
    private static int RESULT_LOAD_IMAGE = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        editTextName = (EditText) rootView.findViewById(yourName);
        text_Comment = (EditText)rootView. findViewById(R.id.commentText);
        text_Comment.setVisibility(View.GONE);
        sendButton = (ImageView)rootView. findViewById(R.id.send_button);
        sendButton.setVisibility(View.GONE);
        editTelephone_No = (EditText) rootView.findViewById(R.id.phoneNo);

        button = (Button) rootView.findViewById(R.id.camera_button);
        imageView = (ImageView) rootView.findViewById(R.id.camera_Image);
        Log.v(TAG, "Inside onCreate Activity of MainActivity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkValue = checkMethod();

                if (checkValue) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    imageFile = new File(Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_PICTURES), "testFile.jpg");

                    tempUri = Uri.fromFile(imageFile);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, 0);
                } else {
                    //code if check returns false
                    Toast.makeText(getActivity(), "Enter ur name and telephone no first", Toast.LENGTH_LONG).show();
                }

            }
        });

        image_Gallery = (Button) rootView.findViewById(R.id.add_image_button);
        image_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue = checkMethod();

                if (checkValue) {
                    Intent i = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                } else {
                    Toast.makeText(getActivity(), "Enter ur name and telephone no first", Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageView sendButton = (ImageView) (rootView.findViewById(R.id.send_button));
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "inside onClickListener method of send button");
                String comment = text_Comment.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "PrivateProject2 order");
                intent.putExtra(Intent.EXTRA_TEXT, " Name: " + name + "\n Contact no: " + phoneNo + "\n \n \n" + comment);
                intent.putExtra(Intent.EXTRA_STREAM, tempUri);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        return rootView;

    }


    private boolean checkMethod() {
        name = editTextName.getText().toString();
        phoneNo = editTelephone_No.getText().toString();
        Log.v(TAG, "onClickListener Activated");

        if (name.equals("") || phoneNo.equals("")) {
            return (false);
        } else
            return (true);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, "onActivityResult");
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Toast.makeText(getActivity(), "the file was saved at " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            setImage();
        }

        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            Log.v(TAG, "inside else if of onActivityResult");
            tempUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(tempUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            bitmap = BitmapFactory.decodeFile(picturePath);
            setImage();
        }
        else {
            Log.v(TAG, "inside else of onActivityResult");

            Toast.makeText(getActivity(), "You didn't select any image. ", Toast.LENGTH_LONG).show();
        }
    }


    private void setImage() {
        Log.v(TAG, "inside setImage of Activity");

        imageView.setImageBitmap(bitmap);
        button.setVisibility(View.GONE);
        editTextName.setVisibility(View.GONE);
        image_Gallery.setVisibility(View.GONE);
        editTelephone_No.setVisibility(View.GONE);
        Log.v(TAG, "make view View visible again");
        text_Comment.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);

    }
}
