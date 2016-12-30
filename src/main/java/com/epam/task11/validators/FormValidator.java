package com.epam.task11.validators;

import com.epam.task11.forms.LoginForm;
import com.epam.task11.forms.ProductForm;
import com.epam.task11.forms.RegisterForm;
import com.epam.task11.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FormValidator {

    public static HashMap<String, String> validateUserOnLogin(LoginForm loginForm) {
        HashMap<String, String> res = new HashMap<>();
        Pattern passwordPattern = Pattern.compile(Constants.PASSWORD_PATTERN_RULE);
        Pattern usernamePattern = Pattern.compile(Constants.USERNAME_PATTERN_RULE);
        if (loginForm.getUserName() == null || !usernamePattern.matcher(loginForm.getUserName()).matches()) {
            res.put("login_username_error", "Error username");
        }
        if (loginForm.getPassword() == null || !passwordPattern.matcher(loginForm.getPassword()).matches()) {
            res.put("login_password_error", "Error password");
        }
        return res;
    }

    public static HashMap<String, String> validateUserOnRegister(RegisterForm registerForm) {

        Pattern firstNamePattern = Pattern.compile(Constants.FIRST_NAME_PATTERN_RULE);
        Pattern usernamePattern = Pattern.compile(Constants.USERNAME_PATTERN_RULE);
        Pattern lastNamePattern = Pattern.compile(Constants.LAST_NAME_PATTERN_RULE);
        Pattern emailPattern = Pattern.compile(Constants.EMAIL_PATTERN_RULE);
        Pattern passwordPattern = Pattern.compile(Constants.PASSWORD_PATTERN_RULE);
        HashMap<String, String> res = new HashMap<>();
        if (registerForm.getFirstName() == null || !firstNamePattern.matcher(registerForm.getFirstName()).matches()) {
            res.put("firstName", "firstName error");
        }
        if (registerForm.getLastName() == null || !lastNamePattern.matcher(registerForm.getLastName()).matches()) {
            res.put("lastName", "lastName error");
        }
        if (registerForm.getUserName() == null || !usernamePattern.matcher(registerForm.getUserName()).matches()) {
            res.put("userName", "userName error");
        }
        if (registerForm.getEmail() == null || !emailPattern.matcher(registerForm.getEmail()).matches()) {
            res.put("email", "email error");
        }
        if (registerForm.getPassword() == null || !passwordPattern.matcher(registerForm.getPassword()).matches()) {
            res.put("password", "password error");
        }
        return res;
    }

    public static Map<String, String> validateProductForm(ProductForm productForm) {
        Map<String, String> resMap = new HashMap<>();
        Pattern productNameFieldPattern = Pattern.compile(Constants.PRODUCT_NAME_PATTERN_RULE);
        Pattern priceFieldPattern = Pattern.compile(Constants.PRICE_PATTERN_RULE);
        Pattern categoriesPattern = Pattern.compile(Constants.CATEGORY_PATTERN_RULE);
        Pattern manufacturersPattern = Pattern.compile(Constants.MANUFACTURER_PATTERN_RULE);
        if (productForm.getProductName() != null
                && productForm.getProductName().length() != 0
                && !productNameFieldPattern.matcher(productForm.getProductName()).matches()) {
            resMap.put("productNameError", "Wrong product name error");
        }
        if (productForm.getPriceFrom() != null
                && productForm.getPriceFrom().length() != 0
                && !priceFieldPattern.matcher(productForm.getPriceFrom()).matches()) {
            resMap.put("priceFromError", "Wrong priceFrom error");
        }
        if (productForm.getPriceTo() != null
                && productForm.getPriceTo().length() != 0
                && !priceFieldPattern.matcher(productForm.getPriceTo()).matches()) {
            resMap.put("priceToError", "Wrong priceTo error");
        }
        if (productForm.getCategories() != null) {
            for (String str : productForm.getCategories()) {
                if (!categoriesPattern.matcher(str).matches()) {
                    resMap.put("categoriesError", "Wrong category parameter");
                    break;
                }
            }
        }
        if (productForm.getManufacturer() != null) {
            for (String str : productForm.getManufacturer()) {
                if (!manufacturersPattern.matcher(str).matches()) {
                    resMap.put("manufacturerError", "Wrong manufacturer parameter");
                    break;
                }
            }
        }
        return resMap;
    }
}
