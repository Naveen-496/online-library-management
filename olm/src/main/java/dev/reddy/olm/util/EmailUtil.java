package dev.reddy.olm.util;

public class EmailUtil {

    public static String getEmailMessage(String name, String host, String token) {
        final String message = "<div style=\"font-family: Arial, sans-serif; background-color: #f2f2f2; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Hello <span style=\"font-weight: bold;\">" + name + "</span>,</h2>"
                + "<p>Your new account has been created.</p>"
                + "<p>Please click the link below to activate your account:</p>"
                + "<div style=\"margin-block: 3rem; \"><a href=\"" + getVerificationUrl(host, token) + "\" style=\"display: inline-block; background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;\">Activate Account</a></div>"
                + "<p><b>The Support Team</b></p>"
                + "</div>";
        return message;
    }


    public static String getMessagePasswordReset(String name, String
            host, String token) {
        final String message = "<div style=\"font-family: Arial, sans-serif; background-color: #f2f2f2; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Hello <span style=\"font-weight: bold;\">" + name + "</span>,</h2>"
                + "<p>Please use the below link to reset your password.</p>"
                + "<div style=\"margin-block: 3rem; \"><a href=\"" + getResetPasswordUrl(host, token) + "\" style=\"display: inline-block; background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;\">Activate Account</a></div>"
                + "<p><b>The Support Team</b></p>"
                + "</div>";
        return message;
    }

    private static String getVerificationUrl(String host, String token) {
        return host + "/verify/account?token=" + token;
    }

    private static String getResetPasswordUrl(String host, String token) {
        return host + "/verify/password?token=" + token;
    }

}
