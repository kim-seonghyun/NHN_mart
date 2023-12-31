package com.nhnacademy.mart;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {
    private static final Logger logger = Logger.getLogger("Customer");

    public static Logger getLogger(){
        logger.setLevel(Level.ALL);
        return logger;
    }


    // 고객 구매 목록
    private final BuyList buyList;

    // 고객 장바구니
    private Basket basket;

    private int cash = 20000;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    // 장바구니 챙기기
    public void bring(Basket basket) {
        this.basket = basket;
    }


    // TODO pickFoods 메서드 구현
    // basket에 담아야 하나?
    // buyList , foodstand의 재고를 비교하기
    public void pickFoods(FoodStand foodStand) {

        for (BuyList.Item item : buyList.getItems()) {
            Food deletedFood = foodStand.delete(item);
            for (int i = 0; i < item.getAmount(); i++) {
                this.basket.add(deletedFood);
            }
        }
        //이제 buyList는 구매할 수 있는 food만 있다.
    }

    // TODO payTox 메서드 구현
    public int payTox(Counter counter) {
        int cost = counter.pay(this.basket);
        cash -= cost;
        logger.info("총 가격은 " + cost + "원 입니다.");
        logger.info("고객님 결제 후 잔액 : " + cash);
        return cash;
    }

}
