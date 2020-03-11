package com.example.huriyah.whatkpasswords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b10;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> randomWords = new ArrayList<>();

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("words_innit.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {

                randomWords.add(mLine);

                //process line

            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }



        int x = 0;
        int i = 5;
        ArrayList<String> pickedList = new ArrayList<>();
        while(x<10) {
            String randomElement = randomWords.get(i);

            pickedList.add(randomElement);
            x ++;
            i = i + 5;

        }
        System.out.println(pickedList);


        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        buttons.add(b10);

        Random r = new Random();

        ArrayList<String> colours = new ArrayList<>();
        //colours.add("#FF8333");
        //colours.add("#E3FF33");
        //colours.add("#FEC8D8");
        colours.add("#AEC6CF");
        colours.add("#FFCCF9");
        colours.add("#D5AAFF");
        colours.add("#B5B9FF");
        colours.add("#85E3FF");



        System.out.println("PICKED LIST" + pickedList);


        for(int b = 0; b < buttons.size(); b++){
            Button but = buttons.get(b);
            String hannah = pickedList.get(r.nextInt(pickedList.size()));
            int generatedRandomNum = r.nextInt(colours.size());
            but.setBackgroundColor(Color.parseColor(colours.get(generatedRandomNum)));

            but.setText(hannah);
            pickedList.remove(hannah);
        }


        final ArrayList<String> password_entered = new ArrayList<>();



        for (final Button button : buttons){



            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if(button.getText().equals("price")) {
                        password_entered.clear();
                        password_entered.add(button.getText().toString());
                        count++;
                        if (count == 3){
                            Toast.makeText(getApplicationContext(),"You have entered an incorrect password.", Toast.LENGTH_SHORT).show();
                            count = 0;
                        }
                        //Toast.makeText(getApplicationContext(), "You have entered a correct password.", Toast.LENGTH_SHORT).show();
                    }

                    else if(button.getText().equals("research")){
                        //password_entered.add(button.getText().toString());
                        if(password_entered.size()==1) {
                            if (password_entered.get(0).equals("price")) {
                                password_entered.add(button.getText().toString());
                                count++;
                                if (count == 3) {
                                    Toast.makeText(getApplicationContext(), "You have entered an incorrect password.", Toast.LENGTH_SHORT).show();
                                    count = 0;
                                    password_entered.clear();
                                }

                            }
                        }


                        else{
                            password_entered.clear();
                            count++;
                            if (count == 3){
                                Toast.makeText(getApplicationContext(),"You have entered an incorrect password.", Toast.LENGTH_SHORT).show();
                                count = 0;
                            }
                        }
                        //Toast.makeText(getApplicationContext(),"You have entered a correct password.", Toast.LENGTH_SHORT).show();
                    }

                    else if(button.getText().equals("links")){


                        if(password_entered.size()==2) {
                            if (password_entered.get(0).equals("price")) {
                                if (password_entered.get(1).equals("research")) {
                                    password_entered.add(button.getText().toString());
                                    count = 0;
                                    Toast.makeText(getApplicationContext(), "You have entered a correct password.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoggedInActivity.class);
                                    startActivity(i);



                                } else {
                                    password_entered.clear();
                                    count++;
                                    if (count == 3) {
                                        Toast.makeText(getApplicationContext(), "You have entered an incorrect password.", Toast.LENGTH_SHORT).show();
                                        count = 0;
                                    }

                                }
                            }
                        }
                        else{
                            password_entered.clear();
                            count++;
                            if (count == 3){
                                Toast.makeText(getApplicationContext(),"You have entered an incorrect password.", Toast.LENGTH_SHORT).show();
                                count = 0;
                            }

                        }
                    }

                    else {
                        count++;
                        if (count == 3){
                            Toast.makeText(getApplicationContext(),"You have entered an incorrect password.", Toast.LENGTH_SHORT).show();
                            count = 0;
                        }

                    }
                }

            });


        }



    }
}
