package jakarta.servlet;

import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletHashes", value = "/ServletHashes")
public class ServletHashes extends HttpServlet {

    private Pbkdf2PasswordHash passwordHash;


    @Inject
    public ServletHashes(Pbkdf2PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("LOGIN",true);


        String password = passwordHash.generate("1234".toCharArray());
        response.getWriter().println(password);

        response.getWriter().println(passwordHash.generate("1234".toCharArray()));

        boolean verificado = passwordHash.verify("1234".toCharArray(),password);
        response.getWriter().println(verificado);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
