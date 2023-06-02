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

public class UserActivity extends AppCompatActivity {

    private Button btnBack;
    private ListView lv_list;

    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnBack = findViewById(R.id.user_btn_back);
        lv_list = findViewById(R.id.user_lv_mealshistory);
        database=new Database(this);
        database.open();
        database.addHistory("天天韓式料理", "124");
        database.addHistory("微積分日式料理", "244");
        database.addHistory("物件導向餐廳", "532");
        database.addHistory("資料庫結構喵喵", "122");
        database.addHistory("早安壓總部", "457");
        showAllHistory();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId()==R.id.user_btn_back){
                    Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }

        };

        btnBack.setOnClickListener(onClickListener);
    }
    private void showAllHistory(){
        Cursor cursor = database.getHistory();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                UserActivity.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "price"},
                new int[]{android.R.id.text1,android.R.id.text2},
                0
        );
        lv_list.setAdapter(adapter);

    }
}