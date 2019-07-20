package br.atm.location.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
* 
* @author Wanessa Nascimento
*
*/
@Entity
public class GeoLocation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String lat;
	
	private String lng;

	public GeoLocation() {
	}

	public GeoLocation(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public Long getId() {
		return id;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoLocation other = (GeoLocation) obj;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GeolocationDao [lat=" + lat + ", lng=" + lng + "]";
	}
}
