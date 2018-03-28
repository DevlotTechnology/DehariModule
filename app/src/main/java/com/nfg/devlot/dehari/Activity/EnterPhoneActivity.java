package com.nfg.devlot.dehari.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nfg.devlot.dehari.Helpers.InputValidation;
import com.nfg.devlot.dehari.R;

public class EnterPhoneActivity extends AppCompatActivity implements View.OnClickListener{

    Button              continue_btn;
    TextInputLayout     phone_textView;
    TextInputEditText   phone_editText;
    InputValidation     _refInputValidation;
    RequestQueue        requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone);

        createView();
        initializeObject();

        continue_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.continueButton_enterPhone_xml) {
            if (checkInput())
            {
                /**
                 *
                 * COMMUNICATION WITH DATABASE GOES HERE
                 *
                 * */



            }
        }
    }

    private boolean checkInput()
    {
        if(!_refInputValidation.isInputEditTextFilled(phone_editText,phone_textView, "Enter Phone"))
        {
            return false;
        }
        else if(!_refInputValidation.isValidPhoneNumber(phone_editText, phone_textView,"Please Enter Valid Phone Number"))
        {
            return false;
        }

        return true;
    }

    private void initializeObject()
    {
        _refInputValidation = new InputValidation(this);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    private void createView()
    {
        continue_btn    = (Button) findViewById(R.id.continueButton_enterPhone_xml);
        phone_editText  = (TextInputEditText) findViewById(R.id.phoneNumber_editText_enterPhone_xml);
        phone_textView  = (TextInputLayout) findViewById(R.id.phoneNumber_textView_enterPhone_xml);
    }
}
