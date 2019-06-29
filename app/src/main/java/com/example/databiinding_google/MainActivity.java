package com.example.databiinding_google;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databiinding_google.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        Before Data Binding
//        setContentView(R.layout.activity_main);
//
//        TextView textViewName = (TextView) findViewById(R.id.text_view_name);
//        TextView textViewOccupation = (TextView) findViewById(R.id.text_view_occupation);
//
//        textViewName.setText("Elon Musk");
//        textViewOccupation.setText("Entrepreneur, Engineer, Inventor, Investor");


//        After Data Binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DataDTO personVO = new DataDTO("Elon Musk", "Entrepreneur, Engineer, Inventor, Investor");

        binding.setPersonVO(personVO);

  //      if without model class
//        binding.textViewName.setText("Elon Musk");
//        binding.textViewOccupation.setText("Entrepreneur, Engineer, Inventor, Investor");
//
    }

}

