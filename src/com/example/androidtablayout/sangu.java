package com.example.androidtablayout;

import org.json.JSONException;
import org.json.JSONObject;

public class sangu {
private Object user_id;

public String prasi() throws JSONException

{
	JSONObject js=new JSONObject();
	js.put("key", user_id);
	return (js.toString());
	
	
}

}