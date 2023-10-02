package com.hcl.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component 
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokentHelper jwtTokentHelper;
    @Autowired
	private CustomUserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String requestTokenAuthorization = request.getHeader("Authorization");
		String username=null;
		String JwtToken=null;
		
		if(requestTokenAuthorization!=null && requestTokenAuthorization.startsWith("Bearer "))
		{
			JwtToken=requestTokenAuthorization.substring(7);
			
			try {
				
			username = this.jwtTokentHelper.extractUsername(JwtToken);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			 
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				  UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				  if(this.jwtTokentHelper.validateToken(JwtToken, userDetails))
				  {
					  UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				  }
				  else
					{
						System.out.println("token is not valid");
					}
				
			}
			else
			{
				System.out.println("user is null");
			}
			
			
		}
		filterChain.doFilter(request, response);
	}


}
