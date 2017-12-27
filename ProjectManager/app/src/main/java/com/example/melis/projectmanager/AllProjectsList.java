package com.example.melis.projectmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class AllProjectsList extends Fragment {
    private ListView projectList;
    private TextView projectName;
    private TextView projectStartDate;
    private TextView projectEndDate;
    private TextView projectOwner;
    private ImageView image;
    private Project [] projectArray = Database.getProjectArray();
    public RequiredMethods obj  = new RequiredMethods();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_projects_list, container, false);
        projectList = (ListView) v.findViewById(R.id.allProjectList);
        allProjectsAdapter adapter = new allProjectsAdapter();
        projectList.setAdapter(adapter);
        v.setVisibility(View.VISIBLE);
        return v;
    }




    public class allProjectsAdapter extends BaseAdapter{
        String projectID;

        @Override
        public int getCount(){
            return Database.getProjectCounter();
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
                view = inflater.inflate(R.layout.all_project_list_row,null);
            }

            projectName = (TextView) view.findViewById(R.id.allprojectNameProfile);
            projectStartDate = (TextView) view.findViewById(R.id.allprojectStartDate);
            projectEndDate = (TextView) view.findViewById(R.id.allprojectEndDate);
            projectOwner = (TextView) view.findViewById(R.id.allprojectOwner);
            image = (ImageView) view.findViewById(R.id.allprojectsImage);

            projectName.setText(projectArray[i].getProjectName());
            projectStartDate.setText(obj.giveDateFormat(projectArray[i].getStartDate()));
            projectEndDate.setText(obj.giveDateFormat(projectArray[i].getEndDate()));
            if (projectArray[i].getOwnerId() == null){
                projectOwner.setText("-");

            }
            else{
                projectOwner.setText(Database.findPersonWithPersonID(projectArray[i].getOwnerId()).getUsername()+ " " + Database.findPersonWithPersonID(projectArray[i].getOwnerId()).getUsersurname());

            }

            if (projectArray[i].getProjectPercentage() > 50){
                image.setImageResource(R.drawable.bluecircle);
            }
            else if (projectArray[i].getProjectPercentage() >= 100){
                image.setImageResource(R.drawable.greencircle);
            }
            else{
                image.setImageResource(R.drawable.redcircle);
            }

            clickFunction(view, projectArray[i].getProjectID().toString());
            return view;
        }

        public void clickFunction(View view, final String projectID){

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("projectID",projectID);
                    projectDetailsAndAssignment pdFragment = new projectDetailsAndAssignment();
                    pdFragment.setArguments(bundle);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_frame_layout,pdFragment);
                    ft.addToBackStack(pdFragment.getClass().getName()).commit();

                }
            });
        }

    }


}
