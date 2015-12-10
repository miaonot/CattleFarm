package util;

public class Cattle { // Å£µÄÊµÀı

	private String idOfCattle;
	private String feature;

	public Cattle(){
		this.feature = "";
		this.idOfCattle = "";
	}
	public Cattle(String id, String feature) {
		this.idOfCattle = id;
		this.feature = feature;
	}

	public String toString() {
		return " " + idOfCattle + " -- " + feature;
	}

	public String getIdOfCattle() {
		return idOfCattle;
	}

	public String getFeature() {
		return feature;
	}
}