
package com.nfg.devlot.dehari.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nfg.devlot.dehari.Adapters.ServiceProviderAdapter;
import com.nfg.devlot.dehari.Adapters.WorkersAdapter;
import com.nfg.devlot.dehari.Models.URL;
import com.nfg.devlot.dehari.Models.WorkersModel;
import com.nfg.devlot.dehari.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SirviceProvidersActivity extends AppCompatActivity {


    RecyclerView            recyclerView;
    LinearLayoutManager     linearLayoutManager;
    String                  service_id="";
    RequestQueue            requestQueue;
    ArrayList<WorkersModel> serviceProvidersList = null;
    ServiceProviderAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sirvice_providers);

        createView();
        initializeObjects();

        /**
         *
         *  Setting information for recycler-view
         *
         * */

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        /**
         *
         *  Retrieving Intent value here
         *  @func RetrieveIntentValue();
         * */

        RetrieveIntentValue();


        /**
         *
         *  Retrieving All Service Providers here
         *  @func RetrieveServiceProviders();
         * */

        RetrieveServiceProviders();
    }

    private void RetrieveServiceProviders()
    {
        if(!service_id.trim().equals(""))
        {
            StringRequest request = new StringRequest(Request.Method.POST, URL.GET_PROVIDERS_BY_SERVICE, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    if(!response.contains("noData"))
                    {

                        Log.d("[*] Check Message", "Inside if");
                        capture_Response(response);

                        /**
                         *
                         *  Adding response to the adapter
                         *  @code adapter.UpdateRecord(serviceProvidersList);
                         *
                         * */

                        adapter.UpdateRecord(serviceProvidersList);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),"There appears to be a problem. Please try again",Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> parameters = new HashMap<String, String>();
                    parameters.put("s_id",service_id);

                    return parameters;
                }
            };

            requestQueue.add(request);
        }
    }

    private void capture_Response(String response)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("providersByService");

            for (int i=0; i<jsonArray.length();i++)
            {

                JSONObject services = jsonArray.getJSONObject(i);

                WorkersModel _refWorkerModel = new WorkersModel();

                _refWorkerModel.setId         (services.getString("sp_id"));
                _refWorkerModel.setName       (services.getString("name"));
                _refWorkerModel.setPhoneNumber(services.getString("phone_number"));
                _refWorkerModel.setEmail      (services.getString("email"));
                _refWorkerModel.setAverage    (services.getString("average"));
                _refWorkerModel.setAccessToken(services.getString("token"));
                _refWorkerModel.setImagePath  (services.getString("image_path"));

                serviceProvidersList.add(_refWorkerModel);
            }
        }
        catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),"There is an Error parsing Services in Services Activity",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void RetrieveIntentValue()
    {
        if(getIntent() != null)
        {
            if(getIntent().hasExtra("Service"))
            {
                service_id = getIntent().getStringExtra("Service");
            }
        }
    }

    private void initializeObjects()
    {
        serviceProvidersList    =  new ArrayList<>();
        adapter                 =  new ServiceProviderAdapter(this, serviceProvidersList);
        linearLayoutManager     =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        requestQueue            =  Volley.newRequestQueue(this);
    }

    private void createView()
    {

        recyclerView    =  findViewById(R.id.recyclerView_service_provider_xml);
    }
}
