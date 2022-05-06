package com.example.cengonline_v1.PostsMVVM;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private List<Post> posts = new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override
    public PostAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostHolder holder, int position) {

        Post currentPost = posts.get(position);
        holder.textViewPostText.setText(currentPost.getPost_text());
        holder.textViewPostAuth.setText(currentPost.getPost_author());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
        notifyDataSetChanged();
    }

    public Post getPostAt(int position){
        return posts.get(position);
    }


    class PostHolder extends RecyclerView.ViewHolder{
        private TextView textViewPostText;
        private TextView textViewPostAuth;

        public PostHolder(View itemView){
            super(itemView);

            //layout operations
            textViewPostText = itemView.findViewById(R.id.post_text_textview);
            textViewPostAuth = itemView.findViewById(R.id.post_auth_textview);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(posts.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Post post);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
