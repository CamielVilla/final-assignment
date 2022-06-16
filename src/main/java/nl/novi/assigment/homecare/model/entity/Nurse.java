package nl.novi.assigment.homecare.model.entity;

import javax.persistence.*;


@Entity (name = "Nurse")
@DiscriminatorValue("nurse")
public class Nurse extends User{
    private int bigNumber;


    public int getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(int bigNumber) {
        this.bigNumber = bigNumber;
    }
}
