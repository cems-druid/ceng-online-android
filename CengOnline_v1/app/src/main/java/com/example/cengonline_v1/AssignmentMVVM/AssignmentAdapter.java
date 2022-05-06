package com.example.cengonline_v1.AssignmentMVVM;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline_v1.R;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentHolder> {
    private List<Assignment> assignments = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AssignmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentHolder holder, int position) {
        Assignment currentAssignment= assignments.get(position);
        holder.textViewAssgnName.setText(currentAssignment.getName_assignment());
        holder.textViewRelCourse.setText(currentAssignment.getName_of_course());
        holder.textViewDueDate.setText(currentAssignment.getDue_date());
        holder.textViewHomework.setText(currentAssignment.getText_of_submission());
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public void setAssignments(List<Assignment> assignments){
        this.assignments = assignments;
        notifyDataSetChanged();
    }

    public Assignment getAssignmentAt(int position){
        return assignments.get(position);
    }

    class AssignmentHolder extends RecyclerView.ViewHolder{
        private TextView textViewAssgnName;
        private TextView textViewRelCourse;
        private TextView textViewDueDate;
        private TextView textViewHomework;

        public AssignmentHolder(View itemView){
            super(itemView);
            textViewAssgnName = itemView.findViewById(R.id.assignment_name_textview);
            textViewRelCourse = itemView.findViewById(R.id.assignment_nameofcourse_textview);
            textViewDueDate = itemView.findViewById(R.id.assignment_duedate_textview);
            textViewHomework = itemView.findViewById(R.id.assignment_homework_textview);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(assignments.get(position));
                    }
                }
                });
        }
    }


    public interface OnItemClickListener{
        public void onItemClick(Assignment assignment);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
this.listener = listener;
    }
}
