package keilane.com.calendario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        /*final CalendarView calendarView = findViewById(R.id.calendarView1);

        // quando selecionado alguma data diferente da padrão
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(MainActivity.this, ClasseCapturaDados.class);
                intent.putExtra("dataLongMiliseconds", (Long) calendarView.getDate());
                startActivity(intent);
            }
        });*/
    }

    public void verEventos(View view) {
    }

    public void criarEvento(View view) {
        startActivity(new Intent(this, keilane.com.eventos.MainActivity.class));
    }
}
