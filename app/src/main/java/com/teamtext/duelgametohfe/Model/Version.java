package com.teamtext.duelgametohfe.Model;

public class Version {
    /**
     * errors :
     * message :
     * result : {"id":1,"version_name":"Terial1","version_id":"1","required":"0","changes":"This is just for trial","link":"google.com","created_at":"2019-11-15 17:48:22","updated_at":"2019-11-15 17:48:22"}
     * status : 200
     */

    private String errors;
    private String message;
    private ResultBean result;
    private int status;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * id : 1
         * version_name : Terial1
         * version_id : 1
         * required : 0
         * changes : This is just for trial
         * link : google.com
         * created_at : 2019-11-15 17:48:22
         * updated_at : 2019-11-15 17:48:22
         */

        private int id;
        private String version_name;
        private String version_id;
        private String required;
        private String changes;
        private String link;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public String getVersion_id() {
            return version_id;
        }

        public void setVersion_id(String version_id) {
            this.version_id = version_id;
        }

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getChanges() {
            return changes;
        }

        public void setChanges(String changes) {
            this.changes = changes;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
