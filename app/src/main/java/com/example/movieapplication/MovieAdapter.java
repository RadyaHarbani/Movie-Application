package com.example.movieapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context context;
    private List<ModelMovie> listMovie;
    private ContactsAdapterListener listener;

    public MovieAdapter(Context context, List<ModelMovie> listMovie, ContactsAdapterListener listener) {
        this.context = context;
        this.listMovie = listMovie;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDescription;
        public ImageView imgMovie;
        public LinearLayout layoutMovie;

        public MyViewHolder(@NonNull View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
            tvDescription = view.findViewById(R.id.tvDescription);
            imgMovie = view.findViewById(R.id.imgMovie);
            layoutMovie = view.findViewById(R.id.layoutMovie);

            layoutMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactSelected(listMovie.get(getAdapterPosition()));
                }
            });
            layoutMovie.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        final ModelMovie modelMovie = this.listMovie.get(position);
        holder.tvTitle.setText(modelMovie.getOriginal_title());
        holder.tvDescription.setText(modelMovie.getOverview());
        String posterUrl = "https://image.tmdb.org/t/p/w500" + modelMovie.getPoster_path();
        Glide.with(holder.itemView.getContext()).load(posterUrl).into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return this.listMovie.size();
    }

    public interface ContactsAdapterListener {
        void onContactSelected(ModelMovie contact);

        void onItemLongClick(int position);
    }
}

