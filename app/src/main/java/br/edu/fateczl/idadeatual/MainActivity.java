package br.edu.fateczl.idadeatual;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 *@author:<Gustavo de Paula>
 */
public class MainActivity extends AppCompatActivity {

    private EditText etAnoNascimento, etMesNascimento, etDiaNascimento;
    private Button verificar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etAnoNascimento = findViewById(R.id.etAno);
        etMesNascimento = findViewById(R.id.etMes);
        etDiaNascimento = findViewById(R.id.etDia);
        verificar = findViewById(R.id.btnVer);
        verificar.setOnClickListener(op -> ver());

    }

    private void ver() {
        int anoNascimento = Integer.parseInt(etAnoNascimento.getText().toString());
        int mesNascimento = Integer.parseInt(etMesNascimento.getText().toString());
        int diaNascimento = Integer.parseInt(etDiaNascimento.getText().toString());



        Calendar calendario = Calendar.getInstance();


        Calendar nascimento = Calendar.getInstance();
        nascimento.set(anoNascimento, mesNascimento-1, diaNascimento);

        int anos = calendario.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        int meses = calendario.get(Calendar.MONTH) - nascimento.get(Calendar.MONTH);
        int dias = calendario.get(Calendar.DAY_OF_MONTH) - nascimento.get(Calendar.DAY_OF_MONTH);

        if (meses < 0) {
            anos--;
            meses += 12;
        }

        if (dias < 0) {
            meses--;
            Calendar mesAnterior = (Calendar) calendario.clone();
            mesAnterior.add(Calendar.MONTH, -1);
            dias += mesAnterior.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        resultado = findViewById(R.id.tvSaida);
        String saidaIdade = getString(R.string.idade);
        String saidaAno = getString(R.string.ano);
        String saidaMes = getString(R.string.mes);
        String saidaDia = getString(R.string.dia);
        String saida = saidaIdade + anos + " " + saidaAno + " " + meses + " " + saidaMes + " " + dias + " " + saidaDia;
        resultado.setText(saida);
    }

}