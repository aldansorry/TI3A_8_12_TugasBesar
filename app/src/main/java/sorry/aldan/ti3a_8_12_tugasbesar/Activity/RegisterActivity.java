package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sorry.aldan.ti3a_8_12_tugasbesar.Helper.DatabaseHelper;
import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;
import sorry.aldan.ti3a_8_12_tugasbesar.MainActivity;
import sorry.aldan.ti3a_8_12_tugasbesar.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNama, edtEmail, edtUsername, edtPassword;
    Button btnRegister;

    SessionManagement sessionManagement;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        sessionManagement = new SessionManagement(this);
        databaseHelper = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama,email,username,password;
                nama = edtNama.getText().toString();
                email = edtEmail.getText().toString();
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                if (nama.trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Nama Harus Diisi",Toast.LENGTH_LONG).show();
                    return;
                }
                if (email.trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Email Harus Diisi",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!email.contains("@")){
                    Toast.makeText(RegisterActivity.this,"Email Harus berisikan @",Toast.LENGTH_LONG).show();
                    return;
                }
                if (username.trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Username Harus Diisi",Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Password Harus Diisi",Toast.LENGTH_LONG).show();
                    return;
                }
                databaseHelper.addUser(nama,email,username,password,"1");
                finish();
            }
        });
    }
}
