package com.tam.tuane.ninaagile.adaptors;


import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.listeners.ShoesOnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class OrderItems extends RecyclerView.Adapter<OrderItems.ViewHolder> {

    private orderItemsInterface onSubmitItems;
    private int numbOfItems;
    private String Color, typeOfShoes,service_name;
    private List<Data> Collection = new ArrayList<>();


    public OrderItems(OrderItems.orderItemsInterface onSubmitItems, int numbOfItems) {
        // Required empty public constructor
        this.onSubmitItems = onSubmitItems;
        this.numbOfItems = numbOfItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_items, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return numbOfItems;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View view;
        public Button submitBtn, removeBtn, insertBtn;
        public Spinner spinner_shoes, spinner_colors;
        public CheckBox checkWash, checkPolish;


        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            spinner_shoes = (Spinner)view.findViewById(R.id.type_shoe_spinner);
            spinner_colors = (Spinner)view.findViewById(R.id.type_color_spinner);
           /* spinner_shoes.setOnItemSelectedListener(new ShoesOnItemSelectedListener());*/
            submitBtn = (Button)view.findViewById(R.id.submit);
          /*  removeBtn = (Button)view.findViewById(R.id.remove);*/
            /*insertBtn = (Button)view.findViewById(R.id.insert);*/
            checkPolish = (CheckBox)view.findViewById(R.id.polish);
            checkWash   = (CheckBox)view.findViewById(R.id.wash);
            submitBtn.setOnClickListener(this);
      /*      removeBtn.setOnClickListener(this);*/
           /* insertBtn.setOnClickListener(this);*/

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.submit:
                    submit();
                    break;
                /*case R.id.remove:
                    remove(getPosition());
                    break;*/
               /* case R.id.insert:
                    insert();
                    break;*/
            }

        }

        /**
         *
         * Adds more service items
         */
        private void submit() {

            if (validate()) {
                Color = spinner_colors.getSelectedItem().toString();
                typeOfShoes = spinner_shoes.getSelectedItem().toString();

                Data data = new Data();
                data.setColorOfShoes(Color);
                data.setTypeOfShoes(typeOfShoes);


                {
                    data.setServiceTypeWash("null");
                    data.setServiceTypePolish("null");

                    if (checkPolish.isChecked()){
                        data.setServiceTypePolish("Polish");
                        data.setServiceType("Polish");

                    }

                    if (checkWash.isChecked()){
                        data.setServiceTypeWash("Wash");
                        data.setServiceType("Wash");
                    }

                }


                Collection.add(data);
                onSubmitItems.onSubmitBtn(Collection);
                remove(getPosition());
                Toast.makeText(view.getContext(), "Item Added to Card.", Toast.LENGTH_SHORT).show();
            }
        } //EOM

        // validating the checkboxes
        private boolean validate() {

            if (spinner_colors.getSelectedItem().toString().equals("Colour") ||
                    spinner_shoes.getSelectedItem().toString().equals("Type of shoe") ) {
                Toast.makeText(view.getContext(), "Choose type of shoe and color of shoe", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!checkWash.isChecked() && !checkPolish.isChecked())
            {
                Toast.makeText(view.getContext(), "Select Wash, Polish or Both.", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;


        }

        private void remove(int position) {


                notifyItemRemoved(position);
                numbOfItems--;
                onSubmitItems.onRemoveItem();
        }

        /**
         * Add More shoes
         */
        private void insert(){
            numbOfItems++;
            notifyItemInserted(numbOfItems );
            onSubmitItems.onAddItem();
        }


    }


    public interface orderItemsInterface {
        void onSubmitBtn (List<Data> Collection);

        void onAddItem();

        void onRemoveItem();

    }


    /**
     * Very important little niche
     * @param _onSubItems
     */
    public void set_onBtnSub(orderItemsInterface _onSubItems) {
        this.onSubmitItems =_onSubItems;

    }

}
