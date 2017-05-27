package brenbread.soundvoltexscorecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        criticalInput = (EditText) findViewById(R.id.criticalInput);
        nearInput = (EditText) findViewById(R.id.nearInput);
        errorInput = (EditText) findViewById(R.id.errorInput);
        scoreOut = (TextView) findViewById(R.id.scoreVal);
        gradeOut = (TextView) findViewById(R.id.gradeOut);
        critValOut = (TextView) findViewById(R.id.critOut);
        nearValOut = (TextView) findViewById(R.id.nearOut);
        noteValOut = (TextView) findViewById(R.id.notesOut);


        calcButton = (Button) findViewById(R.id.calcButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        calcButton.setOnClickListener(MainActivity.this);

    }


    @Override
    public void onClick(View v) {
        String critIn = criticalInput.getText().toString(); //user input
        String nearIn = nearInput.getText().toString();
        String errorIn = errorInput.getText().toString();
        String grade = "";

        if (critIn.equals("") || nearIn.equals("") || errorIn.equals("")) //checks if input is empty
        {
            scoreOut.setText("Input each field.");
        } else { //there is input


            int critFinal = Integer.parseInt(critIn); //parse string to int
            int nearFinal = Integer.parseInt(nearIn);
            int errorFinal = Integer.parseInt(errorIn);

            int totalNotes = critFinal + nearFinal + errorFinal;
            double critVal = 10000000 / totalNotes;
            double nearVal = critVal / 2;
            double totalScore = (critFinal * critVal) + (nearFinal * nearVal);

            //score calculation
            if (critFinal != 0 && nearFinal == 0 && errorFinal == 0) {
                totalScore = 10000000;
                int scoreINT = (int) totalScore;
                String scoreFinal = Integer.toString(scoreINT);
                scoreOut.setText(scoreFinal);
            } else if (critFinal == 0 && nearFinal == 0 && errorFinal == 0) {
                totalScore = 0;
                int scoreINT = (int) totalScore;
                String scoreFinal = Integer.toString(scoreINT);
                scoreOut.setText(scoreFinal);
            } else {
                int scoreINT = (int) totalScore;
                String scoreFinal = Integer.toString(scoreINT);
                scoreOut.setText(scoreFinal);
            }

            grade = gradeCalc(totalScore);

            //convert int to string again
            int critINT = (int) critVal;
            int nearINT = (int) nearVal;


            String critValFinal = Integer.toString(critINT);
            String nearValFinal = Integer.toString(nearINT);
            String totalNotesFinal = Integer.toString(totalNotes);

            //app output

            critValOut.setText(critValFinal);
            nearValOut.setText(nearValFinal);
            noteValOut.setText(totalNotesFinal);
            gradeOut.setText(grade);

        }

    }

    String gradeCalc(double score) {
        String grade = "";
        if (score >= 9900000) {
            grade = "S";
            return grade;
        } else if (score >= 9800000) {
            grade = "AAA+";
            return grade;
        } else if (score >= 9700000) {
            grade = "AAA";
            return grade;
        } else if (score >= 9500000) {
            grade = "AA+";
            return grade;
        } else if (score >= 9300000) {
            grade = "AA";
            return grade;
        } else if (score >= 9000000) {
            grade = "A+";
            return grade;
        } else if (score >= 8700000) {
            grade = "A";
            return grade;
        } else if (score >= 7500000) {
            grade = "B";
            return grade;
        } else if (score >= 6500000) {
            grade = "C";
            return grade;
        } else if (score < 6500000 && score > 0) {
            grade = "D";
            return grade;
        }
        return grade;
    }
}
