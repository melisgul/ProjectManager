package com.example.melis.projectmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ChangeResponsible extends Fragment {
    TextView currentResponsible;
    Person comingPerson;
    Project comingProject;
    Person [] personArray = Database.getPersonArray();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_responsible, container, false);
        currentResponsible = (TextView) v.findViewById(R.id.currentResponsibleAssignment);
        String comingProjectId = getArguments().getString("projectId");
        comingProject = Database.findProjectFromProjectID(comingProjectId);
        comingPerson = Database.findPersonWithPersonID(comingProject.getOwnerId());

        if (comingPerson.getUsername() == null){
            currentResponsible.setText("No current responsible");
        }
        else{
            currentResponsible.setText(comingPerson.getUsername() + " " + comingPerson.getUsersurname());
        }

        ListView listView = (ListView) v.findViewById(R.id.availablePeopleList);
        AssignmentAdapter adapter = new AssignmentAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("item" + position);
            }
        });

        return v;
    }

    public class AssignmentAdapter extends BaseAdapter{
        String projectID;
        TextView nameSurname;
        TextView numberOfProjects;


        @Override
        public int getCount(){
            return Database.getPersonCounter();
        }

        @Override
        public Object getItem(int i){
            return null;
        }

        @Override
        public long getItemId(int i){
            return 0;
        }


        @Override
        public View getView(int i,View view,  ViewGroup viewGroup){
            // if(view == null)
            //view = View.inflate(getContext(),R.layout.current_projects,null);
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (view == null){
                view = inflater.inflate(R.layout.user_assignment_row,null);
            }

            nameSurname = (TextView) view.findViewById(R.id.nameSurname);
            numberOfProjects = (TextView) view.findViewById(R.id.numberOfProjectsPersonHas);


            nameSurname.setText(personArray[i].getUsername() + " " + personArray[i].getUsersurname());

            numberOfProjects.setText(personArray[i].getProjectCounter().toString());

            return view;
        }


    }


}