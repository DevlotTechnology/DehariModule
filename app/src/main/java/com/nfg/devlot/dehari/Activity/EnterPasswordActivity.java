package com.nfg.devlot.dehari.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nfg.devlot.dehari.Helpers.InputValidation;
import com.nfg.devlot.dehari.R;

public class EnterPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Button continue_btn;
    TextInputLayout password_textView;
    TextInputEditText password_editText;
    InputValidation _refInputValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        createWidget();
        initialObjects();


    }


    private void createWidget()
    {
        continue_btn = (Button) findViewById(R.id.continueButton_enterPassword_xml);
        password_editText = (TextInputEditText) findViewById(R.id.password_editText_enterPassword_xml);
        password_textView = (TextInputLayout) findViewById(R.id.password_textView_enterPassword_xml);
    }

    private void initialObjects()
    {
        _refInputValidation = new InputValidation(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.continueButton_enterPassword_xml)
        {
            if(checkInput())
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
        if(!_refInputValidation.isInputEditTextFilled(password_editText, password_textView, "Enter Password"))
        {
            return false;
        }

        return true;
    }
}
