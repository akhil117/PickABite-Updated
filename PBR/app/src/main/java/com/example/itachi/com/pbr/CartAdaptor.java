package com.example.itachi.com.pbr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by akhilbatchu on 19/3/18.
 */

public class CartAdaptor extends RecyclerView.Adapter <CartAdaptor.CartViewHolder>{
    Context mctx;
    List<Cart> cart;

    public CartAdaptor(Context mctx, List<Cart> cart)
    {
        this.mctx = mctx;
        this.cart = cart;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.cartlist,null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Cart c = cart.get(position);
        holder.name.setText(c.getName());
        holder.rupees.setText(c.getRs());

    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    class CartViewHolder extends  RecyclerView.ViewHolder
    {
        TextView rupees,name;
        public CartViewHolder(View itemView)
        {
            super(itemView);
            rupees=itemView.findViewById(R.id.rupeecart);
            name=itemView.findViewById(R.id.namecart);
        }
    }
}

