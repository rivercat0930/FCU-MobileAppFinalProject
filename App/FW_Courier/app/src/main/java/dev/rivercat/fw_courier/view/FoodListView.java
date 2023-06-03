package dev.rivercat.fw_courier.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.rivercat.fw_courier.R;
import dev.rivercat.fw_courier.module.FoodInformation;

public class FoodListView extends BaseAdapter {
    private Context context;
    private ArrayList<FoodInformation> foodInformations;

    public FoodListView(Context context, ArrayList<FoodInformation> foodInformations) {
        this.context = context;
        this.foodInformations = foodInformations;
    }

    @Override
    public int getCount() {
        return foodInformations.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.order_show, viewGroup, false);

        FoodInformation foodInformation = foodInformations.get(i);
        TextView nameField = view.findViewById(R.id.list_restaurant_name);
        TextView descriptionField = view.findViewById(R.id.order_show_tv_description);
        TextView priceField = view.findViewById(R.id.list_restaurant_tv_price);

        nameField.setText(foodInformation.getFoodName());
        descriptionField.setText(foodInformation.getFoodDescription());
        priceField.setText("NT$ " + foodInformation.getFoodPrice());

        return view;
    }
}
