package com.nfg.devlot.dehari.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.nfg.devlot.dehari.Models.CategoryModel;
import com.nfg.devlot.dehari.Models.URL;
import com.nfg.devlot.dehari.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenuFragment extends Fragment {

    RecyclerView                recyclerView;
    LinearLayoutManager         layoutManager;
    ArrayList<CategoryModel>    categoryArrayList;
    RequestQueue                requestQueue;
    CategoryAdapter             adapterObject;
    SwipeRefreshLayout          swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        createView(view);
        initializeObjects();

        /**
         *
         *  SWIPE TO REFRESH LISTENER GOES HERE
         *  @func swipeToRefresh();
         *
         * */

        swipeToRefresh();

        /**
         *
         * COMMUNICATION WITH DATABASE GOES HERE
         * @func getAllServices();
         *
         * */

        getAllServices();

        return view;
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

    private void getAllServices() {

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
        categoryArrayList   = new ArrayList<>();
        requestQueue        = Volley.newRequestQueue(getActivity());
        layoutManager       = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        adapterObject       = new CategoryAdapter(getActivity(),categoryArrayList);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void createView(View view)
    {
        recyclerView        = (RecyclerView) view.findViewById(R.id.categories_recyclerView_mainMenu_xml);
        swipeRefreshLayout  = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh_main_menu_xml);

    }
}
