package com.example.invoices;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

// Shared Preference METHODS

public class AppPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AppPreference(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.s_pref_file), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Setting login status
    public void setLoginStatus(boolean status){
        editor.putBoolean(String.valueOf(R.string.s_pref_login_status), status);
        editor.commit();
    }
    public boolean getLoginStatus(){
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pref_login_status), false);
    }

    // For Message
    public void setDisplayId(Integer id){
        editor.putInt(String.valueOf(R.string.s_pref_id), id);
        editor.commit();
    }
    public Integer getDisplayId(){
        return sharedPreferences.getInt(String.valueOf(R.string.s_pref_id), 0);
    }

    public void setDisplayUsername(String username){
        editor.putString(String.valueOf(R.string.s_pref_username), username);
        editor.commit();
    }
    public String getDisplayUsername(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_username), "username");
    }

    public void setDisplayName(String name){
        editor.putString(String.valueOf(R.string.s_pref_name), name);
        editor.commit();
    }
    public String getDisplayName(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_name), "name");
    }

    public void setDisplaySurname(String surname){
        editor.putString(String.valueOf(R.string.s_pref_surname), surname);
        editor.commit();
    }
    public String getDisplaySurname(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_surname), "surname");
    }

    public void setDisplayEmail(String email){
        editor.putString(String.valueOf(R.string.s_pref_email), email);
        editor.commit();
    }
    public String getDisplayEmail(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_email), "email");
    }

    public void setDisplayCompany(String company){
        editor.putString(String.valueOf(R.string.s_pref_company), company);
        editor.commit();
    }
    public String getDisplayCompany(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_company), "company");
    }

    public void setDisplayAddress(String address){
        editor.putString(String.valueOf(R.string.s_pref_address), address);
        editor.commit();
    }
    public String getDisplayAddress(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_address), "address");
    }

    public void setDisplayCity(String city){
        editor.putString(String.valueOf(R.string.s_pref_city), city);
        editor.commit();
    }
    public String getDisplayCity(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_city), "city");
    }

    public void setDisplayNip(String nip){
        editor.putString(String.valueOf(R.string.s_pref_nip), nip);
        editor.commit();
    }
    public String getDisplayNip(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_nip), "nip");
    }

    public void setDisplayRegon(String regon){
        editor.putString(String.valueOf(R.string.s_pref_regon), regon);
        editor.commit();
    }
    public String getDisplayRegon(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_regon), "regon");
    }

    // For Message
    public void setDisplayMessage(String message){
        editor.putString(String.valueOf(R.string.s_pref_login_message), message);
        editor.commit();
    }
    public String getDisplayMessage(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_message), "message");
    }

    //For JWT
    public void setDisplayJwt(String jwt){
        editor.putString(String.valueOf(R.string.s_pref_jwt), jwt);
        editor.commit();
    }
    public String getDisplayJwt(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_jwt), "jwt");
    }

    // For TOAST Message for response
    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
