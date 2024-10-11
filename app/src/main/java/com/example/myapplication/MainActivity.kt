package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var number1 = 0;
    private var number2 = 0;
    private var score = 0;
    private var attempts = 0;
    private var next = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.number1 = (0..99).random();
        this.number2 = (0..99).random();
        val question = findViewById<TextView>(R.id.question);
        question.text = "$number1 + $number2";
    }

    fun answer(view: View) {
        val input = findViewById<EditText>(R.id.editTextNumber);
        val button = findViewById<Button>(R.id.button);
        val root = input.rootView
        if(attempts < 5){
        if(!next){
            val total = number1 + number2;
            if(input.text.toString().isEmpty()){
                Toast.makeText(this, "Digite uma resposta", Toast.LENGTH_SHORT).show()
                return
            }
            val submit = input.text.toString().toInt();

            if(submit === total){
                root.setBackgroundColor(Color.GREEN);
                this.score +=20;
            } else {
                root.setBackgroundColor(Color.RED);
            }
            button.text = "Próxima";
            this.attempts += 1;
        } else {
            this.number1 = (0..99).random();
            this.number2 = (0..99).random();
            val question = findViewById<TextView>(R.id.question);
            question.text = "$number1 + $number2";
            button.text = "Responder";
        }
        this.next = !next;
        } else {
            root.setBackgroundColor(Color.TRANSPARENT)
            val score = findViewById<TextView>(R.id.score);
            score.text = "Seu Score final é: $score";

        }
    }
}