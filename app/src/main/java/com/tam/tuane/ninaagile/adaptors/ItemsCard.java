package com.tam.tuane.ninaagile.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tam.tuane.ninaagile.R;

import java.util.List;

/**
 * Created by Tuane on 2016/10/16.
 */
public class ItemsCard extends RecyclerView.Adapter<ItemsCard.ViewHolder>  {

    private List<Data> Collection;
    private itemCardInterface onItemCardInterface;

    public ItemsCard( ItemsCard.itemCardInterface onItemCardInterface){
        this.onItemCardInterface = onItemCardInterface;
    }
    @Override
    public ItemsCard.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsCard.ViewHolder holder, int position) {

        StringBuilder stringBuilder = new StringBuilder();
        holder.color_of_shoe.setText(Collection.get(position).getColorOfShoes());
        holder.type_of_shoe.setText(Collection.get(position).getTypeOfShoes());

        if (Collection.get(position).getServiceTypePolish().equals("Polish")){
            stringBuilder.append("Polish");
        }

        if (Collection.get(position).getServiceTypeWash().equals("Wash")){

            if (stringBuilder.length() > 4){
                stringBuilder.append("& Wash");
            }else{
                stringBuilder.append("Wash");
            }

        }

        holder.type_of_service.setText(stringBuilder);

    }

    @Override
    public int getItemCount() {

        if (Collection != null){
            return  Collection.size();
        }else {
            return 0;
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View view;
        public Button remove;
        public TextView type_of_shoe;
        public TextView color_of_shoe;
        public TextView type_of_service;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            remove = (Button)view.findViewById(R.id.removeItem);
            type_of_shoe = (TextView) view.findViewById(R.id.type_of_shoe);
            color_of_shoe = (TextView) view.findViewById(R.id.color_of_shoes);
            type_of_service = (TextView)view.findViewById(R.id.servicetype);
            remove.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.removeItem:
                    removeItem();
                    break;
            }
        }

        /**
         * Item to be removed !!!
         */
        private void removeItem() {

            notifyItemRemoved(getPosition());
            onItemCardInterface.onItemRemoved(getPosition());
        }


    }

    public interface itemCardInterface {
        void onItemRemoved(int item);
    }

    public void set_onitemCardInterface(itemCardInterface onItemCardInterface ){
        this.onItemCardInterface = onItemCardInterface;
    }
    public void set_Collection(List<Data> Collection){

        this.Collection = Collection;
    }
}
