package ru.iteco.fmhandroid.ui.data;

import ru.iteco.fmhandroid.R;

public class Data {

    public static final String rightLogin = "login2";
    public static final String rightPassword = "password2";
    public static final String wrongLogin = "ogin2";
    public static final String wrongPassword = "assword2";

    public static String emptyField = "";
    public static String specSymbolString = "?%$@#";


    public static String sqlInject = "admin' OR '1'='1";
    public static String xssInject = "<script>alert('XSS!')</script>";
    public static String category1 = "Объявление";
    public static String category2 = "День рождения";
    public static String titleNewsCreated = "Title news created";
    public static String descriptionNewsCreated = "Description news created";
    public static String titleNewsUpdated = "Title news updated";
    public static String descriptionNewsUpdated = "Description news updated";
    public static String stringLenght120 = "qSGlLGb PM NMhjhvS oDNqXmETdYE LZg " +
            "uFFuOwXe rm ffyEZ. yAsXi IpAxNuIIx dbhdIxR iruglKWhgo jfaGL XfrXYvtBeSmEFMe ANOBEDmKyYnHJ, tTvJqTXwXjY";
    public static String stringLenght520 = "OZomMqEA niSVvPvVMlATtg , XNjHmKmmIGxiDmOBInyKM" +
            "dnWDYxUtIpGBeTEVKepRYkYBYD, KCHAsRzPWujLLnSuYlMkxTvFhUX/. TEbLsrLTUmYhfEKKKllfcr" +
            "QdviwnHPmmJncyoqaKT wHUSZJEdhYoQmZtQkKJWVtZaGaDxWphfRNfXNsTvhOlUeNzaJLqqiIZUy" +
            "YeAGVyyQkTqvCudMmAVMnRflEKt qMHWpSWGEbKqxpAZlvmDZIrXesAzojtVNaooavBofsOTjZBPX" +
            "mzBrqwGGaDiegTTOjdSF fmcVzHsJhjNfZj kUjtbVgaLnacn FXMUullbbVaMG UvBuqgivxikNzEY" +
            "aTgssbPOsiJvRQAZyE aYTJhloSOifSRSE HdqMOZmxDFdzUcFBNDTCTqKgpskscMzCyMRogkxrqULnAUVK" +
            "vO, vXriVKFRKhyO fdTBkuQKSeHUnqEbDkTRwzt XAgPpwPgeynHgFMfrLNMt zmBjazMSVXSb pcNBE jDVytLkDzWGEOzXLym";


    // ERROR MESSAGES

    public static String toastMsgEmptyField = String.valueOf(R.string.empty_fields);
    public static String toastMsgSomethingWentWrong = String.valueOf(R.string.error);
    public static String toastMsgCannotBeEmpty = String.valueOf(R.string.empty_login_or_password);
    public static String toastMsgTitleToLong = "Title is too long";
    public static String toastMsgLongDescription = "Description is too long";
    public static String toastMsgSpecialCharacters = "The title must not contain special characters";
}
