package com.working.domain;

public class UserInfo {

    /**
     * code : 200
     * success : true
     * data : {"id":"1342396148266156034","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-25 17:05:56","updateUser":"1123598821738675201","updateTime":"2021-01-01 19:00:48","status":1,"isDeleted":0,"tenantId":"000000","code":"","userType":1,"account":"yg01","name":"花舞","realName":"花舞","avatar":"","email":"","phone":"","birthday":"","sex":2,"roleId":"1123598816738675204","deptId":"1123598813738675203","postId":"1123598817738675208","tenantName":"","userTypeName":"WEB","roleName":"普通员工","deptName":"白云第一中队","postName":"普通员工","sexName":"女","userExt":""}
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 1342396148266156034
         * createUser : 1123598821738675201
         * createDept : 1123598813738675201
         * createTime : 2020-12-25 17:05:56
         * updateUser : 1123598821738675201
         * updateTime : 2021-01-01 19:00:48
         * status : 1
         * isDeleted : 0
         * tenantId : 000000
         * code :
         * userType : 1
         * account : yg01
         * name : 花舞
         * realName : 花舞
         * avatar :
         * email :
         * phone :
         * birthday :
         * sex : 2
         * roleId : 1123598816738675204
         * deptId : 1123598813738675203
         * postId : 1123598817738675208
         * tenantName :
         * userTypeName : WEB
         * roleName : 普通员工
         * deptName : 白云第一中队
         * postName : 普通员工
         * sexName : 女
         * userExt :
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String tenantId;
        private String code;
        private int userType;
        private String account;
        private String name;
        private String realName;
        private String avatar;
        private String email;
        private String phone;
        private String birthday;
        private int sex;
        private String roleId;
        private String deptId;
        private String postId;
        private String tenantName;
        private String userTypeName;
        private String roleName;
        private String deptName;
        private String postName;
        private String sexName;
        private String userExt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateDept() {
            return createDept;
        }

        public void setCreateDept(String createDept) {
            this.createDept = createDept;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getTenantName() {
            return tenantName;
        }

        public void setTenantName(String tenantName) {
            this.tenantName = tenantName;
        }

        public String getUserTypeName() {
            return userTypeName;
        }

        public void setUserTypeName(String userTypeName) {
            this.userTypeName = userTypeName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getSexName() {
            return sexName;
        }

        public void setSexName(String sexName) {
            this.sexName = sexName;
        }

        public String getUserExt() {
            return userExt;
        }

        public void setUserExt(String userExt) {
            this.userExt = userExt;
        }
    }
}
