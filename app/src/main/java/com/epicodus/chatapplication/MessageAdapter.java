package com.epicodus.chatapplication;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Guest on 9/21/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private List<Message> list;

    public MessageAdapter(List<Message> list) {
        this.list = list;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {

        Message message = list.get(position);
        holder.textMessage.setText(message.content);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){

            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "Delete");
                menu.add(holder.getAdapterPosition(), 1, 0, "Update");

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView textMessage;

        public MessageViewHolder(View itemView){
            super(itemView);

            textMessage = itemView.findViewById(R.id.textMessage);


        }
    }

}
