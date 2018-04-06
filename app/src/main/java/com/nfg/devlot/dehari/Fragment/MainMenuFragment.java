package com.nfg.devlot.dehari.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nfg.devlot.dehari.Adapters.CategoryAdapter;
import com.nfg.devlot.dehari.Adapters.WorkersAdapter;
import com.nfg.devlot.dehari.Models.CategoryModel;
import com.nfg.devlot.dehari.Models.URL;
import com.nfg.devlot.dehari.Models.WorkersModel;
import com.nfg.devlot.dehari.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenuFragment extends Fragment {

    RecyclerView                recyclerView;
    RecyclerView                recyclerViewWorkers;
    LinearLayoutManager         layoutManager, layoutManagerWorkers;
    ArrayList<CategoryModel>    categoryArrayList;
    ArrayList<WorkersModel>     workersArrayList;
    RequestQueue                requestQueue;
    CategoryAdapter             adapterObject;
    WorkersAdapter              adapterWokersObject;
    SwipeRefreshLayout          swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        createView(view);
        initializeObjects();

        /**
         *
         *  Swipe to refresh code goes here
         *  @func swipeToRefresh();
         *
         * */

        swipeToRefresh();

        /**
         *
         * Communicate with database here
         * @func getAllServices();
         * @func getAllWorkers();
         *
         * */

        getAllServices();


        return view;
    }

    private void getAllWorkers()
    {
        workersArrayList.clear();
        StringRequest request = new StringRequest(Request.Method.POST, URL.GET_PROVIDERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("noData"))
                {
                    Toast.makeText(getActivity(),"No Workers available", Toast.LENGTH_SHORT).show();
                    return;
                }

                /**
                 *
                 *  Capturing response and parsing data
                 *  @func capture_response_for_workers(String response);
                 * */

                capture_response_for_workers(response);

                /**
                 *
                 *  Setting Adapter Data here
                 *  @code adapterWokersObject.UpdateRecord(ArrayList<WorkerModel> data);
                 * */

                adapterWokersObject.UpdateRecord(workersArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"There Appears to be a problem. Please try again later",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("c_id","1");

                return  parameters;
            }
        };

        requestQueue.add(request);
    }

    private void capture_response_for_workers(String response)
    {

        try
        {
            Log.d("Resp->Workers", response);

            JSONObject jsonObject = new JSONObject(response);
            JSONArray  jsonArray = jsonObject.getJSONArray("providerByRating");
            for (int i = 0;i<jsonArray.length();i++)
            {
                WorkersModel _refModel = new WorkersModel();
                JSONObject providers = jsonArray.getJSONObject(i);

                _refModel.setId(providers.getString("sp_id"));
                _refModel.setName(providers.getString("name"));
                _refModel.setPhoneNumber(providers.getString("phone_number"));
                if(!providers.isNull("avgrating"))
                {
                    Log.d("Test->if","Done");
                    _refModel.setAverage(providers.getString("avgrating"));
                }
                else
                {
                    Log.d("Test->else","Done");
                    _refModel.setAverage("0");
                }

                _refModel.setImagePath(providers.getString("image_path"));
                _refModel.setLocation(providers.getString("location"));

                workersArrayList.add(_refModel);

            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void swipeToRefresh() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);


                        /**
                         *
                         * COMMUNICATION WITH DATABASE GOES HERE
                         * @func getAllServices();
                         *
                         * */

                        getAllServices();
                    }
                },3000);
            }
        });

    }

    private void getAllServices()
    {
        categoryArrayList.clear();
        StringRequest request = new StringRequest(Request.Method.POST, URL.GET_ALL_CATEGORIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("noCategory"))
                {
                    Toast.makeText(getActivity(),"No Categories Available, Please refresh to continue",Toast.LENGTH_SHORT).show();
                    return;
                }

                /**
                 *
                 *   CAPTURING RESPONSE AND PARSING HERE
                 *   @func capture_response(String response)
                 * */

                capture_response(response);


                /**
                 *
                 *  SETTING ADAPTER DATA HERE
                 *  @code adapterObject.updateRecord(ArrayList<CategoryModel> data);
                 * */

                adapterObject.updateRecord(categoryArrayList);

                getAllWorkers();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"There Appears to be a problem. Please try again later",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

    private void capture_response(String response) {

        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("category");

            for (int i=0;i<jsonArray.length();i++)
            {
                CategoryModel _refCategoryModel = new CategoryModel();

                JSONObject category = jsonArray.getJSONObject(i);
                _refCategoryModel.setCid(category.getString("c_id"));
                _refCategoryModel.setCname(category.getString("name"));
                _refCategoryModel.setStatus(category.getString("status"));

                categoryArrayList.add(_refCategoryModel);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }


    private void initializeObjects()
    {
        categoryArrayList           = new ArrayList<>();
        workersArrayList            = new ArrayList<>();
        requestQueue                = Volley.newRequestQueue(getActivity());
        layoutManager               = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManagerWorkers        = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        adapterObject               = new CategoryAdapter(getActivity(),categoryArrayList);
        adapterWokersObject         = new WorkersAdapter(getActivity(),workersArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewWorkers.setLayoutManager(layoutManagerWorkers);
        recyclerView.setAdapter(adapterObject);
        recyclerViewWorkers.setAdapter(adapterWokersObject);
    }

    private void createView(View view)
    {
        recyclerView        = (RecyclerView) view.findViewById(R.id.categories_recyclerView_mainMenu_xml);
        recyclerViewWorkers = (RecyclerView) view.findViewById(R.id.recyclerView_topRatedWorkers_main_menu_xml);
        swipeRefreshLayout  = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh_main_menu_xml);

    }
}
