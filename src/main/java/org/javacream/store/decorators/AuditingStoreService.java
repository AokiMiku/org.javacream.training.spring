package org.javacream.store.decorators;

import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditingStoreService implements StoreService {

	@Autowired
	private StoreService delegate;


	public void setDelegate(StoreService delegate) {
		this.delegate = delegate;
	}


	@Override
	public int getStock(String category, String item) {
		System.out.println("Auditing: calling gestStock...");
		int stock = delegate.getStock(category, item);
		return stock;
	}

}