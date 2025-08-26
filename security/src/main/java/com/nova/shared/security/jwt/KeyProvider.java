package com.nova.shared.security.jwt;

import java.security.Key;

public interface KeyProvider {
    /** Key para firmar (privada o secreta) */
    Key signingKey();
    /** Key para verificación (pública o secreta) */
    Key verificationKey();
    /** Algoritmo de firma: e.g. HS256 o RS256 */
    String jwsAlgorithm();
}
