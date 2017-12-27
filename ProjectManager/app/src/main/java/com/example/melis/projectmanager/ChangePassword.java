package com.example.melis.projectmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by melisgulenay on 15/12/2017.
 */

public class ChangePassword extends DialogFragment implements View.OnClickListener{
    public TextView newpassword;
    public TextView reenteredPassword;
    public Button change;
    public Button cancel;
    public Person comingPerson;
    public String userid;
    public TextView warningText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.change_password_popup,null);
        newpassword = (TextView) v.findViewById(R.id.passwordText);
        change = (Button) v.findViewById(R.id.changeButton);
        cancel = (Button) v.findViewById(R.id.cancelButton);
        userid = getArguments().getString("PERSONID");
        comingPerson = Database.findPersonWithPersonID(userid);
        reenteredPassword = (TextView) v.findViewById(R.id.passwordText2);
        warningText =(TextView) v.findViewById(R.id.passwordWarning);
        warningText.setVisibility(View.INVISIBLE);
        setCancelable(false);
        return v;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        cancel.setOnClickListener(this);
        change.setOnClickListener(this);
    }

    public void changeOldPassword(){
        String comingPassword = newpassword.getText().toString();
        String rePassword = reenteredPassword.getText().toString();
        if (comingPassword.equals(rePassword) && !comingPassword.equals("") && !comingPassword.equals("")){
            comingPerson.setPassword(comingPassword);
            dismiss();
        }
        else{
            if (comingPassword.equals("") && comingPassword.equals("")) warningText.setText("Password fields cannot left as blank!");
            else warningText.setText("Entered passwords were not matched.");
            warningText.setVisibility(View.VISIBLE);
        }

    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.cancelButton:
                dismiss();
                break;
            case R.id.changeButton:
                changeOldPassword();
                break;
            default:
                break;

        }
    }



}


