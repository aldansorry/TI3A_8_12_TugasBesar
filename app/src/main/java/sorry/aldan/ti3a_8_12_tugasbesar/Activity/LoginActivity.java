package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;
import sorry.aldan.ti3a_8_12_tugasbesar.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button buttonLogin;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get component
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        //instance
        sessionManagement = new SessionManagement(this);

        //go to main if already login
        if(sessionManagement.isLoggedIn()){
            goToActivity();
        }

        //button function
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make var for store input value
                String email,password;
                //get input value
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                //validate is not null, and not space
                if(email.matches("") || email.trim().isEmpty() || password.matches("") || password.trim().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Email dan Passsword Tidak Boleh Kosong / Space"
                            ,Toast.LENGTH_LONG).show();
                    return;
                }else {
                    sessionManagement.createLoginSession(email, password);
                    goToActivity();
                }
            }
        });

    }
    private void goToActivity(){
        Intent mIntent = new Intent(this,Main2Activity.class);
        startActivity(mIntent);
    }
}
