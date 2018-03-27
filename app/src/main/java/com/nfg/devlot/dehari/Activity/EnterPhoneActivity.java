package com.nfg.devlot.dehari.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nfg.devlot.dehari.Helpers.InputValidation;
import com.nfg.devlot.dehari.R;
import com.nfg.devlot.dehari.Session.UserSession;

public class EnterPhoneActivity extends AppCompatActivity implements View.OnClickListener{

    Button continue_btn;
    TextInputLayout phone_textView;
    TextInputEditText phone_editText;

    InputValidation _refInputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone);

        createView();
        initializeObject();

    }

    private void createView()
    {
        continue_btn    = (Button) findViewById(R.id.continueButton_enterPhone_xml);
        phone_editText  = (TextInputEditText) findViewById(R.id.phoneNumber_editText_enterPhone_xml);
        phone_textView  = (TextInputLayout) findViewById(R.id.phoneNumber_textView_enterPhone_xml);
    }

    private void initializeObject()
    {
        _refInputValidation = new InputValidation(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.phoneNumber_textView_enterPhone_xml) {
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
}
