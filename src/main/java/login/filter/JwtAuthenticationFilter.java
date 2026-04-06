
package login.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import login.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

// we do reader the header
//    set variable for username and token
//    we four checks in filter
//    1) check header start with bearer
//    2) if already authenticated or not & Username is present or not
//    3) validate the token
//   set authentication
//    5) continue the filter chain
//    thats enough





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 1. ALLOW ALL OPTIONS REQUESTS (CORS Pre-flight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 2. SKIP FILTERING FOR PUBLIC AUTH PATHS
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getHeader("Authorization") == null) {
            filterChain.doFilter(request, response);
            return;
        }
        //read header
        String authheader = request.getHeader("Authorization");

        //set variable
        String token = null;
        String username = null;

//        check if header is start with bearer and header is not null
//        after confrim then get token and username
        if (authheader != null && authheader.startsWith("Bearer ")) {
            token = authheader.substring(7);
            username = jwtUtils.extractUsername(token);
        }

//      check if username is present and already authenticated
//        condition is passed then we load from user db

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            //validatetoken   create new auth object and tell
            if (jwtUtils.validateToken(token,userDetails.getUsername())){

                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()

                );


//            setAuthentication   ...before in check authentocat we just check that is auth ornot
//            so use getauthentication ....   here we setauthentication    ... here spring said this is authenticate
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }

        //continue the filterchain
        filterChain.doFilter(request,response);
    }
}
