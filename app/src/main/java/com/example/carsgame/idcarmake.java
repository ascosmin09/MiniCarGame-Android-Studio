package com.example.carsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class idcarmake extends AppCompatActivity {
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
    ImageView imageView; //imageview instance for dinamically displaying the random images
    int[] duplicateflag= new int[images.length]; //array used to check if an image was displayed
    int rndN; // int value where i store the randomly generated number
    int sum=0;
    int buttonstate; //where i save the state of the button in case of orientation changes; 1="submit", 2="next", 3="game over"; more than that, if the button is next or game over i should display the correct car name in yellow, else it should be empty
    int correctwrong; //where i save the state of the textview in case of orientation changes; 1="correct", 2="wrong", 3=empty
    String text1; //storing the state of the button
    String text; //storing the spinner selection
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcarmake);

        imageView = (ImageView) findViewById(R.id.imageview); // linking the instance to the imageview in my layout
        Random rand = new Random(); //generating a random number
        String[] names = new String[images.length]; //getting the names of the cars

        for (int i = 0; i < images.length; i++) { //get the names of the cars in an array so i can display them easier
            names[i] = getResources().getResourceEntryName(images[i]);
            names[i] = names[i].substring(3).toUpperCase(); // i named my images with a numbered prefix to know when i found 30 images haha. getting rid of that prefix here
            names[i] = names[i].replace("_"," ");
        }
        rndN = rand.nextInt(30); //generating the random number to display the first random image
        duplicateflag[rndN]=1; // marking the first displayed image as displayed so it won't repeat
        imageView.setImageResource(images[rndN]); //displaying the random image

        //setting the drop down menu
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        TextView correct = (TextView) findViewById(R.id.correctWrong); //linking the textview instance to my textview that will display if the answer is correct or wrong in the layout
        TextView carname = (TextView) findViewById(R.id.carnameTV);//linking the textview instance to my textview that will display the name of the car in the layout
        mySpinner = (Spinner) findViewById(R.id.spinner2);// linking the spinner instance to my spinner in the layout

        Button button = (Button) findViewById(R.id.submit); //linking the button instance to my "submit/next" button in the layout

        //defining what happens when we click on the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1= (String) button.getText(); //getting the current state of the button ("submit/next"
                text = mySpinner.getSelectedItem().toString().toUpperCase(); //getting the selected item in the spinner
                // if the user taps on submit: check if the selection is correct or no, display the according message, display the name of the car, change button to "next"
                if(text1.compareTo("SUBMIT")==0){

                if (text.compareTo(names[rndN]) == 0) {
                    correct.setText("CORRECT!");
                    correct.setTextColor(Color.GREEN);
                    correctwrong=1;
                } else {
                    correct.setText("WRONG!");
                    correct.setTextColor(Color.RED);
                    correctwrong=2;
                    carname.setText(names[rndN]);
                    carname.setTextColor(Color.YELLOW);
                }

                button.setText("NEXT");
                buttonstate=2;

            }
                //if the user taps on next: change the button to submit, reset the textview for the car and "correct/wrong", mark the image as seen, check if all images were seen(if yes display game over), generate new image
                if(text1.compareTo("NEXT")==0){
                    button.setText("SUBMIT");
                    buttonstate=1;
                    carname.setText(" ");
                    correct.setText(" ");
                    correctwrong=3;
                    duplicateflag[rndN]=1;
                    rndN = rand.nextInt(images.length);
                    sum=0;
                    for(int i=0;i<duplicateflag.length;i++)
                        sum+=duplicateflag[i];
                    if(sum==duplicateflag.length) {
                        button.setText("Game is finished");
                        buttonstate=3;
                    }
                    else if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                        rndN = rand.nextInt(images.length);
                    imageView.setImageResource(images[rndN]); 

                }}
        });
        if(savedInstanceState!=null){
            mySpinner.setSelection(savedInstanceState.getInt("mySpinner"));
            duplicateflag=savedInstanceState.getIntArray("duplicateflag");
            rndN=savedInstanceState.getInt("rndN");
            imageView.setImageResource(images[rndN]);
            buttonstate=savedInstanceState.getInt("buttonstate");
            if(buttonstate==1)button.setText("SUBMIT");
            if(buttonstate==2){
                button.setText("NEXT");
            }
            if(buttonstate==3){
                button.setText("Game is finished");
            }
            correctwrong=savedInstanceState.getInt("correctwrong");
            if(correctwrong==1){
                correct.setText("CORRECT!");
                correct.setTextColor(Color.GREEN);
            }
            if(correctwrong==2){
                correct.setText("WRONG!");
                correct.setTextColor(Color.RED);
                carname.setText(names[rndN]);
                carname.setTextColor(Color.YELLOW);
            }
            if(correctwrong==3){
                correct.setText("");
            }
        }
    }

    @Override
    protected void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("buttonstate",buttonstate);
        outState.putInt("correctwrong",correctwrong);
        outState.putInt("spinner",mySpinner.getSelectedItemPosition());
        outState.putInt("rndN",rndN);
        outState.putIntArray("duplicateflag",duplicateflag);

    }
}