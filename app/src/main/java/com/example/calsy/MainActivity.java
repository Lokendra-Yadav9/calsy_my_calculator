package com.example.calsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
      TextView resultTv,solutionTv;
      AppCompatButton appPercent,appdivide,appmulti,appminus,appadd;
      AppCompatButton appEqual,appBack,appAc,appPoint;
      AppCompatButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btn00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv=findViewById(R.id.result_src);
        solutionTv=findViewById(R.id.solution_src);

        assignId(appEqual,R.id.appEqual);
        assignId(appBack,R.id.appBack);
        assignId(appAc,R.id.appAc);
        assignId(appPoint,R.id.apppoint);


        assignId(appPercent,R.id.appPercent);
        assignId(appdivide,R.id.appdivide);
        assignId(appmulti,R.id.appmulti);
        assignId(appminus,R.id.appminus);
        assignId(appadd,R.id.appadd);

        assignId(btn0,R.id.btn0);
        assignId(btn1,R.id.btn1);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn7,R.id.btn7);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);
        assignId(btn00,R.id.btn00);

    }

    void assignId(AppCompatButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        AppCompatButton  button=(AppCompatButton) view;
        String buttonText=button.getText().toString();
        String dataToCalcuulate=solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
           dataToCalcuulate=dataToCalcuulate.substring(0,dataToCalcuulate.length()-1);
        }
       else {
            dataToCalcuulate = dataToCalcuulate + buttonText;

        }
        solutionTv.setText(dataToCalcuulate);

        String finalResult = getResult(dataToCalcuulate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}