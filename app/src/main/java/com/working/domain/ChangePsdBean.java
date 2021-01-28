package com.working.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ChangePsdBean implements ICommitData {

    public ChangePsdBean(String oldPassword, String newPassword, String newPassword1) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPassword1 = newPassword1;
    }

    /**
     * oldPassword :
     * newPassword :
     * newPassword1 :
     */

    private String oldPassword;
    private String newPassword;
    private String newPassword1;

    @Override
    public void setStatus(int status) {

    }

    @Override
    public int getStatus() {
        return 0;
    }
}
