package com.nfg.devlot.dehari.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nfg.devlot.dehari.Helpers.InputValidation;
import com.nfg.devlot.dehari.R;

public class NewUserFormActivity extends AppCompatActivity implements View.OnClickListener {


    TextInputLayout name_textView,
                    email_textView,
                    password_textView,
                    confirmPassword_textView;


    TextInputEditText   name_editText,
                        email_editText,
                        password_editText,
                        confirmPassword_editText;


    Button continue_btn;
    InputValidation _refInputValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_form);

        createView();
        initializeObject();
    }

    private void initializeObject()
    {
        _refInputValidation = new InputValidation(this);
    }


    private void createView()
    {
        continue_btn = (Button) findViewById(R.id.continueButton_newUser_xml);

        name_textView            = (TextInputLayout) findViewById(R.id.name_textView_newUser_xml);
        email_textView           = (TextInputLayout) findViewById(R.id.email_textView_newUser_xml);
        password_textView        = (TextInputLayout) findViewById(R.id.password_textView_newUser_xml);
        confirmPassword_textView = (TextInputLayout) findViewById(R.id.confirmPassword_textView_newUser_xml);


        name_editText            = (TextInputEditText) findViewById(R.id.name_editText_newUser_xml);
        email_editText           = (TextInputEditText) findViewById(R.id.email_editText_newUser_xml);
        password_editText        = (TextInputEditText) findViewById(R.id.password_editText_newUser_xml);
        confirmPassword_editText = (TextInputEditText) findViewById(R.id.confirmPassword_editText_newUser_xml);
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId() == R.id.continueButton_newUser_xml)
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
        if(!_refInputValidation.isInputEditTextFilled(email_editText, email_textView, "Enter Email"))
        {
            return false;
        }
        else if(!_refInputValidation.isInputEditTextFilled(name_editText, name_textView, "Enter Name"))
        {
            return false;
        }
        else if(!_refInputValidation.isInputEditTextFilled(password_editText, password_textView, "Enter Password"))
        {
            return false;
        }
        else if(!_refInputValidation.isInputEditTextFilled(confirmPassword_editText, confirmPassword_textView, "Enter Confirm Password"))
        {
            return false;
        }
        else if(!_refInputValidation.isInputEditTextEmail(email_editText, email_textView,"Enter Valid Email"))
        {
            return false;
        }
        else if(!_refInputValidation.isInputEditTextMatches(password_editText, confirmPassword_editText, confirmPassword_textView, "Entered Passwords do not match"))
        {
            return false;
        }

        return true;
    }
}
