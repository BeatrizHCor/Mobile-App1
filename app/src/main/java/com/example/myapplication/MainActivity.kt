package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

class MainActivity : AppCompatActivity() {
    private var number1 = 0;
    private var number2 = 0;
    private var score = 0;
    private var attempts = 0;
    private var next = false;
    private var originalBackgroundColor: Int = 0

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
        val output = findViewById<TextView>(R.id.correcao)
        setOriginalbackground(root);

        if (attempts < 5) {
            if (!next) {
                val total = number1 + number2;
                if (input.text.toString().isEmpty()) {
                    Toast.makeText(this, "Digite uma resposta", Toast.LENGTH_SHORT).show()
                    return
                }
                val submit = input.text.toString().toInt();

                if (submit === total) {
                    root.setBackgroundColor(Color.parseColor("#26A83B"))
                    this.score += 20;
                    setOutputText(output,"A resposta está correta")
                } else {
                    root.setBackgroundColor(Color.parseColor("#A81030"))
                    setOutputText(output, "A resposta correta era: $total")
                }
                input.setText("")
                button.text = "Próxima";
                this.attempts += 1;
            } else {
                this.number1 = (0..99).random();
                this.number2 = (0..99).random();
                val question = findViewById<TextView>(R.id.question);
                question.text = "$number1 + $number2";
                root.setBackgroundColor(originalBackgroundColor)

                setOutputText(output, "");
                button.text = "Responder";
            }
            this.next = !next;
        } else {
            root.setBackgroundColor(originalBackgroundColor)
            val scoreText = findViewById<TextView>(R.id.score);
            scoreText.text = "Seu Score final é: $score";
        }
    }

    private fun setOutputText(output: TextView, text: String) {
        output.text = text
    }

    private fun setOriginalbackground(root: View) {
        if (originalBackgroundColor == 0) {
            originalBackgroundColor = (root.background as ColorDrawable).color
        }

    }
}