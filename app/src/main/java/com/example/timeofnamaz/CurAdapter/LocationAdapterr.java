package com.example.timeofnamaz.CurAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeofnamaz.Models.Place;
import com.example.timeofnamaz.R;
import com.example.timeofnamaz.Readingdata1;
import com.example.timeofnamaz.Readingdata10;
import com.example.timeofnamaz.Readingdata2;
import com.example.timeofnamaz.Readingdata3;
import com.example.timeofnamaz.Readingdata4;
import com.example.timeofnamaz.Readingdata5;
import com.example.timeofnamaz.Readingdata6;
import com.example.timeofnamaz.Readingdata7;
import com.example.timeofnamaz.Readingdata8;
import com.example.timeofnamaz.Readingdata9;

import java.util.ArrayList;

public class LocationAdapterr extends RecyclerView.Adapter<LocationAdapterr.viewHolder> {

    ArrayList<Place> places;
    Context context;

    public LocationAdapterr(ArrayList<Place> places, Context context) {
        this.places = places;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.locationsample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Place model = places.get(position);

        holder.textView.setText(model.getName());

        switch (model.getN()) {
            case 1:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text one is clicked " + model.getN(), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata1.class));

                    }
                });
                break;
            case 2:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text two is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata2.class));
                    }

                });
                break;
            case 3:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text three is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata3.class));
                    }
                });
                break;
            case 4:
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text four is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata4.class));

                    }
                });
                break;
            case 5:
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text five is clicked ", Toast.LENGTH_SHORT).show();

                        context.startActivity(new Intent(context, Readingdata5.class));

                    }
                });
                break;
            case 6:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text six is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata6.class));
                    }
                });
                break;
            case 7:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text seven is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata7.class));
                    }
                });
                break;
            case 8:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text eight is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata8.class));
                    }
                });
                break;
            case 9:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text nine is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata9.class));
                    }
                });
                break;
            case 10:


                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "text ten is clicked ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Readingdata10.class));
                    }
                });
                break;


            default:

        }

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);

        }
    }
}
