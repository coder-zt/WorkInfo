package com.working.domain;

/**
 * 用户的数据类型
 */
public class LoginInfo {

    /**
     * tenant_id : 000000
     * user_id : 1123598821738675201
     * dept_id : 1123598813738675201
     * post_id : 1123598817738675201
     * role_id : 1123598816738675201
     * oauth_id :
     * account : admin
     * user_name : admin
     * nick_name : 管理员
     * role_name : administrator
     * avatar : https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpc3N1c2VyIiwiYXVkIjoiYXVkaWVuY2UiLCJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJyb2xlX25hbWUiOiJhZG1pbmlzdHJhdG9yIiwicG9zdF9pZCI6IjExMjM1OTg4MTc3Mzg2NzUyMDEiLCJ1c2VyX2lkIjoiMTEyMzU5ODgyMTczODY3NTIwMSIsInJvbGVfaWQiOiIxMTIzNTk4ODE2NzM4Njc1MjAxIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJkZXRhaWwiOnsidHlwZSI6IndlYiJ9LCJ0b2tlbl90eXBlIjoiYWNjZXNzX3Rva2VuIiwiZGVwdF9pZCI6IjExMjM1OTg4MTM3Mzg2NzUyMDEiLCJhY2NvdW50IjoiYWRtaW4iLCJjbGllbnRfaWQiOiJzd29yZCIsImV4cCI6MTYwODgyNjA5MywibmJmIjoxNjA4ODIyNDkzfQ.Y3ZsZa4uPaQbul2JwVRnt-_ARNZFvpSY18izZliNH2oJjavVhIv8PE3ypVp6qRKORwp8rny99pEsnSqd9SInNA
     * refresh_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpc3N1c2VyIiwiYXVkIjoiYXVkaWVuY2UiLCJ1c2VyX2lkIjoiMTEyMzU5ODgyMTczODY3NTIwMSIsInRva2VuX3R5cGUiOiJyZWZyZXNoX3Rva2VuIiwiY2xpZW50X2lkIjoic3dvcmQiLCJleHAiOjE2MDk0MjcyOTMsIm5iZiI6MTYwODgyMjQ5M30.DIsjinw0TCIUweUKmwYv-2VRt_i_rxqsQlQVrhKMelKokUpudHGDiMosK6TJFlxHbSiHzWKwThjAnsxyMKUE8Q
     * token_type : bearer
     * expires_in : 3600
     * detail : {"type":"web"}
     * license : powered by bladex
     */

    private String tenant_id;
    private String user_id;
    private String dept_id;
    private String post_id;
    private String role_id;
    private String oauth_id;
    private String account;
    private String user_name;
    private String nick_name;
    private String role_name;
    private String avatar;
    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
    private DetailBean detail;
    private String license;

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getOauth_id() {
        return oauth_id;
    }

    public void setOauth_id(String oauth_id) {
        this.oauth_id = oauth_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public static class DetailBean {
        /**
         * type : web
         */

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "DetailBean{" +
                    "type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "tenant_id='" + tenant_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", dept_id='" + dept_id + '\'' +
                ", post_id='" + post_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", oauth_id='" + oauth_id + '\'' +
                ", account='" + account + '\'' +
                ", user_name='" + user_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", role_name='" + role_name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", detail=" + detail +
                ", license='" + license + '\'' +
                '}';
    }
}
