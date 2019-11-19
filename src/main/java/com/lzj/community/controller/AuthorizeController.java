package com.lzj.community.controller;

import com.lzj.community.Provider.GithubProvider;
import com.lzj.community.dto.AccessTokenDTO;
import com.lzj.community.dto.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Authorizing OAuth APPs:
 *      1. Request a user's GitHub identity : GET https://github.com/login/oauth/authorize
 *      2. Users are redirected back to your site by GitHub :POST https://github.com/login/oauth/access_token
 *      3. Response: access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
 *      4. Use the access token to access the API : GET https://api.github.com/user with AccessToken
 *
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_sercret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

//    @Autowired
//    private HttpServletRequest httpServletRequest;


    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest httpServletRequest) throws IOException {


        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_sercret);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        System.out.println(accessToken);
//        System.out.println("username="+ githubProvider.getUser(accessToken).getName());
        GitHubUser gitHubUser = githubProvider.getUser(accessToken);
        if(gitHubUser == null){
            //登陆失败 11/19 : 缺失失败的提示信息

            return "redirect:/"; //执行redirect的时候会把当前路径加上（相当于寻找当前路径下的资源)
        }else{
            //登陆成功 需要进行HTTP的状态化
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", gitHubUser);
            return "redirect:/";

        }
    }
}
