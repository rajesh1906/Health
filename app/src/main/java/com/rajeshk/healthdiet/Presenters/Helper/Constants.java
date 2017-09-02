package com.rajeshk.healthdiet.Presenters.Helper;

import android.content.Context;

import com.rajeshk.healthdiet.Presenters.End_Points.EndPoints;
import com.rajeshk.healthdiet.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ChRajeshKumar on 12-Apr-17.
 */

public class Constants {
    public static Constants constants;
    public  static String   searchkey;
    public  String getSearchKey() {
        return searchkey;
    }
    public Context context;

    public  void setSearchKey(String searchKey) {
        searchkey = searchKey;
    }


    public Constants(){

    }

    public Constants(Context context){
        this.context = context;
    }
    public static Constants getInstance(){
        if(null==constants){
            constants = new Constants();
        }
        return constants;
    }

    public String  getBaseUrl(){
        return EndPoints.getBaseURL();
    }

    public Integer[] getDashBordImages(){

        return new Integer[]{R.drawable.health_tips,R.drawable.yoga_logo,R.drawable.hair,R.drawable.men_workout,R.drawable.girls_workout,R.drawable.health_tips};
    }

    public String[] getFitndDiet_names(){
        return context.getResources().getStringArray(R.array.food_diet);
    }

    public String[] getExcerise_names(){
        return context.getResources().getStringArray(R.array.excerise);
    }

    public Integer[] getViewPagerImages(){
        return new Integer[]{R.drawable.hair, R.drawable.health_tips, R.drawable.men_workout, R.drawable.yoga_logo};
    }



}
