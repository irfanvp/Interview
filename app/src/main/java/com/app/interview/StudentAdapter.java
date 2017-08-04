package com.app.interview;

/**
 * Created by IRFAN on 8/4/2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private List<Student> studentList;
    Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, score;
        ImageView iv_profile;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_name);
            score = (TextView) view.findViewById(R.id.tv_score);
            iv_profile= (ImageView) view.findViewById(R.id.iv_profile);

        }
    }


    public StudentAdapter(List<Student> studentList,Context ctx) {
        this.studentList = studentList;
        this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.levelonerow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.name.setText(student.getName());
        holder.score.setText(student.getScore());
        //holder.iv_profile.setText(movie.getYear());

        Glide.with(ctx).load(student.getImageurl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_profile);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}