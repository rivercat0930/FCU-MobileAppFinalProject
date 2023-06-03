package dev.rivercat.fw_courier.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import dev.rivercat.fw_courier.R;
import dev.rivercat.fw_courier.module.RestaurantInformation;


public class RestaurantView extends BaseAdapter{
    private Context context;
    private ArrayList<RestaurantInformation> restaurantInformations;

    public RestaurantView(Context context, ArrayList<RestaurantInformation> restaurantInformations){
        this.context = context;
        this.restaurantInformations = restaurantInformations;
    }

    @Override
    public int getCount() {
        return restaurantInformations.size();
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
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.restaurant_show,viewGroup,false);
        RestaurantInformation information = restaurantInformations.get(i);
        TextView name = (TextView) view.findViewById(R.id.restaurant_show_tv_name);
        name.setText(information.getRestaurantName());
        TextView des = view.findViewById(R.id.restaurant_show_tv_description);
        des.setText(information.getDescription());
        return view;
    }

}
