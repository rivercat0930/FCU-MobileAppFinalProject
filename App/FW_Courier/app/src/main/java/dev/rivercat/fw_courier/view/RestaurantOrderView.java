package dev.rivercat.fw_courier.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.rivercat.fw_courier.R;
import dev.rivercat.fw_courier.module.HistoryInformation;
import dev.rivercat.fw_courier.module.RestaurantInformation;

public class RestaurantOrderView extends BaseAdapter {
    private Context context;
    private ArrayList<HistoryInformation> historyInformations;

    public RestaurantOrderView(Context context, ArrayList<HistoryInformation> historyInformations) {
        this.context = context;
        this.historyInformations = historyInformations;
    }

    @Override
    public int getCount() {
        return historyInformations.size();
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

        HistoryInformation info = historyInformations.get(i);
        TextView name = (TextView)  view.findViewById(R.id.restaurant_show_tv_name);
        name.setText(info.getUsername());

        String orderStr = "";
        for (String str : info.getHistory())
            orderStr = orderStr + str + " ";
        TextView order = (TextView) view.findViewById(R.id.restaurant_show_tv_description);
        order.setText(orderStr);

        return view;
    }
}
