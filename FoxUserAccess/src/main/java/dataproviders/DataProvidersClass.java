package dataproviders;
import Utilities.CommonUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.*;

public class DataProvidersClass {

    static String[][] registeredString;
    public static Set<String> uniqueRandomString;
    static {
        uniqueRandomString = new LinkedHashSet<>();
    }
    @DataProvider(name="RegisterValid")
    public Object[][] getValidData(Method m)
    {

        for(int i=0;i<6;i++)
        {
            String s=CommonUtils.generateRandomEmailAddress("@fox.com",9);
                uniqueRandomString.add(s);
        }
        return getStrings(m);
    }

    private String[][] getStrings(Method m) {
        String[] allRandom=uniqueRandomString.toArray(new String[0]);
        if(!m.getName().contains("Login"))
        {
            registeredString = getStrings(allRandom);

        }
        return registeredString;
    }

    private String[][] getStrings(String[] allRandom) {

        String[][] string = {
                {"Rahul", "Khanna", allRandom[0], "M", "Fox@123"},
                {"Ishita", "Aggarwal", allRandom[1], "F", "FoxOut"},
                {"Test", "Test2", allRandom[2], "o", "Fox123"},
                {"Rahul", "Khanna", allRandom[3], "M", "123456"},
                {"Ishita", "Aggarwal", allRandom[4], "F", "!@#$%*&"},
                {"Ishita", "Agrawal", allRandom[5], "F", "TestingFoxUserAccess"}
        };
           return string;
        }

    @DataProvider(name="RegisterInValidPassword")
    public Object[][] getInvalidPasswordData()
    {
        return new String[][]{
                {"Test","Oberoi","Testoberoi123@fox.com","M","Fox"},
                {"Rahul","Oberoi","Rahul321qwe@fox.com","M","FoxIn"},
                {"Rahul","Oberoi","Rahul1994qwe@fox.com","M",""},
                {"Rahul","Oberoi","Rahul195qwe@fox.com","M",null},
                {"Rahul","Oberoi","","M","FoxRun"},
                {"Rahul","Oberoi",null,"M","FoxRun"},
        };
    }

    @DataProvider(name="LoginInValid")
    public Object[][] getInValidLoginData()
    {
        return new String[][]{
                {"Rahul121qwe@fox.com","foxo123"}, //invalid password
                {"Ishita121qwe@fox.com","123"}, //invalid password
                {"Ishita123qwe@fox.com",""},   //empty password
                {"Rahul124qwe@fox.com",null},  //null password
                {"Ishita124qwe@fox.com","Ishita124qwe@fox.com"}, //same username & password
                {"Rahulqwe@fox.com","fox@123"},//invalid username & password
        };
    }

    @DataProvider(name="NoHeaders")
    public Object[][] getDataForNoHeaders()
    {
        return new String[][]{
                {"g8h2vv62v@fox.com","Fox@123"}
        };
    }

    @DataProvider(name="NoRegHeaders")
    public Object[][] getRegDataForNoHeaders()
    {
        return new String[][]{
                {"Rahul", "Khanna","odjbmk969@fox.com", "o", "Fox@123"}
        };
    }

    @DataProvider(name="ResetValid")
    public Object[][] getValidResetData()
    {
        return new String[][]{
                {"a0egbgcz4@fox.com"}
        };
    }

    @DataProvider(name="NoHeadersReset")
    public Object[][] getNoHeadersResetData()
    {
        return new String[][]{
                {"yur4cq2i9@fox.com"}
        };
    }

    @DataProvider(name="ResetInValid")
    public Object[][] getInValidResetData()
    {
        return new String[][]{
                {"t@fox.com"},
                {""},
                {null}
        };
    }


}
