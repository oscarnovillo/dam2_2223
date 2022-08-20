package model;

public class DataItem{
	private String name;
	private String id;
	private String abbr;
	private Logos logos;
	private String slug;

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getAbbr(){
		return abbr;
	}

	public Logos getLogos(){
		return logos;
	}

	public String getSlug(){
		return slug;
	}
}
