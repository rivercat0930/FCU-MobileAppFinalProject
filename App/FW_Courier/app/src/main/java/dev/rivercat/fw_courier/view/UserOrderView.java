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

public class UserOrderView extends BaseAdapter {
    private Context context;
    private ArrayList<FoodInformation> foodInformations;

    public UserOrderView(Context context, ArrayList<FoodInformation> foodInformations) {
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
            view = LayoutInflater.from(context).inflate(R.layout.restaurant_show, viewGroup,false);

        FoodInformation currentInformation = foodInformations.get(i);
        TextView nameField = view.findViewById(R.id.restaurant_show_tv_name);
        TextView priceField = view.findViewById(R.id.restaurant_show_tv_description);

        nameField.setText(currentInformation.getFoodName());
        priceField.setText("NT$ " + currentInformation.getFoodPrice());

        return view;
    }
}
