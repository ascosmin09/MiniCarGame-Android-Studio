package com.example.carsgame;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class idcarimage extends AppCompatActivity {

    //in the atention of the developer!!!! the number of the images has to divide by 3 in order for this activity to work!
    Integer[] images = { //add the drawables in an array so i can randomize them easier
            R.drawable._01alfa_romeo,
            R.drawable._02audi,
            R.drawable._03bentley,
            R.drawable._04bmw,
            R.drawable._05bugatti,
            R.drawable._06cadillac,
            R.drawable._07dacia,
            R.drawable._08ferrari,
            R.drawable._09fiat,
            R.drawable._10ford,
            R.drawable._11honda,
            R.drawable._12hyundai,
            R.drawable._13jaguar,
            R.drawable._14jeep,
            R.drawable._15kia,
            R.drawable._16lamborghini,
            R.drawable._17aston_martin,
            R.drawable._18maserati,
            R.drawable._19mercedes,
            R.drawable._20mini,
            R.drawable._21nissan,
            R.drawable._22peugeot,
            R.drawable._23porche,
            R.drawable._24range_rover,
            R.drawable._25renault,
            R.drawable._26rolls_royce,
            R.drawable._27skoda,
            R.drawable._28suzuki,
            R.drawable._29toyota,
            R.drawable._30volkswagen,
    };
    ImageView imageView1,imageView2,imageView3; //imageview instance for dinamically displaying the random images

    int[] duplicateflag= new int[images.length]; //array used to check if an image was displayed
    int rndN; // int value where i store the randomly generated number for the images
    int index1,index2,index3; //where i will keep the random generated number for each image displayed in order to reference it
    int rndN1;// where i randomly generate name for the correct image
    String[] names = new String[images.length]; //getting the names of the cars
    int sum=0;
    int tick=0; // i'll use this to tick if the user clicked on something before he clicked on next
    int selectstore; // i'll use this to store which image the user selected in case of orientation change
    int correctwrong; // i'll use this to store if the message correct or wrong should appear
    boolean buttonstate=true; // i'll use this to store the button state (either "next" either "game over" in case of orientation change
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcarimage);
        imageView1=(ImageView) findViewById(R.id.car1);
        imageView2=(ImageView) findViewById(R.id.car2);
        imageView3=(ImageView) findViewById(R.id.car3);
        Random rand = new Random(); //creating an instance of Random to generate random numbers when i need
        for (int i = 0; i < images.length; i++) { //get the names of the cars in an array so i can display them easier
            names[i] = getResources().getResourceEntryName(images[i]);
            names[i] = names[i].substring(3).toUpperCase();// I named my images with a numbered prefix to know when i found 30 images haha. getting rid of that prefix here
            names[i] = names[i].replace("_"," ");
        }
        TextView correctCar= (TextView) findViewById(R.id.correctCar);
        TextView result=(TextView) findViewById(R.id.result);
        Button next=(Button) findViewById(R.id.next);

        //selecting 3 random different images to display in the 3 imageviews
        rndN=rand.nextInt(images.length);
        imageView1.setImageResource(images[rndN]);
        index1=rndN;
        duplicateflag[rndN]=1;
        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
            rndN = rand.nextInt(images.length);
        imageView2.setImageResource(images[rndN]);
        index2=rndN;
        duplicateflag[rndN]=1;
        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
            rndN = rand.nextInt(images.length);
        imageView3.setImageResource(images[rndN]);
        index3=rndN;
        duplicateflag[rndN]=1;

        //selecting randomly a name that will represent the correct choice between the 3 displayed images
        rndN1=rand.nextInt(2)+1;
        if(rndN1==1) correctCar.setText(names[index1]);
        if(rndN1==2) correctCar.setText(names[index2]);
        if(rndN1==3) correctCar.setText(names[index3]);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectstore=0; //selectstore remembers which image was selected in case of orientation changes, so i can mark it as selected.
                sum=0; //sum is just a simple variable with i add all the duplicateflags. if is equal with the number of images it means that all images were displayed so is game over
                //if the button is "next" i can continue to another selection of images, if not it means is game over
                if(next.getText().equals("NEXT")){
                    //if my tick==1 it means that the user selected and image so he can continue, otherwise it will display that he must select an image(see the else)
                    if(tick-1==0){ tick--;
                        result.setText(""); correctwrong=4;
                        imageView1.setAlpha((float)1);
                        imageView2.setAlpha((float)1);
                        imageView3.setAlpha((float)1);
                        //selecting 3 random different images to display in the 3 imageviews
                        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                        rndN=rand.nextInt(images.length);
                        imageView1.setImageResource(images[rndN]);
                        index1=rndN;
                        duplicateflag[rndN]=1;
                        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                            rndN = rand.nextInt(images.length);
                        imageView2.setImageResource(images[rndN]);
                        index2=rndN;
                        duplicateflag[rndN]=1;
                        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                            rndN = rand.nextInt(images.length);
                        imageView3.setImageResource(images[rndN]);
                        index3=rndN;
                        duplicateflag[rndN]=1;
                        //checking if i displayed all images. if yes, i change the button "next" to "game over"
                        for(int i=0;i<images.length;i++)
                            sum+=duplicateflag[i];
                        if(sum==images.length){
                            next.setText("Game over");
                            buttonstate=false;
                        }

                        //selecting randomly a name that will represent the correct choice between the 3 displayed images
                        rndN1=rand.nextInt(2)+1;
                        if(rndN1==1){
                            correctCar.setText(names[index1]);
                        }
                        if(rndN1==2){
                            correctCar.setText(names[index2]);
                        }
                        if(rndN1==3){
                            correctCar.setText(names[index3]);
                        }
                    }
                    else {
                        result.setTextColor(Color.BLACK);
                        result.setText("Select an image!");
                        correctwrong=3;
                    }

                }
            }
        });

        //defining what happens when an image is clicked(selected)

        imageView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //if my tick(that no image was selected already) verifies, i change the alpha(transparancy) of the photo giving it the "clicked effect" and i set the result accordingly(correct or wrong)
                if(tick==0){
                    tick++;
                    imageView1.setAlpha((float) 0.25);
                    selectstore=1;
                    if(rndN1==1){
                        result.setText("CORRECT!");
                        result.setTextColor(Color.GREEN);
                        correctwrong=1;
                    }
                    else{
                        result.setText("WRONG!");
                        result.setTextColor(Color.RED);
                        correctwrong=2;
                    }
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //if my tick(that no image was selected already) verifies, i change the alpha(transparancy) of the photo giving it the "clicked effect" and i set the result accordingly(correct or wrong)
                if(tick==0){
                    tick++;
                    imageView2.setAlpha((float) 0.25);
                    selectstore=2;
                    if(rndN1==2){
                        result.setText("CORRECT!");
                        result.setTextColor(Color.GREEN);
                        correctwrong=1;
                    }
                    else{
                        result.setText("WRONG!");
                        result.setTextColor(Color.RED);
                        correctwrong=2;
                    }
                }

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //if my tick(that no image was selected already) verifies, i change the alpha(transparancy) of the photo giving it the "clicked effect" and i set the result accordingly(correct or wrong)
                if(tick==0){
                    tick++;
                    imageView3.setAlpha((float) 0.25);
                    selectstore=3;
                    if(rndN1==3){
                        result.setText("CORRECT!");
                        result.setTextColor(Color.GREEN);
                        correctwrong=1;
                    }
                    else{
                        result.setText("WRONG!");
                        result.setTextColor(Color.RED);
                        correctwrong=2;
                    }
                }
            }
        });
        // when orientation changes occur, i recall all the values saved before the activity was killed and set them accordingly
        if(savedInstanceState!=null){
            index1=savedInstanceState.getInt("index1");
            index2=savedInstanceState.getInt("index2");
            index3=savedInstanceState.getInt("index3");
            imageView1.setImageResource(images[index1]);
            imageView2.setImageResource(images[index2]);
            imageView3.setImageResource(images[index3]);
            duplicateflag=savedInstanceState.getIntArray("duplicateflag");
            rndN1=savedInstanceState.getInt("rndN1");
            tick=savedInstanceState.getInt("tick");
            if(rndN1==1){
                correctCar.setText(names[index1]);
            }
            if(rndN1==2){
                correctCar.setText(names[index2]);
            }
            if(rndN1==3){
                correctCar.setText(names[index3]);
            }
            selectstore=savedInstanceState.getInt("selectstore");
            if(selectstore==1)imageView1.setAlpha((float) 0.25);
            if(selectstore==2)imageView2.setAlpha((float) 0.25);
            if(selectstore==3)imageView3.setAlpha((float) 0.25);
            correctwrong=savedInstanceState.getInt("correctwrong");
            if(correctwrong==1){
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
            }
            if(correctwrong==2){
                result.setText("WRONG!");
                result.setTextColor(Color.RED);
            }
            if(correctwrong==3){
                result.setTextColor(Color.BLACK);
                result.setText("Select an image!");
            }
            if(correctwrong==4)result.setText("");
            buttonstate=savedInstanceState.getBoolean("buttonstate");
            if(buttonstate==true)next.setText("NEXT");
            if(buttonstate==false)next.setText("Game over");

        }
    }


    //saving the values for all the dynamic instances that need to be saved in case of orientation change
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("duplicateflag",duplicateflag);
        outState.putInt("index1",index1);
        outState.putInt("index2",index2);
        outState.putInt("index3",index3);
        outState.putInt("rndN1",rndN1);
        outState.putInt("tick",tick);
        outState.putInt("selectstore",selectstore);
        outState.putInt("correctwrong",correctwrong);
        outState.putBoolean("buttonstate",buttonstate);

    }
}