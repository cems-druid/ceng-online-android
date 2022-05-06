package com.example.cengonline_v1.CoursesMVVM;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    private List<Course> courses = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);

        return new CourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        Course currentCourse = courses.get(position);
        holder.textViewName.setText(currentCourse.getName());
        holder.textViewInfo.setText(currentCourse.getCourse_info());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<Course> courses){
        this.courses = courses;
        notifyDataSetChanged(); //--> will be changed into a better one
    }

    public Course getCourseAt(int position){
        return courses.get(position);
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewInfo;


        public CourseHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.course_name_textview);
            textViewInfo = itemView.findViewById(R.id.course_info_textview);

            //this is how selected items get the new view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(courses.get(position));
                    }
                }
            });


        }
    }


    //Edit operations are done via clicking on the item.
    public interface OnItemClickListener{
        public void onItemClick(Course course);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
