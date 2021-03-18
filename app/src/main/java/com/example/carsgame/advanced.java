package com.example.carsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class advanced extends AppCompatActivity {
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
    TextView score,correctCar1,correctCar2,correctCar3,correctorwrong;
    ImageView car1,car2,car3;
    EditText textinput1,textinput2,textinput3;
    Button buttonAdvanced;
    int[] duplicateflag= new int[images.length]; //array used to check if an image was displayed
    int rndN; // int value where i store the randomly generated number for the images
    String[] names = new String[images.length]; //getting the names of the cars
    int index1,index2,index3,scoreAsInt=0,wrong=0,sum,input1state=0,input2state=0,input3state=0;
    boolean mistakechecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        //linking all my instances to the layout
        score=(TextView)findViewById(R.id.score);
        correctCar1=(TextView)findViewById(R.id.correctCar1);
        correctCar2=(TextView)findViewById(R.id.correctCar2);
        correctCar3=(TextView)findViewById(R.id.correctCar3);
        correctorwrong=(TextView)findViewById(R.id.correctorwrong);
        car1=(ImageView)findViewById(R.id.car1);
        car2=(ImageView)findViewById(R.id.car2);
        car3=(ImageView)findViewById(R.id.car3);
        textinput1=(EditText)findViewById(R.id.textinput1);
        textinput2=(EditText)findViewById(R.id.textinput2);
        textinput3=(EditText)findViewById(R.id.textinput3);
        buttonAdvanced=(Button)findViewById(R.id.buttonAdvanced);
        Random rand = new Random(); //creating an instance of Random to generate random numbers when i need
        for (int i = 0; i < images.length; i++) { //get the names of the cars in an array so i can display them easier
            names[i] = getResources().getResourceEntryName(images[i]);
            names[i] = names[i].substring(3).toUpperCase();// I named my images with a numbered prefix to know when i found 30 images haha. getting rid of that prefix here
            names[i] = names[i].replace("_"," ");
        }
        //if i didn't save anything in case of orientational changes, i initialize everything
        if(savedInstanceState==null){
            //selecting 3 random different images to display in the 3 imageviews
            rndN=rand.nextInt(images.length);
            car1.setImageResource(images[rndN]);
            index1=rndN;
            duplicateflag[rndN]=1;
            if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                rndN = rand.nextInt(images.length);
            car2.setImageResource(images[rndN]);
            index2=rndN;
            duplicateflag[rndN]=1;
            if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                rndN = rand.nextInt(images.length);
            car3.setImageResource(images[rndN]);
            index3=rndN;
            duplicateflag[rndN]=1;
        }
        //if the activity was alive before, i restore it's state and all the saved variables
        else{
            score.setText(savedInstanceState.getCharSequence("score"));
            correctCar1.setText(savedInstanceState.getCharSequence("correctCar1"));
            if(correctCar1.getText().toString().compareTo(" ")!=0)correctCar1.setTextColor(Color.YELLOW);
            correctCar2.setText(savedInstanceState.getCharSequence("correctCar2"));
            if(correctCar2.getText().toString().compareTo(" ")!=0)correctCar2.setTextColor(Color.YELLOW);
            correctCar3.setText(savedInstanceState.getCharSequence("correctCar3"));
            if(correctCar3.getText().toString().compareTo(" ")!=0)correctCar3.setTextColor(Color.YELLOW);
            correctorwrong.setText(savedInstanceState.getCharSequence("correctorwrong"));
            if(correctorwrong.getText().toString().compareTo("CORRECT")==0)correctorwrong.setTextColor(Color.GREEN);
            if(correctorwrong.getText().toString().compareTo("WRONG")==0)correctorwrong.setTextColor(Color.RED);
            index1=savedInstanceState.getInt("index1");
            car1.setImageResource(images[index1]);
            index2=savedInstanceState.getInt("index2");
            car2.setImageResource(images[index2]);
            index3=savedInstanceState.getInt("index3");
            car3.setImageResource(images[index3]);
            duplicateflag=savedInstanceState.getIntArray("duplicateflag");
            textinput1.setText(savedInstanceState.getCharSequence("textinput1"));
            textinput2.setText(savedInstanceState.getCharSequence("textinput2"));
            textinput3.setText(savedInstanceState.getCharSequence("textinput3"));
            buttonAdvanced.setText(savedInstanceState.getCharSequence("buttonAdvanced"));
            scoreAsInt=savedInstanceState.getInt("scoreAsInt");
            wrong=savedInstanceState.getInt("wrong");
            input1state=savedInstanceState.getInt("input1state");
            if(input1state==1){
                textinput1.setTextColor(Color.GREEN);
                textinput1.setEnabled(false);
            }
            if(input1state==2){
                textinput1.setTextColor(Color.RED);
            }
            input2state=savedInstanceState.getInt("input1state");
            if(input2state==1){
                textinput2.setTextColor(Color.GREEN);
                textinput2.setEnabled(false);
            }
            if(input2state==2){
                textinput2.setTextColor(Color.RED);
            }
            input3state=savedInstanceState.getInt("input1state");
            if(input3state==1){
                textinput3.setTextColor(Color.GREEN);
                textinput3.setEnabled(false);
            }
            if(input3state==2){
                textinput3.setTextColor(Color.RED);
            }
        }

        buttonAdvanced.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text1 = (String) buttonAdvanced.getText(); //getting the current state of the button ("submit/next")
                if(text1.compareTo("SUBMIT")==0){
                    mistakechecker=true;
                    if(textinput1.getText().toString().isEmpty()==true || textinput2.getText().toString().isEmpty()==true || textinput3.getText().toString().isEmpty()==true){
                        correctorwrong.setTextColor(Color.BLACK);
                        correctorwrong.setText("Fill all boxes!");
                    }
                    else{ correctorwrong.setText("");
                        if(input1state!=1)if(textinput1.getText().toString().toUpperCase().compareTo(names[index1])==0){
                            textinput1.setTextColor(Color.GREEN);
                            textinput1.setEnabled(false);
                            scoreAsInt++;
                            input1state=1;
                        }
                        else {
                            textinput1.setTextColor(Color.RED);
                            mistakechecker=false;
                            input1state=2;
                        }
                        if(input2state!=1)if(textinput2.getText().toString().toUpperCase().compareTo(names[index2])==0){
                            textinput2.setTextColor(Color.GREEN);
                            textinput2.setEnabled(false);
                            scoreAsInt++;
                            input2state=1;
                        }
                        else {
                            textinput2.setTextColor(Color.RED);
                            mistakechecker=false;
                            input2state=2;
                        }
                        if(input3state!=1)if(textinput3.getText().toString().toUpperCase().compareTo(names[index3])==0){
                            textinput3.setTextColor(Color.GREEN);
                            textinput3.setEnabled(false);
                            scoreAsInt++;
                            input3state=1;
                        }
                        else  {
                            textinput3.setTextColor(Color.RED);
                            mistakechecker=false;
                            input3state=2;
                        }
                        score.setText("Score:"+scoreAsInt);
                        if(mistakechecker==false)wrong++;
                        if(wrong==3){
                            buttonAdvanced.setText("NEXT");
                            correctorwrong.setTextColor(Color.RED);
                            correctorwrong.setText("WRONG");
                            if(input1state==2){
                                correctCar1.setText(names[index1]);
                                correctCar1.setTextColor(Color.YELLOW);
                            }
                            if(input2state==2){
                                correctCar2.setText(names[index2]);
                                correctCar2.setTextColor(Color.YELLOW);
                            }
                            if(input3state==2){
                                correctCar3.setText(names[index3]);
                                correctCar3.setTextColor(Color.YELLOW);
                            }
                        }
                        if(mistakechecker==true){
                            buttonAdvanced.setText("NEXT");
                            correctorwrong.setTextColor(Color.GREEN);
                            correctorwrong.setText("CORRECT");
                        }
                    }
                }
                if(text1.compareTo("NEXT")==0){
                    sum=0;
                    for(int i=0;i<duplicateflag.length;i++)
                        sum+=duplicateflag[i];
                    //if all images appeared once it means the game is finished , otherwise we look for images that didn't appear
                    if(sum==duplicateflag.length) {
                        buttonAdvanced.setText("Game is finished");
                    }
                    else{
                        //selecting 3 random different images to display in the 3 imageviews
                        rndN=rand.nextInt(images.length);
                        car1.setImageResource(images[rndN]);
                        index1=rndN;
                        duplicateflag[rndN]=1;
                        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                            rndN = rand.nextInt(images.length);
                        car2.setImageResource(images[rndN]);
                        index2=rndN;
                        duplicateflag[rndN]=1;
                        if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                            rndN = rand.nextInt(images.length);
                        car3.setImageResource(images[rndN]);
                        index3=rndN;
                        duplicateflag[rndN]=1;
                        buttonAdvanced.setText("SUBMIT");
                        wrong=0;
                        textinput1.setEnabled(true);
                        textinput2.setEnabled(true);
                        textinput3.setEnabled(true);
                        correctCar1.setText("");
                        correctCar2.setText("");
                        correctCar3.setText("");
                        correctorwrong.setText("");
                        textinput1.setText(null);
                        textinput2.setText(null);
                        textinput3.setText(null);
                        textinput1.setTextColor(Color.BLACK);
                        textinput2.setTextColor(Color.BLACK);
                        textinput3.setTextColor(Color.BLACK);
                    }
                }

            }
        });

    }
    //saving everything i need to restore in case of orientational changes
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("score",score.getText());
        outState.putCharSequence("correctCar1",correctCar1.getText());
        outState.putCharSequence("correctCar2",correctCar2.getText());
        outState.putCharSequence("correctCar3",correctCar3.getText());
        outState.putCharSequence("correctorwrong",correctorwrong.getText());
        outState.putInt("index1",index1);
        outState.putInt("index2",index2);
        outState.putInt("index3",index3);
        outState.putIntArray("duplicateflag",duplicateflag);
        outState.putCharSequence("textinput1",textinput1.getText());
        outState.putCharSequence("textinput2",textinput1.getText());
        outState.putCharSequence("buttonAdvanced",buttonAdvanced.getText());
        outState.putCharSequence("textinput3",textinput1.getText());
        outState.putInt("input1state",input1state);
        outState.putInt("input2state",input2state);
        outState.putInt("input3state",input3state);
        outState.putInt("scoreAsInt",scoreAsInt);
        outState.putInt("wrong",wrong);
    }
}