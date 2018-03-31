package com.example.itachi.com.pbr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by akhilbatchu on 19/3/18.
 */

public class CartAdaptor extends RecyclerView.Adapter <CartAdaptor.CartViewHolder>{
    Context mctx;
    CardView card;
    List<Cart> cart;
    DatabaseReference db;
    private FirebaseAuth mAuth;


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

    class CartViewHolder extends  RecyclerView.ViewHolder implements  View.OnLongClickListener
    {
        TextView rupees,name;
        CardView card;
        public CartViewHolder(View itemView)
        {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.cardadaptor);
            rupees=itemView.findViewById(R.id.rupeecart);
            name=itemView.findViewById(R.id.namecart);
            card.setOnLongClickListener(this);
        }



        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            Cart car = cart.get(position);
            String value = car.getRs()+car.getName()+car.getId();
            showDeleteDialog(value);
            return true;
        }
        public void showDeleteDialog(final String Value)
        {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mctx);
            LayoutInflater inflater = LayoutInflater.from(mctx);
            final View dialogView = inflater.inflate(R.layout.dialog,null);
            dialogBuilder.setView(dialogView);
            final Button bt = (Button)dialogView.findViewById(R.id.button3);
            final  AlertDialog b =dialogBuilder.create();
            b.show();
           final  String value = Value;
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(value);
                    b.dismiss();
                }
            });
        }
        public Boolean delete(String value)
        {
            mAuth = FirebaseAuth.getInstance();
            Toast.makeText(mctx,"itemdeletedsuccesfully",Toast.LENGTH_LONG).show();
            db = FirebaseDatabase.getInstance().getReference("Cart").child(mAuth.getUid()).child(value);
            db.removeValue();
             return true;
        }
    }





}

