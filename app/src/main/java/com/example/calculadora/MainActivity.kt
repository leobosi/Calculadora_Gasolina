
package com.example.calculadora
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var gasolinaEditText: EditText
    private lateinit var alcoolEditText: EditText
    private lateinit var switchCombustivel: SwitchMaterial
    private lateinit var valeTextView: TextView
    private var valorY = 0.75f
    private var resultado = 0f

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gasolinaEditText = findViewById(R.id.gasolinaEditText)
        alcoolEditText = findViewById(R.id.alcoolEditText)
        switchCombustivel = findViewById(R.id.switchCombustivel)
        valeTextView = findViewById(R.id.valeTextView)
        val calcularButton = findViewById<Button>(R.id.calcularButton)
        calcularButton.setOnClickListener {

            calcular()
        }

        switchCombustivel.setOnCheckedChangeListener { _, isChecked ->
            valorY = if (isChecked) 0.7f else 0.75f
        }

        savedInstanceState?.let {
            valorY = it.getFloat("valorY", 0.75f)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putFloat("valorY", valorY)
        super.onSaveInstanceState(outState)
    }

    fun calcular() {
        val gasolina = gasolinaEditText.text.toString().toFloatOrNull() ?: return
        val alcool = alcoolEditText.text.toString().toFloatOrNull() ?: return

        resultado = gasolina * valorY

        val resultadoescrito = if (alcool <= resultado) {
            "Álcool vale mais a pena"
        } else {
            "Gasolina vale mais a pena"
        }

        valeTextView.text = resultadoescrito
    }}
