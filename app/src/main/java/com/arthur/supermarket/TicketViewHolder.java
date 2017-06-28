package com.arthur.supermarket;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener    {

    final TextView code;
    final TextView name;
    final TextView price;
    final ImageView photo;

    public TicketViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        code = (TextView) view.findViewById(R.id.code);
        name = (TextView) view.findViewById(R.id.name);
        price = (TextView) view.findViewById(R.id.price);
        photo = (ImageView) view.findViewById(R.id.photo);
    }
    @Override
    public void onClick(View view) {

    }


}
