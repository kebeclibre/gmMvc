package lcs.prs.goingmobile.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lcs.prs.goingmobile.entities.Client;

public class GetPrincipal {
	public static Client getClient() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails usr = (UserDetails) principal;
		
		Client clt = new Client();
		clt.setUsername(usr.getUsername());
		clt.setPassword(usr.getPassword());
		
		return clt;
	}
}
