package com.bg.parser.json;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

class Json {
	
	private String path;
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	public Json(){
		this.path = JsonGlobals.Path;		
	}
	
	public void toJson(User user){
		Gson gson = new Gson();
		String json_user = gson.toJson(user);
		String json_file = path + user.getId_user() + ".json";
        try {
        	FileWriter Filewriter = new FileWriter(json_file);
        	Filewriter.write(json_user);
        	Filewriter.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println(json_user);
	}
	
}
