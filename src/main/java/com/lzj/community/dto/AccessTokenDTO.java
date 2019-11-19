package com.lzj.community.dto;

/**
 * If the user accepts your request, GitHub redirects back to your site with a temporary code in a code parameter
 * as well as the state you provided in the previous step in a state parameter.
 * The temporary code will expire after 10 minutes.
 * If the states don't match, then a third party created the request, and you should abort the process
 */

public class AccessTokenDTO {

    private String client_id;
    private String redirect_uri;
    private String client_secret;
    private String code;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
