package vn.phamthang.phamthang_hw_day07.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.phamthang.phamthang_hw_day07.Interface.IOnChangeQuantityView;
import vn.phamthang.phamthang_hw_day07.Model.Food;
import vn.phamthang.phamthang_hw_day07.Presenter.ChangeQuantityPresent;
import vn.phamthang.phamthang_hw_day07.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements IOnChangeQuantityView {
    private ArrayList<Food> mListFood;
    private Context context;
    private ChangeQuantityPresent presenter;

    public CartAdapter(ArrayList<Food> mListFood, Context context, ChangeQuantityPresent presenter) {
        this.mListFood = mListFood;
        this.context = context;
        presenter = new ChangeQuantityPresent(context, this);
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Food food = mListFood.get(position);
        holder.imgFood.setImageResource(food.getImageFood());
        holder.tvFoodName.setText(food.getFoodName());
        if (food.getWeight() >= 1) {
            holder.tvItemWeight.setText(food.getWeight() + " kg");
        } else {
            holder.tvItemWeight.setText((food.getWeight() * 1000) + " g");
        }
        holder.tvFoodPrice.setText("$" + food.getPrice());

        holder.btnPlus.setOnClickListener(v -> {
            onClickPlus(position);
        });

    }

    @Override
    public int getItemCount() {
        return mListFood != null ? mListFood.size() : 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood, btnMinus, btnPlus;
        TextView tvFoodName, tvItemWeight, tvFoodPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvItemWeight = itemView.findViewById(R.id.tvItemWeight);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);

            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }


    @Override
    public void onClickPlus(int id) {
        presenter.onChangePLus(id);
        notifyItemChanged(id);
    }

    @Override
    public void onClickMinus() {

    }
}
