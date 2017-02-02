package com.tam.tuane.ninaagile.listeners;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.front_end.activity.DrawerActivity;
import com.tam.tuane.ninaagile.front_end.fragments.NinaAddress;
import com.tam.tuane.ninaagile.front_end.fragments.Notification;
import com.tam.tuane.ninaagile.front_end.fragments.UserAddress;

/**
 * Created by Tuane on 2016/09/22.
 */
public class ShoesOnItemSelectedListener extends Fragment implements AdapterView.OnItemSelectedListener
    {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
