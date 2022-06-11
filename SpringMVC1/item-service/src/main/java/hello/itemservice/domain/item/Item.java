package hello.itemservice.domain.item;

import lombok.Data;


//@Data는 예측하지 못하게 동작하는 경우가 있기때문에 도메인모델에 사용하기 위험하다. getter setter 따로 쓰는게 좋다.
//DTO같이 단순히 데이터가 거쳐가는 것에 경우 괜찮으나 잘 확인하고 써야한다.
@Data
public class Item {
    private long id;
    private String itemName;
    //null일때를 대비해서 Integer를 쓴다.
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }


}
