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
import android.widget.TextView;


public class AdminProfile extends Fragment implements View.OnClickListener{
    private Button profilePictureButton;
    private Button changePasswordButton;
    private Button showCurrentProjectsButton;
    private Button showCurrentUsersButton;
    private TextView userid;
    private TextView username;
    private TextView department;
    private TextView userSurname;
    private String comingUserID;
    private Person comingPerson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_admin_profile, container, false);
        profilePictureButton = (Button) v.findViewById(R.id.AdminupdateProfilePicture);
        changePasswordButton = (Button) v.findViewById(R.id.AdminchangePasswordButton);
        showCurrentProjectsButton = (Button) v.findViewById(R.id.AdminprojectListBtn);
        showCurrentUsersButton = (Button) v.findViewById(R.id.AdminCurrentUsersBtn);

        userid = (TextView) v.findViewById(R.id.AdminuserIDProfile);
        username = (TextView) v.findViewById(R.id.AdminusernameProfile);
        userSurname = (TextView) v.findViewById(R.id.AdminusersurnemeProfile);
        department = (TextView) v.findViewById(R.id.AdminuserDepartmentProfile);

        comingUserID = getArguments().getString("USERID");
        comingPerson = Database.findPersonWithPersonID(comingUserID);

        userid.setText(comingPerson.getPersonID());
        username.setText(comingPerson.getUsername());
        userSurname.setText(comingPerson.getUsersurname());
        department.setText(comingPerson.getDepartment());

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        profilePictureButton.setOnClickListener(this);
        changePasswordButton.setOnClickListener(this);
        showCurrentProjectsButton.setOnClickListener(this);
        showCurrentUsersButton.setOnClickListener(this);
    }


    public void changePassword(){
        ChangePassword dialog = new ChangePassword();
        Bundle bundle = new Bundle();
        bundle.putString("PERSONID",comingUserID);
        dialog.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        dialog.show(manager,"changePasswordDialog");
    }


    public void listProjects(){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ftransaction = manager.beginTransaction();
        AllProjectsList fragment = new AllProjectsList();
        ftransaction.replace(R.id.main_frame_layout,fragment);
        ftransaction.addToBackStack(fragment.getClass().getName()).commit();

    }

    public void listUsers(){
        FragmentTransaction ftransaction = getFragmentManager().beginTransaction();
        ListCurrentUsers fragment = new ListCurrentUsers();
        ftransaction.replace(R.id.main_frame_layout,fragment);
        ftransaction.addToBackStack(fragment.getClass().getName()).commit();
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.AdminupdateProfilePicture:
                break;
            case R.id.AdminchangePasswordButton:
                changePassword();
                break;
            case R.id.AdminprojectListBtn:
                listProjects();
                break;
            case R.id.AdminCurrentUsersBtn:
                listUsers();
                break;
            default:
                break;
        }

    }






}
