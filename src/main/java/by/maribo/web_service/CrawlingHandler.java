package by.maribo.web_service;

import org.apache.thrift.TException;

import java.util.List;

public class CrawlingHandler implements CrawlingService.Iface {

	public CrawlingHandler() {

	}

	@Override
	public void write(List<Item> items) throws TException {
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}

	@Override
	public List<Item> getItems() throws TException {
		return null;
	}

	@Override
	public void setItems(List<Item> items) throws TException {

	}

	@Override
	public void addItem(Item item) throws TException {

	}

	@Override
	public void deleteItem(Item item) throws TException {

	}

	@Override
	public void modifyItem(Item oldItem, Item newItem) throws TException {

	}
}
