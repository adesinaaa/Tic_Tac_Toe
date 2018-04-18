package com.example.ad.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPlayerActivity extends Activity {
    private int[][] table;
    private boolean xMove;

    private static final String X_PREF = "xpref";
    private static final String O_PREF = "opref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        TextView oScore = findViewById(R.id.o_score);
        TextView xScore = findViewById(R.id.x_score);
        final TableLayout threeTable = findViewById(R.id.three_table_layout);
        final TableLayout fiveTable = findViewById(R.id.five_table_layout);
        radioButtonFive =  findViewById(R.id.five_by_five);
        radioButtonThree = findViewById(R.id.three_by_three);
        radioButtonThree.setChecked(true);

        try {
            SharedPreferences oPrefs = getPreferences(MODE_PRIVATE);
            SharedPreferences xPrefs = getPreferences(MODE_PRIVATE);
            xScore.setText("X Score = "+ xPrefs.getInt(X_PREF,0));
            oScore.setText("O Score = "+ oPrefs.getInt(O_PREF,0));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (radioButtonFive.isChecked()) {
            table = new int[5][5];
            fiveTable.setVisibility(View.VISIBLE);
            threeTable.setVisibility(View.GONE);
        }else {
            table = new int[3][3];
            fiveTable.setVisibility(View.GONE);
            threeTable.setVisibility(View.VISIBLE);

        }
        try {
            radioButtonFive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (radioButtonFive.isChecked()) {
                        table = new int[5][5];
                        fiveTable.setVisibility(View.VISIBLE);
                        threeTable.setVisibility(View.GONE);
                    }else {
                        table = new int[3][3];
                        fiveTable.setVisibility(View.GONE);
                        threeTable.setVisibility(View.VISIBLE);

                    }
                }
            });
            radioButtonThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (radioButtonFive.isChecked()) {
                        table = new int[5][5];
                        fiveTable.setVisibility(View.VISIBLE);
                        threeTable.setVisibility(View.GONE);
                    }else {
                        table = new int[3][3];
                        fiveTable.setVisibility(View.GONE);
                        threeTable.setVisibility(View.VISIBLE);

                    }
                }
            });

        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        xMove=true;
    }
    RadioButton radioButtonFive;
    RadioButton radioButtonThree;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.normal, menu);
        return true;
    }
    public void makeMove(View v)
    {
        try {
            if (radioButtonThree.isChecked()) {
                threeByThree(v);
            } else {
                fiveByFive(v);
            }
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void fiveByFive(View v) {
        int x = 0;
        int y = 0;

        int id = v.getId();
        switch(id)
        {
            case R.id.button500: break;
            case R.id.button501:
                x = 0;
                y = 1;
                break;
            case R.id.button502:
                x = 0;
                y = 2;
                break;
            case R.id.button503:
                x = 0;
                y = 3;
                break;
            case R.id.button504:
                x = 0;
                y = 4;
                break;

            case R.id.button510:
                x = 1;
                y = 0;
                break;
            case R.id.button511:
                x = 1;
                y = 1;
                break;
            case R.id.button512:
                x = 1;
                y = 2;
                break;
            case R.id.button513:
                x = 1;
                y = 3;
                break;
            case R.id.button514:
                x = 1;
                y = 4;
                break;

            case R.id.button520:
                x = 2;
                y = 0;
                break;
            case R.id.button521:
                x = 2;
                y = 1;
                break;
            case R.id.button522:
                x = 2;
                y = 2;
                break;
            case R.id.button523:
                x = 2;
                y = 3;
                break;
            case R.id.button524:
                x = 2;
                y = 4;
                break;

            case R.id.button530:
                x = 3;
                y = 0;
                break;
            case R.id.button531:
                x = 3;
                y = 1;
                break;
            case R.id.button532:
                x = 3;
                y = 2;
                break;
            case R.id.button533:
                x = 3;
                y = 3;
                break;
            case R.id.button534:
                x = 3;
                y = 4;
                break;

            case R.id.button540:
                x = 4;
                y = 0;
                break;
            case R.id.button541:
                x = 4;
                y = 1;
                break;
            case R.id.button542:
                x = 4;
                y = 2;
                break;
            case R.id.button543:
                x = 4;
                y = 3;
                break;
            case R.id.button544:
                x = 4;
                y = 4;
                break;

        }

        if(table[x][y] != 0)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("This cell is not empty!");
            dlgAlert.setTitle("Error");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return;
        }
        Button btn = findViewById(id);
        TextView label = findViewById(R.id.moveTextView);
        if(xMove)
        {
            btn.setText("X");
            table[x][y] = 2;
            label.setText("O move");
            xMove = false;
        }
        else
        {
            btn.setText("O");
            table[x][y] = 1;
            xMove = true;

            label.setText("X move");
        }
        checkResult5();
    }

    private void threeByThree(View v) {
        int x = 0;
        int y = 0;

        int id = v.getId();
        switch(id)
        {
            case R.id.button00: break;
            case R.id.button01: x = 0;
                y = 1;
                break;
            case R.id.button02: x = 0;
                y = 2;
                break;
            case R.id.button10: x = 1;
                y = 0;
                break;
            case R.id.button11: x = 1;
                y = 1;
                break;
            case R.id.button12: x = 1;
                y = 2;
                break;
            case R.id.button20: x = 2;
                y = 0;
                break;
            case R.id.button21: x = 2;
                y = 1;
                break;
            case R.id.button22: x = 2;
                y = 2;
                break;
        }

        if(table[x][y] != 0)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("This cell is not empty!");
            dlgAlert.setTitle("Error");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return;
        }
        Button btn = (Button) findViewById(id);
        TextView label = (TextView) findViewById(R.id.moveTextView);
        if(xMove)
        {
            btn.setText("X");
            table[x][y] = 2;
            label.setText("O move");
            xMove = false;
        }
        else
        {
            btn.setText("O");
            table[x][y] = 1;
            xMove = true;

            label.setText("X move");
        }
        checkResult();
    }

    private void checkResult() {
        SharedPreferences oPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences xPrefs = getPreferences(MODE_PRIVATE);
        boolean empty = false;
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        for (int i = 0; i != 3; ++i)
        {
            for (int j = 0; j != 3; ++j)
            {
                if (table[i][j]==0)
                {
                    empty = true;
                    break;
                }
            }
        }
        if (!empty)
        {
            dlgAlert.setMessage("Draw!");
            dlgAlert.setTitle("Draw");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();

                        }
                    });
            dlgAlert.create().show();

        }

        SharedPreferences.Editor oEditor = oPrefs.edit();
        SharedPreferences.Editor xEditor = xPrefs.edit();
        //check horizontal lines
        for (int i = 0; i != 3; ++i)
        {
            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1)
            {
                int oScore = oPrefs.getInt(O_PREF, 0);
                oEditor.putInt(O_PREF,oScore+1);
                oEditor.apply();
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2)
            {
                int xScore = xPrefs.getInt(X_PREF, 0);
                xEditor.putInt(X_PREF,xScore+1);
                xEditor.apply();
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check vertical lines
        for (int i = 0; i != 3; ++i)
        {
            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1)
            {
                int oScore = oPrefs.getInt(O_PREF, 0);
                oEditor.putInt(O_PREF,oScore+1);
                oEditor.apply();
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2)
            {
                int xScore = xPrefs.getInt(X_PREF, 0);
                xEditor.putInt(X_PREF,xScore+1);
                xEditor.apply();
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1)
        {
            int oScore = oPrefs.getInt(O_PREF, 0);
            oEditor.putInt(O_PREF,oScore+1);
            oEditor.apply();
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2)
        {
            int xScore = xPrefs.getInt(X_PREF, 0);
            xEditor.putInt(X_PREF,xScore+1);
            xEditor.apply();
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1)
        {
            int oScore = oPrefs.getInt(O_PREF, 0);
            oEditor.putInt(O_PREF,oScore+1);
            oEditor.apply();
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2)
        {
            int xScore = xPrefs.getInt(X_PREF, 0);
            xEditor.putInt(X_PREF,xScore+1);
            xEditor.apply();
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
    }

    private void checkResult5() {
        SharedPreferences oPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences xPrefs = getPreferences(MODE_PRIVATE);
        boolean empty = false;
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        int xScore = xPrefs.getInt(X_PREF, 0);
        int oScore = oPrefs.getInt(O_PREF, 0);
        SharedPreferences.Editor oEditor = oPrefs.edit();
        SharedPreferences.Editor xEditor = xPrefs.edit();

        for (int i = 0; i != 5; ++i)
        {
            for (int j = 0; j != 5; ++j)
            {
                if (table[i][j]==0)
                {
                    empty = true;
                    break;
                }
            }
        }
        if (!empty)
        {
            dlgAlert.setMessage("Draw!");
            dlgAlert.setTitle("Draw");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();

                        }
                    });
            dlgAlert.create().show();

        }
        //check horizontal lines
        for (int i = 0; i != 5; ++i)
        {
            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1 && table[i][3] ==1 && table[i][4] ==1)
            {
                oEditor.putInt(O_PREF,oScore+1);
                oEditor.apply();
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2  && table[i][3] ==2 && table[i][4] ==2)
            {
                xEditor.putInt(X_PREF,xScore+1);
                xEditor.apply();
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check vertical lines
        for (int i = 0; i != 5; ++i)
        {
            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1  && table[3][i] ==1 && table[4][i] ==1)
            {
                oEditor.putInt(O_PREF,oScore+1);
                oEditor.apply();
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2  && table[3][i] == 2 && table[4][i] == 2)
            {
                xEditor.putInt(X_PREF,xScore+1);
                xEditor.apply();
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1  && table[3][3] ==1 && table[4][4] ==1)
        {
            oEditor.putInt(O_PREF,oScore+1);
            oEditor.apply();
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2  && table[3][3] == 2 && table[4][4] ==2)
        {
            xEditor.putInt(X_PREF,xScore+1);
            xEditor.apply();
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1)
        {
            oEditor.putInt(O_PREF,oScore+1);
            oEditor.apply();
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2)
        {
            xEditor.putInt(X_PREF,xScore+1);
            xEditor.apply();
            dlgAlert.setTitle("congratulations");
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }


    }

    public void reset(View view) {
        TextView oScore = findViewById(R.id.o_score);
        TextView xScore = findViewById(R.id.x_score);
        SharedPreferences oPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences xPrefs = getPreferences(MODE_PRIVATE);
        oPrefs.edit().putInt(O_PREF,0).apply();
        xPrefs.edit().putInt(X_PREF,0).apply();
        oScore.setText("O Score = "+ oPrefs.getInt(O_PREF, 0));
        xScore.setText("X Score = "+ xPrefs.getInt(X_PREF, 0));
    }

//    private void reset() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Button button = findViewById(getResources().getIdentifier("button"+i+j,
//                        "button",TwoPlayerActivity.this.getPackageName()));
//                button.setText(R.string.empty);
//            }
//        }
//    }
}