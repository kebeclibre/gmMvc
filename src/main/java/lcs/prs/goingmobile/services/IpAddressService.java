package lcs.prs.goingmobile.services;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.services.interfaces.IpAddressServiceIFace;

@Service
public class IpAddressService implements IpAddressServiceIFace {

	private List<String> listAddr;
	private String addrString;
	public List<String> getListAddr() {
		return listAddr;
	}
	public void setListAddr(List<String> listAddr) {
		this.listAddr = listAddr;
	}
	
	@PostConstruct
	public void init() {
	listAddr = new ArrayList<>();
	StringBuilder sb = new StringBuilder();
	
	try {
		Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
		
		
		for (; n.hasMoreElements();)
	    {
	        NetworkInterface e = n.nextElement();

	        Enumeration<InetAddress> a = e.getInetAddresses();
	        for (; a.hasMoreElements();)
	        {
	            InetAddress addr = a.nextElement();
	            if (!addr.isLoopbackAddress()) {
	            	sb.append(addr.getHostAddress());
	            	sb.append("|");
	            }
	           // System.out.println("  " + addr.getHostAddress());
	        }

	    }
	
		
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if (sb.length() <= 7) {
		addrString = "noAdress";
	} else {
        sb.deleteCharAt(sb.length()-1);
        addrString = sb.toString();
	}
}
	@Override
	public String showList() {
		return addrString;
	}
}
