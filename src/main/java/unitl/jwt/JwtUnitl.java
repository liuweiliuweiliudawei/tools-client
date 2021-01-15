package unitl.jwt;


import cn.hutool.core.bean.BeanUtil;
import entil.JwtConfigurationProperties;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author levy
 * @date 2021年1月12日 10:44:25
 */
@AllArgsConstructor
@Component
public class JwtUnitl {

    private static  final Logger log = LoggerFactory.getLogger(JwtUnitl.class);

    private JwtConfigurationProperties jwtConfigurationProperties;

    /**
     * jwt-name
     */
    private static final String JWT_NAME = "jwt" ;
    /**
     * jwt-value
     */
    private static final String JWT_VALUE = "JWT" ;

    /**
     * 生成token
     */
    public String generateToken(Object jwtParam) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfigurationProperties.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Map<String, Object> stringObjectHashMap = BeanUtil.beanToMap(jwtParam);
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam(JWT_NAME, JWT_VALUE)
                .addClaims(stringObjectHashMap)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (jwtConfigurationProperties.getExpireTime().getSeconds() >= 0) {
            long expMillis = nowMillis + jwtConfigurationProperties.getExpireTime().getSeconds() * 1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        log.info("生成token:{}",builder.compact());
        return builder.compact();
    }

}
