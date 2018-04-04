package com.nfg.devlot.dehari.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nfg.devlot.dehari.R;

public class NotificationsMenuFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_notifications_menu, container, false);

        createView(view);
        initializeObject();

        return view;
    }

    private void initializeObject()
    {

    }

    private void createView(View view)
    {

    }
}
