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

public class hint extends AppCompatActivity {
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
    int wrong=0;
    int counter=0;
    int sum=0;
    String[] dashes = new String[images.length];
    String[] alphabet={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] names = new String[images.length]; //getting the names of the cars
    String name;
    int carnametext; //1="", 2=please enter a letter, 3=please enter one letter only, 4=you already entered this letter, 5="You've made "+wrong+" mistake.",6=carname.setText(name);carname.setTextColor(Color.YELLOW);,7=empty +tex color black
    TextView carname;
    EditText letterinput;
    TextView correct;
    TextView dashesbox;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        imageView = (ImageView) findViewById(R.id.imageviewhint); // linking the instance to the imageview in my layout
        Random rand = new Random(); //generating a random number


        for (int i = 0; i < images.length; i++) { //get the names of the cars in an array so i can display them easier
            names[i] = getResources().getResourceEntryName(images[i]);
            names[i] = names[i].substring(3).toUpperCase(); // i named my images with a numbered prefix to know when i found 30 images haha. getting rid of that prefix here
        }
        if(savedInstanceState==null) {
            rndN = rand.nextInt(30); //generating the random number to display the first random image
            duplicateflag[rndN] = 1; // marking the first displayed image as displayed so it won't repeat
            name=names[rndN];
            for(int i=0;i<names[rndN].length();i++){
                if(name.charAt(i)=='_')dashes[rndN]+=" ";
                else dashes[rndN]+="_";
            }
            dashes[rndN]=dashes[rndN].substring(4);
        }

        correct = (TextView) findViewById(R.id.correctWrongHint); //linking the textview instance to my textview that will display if the answer is correct or wrong in the layout
        dashesbox = (TextView) findViewById(R.id.dashes);//linking the textview instance to my textview that will display the name of the car in the layout
        button = (Button) findViewById(R.id.submithint); //linking the button instance to my "submit/next" button in the layout
        letterinput= (EditText) findViewById((R.id.letterinput)); //linking the edit text instance to my edit text field in the layout
        carname=(TextView) findViewById(R.id.carname);//linking the textview instance to my textview that will display the name of the car in yellow

        if(savedInstanceState!=null){
            carname.setText(savedInstanceState.getCharSequence("carname"));
            correct.setText(savedInstanceState.getCharSequence("correct"));
            if(correct.getText().toString().compareTo("CORRECT!")==0)correct.setTextColor(Color.GREEN);
            if(correct.getText().toString().compareTo("WRONG!")==0)correct.setTextColor(Color.RED);
            if(correct.getText().toString().compareTo("CORRECT!")==0 || correct.getText().toString().compareTo("WRONG!")==0)carname.setTextColor(Color.YELLOW);
            else carname.setTextColor(Color.BLACK);
            button.setText(savedInstanceState.getCharSequence("button"));
            alphabet=savedInstanceState.getStringArray("alphabet");
            dashesbox.setText(dashes[rndN]);
            duplicateflag=savedInstanceState.getIntArray("duplicateflag");
            letterinput.setText(savedInstanceState.getString("letterinput"));
            rndN=savedInstanceState.getInt("rndN");
            imageView.setImageResource(images[rndN]);
            dashes=savedInstanceState.getStringArray("dashes");
            counter=savedInstanceState.getInt("counter");
            wrong=savedInstanceState.getInt("wrong");
            name=names[rndN];
        }

        imageView.setImageResource(images[rndN]); //displaying the random image
        //displayng the corresponding dashes for the first car

        dashesbox.setText(dashes[rndN]);

