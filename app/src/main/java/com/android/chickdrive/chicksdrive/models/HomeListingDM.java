package com.android.chickdrive.chicksdrive.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class HomeListingDM{

	@SerializedName("Broyler")
	private List<HomeGenericLisDM> broyler;

	@SerializedName("Layer")
	private List<HomeGenericLisDM> layer;

	public void setBroyler(List<HomeGenericLisDM> broyler){
		this.broyler = broyler;
	}

	public List<HomeGenericLisDM> getBroyler(){
		return broyler;
	}

	public void setLayer(List<HomeGenericLisDM> layer){
		this.layer = layer;
	}

	public List<HomeGenericLisDM> getLayer(){
		return layer;
	}

	@Override
 	public String toString(){
		return 
			"HomeListingDM{" + 
			"broyler = '" + broyler + '\'' + 
			",layer = '" + layer + '\'' + 
			"}";
		}
}