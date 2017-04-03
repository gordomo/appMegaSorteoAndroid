package com.vetasrl.appmegasorteo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by mmori on 2/2/2017.
 */

public class logInHelper extends Application {

//    private String baseUrl = "http://10.0.2.2:80/megaback/";
    private String baseUrl = "http://grupovetasrl.com/proyectos/megaback/";

    public String getBaseUrl() {
        return baseUrl;
    }
    public boolean checkLogIn(Context context){

        if(getLocalUser(context) != "") return true;
        return false;
    }

    public String getLocalUser(Context context){

        SharedPreferences sharedPref = context.getSharedPreferences("AppMegaSorteo", Context.MODE_PRIVATE);
        String usr = sharedPref.getString("usr", "");
        return usr;
    }

    public void remoteLogInByUsrMail(){

    }

    public String getRemoteUrlForLogIn(String mail){
        String uri = this.baseUrl + "actions.php?action=getUsrByMail" + "&mail=" + mail;
        return uri;
    }

    public String getRemoteUrlForRegistrarse(String mail){
        String uri = this.baseUrl + "actions.php?action=addUsrSoloMail&mail=" + mail;
        return uri;
    }


    public String getRemoteUrlForNews(){
        String uri = this.baseUrl + "actions.php?action=getNews&habilitadas=1";
        return uri;
    }

    public String getRemoteUrlForWinners(){
        String uri = this.baseUrl + "actions.php?action=getGanadores&habilitadas=1";
        return uri;
    }

    public String getRemoteUrlForEditUsrs(String id, String gsmcode){
        String uri = this.baseUrl + "actions.php?action=editGcmUsr&id="+id+"&gsmcode="+gsmcode;
        return uri;
    }

    public void mostrarToast(Context context, String mensaje){
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, mensaje, duration);
        toast.show();
    }

}
