package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utils {

    /**
     * Get formatted error message for logging
     * @return formatted string with error and it's cause
     */
    public static String getErrMessage(Exception e) {
        return e + "; Caused by: " + e.getCause().toString();
    }

    /**
     * invalidate session and redirect to catalog
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        System.out.println("request");
        response.sendRedirect("index.jsp");
    }

}
