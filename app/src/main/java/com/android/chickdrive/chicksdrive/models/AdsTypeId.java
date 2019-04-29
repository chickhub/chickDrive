package com.android.chickdrive.chicksdrive.models;

public class AdsTypeId{
	private String type;
	private int iD;
	private String id;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
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
			"AdsTypeId{" + 
			"type = '" + type + '\'' + 
			",iD = '" + iD + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}
