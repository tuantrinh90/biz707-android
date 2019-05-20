package com.bon.customview.edittext.validators;

/**
 * IP address validator extend;
 *
 * @author Dang
 * @year 2016
 */

import android.os.Build;

import java.util.regex.Pattern;

public class IpValidator extends RegExpValidator {
    public static Pattern CNDITIONEXP;

    static {
        if (Build.VERSION.SDK_INT >= 8) {
            CNDITIONEXP = android.util.Patterns.IP_ADDRESS;
        } else {
            CNDITIONEXP = Pattern.compile(
                    "^((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                            + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                            + "|[1-9][0-9]|[0-9]))$");
        }
    }

    public IpValidator(String error) {
        super(CNDITIONEXP, error);
    }
}
