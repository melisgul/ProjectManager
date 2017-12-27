package com.example.melis.projectmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class Login extends Fragment {
    public EditText userID;
    public EditText password;
    public Button submitButton;
    public TextView warningLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("login is reached.");
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initilizePersonProject();
        userID = (EditText) v.findViewById(R.id.usernameLogin);
        password = (EditText) v.findViewById(R.id.passwordLogin);
        submitButton = (Button) v.findViewById(R.id.submitButtonLogin);
        warningLabel = (TextView) v.findViewById(R.id.loginWarning);
        warningLabel.setVisibility(View.INVISIBLE);
        return v;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View w){
                System.out.println("submit was clicked");
                submitButtonAction();
            }
        });

    }



    public void submitButtonAction(){
        String comingUserID = userID.getText().toString();
        String comingPassword = password.getText().toString();
        Boolean check = false;
        check = Database.loginCheck(comingUserID,comingPassword);


        if (check){
            Person enteredPerson = Database.findPersonWithPersonID(comingUserID);
            if (!enteredPerson.getPermission()){
                Bundle bundle=new Bundle();
                bundle.putString("USERID", comingUserID);
                FragmentManager fmanager = getFragmentManager();
                FragmentTransaction ftransaction = fmanager.beginTransaction();
                NormalProfile fragmentObject = new NormalProfile();
                fragmentObject.setArguments(bundle);
                ftransaction.replace(R.id.main_frame_layout,fragmentObject);
                ftransaction.addToBackStack(fragmentObject.getClass().getName()).commit();
            }
            else{
                Bundle bundle=new Bundle();
                bundle.putString("USERID", comingUserID);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ftransaction = manager.beginTransaction();
                AdminProfile fragment = new AdminProfile();
                fragment.setArguments(bundle);
                ftransaction.replace(R.id.main_frame_layout,fragment);
                ftransaction.addToBackStack(fragment.getClass().getName()).commit();
            }

        }
        else {
            warningLabel.setVisibility(View.VISIBLE);
        }
    }





    public void initilizePersonProject(){
        System.out.println("people and projects were created.");
        Person p1 = new Person("Melis","123456","sw dev","T086087","Gulenay",false);
        Person p2 = new Person("Songul","123", "sys ad","T086088","sarac",false);
        Person p3 = new Person("Ceyda","456", "sw developer","T086089","aladag",false);
        Person p4 = new Person("Özlem", "111", "Manager", "T081081","Yurdakurban",true);


        Date startDate,endDate;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.DATE, 30);
        cal.set(Calendar.YEAR, 2017);
        startDate = cal.getTime();
        cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.YEAR, 2017);
        endDate = cal.getTime();

        Project pro1 = new Project("kurumsal", startDate,endDate);
        Project pro2 = new Project("bireysel", startDate,endDate);
        Project pro3 = new Project("sth", startDate,endDate);
        Database.addProject(pro1);
        Database.addProject(pro2);
        Database.addProject(pro3);

        Project pro4 = new Project("kurumsal4", startDate,endDate);
        Project pro5 = new Project("bireysel4", startDate,endDate);
        Database.addProject(pro4);
        Database.addProject(pro5);

        Project pro6 = new Project("kurumsal2", startDate,endDate);
        Project pro7 = new Project("bireysel2", startDate,endDate);
        Project pro8 = new Project("sth2", startDate,endDate);
        Project pro9 = new Project("kurumsal3", startDate,endDate);
        Project pro10 = new Project("bireysel3", startDate,endDate);
        Project pro11 = new Project("sth3", startDate,endDate);
        Database.addProject(pro6);
        Database.addProject(pro7);
        Database.addProject(pro8);
        Database.addProject(pro9);
        Database.addProject(pro10);
        Database.addProject(pro11);




        p1.addProject(pro1);
        p1.addProject(pro2);
        p1.addProject(pro3);
        p1.addProject(pro4);
        p1.addProject(pro5);
        p1.addProject(pro6);
        p1.addProject(pro7);
        p1.addProject(pro8);

        Database.addPerson(p1);
        Database.addPerson(p2);
        Database.addPerson(p3);
        Database.addPerson(p4);

    }


}
