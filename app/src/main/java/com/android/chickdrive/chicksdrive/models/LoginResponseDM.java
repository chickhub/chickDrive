package com.android.chickdrive.chicksdrive.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LoginResponseDM{

	@SerializedName("Status")
	private String status;

	@SerializedName("$id")
	private String id;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponseDM{" + 
			"status = '" + status + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}