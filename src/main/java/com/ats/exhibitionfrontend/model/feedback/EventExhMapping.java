
package com.ats.exhibitionfrontend.model.feedback;

public class EventExhMapping {

	private int mapId;

	private int exhId;

	private int eventId;

	private String eventName;

	private int isUsed;

	private String stallNo;

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

	public String getStallNo() {
		return stallNo;
	}

	public void setStallNo(String stallNo) {
		this.stallNo = stallNo;
	}

	@Override
	public String toString() {
		return "EventExhMapping [mapId=" + mapId + ", exhId=" + exhId + ", eventId=" + eventId + ", eventName="
				+ eventName + ", isUsed=" + isUsed + ", stallNo=" + stallNo + "]";
	}

	
}
