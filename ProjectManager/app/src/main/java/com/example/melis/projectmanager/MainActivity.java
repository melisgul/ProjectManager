package com.example.melis.projectmanager;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.melis.projectmanager.AdminProfile;
import com.example.melis.projectmanager.Database;
import com.example.melis.projectmanager.NormalProfile;
import com.example.melis.projectmanager.Person;
import com.example.melis.projectmanager.Project;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ViewStub vs;
    private View miflated;
    Button loginBtn;
    TextView userID;
    TextView password;
    TextView warningLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ftransaction = getSupportFragmentManager().beginTransaction();
        Login login = new Login();
        ftransaction.replace(R.id.main_frame_layout,login);
        ftransaction.commit();
    }
}



