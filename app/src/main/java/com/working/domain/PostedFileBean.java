package com.working.domain;

/**
 * 文件上传成功的数据
 */
public class PostedFileBean {

    /**
     * code : 200
     * data : {"attachId":-1,"domain":"http://114.115.215.68:9000/caster","link":"http://114.115.215.68:9000/caster/upload/20210103/bd0563c8bad915b41c84d11309d30bdc.jpeg","name":"upload/20210103/bd0563c8bad915b41c84d11309d30bdc.jpeg","originalName":"1609648989750930.jpeg"}
     * msg : 操作成功
     * success : true
     */

    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * attachId : -1
         * domain : http://114.115.215.68:9000/caster
         * link : http://114.115.215.68:9000/caster/upload/20210103/bd0563c8bad915b41c84d11309d30bdc.jpeg
         * name : upload/20210103/bd0563c8bad915b41c84d11309d30bdc.jpeg
         * originalName : 1609648989750930.jpeg
         */

        private int attachId;
        private String domain;
        private String link;
        private String name;
        private String originalName;

        public int getAttachId() {
            return attachId;
        }

        public void setAttachId(int attachId) {
            this.attachId = attachId;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }
    }
}
