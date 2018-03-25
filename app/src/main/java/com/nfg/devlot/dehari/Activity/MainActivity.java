package com.nfg.devlot.dehari.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.nfg.devlot.dehari.R;

public class MainActivity extends AppCompatActivity {

    Button    continueWithMobile_btn;
    TextView  continueWithFacebook_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createView();
        initializeObject();
    }


    private void createView()
    {
        continueWithMobile_btn    = (Button) findViewById(R.id.continueWithPhoneNumber_button_main_xml);
        continueWithFacebook_btn  = (TextView) findViewById(R.id.continueWithFacebook_textView_xml);
    }


    private void initializeObject()
    {

    }
}
