package dev.reddy.olm;

public class Constants {

    public static final String ROLE = "role";
    public static final String EMPTY_VALUE = "empty";
    public static final String AUTHORITIES = "authorities";

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_DELIMITER = ",";
    public static final String USER_AUTHORITIES = "book:reserve,book:unreserve";
    public static final String ADMIN_AUTHORITIES = "user:create,user:update,user:read,book:create,book:read,book:update,book:delete";
    public static final String SUPER_ADMIN_AUTHORITIES = "user:create,user:update,user:read,user:delete,book:create,book:read,book:update,book:delete";
    public static final String MANAGER_AUTHORITIES = "book:create,book:read,book:update,book:delete";
}
