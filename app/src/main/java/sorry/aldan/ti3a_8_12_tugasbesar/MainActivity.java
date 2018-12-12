package sorry.aldan.ti3a_8_12_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import sorry.aldan.ti3a_8_12_tugasbesar.Activity.LoginActivity;
import sorry.aldan.ti3a_8_12_tugasbesar.Activity.ListLaporanActivity;
import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;

public class MainActivity extends AppCompatActivity {


    TextView txtUser;
    ImageView imgHome;
    ImageView imgSmartCity;
    ImageView imgUser;

    SessionManagement sessionManagement;
    HashMap<String,String> loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get component
        txtUser = findViewById(R.id.txtUser);
        imgHome = findViewById(R.id.imgGambar);
        imgSmartCity = findViewById(R.id.imgSmartCity);
        imgUser = findViewById(R.id.imgUser);

        //instance
        sessionManagement = new SessionManagement(this);

        //validate is already login
        if(!sessionManagement.isLoggedIn()){
            goToLogin();
        }

        //get login user
        loginUser = sessionManagement.getUserInformation();

        //change text to username
        txtUser.setText(loginUser.get(sessionManagement.KEY_USERNAME));

        //onclick logout
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagement.logoutUser();
            }
        });

        imgSmartCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListLaporanActivity.class);
                startActivity(i);
            }
        });
    }
    private void goToLogin(){
        Intent mIntent = new Intent(this,LoginActivity.class);
        startActivity(mIntent);
    }
}
