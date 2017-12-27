package com.example.melis.projectmanager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ProjectList extends Fragment {
    public Person currentPerson;
    public ListView projectList;
    public TextView projectId;
    public TextView projectName;
    public TextView projectStartDate;
    public TextView projectEndDate;
    public RequiredMethods obj  = new RequiredMethods();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        System.out.println("hello can you hear me?");
        v = inflater.inflate(R.layout.fragment_project_list, container, false);
        String currentPersonid = getArguments().getString("USERID");
        projectList = (ListView) v.findViewById(R.id.listviewUserProfile);
        currentPerson = Database.findPersonWithPersonID(currentPersonid);
        projectListAdapter adapter = new projectListAdapter();
        projectList.setAdapter(adapter);
        v.setVisibility(View.VISIBLE);

        return v;
    }


    public class projectListAdapter extends BaseAdapter {
        private Context mContext;


        @Override
        public int getCount(){
            return currentPerson.getProjectCounter();
        }

        @Override
        public Object getItem(int i){
            return null;
        }

        @Override
        public long getItemId(int i){
            return 0;
        }

        public void clickFunction(View view, final String projectID){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("projectID",projectID);
                    ProjectDetails pdFragment = new ProjectDetails();
                    pdFragment.setArguments(bundle);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_frame_layout,pdFragment);
                    ft.addToBackStack(pdFragment.getClass().getName()).commit();

                }
            });
        }

        @Override
        public View getView(int i,View view,  ViewGroup viewGroup){
            // if(view == null)
            //view = View.inflate(getContext(),R.layout.current_projects,null);
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (view == null){
                view = inflater.inflate(R.layout.current_projects,null);
            }

            projectName = (TextView)  view.findViewById(R.id.projectNameProfile);
            projectStartDate = (TextView)  view.findViewById(R.id.projectStartDate);
            projectEndDate = (TextView)  view.findViewById(R.id.projectEndDate);



            projectName.setText(currentPerson.projects[i].getProjectName());
            projectStartDate.setText(obj.giveDateFormat(currentPerson.projects[i].getStartDate()));
            projectEndDate.setText(obj.giveDateFormat(currentPerson.projects[i].getEndDate()));

            clickFunction(view,currentPerson.projects[i].getProjectID().toString());

            return view;
        }


        public void projectInformationPage(){

        }

    }


}
