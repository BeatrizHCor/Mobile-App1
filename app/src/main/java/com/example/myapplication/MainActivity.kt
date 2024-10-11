package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random


class MainActivity : AppCompatActivity() {
    var num1 = 0
    var num2 = 0
    var resposta = 0
    var score = 0
    var perguntaAtual = 1

    lateinit var perguntaTextView: TextView
    lateinit var scoreTextView: TextView
    lateinit var respostaEditText: EditText
    lateinit var botao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botao = findViewById<Button>(R.id.submit)
        perguntaTextView = findViewById<TextView>(R.id.pergunta)
        scoreTextView = findViewById<TextView>(R.id.score)
        respostaEditText = findViewById<EditText>(R.id.resposta)
    }

    private fun gerarPergunta() {
        val random: Random = Random()
        num1 = random.nextInt(100)
        num2 = random.nextInt(100)
        resposta = num1 + num2
        perguntaTextView!!.text = "$num1 + $num2 = ?"
    }

    private fun checarResposta() {
        val respostaUsuario = respostaEditText!!.text.toString()
        if (respostaUsuario.isEmpty()) {
            Toast.makeText(this, "Digite uma resposta", Toast.LENGTH_SHORT).show()
            return
        }

        if (respostaUsuario.toInt() == resposta) {
            score += 20
            //findViewById<View>(R.id.pergunta).setBackgroundResource(Color.GREEN)
        } else {
            //findViewById<View>(R.id.pergunta).setBackgroundResource(Color.RED)
            Toast.makeText(this, "Resposta correta: $resposta", Toast.LENGTH_SHORT).show()
        }

        if (perguntaAtual == 5) {
            perguntaTextView!!.visibility = View.GONE
            respostaEditText!!.visibility = View.GONE
            botao!!.visibility = View.GONE

            scoreTextView!!.visibility = View.VISIBLE
            scoreTextView!!.text = "Sua pontuação: $score/100"
        } else {
            perguntaAtual++
            gerarPergunta()
            respostaEditText!!.setText("")
        }
    }
}