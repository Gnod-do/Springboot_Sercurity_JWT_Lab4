package ra.payload.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class SignupRequest {

    private String userName;

    private String password;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date created = new Date();
    private Set<String> listRoles;


    public SignupRequest(String userName, String password, Set<String> listRoles) {
        this.userName = userName;
        this.password = password;
        this.listRoles = listRoles;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        String dateNow = sdf.format(now);
        try {
            this.created = sdf.parse(dateNow);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(Set<String> listRoles) {
        this.listRoles = listRoles;
    }
}
