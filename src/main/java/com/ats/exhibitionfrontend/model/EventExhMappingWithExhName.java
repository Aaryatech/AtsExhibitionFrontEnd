package com.ats.exhibitionfrontend.model;
 

public class EventExhMappingWithExhName {
	 
	private int mapId; 
	private int exhId; 
	private String exhName; 
	private int eventId; 
	private String eventName; 
	private int isUsed;
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public int getExhId() {
		return exhId;
	}
	public void setExhId(int exhId) {
		this.exhId = exhId;
	}
	public String getExhName() {
		return exhName;
	}
	public void setExhName(String exhName) {
		this.exhName = exhName;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "EventExhMappingWithExhName [mapId=" + mapId + ", exhId=" + exhId + ", exhName=" + exhName + ", eventId="
				+ eventId + ", eventName=" + eventName + ", isUsed=" + isUsed + "]";
	}
	
	

}
