namespace java by.maribo.web_service

struct Item {
    1: string title,
    2: string content,
}

service CrawlingService {
    void write(1:list<Item> items),

    list<Item> getItems(),
    void setItems(1:list<Item> items),

    void addItem(1:Item item),
    void deleteItem(1:Item item),
    void modifyItem(1:Item oldItem, 2:Item newItem),
}