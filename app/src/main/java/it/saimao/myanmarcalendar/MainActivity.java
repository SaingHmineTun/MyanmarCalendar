package it.saimao.myanmarcalendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import it.saimao.myanmarcalendar.databinding.ActivityMainBinding;
import mmcalendar.MyanmarDate;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DatePickerDialog dialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();
    }

    private void initUi() {
        dialog = new DatePickerDialog(this);
        dialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
           binding.etDate.setText(String.format(Locale.ENGLISH, "%d/%d/%d", dayOfMonth, month + 1, year));
        });

    }

    private void initListeners() {
        binding.btCalculate.setOnClickListener(v -> {
            try {
                // 01/01/1970
                String[] date = binding.etDate.getText().toString().split("/");
                if (date.length != 3) throw new Exception("Invalid date");
                int day = Integer.parseInt(date[0]), month = Integer.parseInt(date[1]), year = Integer.parseInt(date[2]);
                MyanmarDate mmDate = MyanmarDate.of(year, month, day);
//                String mmYear = mmDate.getYear();
//                String mmMonth = mmDate.getMonthName();
//                String mmDay = mmDate.getMoonPhase() + " " + mmDate.getFortnightDay();
//                binding.tvDate.setText(String.format(Locale.getDefault(), "မြန်မာသက္ကရာဇ် - %s၊ %sလ၊ %s ရက်", mmYear, mmMonth, mmDay));
                binding.tvDate.setText(mmDate.format("S s k, B y k, M p f r En"));

            } catch (Exception e) {
                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btSelectDate.setOnClickListener(v -> {
            dialog.show();
        });
    }
}
