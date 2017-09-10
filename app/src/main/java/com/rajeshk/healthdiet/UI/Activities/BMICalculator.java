package com.rajeshk.healthdiet.UI.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rajeshk.healthdiet.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 10-09-2017.
 */

public class BMICalculator extends AppCompatActivity
{
    @Bind(R.id.weight)
    EditText weight;
    @Bind(R.id.height)
    EditText height;
    @Bind(R.id.result)
    TextView result;

    @Bind(R.id.result_helth)
    TextView result_helth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmicalculator);
        ButterKnife.bind(BMICalculator.this);
    }

    @OnClick(R.id.calc)
    void calculate(){
        try {
            hideKeyboard();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(checkValidation())
        calculateBMI();
    }

    private boolean checkValidation(){
        if(weight.getText().toString().length()==0){
            Toast.makeText(this,"Please enter valid weight",Toast.LENGTH_SHORT).show();
          return  false;

        }else if(weight.getText().toString().length()!=0){
            if(Integer.parseInt(weight.getText().toString())==0){
                Toast.makeText(this,"Please enter valid weight",Toast.LENGTH_SHORT).show();
                return false;
            }else{
                 if(height.getText().toString().length()==0){
                    Toast.makeText(this,"Please enter valid height",Toast.LENGTH_SHORT).show();
                    return false;
                }else if(height.getText().toString().length()!=0){
                    if(Integer.parseInt(height.getText().toString())==0) {
                        Toast.makeText(this, "Please enter valid height", Toast.LENGTH_SHORT).show();
                        return false;
                    }else{
                        return true;
                    }

                }
            }
        }

        return true;
    }

    public void calculateBMI() {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";
        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {

            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }
        result.setText("Your BMI is: "+bmi);
        result_helth.setText("Health condition is: "+bmiLabel);

    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
