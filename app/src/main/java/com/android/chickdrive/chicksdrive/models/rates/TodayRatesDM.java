package com.android.chickdrive.chicksdrive.models.rates;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static android.os.UserHandle.readFromParcel;

@Generated("com.robohorse.robopojogenerator")
public class TodayRatesDM implements Parcelable {

	@SerializedName("LEggRate")
	private int lEggRate;

	@SerializedName("LCage")
	private int lCage;

	@SerializedName("City")
	private City city;

	@SerializedName("BMeat")
	private Object bMeat;

	@SerializedName("Date")
	private String date;

	@SerializedName("BRetailAlive")
	private int bRetailAlive;

	@SerializedName("LGramRate")
	private int lGramRate;

	@SerializedName("BFarmRate")
	private int bFarmRate;

	@SerializedName("ID")
	private int iD;

	@SerializedName("BWholeSaleAlive")
	private int bWholeSaleAlive;

	@SerializedName("LFloor")
	private int lFloor;

	@SerializedName("LStarter")
	private int lStarter;

	@SerializedName("$id")
	private String id;

	public TodayRatesDM(Parcel in) {
		super();
		readFromParcel(in);
	}

	public void setLEggRate(int lEggRate){
		this.lEggRate = lEggRate;
	}

	public int getLEggRate(){
		return lEggRate;
	}

	public void setLCage(int lCage){
		this.lCage = lCage;
	}

	public int getLCage(){
		return lCage;
	}

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setBMeat(Object bMeat){
		this.bMeat = bMeat;
	}

	public Object getBMeat(){
		return bMeat;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setBRetailAlive(int bRetailAlive){
		this.bRetailAlive = bRetailAlive;
	}

	public int getBRetailAlive(){
		return bRetailAlive;
	}

	public void setLGramRate(int lGramRate){
		this.lGramRate = lGramRate;
	}

	public int getLGramRate(){
		return lGramRate;
	}

	public void setBFarmRate(int bFarmRate){
		this.bFarmRate = bFarmRate;
	}

	public int getBFarmRate(){
		return bFarmRate;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setBWholeSaleAlive(int bWholeSaleAlive){
		this.bWholeSaleAlive = bWholeSaleAlive;
	}

	public int getBWholeSaleAlive(){
		return bWholeSaleAlive;
	}

	public void setLFloor(int lFloor){
		this.lFloor = lFloor;
	}

	public int getLFloor(){
		return lFloor;
	}

	public void setLStarter(int lStarter){
		this.lStarter = lStarter;
	}

	public int getLStarter(){
		return lStarter;
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
			"TodayRatesDM{" + 
			"lEggRate = '" + lEggRate + '\'' + 
			",lCage = '" + lCage + '\'' + 
			",city = '" + city + '\'' + 
			",bMeat = '" + bMeat + '\'' + 
			",date = '" + date + '\'' + 
			",bRetailAlive = '" + bRetailAlive + '\'' + 
			",lGramRate = '" + lGramRate + '\'' + 
			",bFarmRate = '" + bFarmRate + '\'' + 
			",iD = '" + iD + '\'' + 
			",bWholeSaleAlive = '" + bWholeSaleAlive + '\'' + 
			",lFloor = '" + lFloor + '\'' + 
			",lStarter = '" + lStarter + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {


	}

    public static final Parcelable.Creator<TodayRatesDM> CREATOR = new Parcelable.Creator<TodayRatesDM>() {
        public TodayRatesDM createFromParcel(Parcel in) {
            return new TodayRatesDM(in);
        }

        public TodayRatesDM[] newArray(int size) {

            return new TodayRatesDM[size];
        }

    };


}