package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import dev.rivercat.fw_courier.view.Database;

public class OrderActivity extends AppCompatActivity {

    private Button btnSend;
    private ListView lv_order;
    private ListView lv_food;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        lv_food=findViewById(R.id.order_lv_item);
        lv_order=findViewById(R.id.order_lv_choosed);
        btnSend = findViewById(R.id.order_btn_send);
        database=new Database(this);
        database.open();
        database.addMeal("好吃漢堡", "健康滿滿","150");
        database.addMeal("不太好吃漢堡", "蔬菜偏多","100");
        database.addMeal("有點好吃漢堡", "肉類滿滿","200");
        database.addMeal("很好吃漢堡", "A5和牛限定","1500");
        database.addMeal("難吃漢堡", "全都生菜","20");
        database.addUorder("不太好吃漢堡", "100");
        database.addUorder("很好吃漢堡", "1500");
        database.addUorder("有點好吃漢堡", "200");
        showOrder();
        showMeal();
    View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.order_btn_send){
                    Intent intent = new Intent(OrderActivity.this, PayActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnSend.setOnClickListener(onClickListener);

    }

    private void showMeal() {
        Cursor cursor = database.getAllMeal();

        String[] from = new String[]{"name", "description", "price"};
        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                OrderActivity.this,
                R.layout.list_item_food,
                cursor,
                from,
                to,
                0
        );
        lv_food.setAdapter(adapter);
}
    private void showOrder(){
        Cursor cursor = database.getAllOrder();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                OrderActivity.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "price"},
                new int[]{android.R.id.text1,android.R.id.text2},
                0
        );
        lv_order.setAdapter(adapter);

    }

}