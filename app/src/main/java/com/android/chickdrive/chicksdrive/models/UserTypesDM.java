package com.android.chickdrive.chicksdrive.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class UserTypesDM{

	@SerializedName("ID")
	private int iD;

	@SerializedName("TypeUser")
	private String typeUser;

	@SerializedName("$id")
	private String id;

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setTypeUser(String typeUser){
		this.typeUser = typeUser;
	}

	public String getTypeUser(){
		return typeUser;
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
			"UserTypesDM{" + 
			"iD = '" + iD + '\'' + 
			",typeUser = '" + typeUser + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}