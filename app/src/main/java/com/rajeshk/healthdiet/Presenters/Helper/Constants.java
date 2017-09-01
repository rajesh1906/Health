package com.rajeshk.healthdiet.Presenters.Helper;

import com.rajeshk.healthdiet.Presenters.End_Points.EndPoints;
import com.rajeshk.healthdiet.R;

import java.lang.reflect.Array;

/**
 * Created by ChRajeshKumar on 12-Apr-17.
 */

public class Constants {
    public static Constants constants;
    public  static String   searchkey;
    public  String getSearchKey() {
        return searchkey;
    }

    public  void setSearchKey(String searchKey) {
        searchkey = searchKey;
    }


    public Constants(){

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

    public Integer[] getImages(){

        return new Integer[]{R.drawable.health_tips,R.drawable.yoga_logo,R.drawable.hair,R.drawable.men_workout,R.drawable.girls_workout,R.drawable.health_tips};

    }

}
