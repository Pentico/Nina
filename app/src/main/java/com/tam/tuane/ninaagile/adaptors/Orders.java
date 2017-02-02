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
import com.tam.tuane.ninaagile.front_end.fragments.AddFragment;
import com.tam.tuane.ninaagile.front_end.fragments.Engine;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Orders extends Engine implements OrderItems.orderItemsInterface  {


    public Orders() {
        // Required empty public constructor
    }

    public OrderItems adapter;
    private OrderItems.orderItemsInterface  _onSubmitItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        if (view instanceof RecyclerView) {
            itemCount = 1;
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new OrderItems (_onSubmitItems, 1); /// The one is for the single addService
            adapter.set_onBtnSub(this);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onAttach(Context  context){
        super.onAttach(context);
        if (context instanceof OrderItems.orderItemsInterface) {
            _onSubmitItems = (OrderItems.orderItemsInterface) context;
        }else {
          /*  throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _onSubmitItems = null;
    }


    @Override
    public void onSubmitBtn(List<Data> Collection) {
        setCollection(Collection);
        Toast.makeText(getActivity(),"Added " + getCollection().size() + "Collection" ,
                Toast.LENGTH_SHORT).show();

        onRefresh();

        if (itemCount < 0)
            itemCount = 0;

        if (itemCount == 0){

            AddFragment fragment1 = new AddFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.items_fragment,fragment1).commit();
        }
    }

    /**
     * MakeOrder item added setup !!!
     */
    @Override
    public void onAddItem() {


        itemCount++;
    }

    @Override
    public void onRemoveItem() {
        itemCount--;

        if (itemCount < 0)
            itemCount =0;

        if (itemCount == 0){

            AddFragment fragment1 = new AddFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.items_fragment,fragment1).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
