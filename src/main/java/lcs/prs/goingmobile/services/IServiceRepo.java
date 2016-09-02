package lcs.prs.goingmobile.services;

public interface IServiceRepo<T,K> extends ServiceIFace {
	
	void save(T element);
	void findById(K key);
	T findByUsername(String username);
	
}
