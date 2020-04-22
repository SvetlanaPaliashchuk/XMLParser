package by.epam.jwd.entity.type;
public enum OrderTagName {
	ORDERS("orders"),
	ORDER("order"),
    ID("id"),
    TOUR_TYPE("tourType"),
    COUNTRY("country"),
    STARTDATE("startDate"),
    DURATION("duration"),
    CITY("city"),
    NUMBEROFTOURISTS("numberOfTourists"),
    TOURISTS("tourists"),
    TOURIST("tourist"),
    SURNAME("surname"),
    NAME("name"),
    PASSPORT("passport"),
    TRANSPORT("transport"),
    MEAL("meal"),
    PRICE("price");
	
	 private String name;

	 OrderTagName(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }
}