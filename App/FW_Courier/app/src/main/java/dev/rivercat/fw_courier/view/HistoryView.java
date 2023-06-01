package dev.rivercat.fw_courier.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.rivercat.fw_courier.R;

public class HistoryView extends BaseAdapter{
    private Context context;
    private ArrayList<String> historys;

    public  HistoryView(Context context,   ArrayList<String> historys) {
        this.context = context;
        this.historys  = historys;
    }
    @Override
    public int getCount() {
        return historys.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.list_restaurant,viewGroup,false);
        String  info = historys.get(i).replace("[","").replace("]", "");
        TextView name = (TextView)  view.findViewById(R.id.list_restaurant_name);
        name.setText(info);

        return view;
    }



}

