/*
 * Copyright 2015 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.android.authorization.oauth2;

import android.net.Uri;
import android.util.Pair;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import static org.jboss.aerogear.android.pipe.util.UrlUtils.appendToBaseURL;

/**
 * A collection of utility methods for working with OAuth2
 *
 */
public final class OAuth2Utils {

    private OAuth2Utils() {
    }

    public static URL buildAuthzURL(OAuth2Properties config, String state) throws UnsupportedEncodingException, MalformedURLException {
        URL authzEndpoint = appendToBaseURL(config.getBaseURL(), config.getAuthzEndpoint());
        Uri redirectURL = Uri.parse(config.getRedirectURL());
        
        ArrayList<String> scopes = new ArrayList<String>(config.getScopes());
        String clientId = config.getClientId();

        String query = "?scope=%s&redirect_uri=%s&client_id=%s&state=%s&response_type=code";
        query = String.format(query, formatScopes(scopes),
                URLEncoder.encode(redirectURL.toString(), "UTF-8"),
                clientId, state);

        if (config.getAdditionalAuthorizationParams() != null
                && config.getAdditionalAuthorizationParams().size() > 0) {
            for (Pair<String, String> param : config.getAdditionalAuthorizationParams()) {
                query += String.format("&%s=%s", URLEncoder.encode(param.first, "UTF-8"), URLEncoder.encode(param.second, "UTF-8"));
            }
        }

        return new URL(authzEndpoint.toString() + query);
    }

    public static String formatScopes(ArrayList<String> scopes) throws UnsupportedEncodingException {

        StringBuilder scopeValue = new StringBuilder();
        String append = "";
        for (String scope : scopes) {
            scopeValue.append(append);
            scopeValue.append(URLEncoder.encode(scope, "UTF-8"));
            append = "+";
        }

        return scopeValue.toString();
    }

}
