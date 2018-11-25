package sorry.aldan.ti3a_8_12_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import sorry.aldan.ti3a_8_12_tugasbesar.Activity.LoginActivity;
import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;

public class MainActivity extends AppCompatActivity {


    TextView tvEmail;
    Button buttonLogout;

    SessionManagement sessionManagement;
    HashMap<String,String> loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get component
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //instance
        sessionManagement = new SessionManagement(this);

        //validate is already login
        if(!sessionManagement.isLoggedIn()){
            goToLogin();
        }

        //get login user
        loginUser = sessionManagement.getUserInformation();

        //change text to username
        tvEmail.setText(loginUser.get(sessionManagement.KEY_EMAIL));

        //onclick logout
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagement.logoutUser();
            }
        });
    }
    private void goToLogin(){
        Intent mIntent = new Intent(this,LoginActivity.class);
        startActivity(mIntent);
    }
}
