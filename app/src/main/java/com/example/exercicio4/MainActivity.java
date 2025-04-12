package com.example.exercicio4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    TextView txtTexto, txtTamanhoSp;
    SeekBar skbRed;
    CheckBox chkNegrito, chkItalico, chkMaiusculas;
    RadioGroup rdgCor;
    RadioButton rdbRosa, rdbVerde, rdbAzul;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNome = findViewById(R.id.edtNome);
        txtTexto = findViewById(R.id.txtTexto);
        txtTamanhoSp = findViewById(R.id.txtTamanhoSp);
        skbRed = findViewById(R.id.skbRed);
        chkNegrito = findViewById(R.id.chkNegrito);
        chkItalico = findViewById(R.id.chkItalico);
        chkMaiusculas = findViewById(R.id.chkMaiusculas);
        rdgCor = findViewById(R.id.rdgCor);
        rdbRosa = findViewById(R.id.rdbRosa);
        rdbVerde = findViewById(R.id.rdbVerde);
        rdbAzul = findViewById(R.id.rdbAzul);
        imgButton = findViewById(R.id.imgButton);

        imgButton.setOnClickListener(view -> {
            String texto = edtNome.getText().toString();
            if(chkMaiusculas.isChecked()){
                texto = texto.toUpperCase();
            }
            txtTexto.setText(texto);
            aplicarEstilos();
        });

        skbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtTexto.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
                txtTamanhoSp.setText(progress + "sp");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        rdgCor.setOnCheckedChangeListener((group, checkedId) -> aplicarEstilos());

        chkNegrito.setOnClickListener(v -> aplicarEstilos());
        chkItalico.setOnClickListener(v -> aplicarEstilos());
        chkMaiusculas.setOnClickListener(v -> aplicarEstilos());
    }

    private void aplicarEstilos(){
        String texto = edtNome.getText().toString();
        int estilo = Typeface.NORMAL;
        if(chkMaiusculas.isChecked()){
            texto = texto = texto.toUpperCase();
        }
        txtTexto.setText(texto);

        if(chkNegrito.isChecked() && chkItalico.isChecked()) {
            estilo = Typeface.BOLD_ITALIC;
        } else if(chkNegrito.isChecked()) {
            estilo = Typeface.BOLD;
        } else if(chkItalico.isChecked()) {
            estilo = Typeface.ITALIC;
        }
        txtTexto.setTypeface(null, estilo);

        int cor = Color.BLACK;
        if(rdbRosa.isChecked()) {
            cor = Color.parseColor("#FF1493");
        } else if(rdbVerde.isChecked()) {
            cor = Color.parseColor("#4CAF50");
        } else if(rdbAzul.isChecked()) {
            cor = Color.parseColor("#4169E1");
        }
        txtTexto.setTextColor(cor);
    }
}