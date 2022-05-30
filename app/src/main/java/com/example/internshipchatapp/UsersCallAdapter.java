package com.example.internshipchatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersCallAdapter extends RecyclerView.Adapter<UsersCallAdapter.UserCallHolder> {


    private ArrayList<UsersChat> usersChats;
    private Context context;
    private UsersCallAdapter.OnUserClickListener onUserClickListener;

    public UsersCallAdapter(ArrayList<UsersChat> usersChats, Context context, UsersCallAdapter.OnUserClickListener onUserClickListener) {
        this.usersChats = usersChats;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }


    @NonNull
    @Override
    public UserCallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_chat_holder,parent,false);
        return new UsersCallAdapter.UserCallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCallHolder holder, int position) {

        holder.username.setText(usersChats.get(position).getUsername());
        Glide.with(context).load(usersChats.get(position).getProfile_pic()).error(R.drawable.ic_account)
                .placeholder(R.drawable.ic_account).into(holder.imageView);
    }

    //interface created to open the chat section
    interface OnUserClickListener{
        void OnUserClicked(int position);
    }

    @Override
    public int getItemCount() {
        return usersChats.size();
    }

    class UserCallHolder extends RecyclerView.ViewHolder{
        TextView username;
        ImageView imageView;

        public UserCallHolder(@NonNull View itemView) {
            super(itemView);

            //userClickMethod added to adapter position
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.OnUserClicked(getAdapterPosition());
                }
            });

            username = itemView.findViewById(R.id.username_show);
            imageView = itemView.findViewById(R.id.profile_show);
    }
}
}
