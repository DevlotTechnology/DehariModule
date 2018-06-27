package com.nfg.devlot.dehari.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfg.devlot.dehari.Activity.WorkerProfileActivity;
import com.nfg.devlot.dehari.Models.ServicesModel;
import com.nfg.devlot.dehari.Models.WorkersModel;
import com.nfg.devlot.dehari.R;
import java.util.ArrayList;

/**
 * Created by hassan on 4/9/18.
 */

public class ServiceProviderAdapter  extends RecyclerView.Adapter<ServiceProviderAdapter.ViewHolderServicesAdapter>
{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<WorkersModel> menuData;

    public ServiceProviderAdapter(Context context, ArrayList<WorkersModel> data)
    {
        this.context    = context;
        this.menuData   = data;
        inflater        = LayoutInflater.from(this.context);
    }

    @Override
    public ViewHolderServicesAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.service_providers_list_row_layout,parent,false);
        return new ViewHolderServicesAdapter(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderServicesAdapter holder, int position) {

        if(holder!=null)
        {
            holder.worker_name.setText(menuData.get(position).getName());
            holder.worker_location.setText(menuData.get(position).getLocation());
            holder.worker_rating.setRating(Float.parseFloat(menuData.get(position).getAverage()));
            holder.worker_average_rating.setText(menuData.get(position).getAverage());
            holder.worker_profession.setText(menuData.get(position).getProfession());
        }
    }

    @Override
    public int getItemCount() {
        return menuData.size();
    }


    public void UpdateRecord(ArrayList<WorkersModel> menuData)
    {
        this.menuData = menuData;
        notifyDataSetChanged();
    }

    protected class ViewHolderServicesAdapter extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView        worker_name;
        TextView        worker_profession;
        TextView        worker_location;
        TextView        worker_average_rating;
        RatingBar       worker_rating;
        ImageView       worker_profile;
        Button          worker_hire_now_button;
        RelativeLayout  parentLayout;

        protected ViewHolderServicesAdapter(View view)
        {
            super(view);

            worker_name             = (TextView)       view.findViewById(R.id.workerName_textView_serviceProviders_list_xml);
            worker_profession       = (TextView)       view.findViewById(R.id.workerType_textView_serviceProviders_list_xml);
            worker_location         = (TextView)       view.findViewById(R.id.location_textView_serviceProviders_list_xml);
            worker_average_rating   = (TextView)       view.findViewById(R.id.workerAverageRaing_textView_serviceProviider_xml);
            worker_profile          = (ImageView)      view.findViewById(R.id.workerImage_serviceProviders_list_xml);
            worker_hire_now_button  = (Button)         view.findViewById(R.id.hireNow_Button_serviceProvider_list_row);
            worker_rating           = (RatingBar)      view.findViewById(R.id.workerRating_ratingBar_serviceProviders_list_xml);
            parentLayout            = (RelativeLayout) view.findViewById(R.id.parentLayout_relativeLayout_serviceProvider_list_xml);



            /**
             *
             * SETTING RECYCLER VIEW ON ITEM CLICK LISTENER HERE
             * @code parentLayout.setOnClickListener(OnClickListener);
             * @func onClick(View v);
             *
             * */

            parentLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if(v.getId() == R.id.parentLayout_relativeLayout_serviceProvider_list_xml)
            {
                Intent intent = new Intent(context, WorkerProfileActivity.class);
                intent.putExtra("sid",menuData.get(getAdapterPosition()).getId());
                intent.putExtra("name",menuData.get(getAdapterPosition()).getName());
                intent.putExtra("phone",menuData.get(getAdapterPosition()).getPhoneNumber());
                intent.putExtra("location",menuData.get(getAdapterPosition()).getLocation());
                intent.putExtra("averagerating",menuData.get(getAdapterPosition()).getAverage());
                intent.putExtra("imagepath",menuData.get(getAdapterPosition()).getImagePath());
                intent.putExtra("token",menuData.get(getAdapterPosition()).getAccessToken());
                context.startActivity(intent);
            }
        }
    }
}