        //defining what happens when we click on the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carname.setText("");
                dashesbox.setText(dashes[rndN]);
                String text1 = (String) button.getText(); //getting the current state of the button ("submit/next")
                String letter = letterinput.getText().toString().toUpperCase(); //getting the current letter
                counter = 0;
                if (text1.compareTo("SUBMIT") == 0) {
                    //making sure the user entered something and not just clicked submit
                    if (letterinput.length() == 0 || letterinput.getText().toString().isEmpty() == true) {
                        carname.setText("Please enter a letter!");
                    }
                    //making sure the user entered a single letter
                    if (letterinput.getText() != null) if (letterinput.length() > 1) {
                        carname.setText("Please enter ONE letter only!");

                    }
                    //making sure the user entered a letter and not other character
                    if (letterinput.length() == 1) {
                        if (letterinput.getText().toString().toUpperCase().toCharArray()[0] < 65 || letterinput.getText().toString().toUpperCase().toCharArray()[0] > 90) {
                            carname.setText("Please enter a letter!");

                        } else {
                            //making sure that the user doesn't enter the same character twice, even if is wrong
                            for (int i = 0; i < alphabet.length; i++) {
                                char x = alphabet[i].charAt(0);
                                String y = Character.toString(x);
                                if (letterinput.getText().toString().toUpperCase().compareTo(y) == 0)
                                    alphabet[i] += "0";
                            }
                            if (alphabet[letterinput.getText().toString().toUpperCase().toCharArray()[0] - 65].length() > 2) {
                                carname.setText("You already entered this letter!");
                                //if we pass all the check for the user input we know that the user entered a valid letter so we verify if the lettered entered is in the name of the car
                            } else {
                                for (int i = 0; i < names[rndN].length(); i++) {
                                    char a = name.charAt(i);
                                    if (a == letter.charAt(0)) { //if yes, we have a counter to tell us this and we modify the dashes accordingly
                                        counter++;
                                        char[] inter = dashes[rndN].toCharArray();
                                        inter[i] = a;
                                        dashes[rndN] = String.valueOf(inter);
                                    }
                                }
                                if (counter == 0) { // if we didn't find any letter the counter never increased so we inform the user he made a mistake
                                    wrong++; // we count the mistakes
                                    if(wrong==1) carname.setText("You've made "+wrong+" mistake.");
                                    else carname.setText("You've made "+wrong+" mistakes.");
                                    letterinput.setText(null);
                                }
                                if (wrong == 3) { //if the user made 3 mistakes we end this level with the message "wrong" and offer him the "next button"
                                    correct.setText("WRONG!");
                                    correct.setTextColor(Color.RED);
                                    carname.setText(name);
                                    carname.setTextColor(Color.YELLOW);
                                    button.setText("NEXT");
                                }
                                if (counter > 0) { //if we replaced at least a letter we check if the word is complete(guessed). if yes, we display correct, the name of the car and the next button
                                    if (names[rndN].compareTo(dashes[rndN].replace(" ", "_")) == 0) {
                                        correct.setText("CORRECT!");
                                        correct.setTextColor(Color.GREEN);
                                        dashesbox.setText(dashes[rndN]);
                                        button.setText("NEXT");
                                    }
                                    dashesbox.setText(dashes[rndN]);
                                    letterinput.setText(null);
                                }
                            }
                        }
                    }
                }
                        if (text1.compareTo("NEXT") == 0) { // if the next button was pressed we reset the program to the initial state so the user starts another level
                            button.setText("SUBMIT");
                            carname.setText("");
                            correct.setText("");
                            carname.setTextColor(Color.BLACK);
                            wrong=0;
                            duplicateflag[rndN] = 1;
                            sum=0;
                            for(int i=0;i<duplicateflag.length;i++)
                                sum+=duplicateflag[i];
                            //if all images appeared once it means the game is finished , otherwise we look for images that didn't appear
                            if(sum==duplicateflag.length) button.setText("Game is finished");
                            else if(duplicateflag[rndN]==1)while(duplicateflag[rndN]==1)
                                rndN = rand.nextInt(images.length);
                            imageView.setImageResource(images[rndN]);
                            name=names[rndN];
                            for(int i=0;i<names[rndN].length();i++){
                                if(name.charAt(i)=='_')dashes[rndN]+=" ";
                                else dashes[rndN]+="_";
                            }
                            dashes[rndN]=dashes[rndN].substring(4);
                            dashesbox.setText(dashes[rndN]);

                            letterinput.setText(null);
                            //finally we reset the alphabet so for the next guess we'll be able to tell the user if he entered the same character twice
                            char letteraschar;
                            for(int i=0;i<alphabet.length;i++){
                                letteraschar=(char)(i+65);
                                alphabet[i]= Character.toString(letteraschar);
                            }
                    }

            }});
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("carname",carname.getText());
        outState.putCharSequence("correct",correct.getText());
        outState.putCharSequence("button",button.getText());
        outState.putStringArray("alphabet",alphabet);
        outState.putIntArray("duplicateflag",duplicateflag);
        outState.putString("letterinput",letterinput.getText().toString().toUpperCase());
        outState.putInt("rndN",rndN);
        outState.putStringArray("dashes",dashes);
        outState.putInt("counter",counter);
        outState.putInt("wrong",wrong);

    }
}