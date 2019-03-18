package com.elvis.mzmanager.network;

import com.elvis.mzmanager.App;

public class Api {




    public static final String SERVER_API = "http://sim.519liuliang.com/";


    /**
     * http://sim.519liuliang.com/pages/login.do
     * username: root
     * password: root
     */
    public static final String USER_LOGIN = getApi() + "pages/login.do";

    /**
     * http://sim.519liuliang.com/card/grid.do
     * responseFunction=grid
     * pageSize=100
     * pageNo=1
     * rfm=0.025559604469467256
     */
    public static final String GET_CARDLIST = getApi() + "card/grid.do";
    public static final String TOKEN_KEY = "jsessionid";

    private static String getApi() {
        return App.getInstance().getApi();
    }



    //error code

    public static  final  int RESPONSE_EXPIRED=7;
}
