package com.nfg.devlot.dehari.Activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nfg.devlot.dehari.Helpers.InputValidation;
import com.nfg.devlot.dehari.Models.URL;
import com.nfg.devlot.dehari.Models.UserModel;
import com.nfg.devlot.dehari.R;
import com.nfg.devlot.dehari.Retrofit.RetrofitApiClient;
import com.nfg.devlot.dehari.Retrofit.RetrofitInterface;

public class EnterPhoneActivity extends AppCompatActivity implements View.OnClickListener{

    Button              continue_btn;
    TextInputLayout     phone_textView;
    TextInputEditText   phone_editText;
    InputValidation     _refInputValidation;
    RetrofitInterface   _refrofitApi;


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

                _refrofitApi = new RetrofitApiClient().getApiClient(URL.CHECK_PHONE).create(RetrofitInterface.class);
                Call<String> networkingCall = _refrofitApi.checkUserMobile(phone_editText.getText().toString().trim());

                if(networkingCall!=null)
                {
                    networkingCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if(response.isSuccessful())
                            {
                                Log.d("Res->",response.body());
                                if (response.body().contains("true"))
                                {
                                    Toast.makeText(getApplicationContext(), "Mobile Number Exists", Toast.LENGTH_SHORT).show();
                                }
                                else if(response.body().trim().contains("false"))
                                {
                                    Toast.makeText(getApplicationContext(), "Mobile Number Do not exists", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("Error->",t.getMessage());
                            Toast.makeText(getApplicationContext(),"There appears to be problem, Please try again later", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

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
    }

    private void createView()
    {
        continue_btn    = (Button) findViewById(R.id.continueButton_enterPhone_xml);
        phone_editText  = (TextInputEditText) findViewById(R.id.phoneNumber_editText_enterPhone_xml);
        phone_textView  = (TextInputLayout) findViewById(R.id.phoneNumber_textView_enterPhone_xml);
    }
}
