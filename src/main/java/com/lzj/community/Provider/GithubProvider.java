package com.lzj.community.Provider;


import com.alibaba.fastjson.JSON;
import com.lzj.community.dto.AccessTokenDTO;
import com.lzj.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GithubProvider {

    /**
     *  通过okHTTP来模拟post & get请求 得到用于验证的AccessToken
     * @param accessTokenDTO  AccessToken的数据传递模型
     * @return AccessToken
     * @throws IOException
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String url = "https://github.com/login/oauth/access_token";

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String bodyStr = response.body().string();
            //access_token=ff5c174993ed1f24d1286df5352dce2b311fa248&scope=&token_type=bearer 需要拆分得到access_token
            // str ---> access_token=... , scope=... , token_type=...
            // ---> access_token

            String token = bodyStr.split("&")[0].split("=")[1];
            return token;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.github.com/user?access_token="+accessToken;

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String bodyStr = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(bodyStr, GitHubUser.class);
            return gitHubUser;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
