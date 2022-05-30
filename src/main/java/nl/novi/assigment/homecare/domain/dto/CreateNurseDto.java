package nl.novi.assigment.homecare.domain.dto;

public class CreateNurseDto {


    private String name;
    private String email;
    private String password;
    private int bigNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(int bigNumber) {
        this.bigNumber = bigNumber;
    }
}
