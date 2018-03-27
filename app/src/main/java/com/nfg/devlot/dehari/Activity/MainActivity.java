package com.nfg.devlot.dehari.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nfg.devlot.dehari.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button    continueWithMobile_btn;
    TextView  continueWithFacebook_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createView();
        initializeObject();

        continueWithMobile_btn.setOnClickListener(this);
    }


    private void createView()
    {
        continueWithMobile_btn    = (Button) findViewById(R.id.continueWithPhoneNumber_button_main_xml);
        continueWithFacebook_btn  = (TextView) findViewById(R.id.continueWithFacebook_textView_xml);
    }


    private void initializeObject()
    {

    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.continueWithPhoneNumber_button_main_xml)
        {
            startActivity(new Intent(getApplicationContext(),EnterPhoneActivity.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        }
        else if(v.getId() == R.id.continueWithFacebook_textView_xml)
        {
            Toast.makeText(getApplicationContext(),"This Option is diabled by Senior Developer", Toast.LENGTH_SHORT).show();
        }
    }
}
