package com.example.melis.projectmanager;

import java.util.Date;

/**
 * Created by melisgulenay on 24/11/2017.
 */

public class Project {
    private String projectName;
    private Date startDate;
    private Date endDate;
    private Integer projectID;
    private static int projectIDcounter = 0;
    private String ownerId;
    private float percentageOfTheProject;
    private float hourOfTheProject;
    private float doneHours;



    public Project(String projectName, Date startDate, Date endDate){ //constructor
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        projectIDcounter++;
        this.projectID = projectIDcounter;
        ownerId = null;
        percentageOfTheProject = 0;
        hourOfTheProject = (int) (endDate.getTime() - startDate.getTime());
        hourOfTheProject = (int) (hourOfTheProject / (1000 * 60 * 60));
        System.out.println("hour of the projects are" + hourOfTheProject);
        doneHours = 0;
    }

    @Override
    protected void finalize() throws Throwable {  //kind of destructor
        projectIDcounter--;
    }


    //getter and setter methods

    public boolean attendAproject(String id){
        if (ownerId != null) return false;
        ownerId = id;
        return true;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }


    public void setStartDate(Date newDate){
        startDate = newDate;
    }

    public void setEndDate(Date newDate){
        endDate = newDate;
    }

    public String getProjectName(){
        return this.projectName;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

    public Integer getProjectID(){
        return this.projectID;
    }

    public void calculatePercentage() {
        System.out.println("done hours" + doneHours);
        System.out.println("project percentage  " + percentageOfTheProject);
        System.out.println("hour of the project  " + hourOfTheProject);
        percentageOfTheProject = doneHours / hourOfTheProject * 100;
        System.out.println("project percentage 2   " + percentageOfTheProject );

    }

    public void increasePercentage(float hour){
        float temp = doneHours + hour;
        if (temp <= hourOfTheProject) {
            doneHours += hour;
        }
        else {
            doneHours = hourOfTheProject;
        }
        calculatePercentage();

    }

    public float getProjectPercentage(){return (float)percentageOfTheProject;}

    public Float getHourOfTheProject(){ return hourOfTheProject;}

    public Float getDoneHours(){ return doneHours;}

    public Float getRemainingHours(){
        return hourOfTheProject-doneHours;
    }

    public String getOwnerId(){ return ownerId;}


}
