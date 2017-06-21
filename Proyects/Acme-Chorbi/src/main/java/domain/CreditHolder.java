
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class CreditHolder extends Actor {

	// Constructors -----------------------------------------------------------

	public CreditHolder() {
		super();
	}

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

}
