package com.android.chickdrive.chicksdrive.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LayerItem{

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

	@SerializedName("DailyProduction")
	private int dailyProduction;

	@SerializedName("ID")
	private int iD;

	@SerializedName("City")
	private String city;

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

	public void setDailyProduction(int dailyProduction){
		this.dailyProduction = dailyProduction;
	}

	public int getDailyProduction(){
		return dailyProduction;
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
			"LayerItem{" + 
			"weightMin = '" + weightMin + '\'' + 
			",weightMax = '" + weightMax + '\'' + 
			",image3 = '" + image3 + '\'' + 
			",image2 = '" + image2 + '\'' + 
			",image1 = '" + image1 + '\'' + 
			",dailyProduction = '" + dailyProduction + '\'' + 
			",iD = '" + iD + '\'' + 
			",city = '" + city + '\'' + 
			"}";
		}
}