package com.example.itachi.com.pbr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ITACHI on 02-03-2018.
 */
public class ListViewAdaptor extends RecyclerView.Adapter<ListViewAdaptor.MyViewHolder>{
    private LayoutInflater inflater;
    ArrayList<Cakes> info;
    private Context context;

    public ListViewAdaptor(Context context, ArrayList<Cakes> info)
    {
        inflater = LayoutInflater.from(context);
        this.info = info;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(info.get(position).getId());
        holder.text2.setText(info.get(position).getName());
        Picasso.with(context).load(info.get(position).getimage()).placeholder(R.drawable.progress_animation)
                .resize(900, 900).into(holder.img);
        holder.text3.setText("₹ "+info.get(position).getRS());

    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text,text2,text3;
        ImageView img;
        private final Context context;
        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            CardView card = (CardView) itemView.findViewById(R.id.card);
            text = (TextView) itemView.findViewById(R.id.itemtext1);
            text3=(TextView)itemView.findViewById(R.id.Rs);
            text2 = (TextView) itemView.findViewById(R.id.itemtext2);
            img = (ImageView) itemView.findViewById(R.id.itemimage);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Cakes d = info.get(position);
            Intent intent = new Intent(context,Checkout.class);
            String n = d.getName();
            String r = d.getRS();
            String i = d.getimage();
            String id = d.getId();
            intent.putExtra("ID",id);
            intent.putExtra("name",n);
            intent.putExtra("cost",r);
            intent.putExtra("image",i);
            context.startActivity(intent);
        }
    }

}
