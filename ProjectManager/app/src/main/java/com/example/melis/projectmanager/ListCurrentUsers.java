package com.example.melis.projectmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ListCurrentUsers extends Fragment {
    private ListView listUsers;
    private Person [] personArray = Database.getPersonArray();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment_list_current_users, container, false);
        listUsers = (ListView) v.findViewById(R.id.allUsersList);
        CurrentUsersAdapter adapter = new CurrentUsersAdapter();
        listUsers.setAdapter(adapter);
        return v;
    }


    public class CurrentUsersAdapter extends BaseAdapter{
        TextView name;
        ImageView image;
        ImageView arrowImage;



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

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (view == null){
                view = inflater.inflate(R.layout.all_user_list_row,null);
            }

            name = (TextView) view.findViewById(R.id.usernamesurnamelist);
            name.setText(personArray[i].getUsername() + " " + personArray[i].getUsersurname());
            arrowImage = (ImageView) view.findViewById(R.id.arrowInUserList);
            arrowImage.setImageResource(R.drawable.arrow);
            final Person currentPerson = personArray[i];

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ftransaction = getFragmentManager().beginTransaction();
                    UserDetails fragment = new UserDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("userID",currentPerson.getPersonID());
                    fragment.setArguments(bundle);
                    ftransaction.replace(R.id.main_frame_layout,fragment);
                    ftransaction.addToBackStack(fragment.getClass().getName()).commit();
                }
            });




            return view;
        }



    }



}
