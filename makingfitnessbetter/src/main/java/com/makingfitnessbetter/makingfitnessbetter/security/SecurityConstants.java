package com.makingfitnessbetter.makingfitnessbetter.security;

public class SecurityConstants {
    // on milli seconds ( / 1000 to have the seconds )
    //this token is valid for 7 day token
    public static final long Token_Expiration_Time=604800000;
    public static final String Token_Prefix="Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String TOKEN_SECRET = "dfg893hdc475zwerop4tghg4ddfdfgdsdfeqaas?=-0ljznm0-9";
    public static String getTokenSecret(){return TOKEN_SECRET;}

}
