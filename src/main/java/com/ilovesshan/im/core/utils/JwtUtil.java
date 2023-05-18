package com.ilovesshan.im.core.utils;


import com.alibaba.fastjson.JSON;
import com.ilovesshan.im.model.po.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRETKEY = "121DD883-0E22-13E1-CF49-AF2F8F3324B1";

    public String generatorToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return generatorToken(map);
    }

    public User parseToken(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
        LinkedHashMap user = (LinkedHashMap) body.get("user");
        return JSON.parseObject(JSON.toJSONString(user), User.class);
    }


    private String generatorToken(Map<String, Object> map) {
        return Jwts.builder().setClaims(map)
                .setExpiration(generatorExpiration())
                .signWith(SignatureAlgorithm.HS512, SECRETKEY)
                .compact();
    }

    private Date generatorExpiration() {
        return new Date(System.currentTimeMillis() + 9000000);
    }
}