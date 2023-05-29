package dev.rivercat.fw_courier.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;

import dev.rivercat.fw_courier.R;
import dev.rivercat.fw_courier.module.RestaurantInformation;
import dev.rivercat.fw_courier.module.Restaurantformation;

public class RestaurantView {

  public class RestaurantView extends BaseAdapter{
    private Context context;
    private ArrayList<RestaurantInformation>restaurantsInformations){

  public RestaurantView(Context context, ArrayList<RestaurantInformation> restaurantsInformations) {
    this.context = context;
    this.restaurantsInformations = restaurantsInformations;
  }
  @Override
    public int getCount() {
      return restaurantsInformations.size();
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
    if (view == null) {
      view = LayoutInflater.from(context).inflate(R.layout.restaurant_show, viewGroup, false);
      RestaurantInformation information = restaurantsInformations.get(i);
      TextView name = (TextView) view.findViewById(R.id.restaurant_show_tv_name);
      name.setText(information.getRestaurantName());

      TextView des =view.findViewById(R.id.restaurant_show_tv_description);
      des.setText(information.getDescription());

      return View;
    }
  }
}
