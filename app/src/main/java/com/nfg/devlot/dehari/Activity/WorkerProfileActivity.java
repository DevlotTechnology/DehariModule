package com.nfg.devlot.dehari.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nfg.devlot.dehari.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class WorkerProfileActivity extends AppCompatActivity
{

    ImageView           call_btn,
                        message_btn;
    TextView            userName_editText,
                        userLocaton_editText;
    RatingBar           ratingBar;
    CircleImageView     profileImage;
    RequestQueue        requestQueue;

    String              sentId,
                        sentName,
                        sentPhone,
                        sentLocation,
                        sentRating;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        createView();
        initializeObject();

        if(getIntent()!=null)
        {
            if(getIntent().hasExtra("sid") &&
                    getIntent().hasExtra("name") &&
                    getIntent().hasExtra("phone") &&
                    getIntent().hasExtra("location") &&
                    getIntent().hasExtra("averagerating"))
            {
                sentId = getIntent().getStringExtra("sid");
                sentName = getIntent().getStringExtra("name");
                sentPhone = getIntent().getStringExtra("phone");
                sentLocation = getIntent().getStringExtra("location");
                sentRating = getIntent().getStringExtra("averagerating");
            }
        }
    }

    private void initializeObject()
    {
        requestQueue = Volley.newRequestQueue(this);
    }

    private void createView()
    {
        call_btn                = (ImageView) findViewById(R.id.callIcon_ImageView_workerProfile_xml);
        message_btn             = (ImageView) findViewById(R.id.messageIcon_imageView_workerProfile_xml);
        userName_editText       = (TextView)  findViewById(R.id.workerName_textView_workerProfile_xml);
        userLocaton_editText    = (TextView)  findViewById(R.id.userLocation_textView_workerProfile_xml);
        ratingBar               = (RatingBar) findViewById(R.id.workerRating_ratingBar_workerProfile_xml);
        profileImage            = (CircleImageView) findViewById(R.id.workerProfile_circleImageView_workerProfile_xml);
    }
}
