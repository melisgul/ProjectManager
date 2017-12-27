package com.example.melis.projectmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by melisgulenay on 24/11/2017.
 */

public class Person {
    private String username;
    private String usersurname;
    private String password;
    private String department;
    private String personID;
    Project [] projects = new Project[5];
    private int projectCounter;
    private Integer imageid;
    private Boolean permission;


    public Person(){ projectCounter = 0; };

    public Person(String username, String password, String department, String personID, String usersurname, Boolean permission){
        this.username = username;
        this.password = password;
        this.department = department;
        this.personID = personID;
        this.usersurname = usersurname;
        projectCounter = 0;
        imageid = R.drawable.userimage;
        this.permission = permission;
    }


    public boolean addProject(Project newProject){ //add new project to user
        if (projectCounter == 5) return false;

        projects[projectCounter] = newProject;

        if (newProject.getOwnerId()!= null){
            Person person = Database.findPersonWithPersonID(newProject.getOwnerId());
            person.deleteProject(newProject);
        }
        newProject.attendAproject(personID);
        projectCounter++;
        return true;
    }



    public boolean deleteProject(Project deleteProject){

        if (searchProject(deleteProject) == false) return false;

        List<Project> list = new ArrayList<>(Arrays.asList(projects));
        list.removeAll(Arrays.asList(deleteProject));
        projects = list.toArray(projects);
        return true;
    }


    public boolean searchProject(Project searchedProject){

        for (int i = 0; i< projectCounter; i++){
            if (searchedProject.getProjectID() == projects[i].getProjectID())
                return true;
        }
        return false;
    }
    public int getProjectCounter(){return this.projectCounter;}

    public String getUsername(){
        return this.username;
    }
    public  String getPassword(){
        return this.password;
    }

    public String getDepartment(){
        return this.department;
    }

    public String getPersonID(){
        return this.personID;
    }

    public String getUsersurname(){
        return this.usersurname;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setPersonID(String personID){
        this.personID = personID;
    }

    public void setUsersurname(String usersurname){
        this.usersurname = usersurname;
    }

    public Integer getImageURI(){return imageid;}

    public Boolean getPermission(){return permission;}

}
