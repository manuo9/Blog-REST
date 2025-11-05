package com.spring.nscl.common;

/**
 * Common message constants used in ResponseModel across NSCL.
 */
public final class NscMessageConstants {

    private NscMessageConstants() {
        // Prevent instantiation
    }

    // ✅ General status messages
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String BAD_REQUEST = "BAD_REQUEST";

    // ✅ Common CRUD messages
    public static final String RECORD_CREATED = "Record created successfully.";
    public static final String RECORD_UPDATED = "Record updated successfully.";
    public static final String RECORD_DELETED = "Record deleted successfully.";
    public static final String RECORD_FETCHED = "Record fetched successfully.";
    public static final String RECORD_NOT_FOUND = "Record not found.";
    public static final String DUPLICATE_RECORD = "Duplicate record found.";
    public static final String OPERATION_FAILED = "Operation failed. Please try again later.";

    // ✅ Authentication / Authorization
    public static final String LOGIN_SUCCESS = "Login successful.";
    public static final String LOGIN_FAILED = "Invalid username or password.";
    public static final String TOKEN_EXPIRED = "Authentication token expired.";
    public static final String TOKEN_INVALID = "Invalid authentication token.";
    public static final String ACCESS_DENIED = "Access denied.";

    // ✅ Server & validation
    public static final String INTERNAL_SERVER_ERROR = "Internal server error occurred.";
    public static final String INVALID_INPUT = "Invalid input data provided.";
    public static final String MISSING_REQUIRED_FIELDS = "Required fields are missing.";

    // ✅ File or data handling
    public static final String FILE_UPLOAD_SUCCESS = "File uploaded successfully.";
    public static final String FILE_UPLOAD_FAILED = "File upload failed.";
    public static final String FILE_NOT_FOUND = "File not found.";
}
