package model;

import java.util.List;

public class RespustaAPI {
	private List<DataItem> data;
	private boolean status;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}
