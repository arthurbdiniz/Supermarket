package com.arthur.supermarket;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.arthur.supermarket.Model.Product;


import java.util.ArrayList;


public class TicketAdapter extends RecyclerView.Adapter implements View.OnClickListener ,Filterable {



    private ArrayList<Product> tickets;
    private ArrayList<Product> tempTicketsText;
    private ArrayList<Product> tempTicketsCategory;
    private ArrayList<Product> tempTicketsLocation;


    private ArrayList<Product> filteredTickets;
    private ArrayList<Product> tempList;
    private FriendFilter friendFilter;
    private RecyclerView recyclerView;
    private Context context;
    private  TicketViewHolder ticketViewHolder;


    public TicketAdapter(ArrayList<Product> tickets, Context context, RecyclerView recyclerView) {
        this.tickets = tickets;
        this.filteredTickets = tickets;
        this.context = context;
        this.recyclerView = recyclerView;




        getFilter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.product_item, parent, false);

        TicketViewHolder ticketViewHolder = new TicketViewHolder(view);
        view.setOnClickListener(this);


        return ticketViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ticketViewHolder = (TicketViewHolder) holder;

        final Product product  = filteredTickets.get(position);

        ticketViewHolder.name.setText(product.getName());
        ticketViewHolder.code.setText(product.getCode());
        ticketViewHolder.price.setText(Double.toString(product.getPrice()));
        ticketViewHolder.photo.setImageResource(product.getPhoto());


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return filteredTickets.size();
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.photo:
                Snackbar.make(v, "Estamos trabalhando nisso, em breve estará disponivel!", Snackbar.LENGTH_LONG)
                       .setAction("No action", null).show();
                break;
        }
    }

    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new FriendFilter();
        }

        return friendFilter;
    }

    private class FriendFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            tempList = new ArrayList<>();
            tempTicketsText = new ArrayList<>();
            tempTicketsCategory = new ArrayList<>();
            tempTicketsLocation = new ArrayList<>();
            String search = (String) constraint;


            String[] parts = search.split(";");

            String searchField = parts[0];
            String ufField = parts[1];
            String categoryField = parts[2];


            if(constraint!=null && constraint.length()>0) {

                //Search content by text
                for (Product ticket : tickets) {
                    if (ticket.getName().toLowerCase().contains(searchField.toLowerCase())) {
                        tempTicketsText.add(ticket);
                    }
                }



                tempList = tempTicketsText;

                filterResults.count = tempList.size();
                filterResults.values = tempList;

            } else {
                filterResults.count = tickets.size();
                filterResults.values = tickets;
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredTickets = (ArrayList<Product>) results.values;
            notifyDataSetChanged();
        }
    }




}

