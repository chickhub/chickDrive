package com.android.chickdrive.chicksdrive.models.more;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GenericMedFeedHatchDM{

	@SerializedName("ContactNo")
	private int contactNo;

	@SerializedName("Email")
	private String email;

	@SerializedName("Address")
	private String address;

	@SerializedName("ID")
	private int iD;

	@SerializedName("City")
	private String city;

	@SerializedName("$id")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("Logo")
	private String logo;

	public void setContactNo(int contactNo){
		this.contactNo = contactNo;
	}

	public int getContactNo(){
		return contactNo;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLogo(String logo){
		this.logo = logo;
	}

	public String getLogo(){
		return logo;
	}

	@Override
 	public String toString(){
		return 
			"GenericMedFeedHatchDM{" + 
			"contactNo = '" + contactNo + '\'' + 
			",email = '" + email + '\'' + 
			",address = '" + address + '\'' + 
			",iD = '" + iD + '\'' + 
			",city = '" + city + '\'' + 
			",$id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",logo = '" + logo + '\'' + 
			"}";
		}
}