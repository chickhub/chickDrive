package com.android.chickdrive.chicksdrive.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BroylerItem{

	@SerializedName("Days_Age")
	private int daysAge;

	@SerializedName("WeightMin")
	private int weightMin;

	@SerializedName("WeightMax")
	private int weightMax;

	@SerializedName("Image3")
	private String image3;

	@SerializedName("Image2")
	private String image2;

	@SerializedName("Image1")
	private String image1;

	@SerializedName("Quantity")
	private int quantity;

	@SerializedName("ID")
	private int iD;

	@SerializedName("City")
	private String city;

	public void setDaysAge(int daysAge){
		this.daysAge = daysAge;
	}

	public int getDaysAge(){
		return daysAge;
	}

	public void setWeightMin(int weightMin){
		this.weightMin = weightMin;
	}

	public int getWeightMin(){
		return weightMin;
	}

	public void setWeightMax(int weightMax){
		this.weightMax = weightMax;
	}

	public int getWeightMax(){
		return weightMax;
	}

	public void setImage3(String image3){
		this.image3 = image3;
	}

	public String getImage3(){
		return image3;
	}

	public void setImage2(String image2){
		this.image2 = image2;
	}

	public String getImage2(){
		return image2;
	}

	public void setImage1(String image1){
		this.image1 = image1;
	}

	public String getImage1(){
		return image1;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
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

	@Override
 	public String toString(){
		return 
			"BroylerItem{" + 
			"days_Age = '" + daysAge + '\'' + 
			",weightMin = '" + weightMin + '\'' + 
			",weightMax = '" + weightMax + '\'' + 
			",image3 = '" + image3 + '\'' + 
			",image2 = '" + image2 + '\'' + 
			",image1 = '" + image1 + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",iD = '" + iD + '\'' + 
			",city = '" + city + '\'' + 
			"}";
		}
}