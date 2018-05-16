package com.ajay.android.onlinecafeteriarecharge;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ItemsList extends ArrayAdapter<Items> {

    private Activity context;
    public List<Items> itemsList;


    public ItemsList(Activity context, List<Items> itemsList) {

        super(context, R.layout.item_layout, itemsList);
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();

        View listViewItem = layoutInflater.inflate(R.layout.item_layout, null, true);
        TextView itemNameTextView = listViewItem.findViewById(R.id.nameTextView);
        TextView itemPriceTextView = listViewItem.findViewById(R.id.priceTextView);
        TextView itemQuantityTextView = listViewItem.findViewById(R.id.quantityTextView);

        final Items items = itemsList.get(position);

        itemNameTextView.setText(items.getItemName().toUpperCase());
        itemPriceTextView.setText("Price: " + items.getItemPrice()+ " ₹");
        itemQuantityTextView.setText("Quantity: " + items.getItemQuantity());



        return listViewItem;
    }
}
