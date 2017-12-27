package com.example.melis.projectmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class projectDetailsAndAssignment extends Fragment implements View.OnClickListener {
    private Button changeResponsibleBtn;
    private Button delayProjectBtn;
    private TextView projectName;
    private TextView projectStartDate;
    private TextView projectEndDate;
    private TextView projectResponsibleName;
    private Project comingProject;
    private String projectId;
    private RequiredMethods obj = new RequiredMethods();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project_details_and_assignment, container, false);
        projectName = (TextView) v.findViewById(R.id.projectNameAdmin);
        projectStartDate = (TextView) v.findViewById(R.id.projectStartDateAdmin);
        projectEndDate = (TextView) v.findViewById(R.id.projectEndDateAdmin);
        projectResponsibleName = (TextView) v.findViewById(R.id.projectResponsibleAdmin);
        changeResponsibleBtn = (Button) v.findViewById(R.id.changeResponsibleButton);
        delayProjectBtn = (Button) v.findViewById(R.id.delayProjectButton);

        projectId = getArguments().getString("projectID");
        comingProject = Database.findProjectFromProjectID(projectId);
        projectName.setText(comingProject.getProjectName());
        projectStartDate.setText(obj.giveDateFormat(comingProject.getStartDate()));
        projectEndDate.setText(obj.giveDateFormat(comingProject.getEndDate()));

        Person responsiblePerson = Database.findPersonWithPersonID(comingProject.getOwnerId());
        if (responsiblePerson.getUsername() == null){
            projectResponsibleName.setText("No responsible person.");
            projectResponsibleName.setTextColor(getResources().getColor(R.color.whiteColor));
        }
        else projectResponsibleName.setText(responsiblePerson.getUsername() + " " + responsiblePerson.getUsersurname());

        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        changeResponsibleBtn.setOnClickListener(this);
        delayProjectBtn.setOnClickListener(this);
    }

    public void delayProjectPage(){
        System.out.print("delay it!");
    }

    public void changeResponsible(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        ChangeResponsible changeResponsible = new ChangeResponsible();
        Bundle bundle = new Bundle();
        bundle.putString("projectId",comingProject.getProjectID().toString());
        changeResponsible.setArguments(bundle);
        transaction.replace(R.id.main_frame_layout,changeResponsible);
        transaction.addToBackStack(changeResponsible.getClass().getName());
        transaction.commit();
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.delayProjectButton:
                delayProjectPage();
                break;
            case R.id.changeResponsibleButton:
                changeResponsible();
                break;
            default:
                break;
        }

    }

}
