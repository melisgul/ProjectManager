package com.example.melis.projectmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NormalProfile extends Fragment implements View.OnClickListener{
    public TextView usernameProfile;
    public TextView usersurnameProfile;
    public TextView userIDProfile;
    public TextView userDepartmentProfile;
    public String username;
    public Button projectsBtn;
    public Person currentPerson;
    public ImageView profilePicture;
    public Button updatePicture;
    private int PICK_IMAGE_REQUEST = 1;
    public Button changePasswordButton;
    public int REQUEST_CODE = 1;
    public ImageView image;


    public NormalProfile() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_normal_profile, container, false);
        username = getArguments().getString("USERID"); //person id
        currentPerson  = Database.findPersonWithPersonID(username); //person herself
        usernameProfile = (TextView) v.findViewById(R.id.usernameProfile);
        usernameProfile.setText(currentPerson.getUsername());
        usersurnameProfile = (TextView) v.findViewById(R.id.usersurnemeProfile);
        usersurnameProfile.setText(currentPerson.getUsersurname());
        userIDProfile = (TextView) v.findViewById(R.id.userIDProfile);
        userIDProfile.setText(currentPerson.getPersonID());
        userDepartmentProfile = (TextView) v.findViewById(R.id.userDepartmentProfile);
        userDepartmentProfile.setText(currentPerson.getDepartment());
        projectsBtn = (Button) v.findViewById(R.id.projectListBtn);
        updatePicture = (Button) v.findViewById(R.id.updateProfilePicture);
        changePasswordButton = (Button) v.findViewById(R.id.changePasswordButton);
        image = (ImageView) v.findViewById(R.id.profilePicture);
        image.setBackgroundResource(currentPerson.getImageURI());
        v.setVisibility(View.VISIBLE);
        return v;
    }




    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        projectsBtn.setOnClickListener(this);
        updatePicture.setOnClickListener(this);
        changePasswordButton.setOnClickListener(this);

    }

    public void openTheProjectList(){
        Bundle bundle = new Bundle();
        bundle.putString("USERID",currentPerson.getPersonID());
        ProjectList pl = new ProjectList();
        pl.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout,pl);
        ft.addToBackStack(pl.getClass().getName());
        ft.commit();
    }


    public void chooseProfilePictureFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }



    public void changePasswordPopupWindow(){
        FragmentManager manager = getFragmentManager();
        ChangePassword dialog = new ChangePassword();
        Bundle bundle = new Bundle();
        bundle.putString("PERSONID",currentPerson.getPersonID());
        dialog.setArguments(bundle);
        dialog.show(manager,"changepassword_dialog");
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.projectListBtn:
                openTheProjectList();
                break;
            case R.id.updateProfilePicture:
                chooseProfilePictureFromGallery();
                break;
            case R.id.changePasswordButton:
                changePasswordPopupWindow();
            default:
                break;
        }

    }


}
