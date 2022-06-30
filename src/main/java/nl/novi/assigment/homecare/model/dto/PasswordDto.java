package nl.novi.assigment.homecare.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordDto {
    @NotNull
    private String oldPassword;
    @NotNull
    @Size(min = 6)
    private String newPassword;
    @NotNull
    @Size(min = 6)
    private String repeatNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }
}
