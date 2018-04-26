package org.great.entity;


public class CarPart {
	private String id;
    private String name;
    private double buyprice;
    private String filecode;
    private String filepath;
    private String imgpath;
	public CarPart() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBuyprice() {
		return buyprice;
	}
	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}

	public String getFilecode() {
		return filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
    
    
}
