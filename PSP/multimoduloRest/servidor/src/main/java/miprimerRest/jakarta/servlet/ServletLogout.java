package miprimerRest.jakarta.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletLogout", value = "/ServletLogout")
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
)
public class ServletLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.logout();
        //request.getSession().removeAttribute("USERLOGIN");
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
