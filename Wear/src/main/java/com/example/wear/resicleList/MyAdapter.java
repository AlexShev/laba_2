package com.example.wear.resicleList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wear.R;
import com.example.wear.Item;
import com.example.wear.Recipe;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<Recipe> items;
    private ItemClickListener mClickListener;

    public void setItems(List<Recipe> items)
    {
//        this.recipes.clear();
//        this.recipes.addAll(recipes);
        this.items = items;
        notifyDataSetChanged();
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener
    {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ItemClickListener mClickListener;

        TextView nametxt;
        // new views

        public ViewHolder(View itemView, ItemClickListener mClickListener)
        {
            super(itemView);
            this.mClickListener = mClickListener;

            nametxt = itemView.findViewById(R.id.info_text);
            // new views

            itemView.setOnClickListener(this);
        }

        public void set(Recipe item)
        {
            nametxt.setText(item.getName());
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)

                mClickListener.onItemClick(view, getBindingAdapterPosition());
        }
    }

    public MyAdapter(List<Recipe> recipes)
    {
        this.items = recipes;
        setStateRestorationPolicy(StateRestorationPolicy.ALLOW);
    }

    public MyAdapter() { }

    // convenience method for getting data at click position
    public Recipe getItem(int id) { return items.get(id); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.set(items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener)
    {
        this.mClickListener = itemClickListener;
    }
}
