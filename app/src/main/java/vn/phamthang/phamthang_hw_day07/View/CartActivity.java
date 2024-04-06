package vn.phamthang.phamthang_hw_day07.View;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.phamthang_hw_day07.Adapter.CartAdapter;
import vn.phamthang.phamthang_hw_day07.Helper.Database;
import vn.phamthang.phamthang_hw_day07.Interface.IOnChangeQuantityView;
import vn.phamthang.phamthang_hw_day07.Model.Food;
import vn.phamthang.phamthang_hw_day07.Presenter.ChangeQuantityPresent;
import vn.phamthang.phamthang_hw_day07.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private ArrayList<Food> getListFood;
    private ArrayList<Food> mListFood;
    private ChangeQuantityPresent present;

    private CartAdapter cartAdapter;
    private Database food_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initData();
        initView();
    }

    private void initData() {
////         khởi tạo dữ liệu r thêm vào database
        food_table = new Database(this);
        getListFood = new ArrayList<>();
        getListFood.add(new Food(1, R.drawable.beetles, "Beetles", 6.1));
        getListFood.add(new Food(2, R.drawable.lemon, "Lemon", 5.7));
        getListFood.add(new Food(3, R.drawable.potato, "Potato", 5.2));
        getListFood.add(new Food(4, R.drawable.cucumber, "Cucumber", 4.4));
        if(food_table == null){
            for (Food food : getListFood) {
                food_table.addFood(food);
            }
        }

        //lấy all dữ liệu set vào array list để truyền vào adapter
        mListFood = food_table.getAll();
        Log.d("OnCreate", food_table.getAll().get(2).toString());

    }

    private void initView() {
        rcv = findViewById(R.id.rcvFood);
        cartAdapter = new CartAdapter(mListFood, CartActivity.this, present);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rcv.setLayoutManager(layoutManager);

        rcv.setAdapter(cartAdapter);
    }


}