package com.example.carsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button idcarmake= (Button) findViewById(R.id.idcarmake); //link the button to the first button "identify the car make" from the layout

        //set the intent for when is tapped
        idcarmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,idcarmake.class);
                startActivity(intent1);
            }
        });

        Button hint=(Button) findViewById(R.id.hints);////link the button instance "hint" to the button "hints" from the layout

        //set the intent for when is tapped
        hint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,hint.class);
                startActivity(intent);
            }
        });

        Button idcarimage= (Button) findViewById(R.id.idcarimage); //link the button to the 3rd button "identify the car image" from the layout

        //set the intent for when is tapped
        idcarimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,idcarimage.class);
                startActivity(intent);
            }
        });

        Button advanced=(Button)findViewById(R.id.advanced);

        //set the intent for when is tapped
        advanced.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,advanced.class);
                startActivity(intent);
            }
        });
    }

}