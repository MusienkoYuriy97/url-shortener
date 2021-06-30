package by.solbegsoft.shortener.demo.common;

public class ProtocolChecker {

    public static String setPrefix(String url){
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        }else {
            return "https://" + url;
        }
    }
}
