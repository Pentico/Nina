package com.tam.tuane.ninaagile.adaptors;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.front_end.fragments.Engine;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends Engine  implements ItemsCard.itemCardInterface{

    private ItemsCard.itemCardInterface onItemCardInterface;

    public Cart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        if (view instanceof RecyclerView) {

            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new ItemsCard(onItemCardInterface);
            adapter.set_onitemCardInterface(this);
            recyclerView.setAdapter(adapter);
        }

        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemsCard.itemCardInterface) {
            onItemCardInterface = (ItemsCard.itemCardInterface)context;
        }else {
             /*  throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onItemCardInterface = null;
    }


    @Override
    public void onItemRemoved(int item) {

        getCollection().remove(item);
        Toast.makeText(getActivity(), "Item removed" + item, Toast.LENGTH_SHORT).show();
        onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


}
