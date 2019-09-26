package keilane.com.calendario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Date data_selecionada = new Date();
    TextView btn;
    Intent intent;
    String curDate;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        intent = new Intent(MainActivity.this, keilane.com.eventos.MainActivity.class);
        final CalendarView calendarView = findViewById(R.id.calendarView1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        intent.putExtra("data_selecionada", sdf.format(new Date(calendarView.getDate())));

        // quando selecionado alguma data diferente da padrão
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                curDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                intent.putExtra("dataSelected", curDate);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
        });
    }

    public void verEventos(View view) {
    }

    public void criarEvento(View view) {
        startActivity(intent);
    }
}
