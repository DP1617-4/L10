
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Chorbi extends Actor {

	// Constructors -----------------------------------------------------------

		public Chorbi() {
			super();
		}

		// Attributes -------------------------------------------------------------
		
		private String picture;
		private String description;
		private String relationshipType;
		private Date bithDate;
		private String genre;
		private Boolean banned;
		private String country;
		private String state;
		private String province;
		private String city;
		
		@NotBlank
		@URL
		public String getPicture() {
			return picture;
		}
		public void setPicture(String picture) {
			this.picture = picture;
		}
		@NotBlank
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@NotBlank
		@Pattern(regexp = "^ACTIVITIES|FRIENDSHIP|LOVE$")
		public String getRelationshipType() {
			return relationshipType;
		}
		public void setRelationshipType(String relationshipType) {
			this.relationshipType = relationshipType;
		}
		@Past
		public Date getBithDate() {
			return bithDate;
		}
		public void setBithDate(Date bithDate) {
			this.bithDate = bithDate;
		}
		@NotBlank
		@Pattern(regexp = "^MAN|WOMAN$")
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public Boolean getBanned() {
			return banned;
		}
		public void setBanned(Boolean banned) {
			this.banned = banned;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		@NotBlank
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}

		// Relationships ----------------------------------------------------------

}