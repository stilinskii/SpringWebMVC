package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static ConcurrentHashMap
    private static long sequence = 0L;

    public Item save (Item... item){
        for(int i = 0;i< item.length;i++){
        item[i].setId(++sequence);
        store.put(item[i].getId(), item[i]);
        }
        return item[0];
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId,Item updateParam){
        Item findItem = store.get(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        //정석으로 하려고하면 객체를 받는게 맞다
    }

    public void clearStore(){
        store.clear();
    }

}
