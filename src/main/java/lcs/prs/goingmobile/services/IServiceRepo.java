package lcs.prs.goingmobile.services;

public interface IServiceRepo<T,K> {
	
	void save(T element);
	void findById(K key);
	
}
