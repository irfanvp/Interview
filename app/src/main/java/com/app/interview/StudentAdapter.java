package com.app.interview;

/**
 * Created by IRFAN on 8/4/2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view ;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levelonerow, parent, false);

             /*   view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {


                    @Override
                    public boolean onPreDraw() {
                        final int type = viewType;
                        final ViewGroup.LayoutParams lp = view.getLayoutParams();

                            switch (type) {
                                case 1:

                                    lp.width = view.getWidth() / 2;
                                    break;
                            }
                            view.setLayoutParams(lp);
                            final RecyclerView.LayoutManager lm =
                                    (RecyclerView.LayoutManager) ((RecyclerView) parent).getLayoutManager();
                        //   lm.invalidateSpanAssignments();

                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }

                });*/


                return new MyViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leveltwo, parent, false);
                return new MyViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levelthree, parent, false);
                return new MyViewHolder(view);
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levelfour, parent, false);
                return new MyViewHolder(view);
            case 5:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levelfive, parent, false);
                return new MyViewHolder(view);
        }
        return null;

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

    @Override
    public int getItemViewType(int position) {
        if (studentList != null) {
            Student object = studentList.get(position);
            if (object != null) {
                return Integer.parseInt(object.getLevel());
            }
        }
        return 0;
    }
}