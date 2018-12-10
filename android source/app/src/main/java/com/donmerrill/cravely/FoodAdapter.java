package com.donmerrill.cravely;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context ctx;
    private List<Food> foodList;

    public FoodAdapter(Context ctx, List<Food> foodList){
        this.ctx = ctx;
        this.foodList = foodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.food_list, null);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position){

        Food f = foodList.get(position);

        holder.textViewFoodID.setText(String.valueOf(f.getFoodID()));
        holder.textViewFoodName.setText(f.getFoodName());
        holder.textViewOrigin.setText(f.getOrigin());
        holder.textViewDesc.setText(f.getDescription());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView textViewFoodID, textViewFoodName, textViewOrigin, textViewDesc;

        public FoodViewHolder(View itemView){
            super(itemView);

            textViewFoodID = itemView.findViewById(R.id.textViewFoodID);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewOrigin = itemView.findViewById(R.id.textViewOrigin);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
        }
    }


}
