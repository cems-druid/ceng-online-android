package com.example.cengonline_v1.AnnouncementMVVM;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementHolder> {
    private List<Announcement> announcements = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AnnouncementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item,parent,false);
        return new AnnouncementHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementHolder holder, int position) {
        Announcement currentAnnouncement = announcements.get(position);
        holder.textViewText.setText(currentAnnouncement.getAnnouncement_text());
        holder.textViewDate.setText(currentAnnouncement.getDate_announcement());
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public void setAnnouncements(List<Announcement> announcements){
        this.announcements = announcements;
        notifyDataSetChanged();
    }

    public Announcement getAnnouncementAt(int position){
        return announcements.get(position);
    }


    class AnnouncementHolder extends RecyclerView.ViewHolder{
        private TextView textViewText;
        private TextView textViewDate;

        public AnnouncementHolder(View itemView){
            super(itemView);
            textViewText = itemView.findViewById(R.id.announcement_text_item);
            textViewDate = itemView.findViewById(R.id.announcement_date_item);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){

                        listener.onItemClick(announcements.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Announcement announcement);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
