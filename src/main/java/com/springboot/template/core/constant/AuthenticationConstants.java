package com.springboot.template.core.constant;

public final class AuthenticationConstants {
    public static final String NAME_TEXT_PATTERN = "^[a-zA-ZçÇğĞıIiİöÖşŞüÜ]+[a-zA-ZçÇğĞıIiİöÖşŞüÜ\\s]+$";
    public static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[ !\\\"#$%&'()*+,-./:;<=>?@[]^_`{|}~]).{8,}$";
    public static final String USER_PREFIX = "USER_";
    public static final String USER_INITIAL_VALUE = "10000";
    public static final String USER_NUM_FORMAT = "%08d";
    public static final String USER_GENERATOR = "user_seq";
    public static final String GENERATOR_CLASS = "com.springboot.template.core.entity.id.generator.SequenceIdGenerator";
}
