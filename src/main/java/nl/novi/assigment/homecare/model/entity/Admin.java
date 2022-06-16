package nl.novi.assigment.homecare.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity (name = "Admin")
@DiscriminatorValue("admin")
public class Admin extends User{

}
