package com.orders.orderProcessingService.Security;

import com.orders.orderProcessingService.entity.User;
import com.orders.orderProcessingService.repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateJwtToken {

    private static final Logger logger = LogManager.getLogger(ValidateJwtToken.class);

    @Autowired
    UserRepository userRepository;

    public boolean validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken == null || !jwtToken.startsWith("Bearer ")) {
            return false;
        }
        jwtToken = jwtToken.substring(7); // Remove "Bearer " prefix
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey("3d8f2c4a3d9a3d2c4a3d9a3d8f2c4a3d9a3d2c4a3d9a3d8f2c4a3d9a3d2c4a3d")
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return checkClaimInDatabase(claims.get("sub").toString());


        } catch (ExpiredJwtException e) {
            logger.error("Token expired");
            return false;
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            logger.error("Invalid Token usage");
            return false;

        } catch (JwtException e) {
            logger.error(e.getMessage());
            return false;
        }
    }


    public boolean checkClaimInDatabase(String claimValue) {
        User user = userRepository.findByUsername(claimValue);
        if (user.getUsername().equals(claimValue))
            return true;
        else
            return false;
    }
}
