package com.example.ims.constants;

public class ImsConstants {

    public  class ApiPaths{
        public static final String INDENT_ROOT="api/v1/indents";
        public static final String INDENT_ID ="/{indentId}";

        public static final String RETURN_INDENT_ROOT="/return";
        public static final String RETURN_INDENT_ID ="/return/{indentId}";

        public static final String LOCATION_ROOT="api/v1/locations";
        public static final String LOCATION_ID ="/{locationId}";
        public static final String LOCATION_PLACE_PRODUCTS ="/{locationId}/placeProducts";
        public static final String LOCATION_REMOVE_PRODUCTS ="/{locationId}/removeProducts";
        public static final String LOCATION_PRODUCTS ="/{locationId}/products";
        public static final String LOCATION_INDENTS ="/{locationId}/indents";
        public static final String LOCATION_EMPTY ="/empty";

        public static final String PRODUCT_ROOT="api/v1/products";
        public static final String PRODUCT_ID ="/{productId}";
        public static final String PRODUCT_LOCATIONS ="/{productId}/locations";
        public static final String PRODUCT_LOWSTOCK ="/lowstock";
        public static final String PRODUCT_SLOWMOVING ="/slowmoving";
        public static final String PRODUCT_FASTMOVING ="/fastmoving";

        public static final String USER_ROOT="api/v1/users";
        public static final String USER_ID ="/{userId}";
        public static final String LOGIN ="/login";
        public static final String CHANGE_PASSWORD ="/change_password";
        public static final String DEPARTMENTS ="/departments";
        public static final String DEPARTMENT_ID ="/departments/{department_id}";


        public static final String ENTERPRISE_ROOT="api/v1/enterprices";
        public static final String ENTERPRICE_ID ="/{enterpriceId}";
    };

    public enum IndentTypes {
        INCOMING,
        OUTGOING;

    }

    public enum IndentStatuses {
        PENDING,
        DELIVERED;

    }

    public enum ProductStatus{
        ACTIVE,
        DELETED
    }

    public enum EnterpriseTypes{
        SUPPLIER,
        DISTRIBUTOR,
        CLIENT
    }
}
