package lcs.prs.goingmobile.services.interfaces;

import lcs.prs.goingmobile.services.ServiceIFace;

public interface IServiceRepo<T,K>  {
	
	void save(T element);
	void findById(K key);
	T findByUsername(String username);
	
}
