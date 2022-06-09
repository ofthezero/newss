package mpt.ru.mar.news;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    DatePickerDialog.OnDateSetListener picker;
    Spinner spnRoles;
    EditText txtLogin, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialize();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.roles));
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnRoles.setAdapter(adapter);
    }

    private void initialize() {
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        spnRoles = findViewById(R.id.spnRoles);
        databaseHelper = new DatabaseHelper(this);
    }

    public void registerClick(View view) {
        Boolean checkInsertData = databaseHelper
                .insertUser(txtLogin.getText().toString().trim(), txtPassword.getText().toString().trim(), spnRoles.getSelectedItem().toString());
        if (checkInsertData) {
            Cursor res = databaseHelper.getData(txtLogin.getText().toString().trim(), txtPassword.getText().toString().trim());
            if (res.getCount() == 0) {
                return;
            }
            while (res.moveToNext()) {
                if (res.getString(7).equals("Администратор")) {
                    startActivity(new Intent(this, AllNewsActivityAdministrator.class)
                            .putExtra("Id", res.getInt(0)));
                } else {
                    startActivity(new Intent(this, AllNewsActivity.class));
                }
                finish();
            }
        }
    }
}