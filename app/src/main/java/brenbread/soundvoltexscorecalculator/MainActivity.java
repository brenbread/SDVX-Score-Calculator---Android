package brenbread.soundvoltexscorecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

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
        resetButton.setOnClickListener(MainActivity.this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.calcButton:
                String critIn = criticalInput.getText().toString(); //user input
                String nearIn = nearInput.getText().toString();
                String errorIn = errorInput.getText().toString();
                String grade = "";

                if (critIn.equals("") || nearIn.equals("") || errorIn.equals("")) //checks if input is empty
                {
                    scoreOut.setText("Input each field");
                    gradeOut.setText("");
                    critValOut.setText("");
                    nearValOut.setText("");
                    noteValOut.setText("");
                } else
                { //there is input in all fields
                    int critFinal = Integer.parseInt(critIn); //parse string to int
                    int nearFinal = Integer.parseInt(nearIn);
                    int errorFinal = Integer.parseInt(errorIn);

                    int totalNotes = critFinal + nearFinal + errorFinal;
                    BigDecimal critValB = new BigDecimal(10000000);
                    BigDecimal critVal = critValB.divide(new BigDecimal(totalNotes),20,BigDecimal.ROUND_HALF_UP);
                    BigDecimal nearVal = critVal.divide(new BigDecimal(2),20,BigDecimal.ROUND_HALF_UP); //crit/2
                    BigDecimal totalScoreA = critVal.multiply(new BigDecimal(critFinal));
                    BigDecimal totalScoreB = nearVal.multiply(new BigDecimal(nearFinal));
                    BigDecimal totalScoreAdded = totalScoreA.add(totalScoreB);

                    int totalScore = totalScoreAdded.intValue();

                    //score calculation
                    if (critFinal != 0 && nearFinal == 0 && errorFinal == 0) {
                        totalScore = 10000000;
                        String scoreFinal = Integer.toString(totalScore);
                        scoreOut.setText(scoreFinal);
                    } else if (critFinal == 0 && nearFinal == 0 && errorFinal == 0) {
                        totalScore = 0;
                        String scoreFinal = Integer.toString(totalScore);
                        scoreOut.setText(scoreFinal);
                    } else {
                        String scoreFinal = Integer.toString(totalScore);
                        scoreOut.setText(scoreFinal);
                    }

                    grade = gradeCalc(totalScore); //gets grade calc

                    //convert double to then int to string
                    int critINT = critVal.intValue();
                    int nearINT = nearVal.intValue();


                    String critValFinal = Integer.toString(critINT);
                    String nearValFinal = Integer.toString(nearINT);
                    String totalNotesFinal = Integer.toString(totalNotes);

                    //app output
                    critValOut.setText(critValFinal);
                    nearValOut.setText(nearValFinal);
                    noteValOut.setText(totalNotesFinal);
                    gradeOut.setText(grade);

                }
                break;

            case R.id.resetButton:
                criticalInput.setText("");
                nearInput.setText("");
                errorInput.setText("");
                scoreOut.setText("");
                gradeOut.setText("");
                critValOut.setText("");
                nearValOut.setText("");
                noteValOut.setText("");
                break;
        }
    }

    //grade calculation
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
