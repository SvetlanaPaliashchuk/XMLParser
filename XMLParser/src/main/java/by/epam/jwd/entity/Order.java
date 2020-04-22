package by.epam.jwd.entity;

import java.util.List;

import by.epam.jwd.entity.type.Country;
import by.epam.jwd.entity.type.Meal;
import by.epam.jwd.entity.type.TourType;
import by.epam.jwd.entity.type.TransportType;

public class Order {
	private TourType tourType;
	private Country country;
	private String id;
	private String city;
	private String startDate;
	private int duration;
	private int numberOfTourists;
	private List<Tourist> tourists;
	private TransportType transport;
	private Meal meal;
	private double price;

	public Order() {
	}

	private Order(OrderBuilder builder) {
		this.tourType = builder.tour;
		this.country = builder.country;
		this.id = builder.id;
		this.city = builder.city;
		this.startDate = builder.date;
		this.duration = builder.duration;
		this.numberOfTourists = builder.numberOfTourists;
		this.tourists = builder.tourists;
		this.transport = builder.transport;
		this.meal = builder.meal;
		this.price = builder.price;
	}

	public TourType getTourType() {
		return tourType;
	}

	public Country getCountry() {
		return country;
	}

	public String getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getDate() {
		return startDate;
	}

	public int getDuration() {
		return duration;
	}

	public int getNumberOfTourists() {
		return numberOfTourists;
	}

	public List<Tourist> getTourists() {
		return tourists;
	}

	public TransportType getTransport() {
		return transport;
	}

	public Meal getMeal() {
		return meal;
	}

	public double getPrice() {
		return price;
	}

	public static class OrderBuilder {
		private TourType tour;
		private Country country;
		private String id;
		private String city;
		private String date;
		private int duration;
		private int numberOfTourists;
		private List<Tourist> tourists;
		private TransportType transport;
		private Meal meal;
		private double price;

		public OrderBuilder() {

		}

		public OrderBuilder setTourType(TourType tour) {
			this.tour = tour;
			return this;
		}

		public OrderBuilder setCountry(Country country) {
			this.country = country;
			return this;
		}

		public OrderBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public OrderBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		public OrderBuilder setDate(String date) {
			this.date = date;
			return this;
		}

		public OrderBuilder setDuration(int duration) {
			this.duration = duration;
			return this;
		}

		public OrderBuilder setNumberOfTourists(int numberOfTourists) {
			this.numberOfTourists = numberOfTourists;
			return this;
		}

		public OrderBuilder setTourists(List<Tourist> tourists) {
			this.tourists = tourists;
			return this;
		}

		public OrderBuilder setTransport(TransportType transport) {
			this.transport = transport;
			return this;
		}

		public OrderBuilder setMeal(Meal meal) {
			this.meal = meal;
			return this;
		}

		public OrderBuilder setPrice(double price) {
			this.price = price;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Order other = (Order) obj;

		return (tourType == other.getTourType() || (tourType != null && tourType.equals(other.getTourType())))
				&& (country == other.getCountry() || (country != null && country.equals(other.getCountry())))
				&& (id == other.getId()) && ((city != null && city.equals(other.getCity())))
				&& ((startDate != null && startDate.equals(other.getDate()))) && (duration == other.getDuration())
				&& (numberOfTourists == other.getNumberOfTourists())
				&& (tourists == other.getTourists() || (tourists != null && tourists.equals(other.getTourists())))
				&& (transport == other.getTransport() || (transport != null && transport.equals(other.getTransport())))
				&& (meal == other.getMeal() || (meal != null && meal.equals(other.getMeal())))
				&& (price == other.getPrice());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + duration;
		result = prime * result + numberOfTourists;
		result = prime * result + ((tourists == null) ? 0 : tourists.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", country=" + country + ", tour type=" + tourType + ", city="
				+ city + ", date=" + startDate + ", duration=" + duration + ", numberOfTourists=" + numberOfTourists
				+ ", tourists=" + tourists + ", transport=" + transport + ", meal=" + meal + ", price=" + price + "]";
	}
}