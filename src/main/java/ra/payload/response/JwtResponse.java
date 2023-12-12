package ra.payload.response;

import java.util.List;

public class JwtResponse {
    String token;
    private String type = "Bearer";
//    private int userId;
    private String userName;
    private List<String> listRoles;

    public JwtResponse(String token, String userName, List<String> listRoles) {
        this.token = token;
        this.userName = userName;
        this.listRoles = listRoles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public int getUserId() {
//        return userId;
//    }

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }
}
