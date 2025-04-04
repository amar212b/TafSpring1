package com.project.utils;

/**
 * Class containing methods for generating dynamic values.
 */
public class DynamicValues {

    public String getDynamicUsername() {
        return "GeneratedUser123";
    }

    public String getDynamicPassword() {
        return "SecurePassword@123";
    }

    public String getTestMessage() {
        return "This is a dynamic test message.";
    }

    public String getUserWithRole(String role) {
        return "UserRole-" + role;
    }

}
