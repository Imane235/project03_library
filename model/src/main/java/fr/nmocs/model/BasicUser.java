package fr.nmocs.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BASIC")
public class BasicUser extends User {

}
