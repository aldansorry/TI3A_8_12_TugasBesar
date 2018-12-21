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

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin,btnRegister;
    SessionManagement sessionManagement;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get component
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        //instance
        sessionManagement = new SessionManagement(this);
        databaseHelper = new DatabaseHelper(this);

        //go to main if already login
        if(sessionManagement.isLoggedIn()){
            goToActivity();
        }

        //button function
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make var for store input value
                String username,password;
                //get input value
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();

                //validate is not null, and not space
                if(username.matches("") || username.trim().isEmpty() || password.matches("") || password.trim().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Email dan Passsword Tidak Boleh Kosong / Space"
                            ,Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (databaseHelper.autentikasi(username,password)){
                        sessionManagement.createLoginSession(username, password);
                        goToActivity();
                    }else{
                        Toast.makeText(LoginActivity.this,"Username belum terdaftar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    private void goToActivity(){
        Intent mIntent = new Intent(this,MainActivity.class);
        startActivity(mIntent);
    }
}
