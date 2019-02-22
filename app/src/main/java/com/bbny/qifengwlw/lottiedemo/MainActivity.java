package com.bbny.qifengwlw.lottiedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView tv_json_name;
    LottieAnimationView img_anim;
    Button btn_1, btn_2;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_json_name = findViewById(R.id.tv_json_name);
        img_anim = findViewById(R.id.img_anim);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);

        img_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!img_anim.isAnimating()) {
                    img_anim.playAnimation();
                }
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos -= 1;
                if (pos>0) {
                }else{
                    pos = fileNames.size()-1;
                }
                setAnim(fileNames.get(pos));
                Log.d("pos", "onClick: pos" + pos);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos += 1;
                if (fileNames.size() > pos) {
                }else{
                    pos = 0;
                }
                setAnim(fileNames.get(pos));
                Log.d("pos", "onClick: pos" + pos);
            }
        });
        getAssestsFiles();
        setAnim(fileNames.get(pos));
    }

    ArrayList<String> fileNames = new ArrayList<String>();

    public void getAssestsFiles() {
        Pattern pattern = Pattern.compile("[a-zA-Z]+(\\.json)$");
        try {
            String[] list = getAssets().list("");
            for (String fileName : list) {
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.matches()) {
                    fileNames.add(fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setAnim(String name) {
        if (img_anim.isAnimating()){
            img_anim.cancelAnimation();
        }
        try {
            tv_json_name.setText("JsonName:"+name+"   ("+(pos+1)+"/"+fileNames.size()+")");
            Log.d("pos", "JsonName: "+name);
            img_anim.setAnimation(name);
            img_anim.loop(false);
            img_anim.playAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
