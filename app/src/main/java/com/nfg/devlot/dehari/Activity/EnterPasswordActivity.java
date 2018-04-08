package com.nfg.devlot.dehari.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nfg.devlot.dehari.Helpers.InputValidation;
import com.nfg.devlot.dehari.Models.URL;
import com.nfg.devlot.dehari.Models.UserModel;
import com.nfg.devlot.dehari.R;
import com.nfg.devlot.dehari.SQL.DatabaseHelper;
import com.nfg.devlot.dehari.Session.UserSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class EnterPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Button              continue_btn;
    TextInputLayout     password_textView;
    TextInputEditText   password_editText;
    InputValidation     _refInputValidation;
    RequestQueue        requestQueue;
    DatabaseHelper      _refLocalDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        createWidget();
        initialObjects();

        continue_btn.setOnClickListener(this);
    }


    private void createWidget()
    {
        continue_btn        = (Button) findViewById(R.id.continueButton_enterPassword_xml);
        password_editText   = (TextInputEditText) findViewById(R.id.password_editText_enterPassword_xml);
        password_textView   = (TextInputLayout) findViewById(R.id.password_textView_enterPassword_xml);
    }

    private void initialObjects()
    {
        _refInputValidation     = new InputValidation(this);
        requestQueue            = Volley.newRequestQueue(getApplicationContext());
        _refLocalDbHelper       = new DatabaseHelper(this);
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
                 *  Diabling Continue button here
                 *  @code continue_btn.setEnabled(false);
                 *
                 * */

                continue_btn.setEnabled(false);

                /**
                 *
                 * COMMUNICATION WITH DATABASE GOES HERE
                 *
                 * */

                StringRequest request = new StringRequest(Request.Method.POST, URL.CHECK_PASSWORD, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        /**
                         *
                         *  Enabling Continue button here
                         *  @code continue_btn.setEnabled(true);
                         * */

                        continue_btn.setEnabled(true);


                        if(response.contains("noData"))
                        {
                            Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Capture_response(response);

                        startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));
                        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        /**
                         *
                         *  Enabling Continue button here
                         *  @code continue_btn.setEnabled(true);
                         * */

                        continue_btn.setEnabled(true);

                        Toast.makeText(getApplicationContext(),"There Appears to be problem, Please try again later",Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("phone", UserSession.uPhone);
                        parameters.put("password",password_editText.getText().toString());

                        return parameters;
                    }
                };

                requestQueue.add(request);


            }
        }
    }

    private void Capture_response(String response) {

        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("service_seeker");

            UserSession.uid         =   jsonArray.getJSONObject(0).getString("ss_id");
            UserSession.uname       =   jsonArray.getJSONObject(0).getString("name");
            UserSession.uemail      =   jsonArray.getJSONObject(0).getString("email");

            UserSession.imagePath   =   jsonArray.getJSONObject(0).getString("image_path");


            UserModel _refUserModel = new UserModel();
            _refUserModel.setName(UserSession.uname);
            _refUserModel.setSs_id(UserSession.uid);
            _refUserModel.setEmail(UserSession.uemail);
            _refUserModel.setImagePath(UserSession.imagePath);
            _refUserModel.setPhone_number(UserSession.uPhone);


            /**
             *
             *  Inserting data in local datbase here
             *  @code  DatabaseHelper.insertUser( UserModel obj);
             *
             * */

            _refLocalDbHelper.insertUser(_refUserModel);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
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
