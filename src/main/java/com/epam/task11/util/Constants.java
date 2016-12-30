package com.epam.task11.util;

public class Constants {
    public static final String DEFAULT_LOCALE = "defaultLocale";
    public static final String AVAILABLE_LOCALES = "availableLocales";
    public static final String LANGUAGE_DESCRIPTOR_NAME = "languageDescriptor";


    public static final String CAPTCHA_HANDLER_NAME = "captcha_handler";
    public static final String USER_CAPTCHA_CODE = "captchaConfirm";
    public static final String USER_CAPTCHA_CODE_HIDDEN = "captchaCodeInHiddenField";
    public static final String CAPTCHA_CODE_COOKIE = "captchaCodeInCookie";
    public static final String CAPTCHA_CODE_TO_PUT_INTO_HIDDEN = "captchaCodeToPutIn";
    public static final String CAPTCHA_IMAGE_GENERATION = "captchaImageGeneration";
    public static final String AVATARS_STORAGE = "AvatarImagePath";
    public static final String IMG_FORMAT = "jpg";
    //From requests
    public static final String REQUEST_PRODUCT_ID = "productId";
    public static final String REQUEST_CART_NEW_NUMBER_OF_ITEMS = "numberOfItems";

    //Form request
    public static final String ATTRIBUTES_APPENDER = "com_epam_pre_prod";
    public static final String REQUEST_PRODUCTS = "_products";
    public static final String REQUEST_MANUFACTURERS = "_manufacturers";
    public static final String REQUEST_CATEGORIES = "_categories";
    public static final String PRODUCTS_NUMBER_PAGES = "_number_of_pages";
    public static final String PRODUCT_FORM = "_product_form";
    public static final String PRODUCTS_COUNT = "_products_number";
    public static final String PRODUCTS_RANGE_FROM = "_products_range_from";
    public static final String PRODUCTS_RANGE_TO = "_products_range_to";
    public static final String CART_TO_DISPLAY = "_cart_to_display";
    public static final String ORDER_CART = "_order";

    //Session
    public static final String SESSION_CART = "_cart";
    public static final String SESSION_USER = "_user";
    public static final String SESSION_LOCALE = "locale";

    //Cookie
    public static final String COOKIE_LOCALE = "locale";

    //Product page
    public static final String SORT_TYPE = "sort";
    public static final String ITEMS_ON_PAGE = "itemsOnPage";
    public static final String PAGE_NUMBER = "page";
    public static final String PRICE_FROM = "priceFrom";
    public static final String PRICE_TO = "priceTo";
    public static final String PRODUCT_NAME = "productName";
    public static final String CATEGORIES = "category";
    public static final String MANUFACTURER = "manufacturer";


    //Product page settings
    public static final String DEFAULT_ITEMS_ON_PAGE = "9";
    public static final String DEFAULT_PAGE_SORTING_FIELD = "name";
    public static final String DEFAULT_PAGE_NUMBER = "1";

    //Patterns
    public static final String FIRST_NAME_PATTERN_RULE = "^([a-zA-Z]{1,20}|[\u0410-\u044f]{1,20})$";
    public static final String LAST_NAME_PATTERN_RULE = "^([a-zA-Z]{1,20}|[\u0410-\u044f]{1,20})$";
    public static final String EMAIL_PATTERN_RULE = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN_RULE = "^[a-zA-Z0-9_]{1,20}$";
    public static final String USERNAME_PATTERN_RULE = "^[a-zA-Z0-9]{1,20}$";
    public static final String PRODUCT_NAME_PATTERN_RULE = "^[a-zA-Z0-9 ]{1,20}$";
    public static final String PRICE_PATTERN_RULE = "^[0-9]+$";
    public static final String CATEGORY_PATTERN_RULE = "^[0-9]+$";
    public static final String MANUFACTURER_PATTERN_RULE = "^[0-9]+$";

    //Services
    public static final String MANUFACTURER_SERVICE_NAME = "manufacturerService";
    public static final String USER_SERVICE_NAME = "UserService";
    public static final String PRODUCT_SERVICE_NAME = "productService";
    public static final String CATEGORY_SERVICE_NAME = "categoryService";
    public static final String ORDER_SERVICE_NAME = "OrderService";

    //Manufacturer DB Fields
    public static final String DB_MANUFACTURER_ID = "idmanufacturer";
    public static final String DB_MANUFACTURER_NAME = "name";

    //Product DB Fields
    public static final String DB_PRODUCT_ID = "idproduct";
    public static final String DB_PRODUCT_NAME = "name";
    public static final String DB_PRODUCT_DESCRIPTION = "description";
    public static final String DB_PRODUCT_PRICE = "price";
    public static final String DB_PRODUCT_IMAGE_FIRST = "image_first";
    public static final String DB_PRODUCT_IMAGE_SECOND = "image_second";

    //User DB Fields
    public static final String DB_USER_ID = "iduser";
    public static final String DB_USER_NAME = "username";
    public static final String DB_FIRST_NAME = "first_name";
    public static final String DB_LAST_NAME = "last_name";
    public static final String DB_EMAIL = "email";
    public static final String DB_PASSWORD = "password";
    public static final String DB_SUBSCRIBE_NAME = "is_to_subscribe";
    public static final String DB_AVATAR_NAME = "avatar_name";
    public static final String DB_DATE_BAN = "date_ban";
    public static final String DB_FAIL_ATTEMPTS = "fail_attempts";

    //CategoryService DB Fields
    public static final String DB_CATEGORY_ID = "idcategory";
    public static final String DB_CATEGORY_NAME = "name";

    //Common DB Fields
    public static final String DB_COUNT = "count";

    public static final String WEB_XML_LANGUAGE_STORAGE_TYPE = "LanguageStorageType";
    public static final String LANGUAGE_STORAGE_TYPE_SESSION = "LanguageStorageTypeSession";
    public static final String LANGUAGE_STORAGE_TYPE_COOKIE = "LanguageStorageTypeCookie";
    public static final String TIME_LANGUAGE_COOKIE_LIVE = "timeLanguageCookie";
    public static final String REDIRECTED_URL = "urlToRedirect";

    public static final String DB_ROLE_ID = "idrole";
    public static final String DB_ROLE_NAME = "name";
    public static final String DB_ROLE_DESCRIPTION = "description";
}
