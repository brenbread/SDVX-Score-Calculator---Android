package brenbread.soundvoltexscorecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public EditText criticalInput;
    public EditText nearInput;
    public EditText errorInput;

    public Button calcButton;
    public Button resetButton;

    public TextView scoreOut;
    public TextView gradeOut;
    public TextView critValOut;
    public TextView nearValOut;
    public TextView noteValOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criticalInput = (EditText)findViewById(R.id.criticalInput);
        nearInput = (EditText)findViewById(R.id.nearInput);
        errorInput = (EditText)findViewById(R.id.errorInput);

        calcButton = (Button)findViewById(R.id.calcButton);
        resetButton = (Button)findViewById(R.id.resetButton);

        scoreOut = (TextView)findViewById(R.id.scoreVal);
        gradeOut = (TextView)findViewById(R.id.gradeOut);
        critValOut = (TextView)findViewById(R.id.critOut);
        nearValOut = (TextView)findViewById(R.id.nearOut);
        noteValOut = (TextView)findViewById(R.id.notesOut);

        int critIn = Integer.parseInt(criticalInput.getText().toString()); //from user input
        int nearIn = Integer.parseInt(nearInput.getText().toString());
        int errorIn = Integer.parseInt(errorInput.getText().toString());
        int totalNotes = critIn + nearIn + errorIn;
        int totalScore = 0;
        calculateScore(critIn, nearIn, errorIn, totalNotes, totalScore);

    }
    void calculateScore(int critIn, int nearIn, int errorIn, int totalScore, int totalNotes) {
        if (critIn !=0 && nearIn == 0 && errorIn ==0) {
            scoreOut.setText("1000000");
        }

        else if (critIn ==0 && nearIn == 0 && errorIn == 0) {
            scoreOut.setText("0");
        }

        else {
            totalScore = ((critIn + (nearIn / 2)) / totalNotes) * 10000000;
            scoreOut.setText(totalScore);
        }

    }





}
