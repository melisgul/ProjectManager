package com.example.melis.projectmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class UserDetails extends Fragment {
    private TextView personId;
    private TextView personName;
    private TextView personSurname;
    private TextView department;
    private Person comingPerson;
    private TextView warning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_details, container, false);
        personId = (TextView) v.findViewById(R.id.personIdInfoAdmin);
        personName = (TextView) v.findViewById(R.id.nameInfoAdmin);
        personSurname = (TextView) v.findViewById(R.id.surnameInfoAdmin);
        department = (TextView) v.findViewById(R.id.departmentInfoAdmin);

        String comingPersonId = getArguments().getString("userID");
        comingPerson = Database.findPersonWithPersonID(comingPersonId);

        personId.setText(comingPerson.getPersonID());
        personName.setText(comingPerson.getUsername());
        personSurname.setText(comingPerson.getUsersurname());
        department.setText(comingPerson.getDepartment());

        if (comingPerson.getProjectCounter() != 0){
            ListView listView = (ListView) v.findViewById(R.id.personalInfoList);
            UserDetailsListAdapter adapter = new UserDetailsListAdapter();
            listView.setAdapter(adapter);
        }
        else{
            warning = (TextView) v.findViewById(R.id.currentProjectsInfoAdmin);
            warning.setText("No available projects.");
            warning.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        return v;
    }

    public class UserDetailsListAdapter extends BaseAdapter{
        TextView projectName;
        TextView projectTotalHours;
        TextView projectRemainingHours;
        ImageView image;

        @Override
        public int getCount(){
            return comingPerson.getProjectCounter();
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

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (view == null){
                view = inflater.inflate(R.layout.user_projectlist_row,null);
            }
            final Project project = comingPerson.projects[i];
            projectName = (TextView) view.findViewById(R.id.userListProjectName);
            projectTotalHours = (TextView) view.findViewById(R.id.userListProjectTotalHour);
            projectRemainingHours = (TextView) view.findViewById(R.id.userListProjectRemainingHour);
            image = (ImageView) view.findViewById(R.id.userDetailsArrowImage);
            projectName.setText(project.getProjectName());
            projectTotalHours.setText(project.getHourOfTheProject().toString());
            projectRemainingHours.setText(project.getRemainingHours().toString());
            image.setImageResource(R.drawable.arrow);

            final Project goingProject = project;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("projectID",project.getProjectID().toString());
                    projectDetailsAndAssignment pdFragment = new projectDetailsAndAssignment();
                    pdFragment.setArguments(bundle);
                    FragmentManager fm = getFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_frame_layout,pdFragment);
                    ft.addToBackStack(pdFragment.getClass().getName()).commit();
                }
            });

            return view;
        }



    }


}
