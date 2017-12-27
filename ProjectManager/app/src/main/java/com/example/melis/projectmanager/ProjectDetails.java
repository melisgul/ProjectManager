package com.example.melis.projectmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ProjectDetails extends Fragment {
    private TextView projecId;
    private TextView projectName;
    private TextView projectStartDate;
    private TextView projectEndDate;
    private RequiredMethods obj = new RequiredMethods();
    private EditText enteredHours;
    private Button hourbutton;
    private Project comingProject;
    private ProgressBar projectPercentageProgressBar;
    private TextView percentageOfTheProject;
    private SwipeRefreshLayout refreshPercentage;
    private TextView totalhour;
    private TextView donehour;
    private TextView remaninigHours;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project_details,container,false);
        projecId = (TextView) v.findViewById(R.id.projectDetailProjectId);
        projectName = (TextView) v.findViewById(R.id.projectDetailProjectName);
        projectStartDate = (TextView) v.findViewById(R.id.projectDetailProjectStartDate);
        projectEndDate = (TextView) v.findViewById(R.id.projectDetailProjectEndDate);

        String comingProjectID = getArguments().getString("projectID");
        comingProject = Database.findProjectFromProjectID(comingProjectID);

        projecId.setText(comingProjectID);
        projectName.setText(comingProject.getProjectName());
        projectStartDate.setText(obj.giveDateFormat(comingProject.getStartDate()));
        projectEndDate.setText(obj.giveDateFormat(comingProject.getEndDate()));
        hourbutton = (Button) v.findViewById(R.id.hourButton);
        enteredHours = (EditText) v.findViewById(R.id.editTextHours);
        projectPercentageProgressBar = (ProgressBar) v.findViewById(R.id.progressBarProjectDetails);
        projectPercentageProgressBar.setProgress((int)comingProject.getProjectPercentage());
        percentageOfTheProject = (TextView) v.findViewById(R.id.projectPercentageDetails);
        percentageOfTheProject.setText(comingProject.getProjectPercentage() + " %");
        refreshPercentage = (SwipeRefreshLayout) v.findViewById(R.id.refreshProgress);
        totalhour = (TextView) v.findViewById(R.id.totalProjectDetailHour);
        totalhour.setText(comingProject.getHourOfTheProject().toString());
        donehour = (TextView) v.findViewById(R.id.enteredProjectDetailHour);
        donehour.setText(comingProject.getDoneHours().toString());
        remaninigHours = (TextView) v.findViewById(R.id.remainingHours);
        remaninigHours.setText(comingProject.getRemainingHours().toString());


        hourbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHours();
            }
        });

        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshPercentage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                projectPercentageProgressBar.setProgress((int)comingProject.getProjectPercentage());
                percentageOfTheProject.setText(comingProject.getProjectPercentage() + " %");
                donehour.setText(comingProject.getDoneHours().toString());
                remaninigHours.setText(comingProject.getRemainingHours().toString());
                refreshPercentage.setRefreshing(false);
                enteredHours.setText(null);
            }
        });
    }




    public void setHours(){
        System.out.println("set hours");
        float hour = Integer.parseInt(enteredHours.getText().toString());
        comingProject.increasePercentage(hour);
        enteredHours.setText(null);
    }

}
