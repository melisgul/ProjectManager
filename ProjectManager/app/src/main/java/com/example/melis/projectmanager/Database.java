package com.example.melis.projectmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by melisgulenay on 24/11/2017.
 */

public class Database {
    private static Project [] projectArray = new Project[50];
    private static Person [] personArray = new Person[50];
    private static int personCounter;
    private static int projectCounter;



    private Database() {
        personCounter = 0;
        projectCounter = 0;
    };

    public static Person[] getPersonArray() {
        return personArray;
    }

    public static Project[] getProjectArray() {
        return projectArray;
    }

    public static void printTheDatabaseUsers(){
        for(int i = 0; i< personCounter ; i++){
            System.out.println(personArray[i].getUsername());
        }
    }



    public static void addProject(Project newProject){
        if(projectCounter == 50){
            Project [] tempArray = new Project[100];
            for (int i = 0; i<50; i++){
                tempArray[i] = projectArray[i];
            }

            projectArray = new Project[100];

            for (int i = 0; i<50; i++) {
                projectArray[i] = tempArray[i];
            }
        }

        projectArray[projectCounter] = newProject;
        projectCounter++;

    }

    public static void addPerson(Person newPerson){
        if(personCounter == 50){
            Person [] tempArray = new Person[100];
            for (int i = 0; i<50; i++){
                tempArray[i] = personArray[i];
            }

            personArray = new Person[100];

            for (int i = 0; i<50; i++) {
                personArray[i] = tempArray[i];
            }
        }

        personArray[personCounter] = newPerson;
        personCounter++;

    }


    public static void deleteProject(Project deleteProject){
        List<Project> list = new ArrayList<>(Arrays.asList(projectArray));
        list.removeAll(Arrays.asList(deleteProject));
        projectArray = list.toArray(projectArray);
    }


    public static void deletePerson(Person deletePerson){
        List<Person> list = new ArrayList<>(Arrays.asList(personArray));
        list.removeAll(Arrays.asList(deletePerson));
        personArray = list.toArray(personArray);
    }

    public static boolean loginCheck(String userID,String password){

        Person pr = findPersonWithPersonID(userID);
        if (pr.getPersonID().equals("")) return false;

        if(pr.getPassword().equals(password)) return true;
        else return false;

    }


    public static Person findPersonWithPersonID(String userID){
        for(int i = 0; i< personCounter ; i++){
            if (personArray[i].getPersonID().equals(userID)) return personArray[i];

        }
        Person nullPerson = new Person("","","","","",false);

        return nullPerson;
    }


    public static Project findProjectFromProjectID(String projectid){
        for (int i = 0; i< projectCounter;i++){
            if (projectArray[i].getProjectID().toString() == projectid) {
                return projectArray[i];
            }
        }

        Project nullProject = new Project("",new  Date(), new Date());

        return nullProject;
    }


    public static int getProjectCounter(){return projectCounter;}

    public static int getPersonCounter(){return personCounter;}

}
