package com.i2group.auth.rest.transport.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

/**
 * Handles the generation and verification of JWT tokens.
 */
public class TokenManager {

  private static final String GENERIC_AUTH_ERROR = "Invalid authorization token.";
  private static final String ISSUER = "auth0";
  private static final String JWT_SECRET = "secret";

  /**
   * Generates a signed JWT token.
   *
   * @return The generated JWT token.
   */
  public static String generateToken() {
    final Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.SECOND, 10);
    return JWT.create()
        .withIssuer(ISSUER)
        .withExpiresAt(calendar.getTime())
        .sign(Algorithm.HMAC256(JWT_SECRET));
  }

  /**
   * Verifies the integrity of a JWT token.
   *
   * @param token The token to verify.
   * @return A {@link AuthValidator} with the result.
   */
  public static AuthValidator verifyToken(String token) {
    AuthValidator result;
    try {
      final Algorithm algorithm = Algorithm.HMAC256("secret");
      final JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer(ISSUER)
          .build();
      final DecodedJWT jwt = verifier.verify(token);
      result = new AuthValidator(token.equals(jwt.getToken()), GENERIC_AUTH_ERROR);
    } catch (TokenExpiredException ex) {
      result = new AuthValidator(false, "Expired token.", ex.getMessage());
    } catch (JWTVerificationException ex) {
      result = new AuthValidator(false, GENERIC_AUTH_ERROR, ex.getMessage());
    }

    return result;
  }
}
