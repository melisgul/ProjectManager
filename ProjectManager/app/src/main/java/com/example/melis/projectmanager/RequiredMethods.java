package com.example.melis.projectmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by melisgulenay on 13/12/2017.
 */

public class RequiredMethods {

    public String giveDateFormat(Date comingDate){
        String sDate = new SimpleDateFormat("dd-MM-yyyy").format(comingDate);
        return sDate;
    }


}
