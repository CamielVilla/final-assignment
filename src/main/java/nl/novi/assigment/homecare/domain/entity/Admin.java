package nl.novi.assigment.homecare.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name = "Admin")
@DiscriminatorValue("admin")
public class Admin extends User{

}
