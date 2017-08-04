package com.app.interview;

/**
 * Created by IRFAN on 8/4/2017.
 */

public class Student {
    String name;
    String level;
    String score;
    String imageurl;


   /* public Student(String name,String level,String score,String imageurl){
        this.name=name;
        this.level=level;
        this.score=score;
        this.imageurl=imageurl;

    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

