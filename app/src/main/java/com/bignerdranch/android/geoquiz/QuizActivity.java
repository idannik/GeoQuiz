package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private int mCurrentIndex;



    private TextView mQuestionTextView;
    private ArrayList<Question> mQuestionBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initQuestionBank();
        initViews();
        updateQuestion();

    }


    private void initQuestionBank() {
        mCurrentIndex = 0;
        mQuestionBank = new ArrayList<>();
        mQuestionBank.add(new Question(R.string.question_oceans, true));
        mQuestionBank.add(new Question(R.string.question_mideast, false));
        mQuestionBank.add(new Question(R.string.question_africa, false));
        mQuestionBank.add(new Question(R.string.question_americas, true));
        mQuestionBank.add(new Question(R.string.question_asia, true));
    }
    private void initViews() {
        initButtons();
        initTextView();
    }


    private void initButtons() {
        initTrueButton();
        initFalseButton();
        initNextMethod();
        initBackMethod();
    }

    private void initNextMethod() {
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuestionIndex();
                updateQuestion();
            }
        });
    }

    private void initBackMethod() {
        mNextButton = (Button) findViewById(R.id.back_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseQuestionIndex();
                updateQuestion();
            }
        });
    }

    private void initFalseButton() {
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);

            }
        });
    }

    private void initTrueButton() {
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
    }
    private void initTextView() {
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                increaseQuestionIndex();
                updateQuestion();
            }
        });
    }

    private void decreaseQuestionIndex() {
        mCurrentIndex= (mCurrentIndex+mQuestionBank.size()-1) % mQuestionBank.size();
    }

    private void increaseQuestionIndex() {
        mCurrentIndex= (mCurrentIndex+1) % mQuestionBank.size();
    }

    private void updateQuestion() {
        int question = mQuestionBank.get(mCurrentIndex).getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        int messageResId=0;
        if (userPressedTrue == mQuestionBank.get(mCurrentIndex).isAnswerTrue()) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,    messageResId, Toast.LENGTH_LONG).show();
    }



    public TextView getQuestionTextView() {
        return mQuestionTextView;
    }

    public void setQuestionTextView(TextView questionTextView) {
        mQuestionTextView = questionTextView;
    }
    public Button getTrueButton() {
        return mTrueButton;
    }

    public void setTrueButton(Button trueButton) {
        mTrueButton = trueButton;
    }

}
