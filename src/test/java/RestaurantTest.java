import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @BeforeEach
    public void setuptest(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Restaurant mockRestaurant = Mockito.spy(restaurant);
        LocalTime lt = LocalTime.of(13,10,5);
        Mockito.doReturn(lt).when(mockRestaurant).getCurrentTime();
        boolean statusOfRestaurant = mockRestaurant.isRestaurantOpen();
        Assertions.assertEquals(true,statusOfRestaurant);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant mockRestaurant = Mockito.spy(restaurant);
        LocalTime lt = LocalTime.of(3,10,5);
        Mockito.doReturn(lt).when(mockRestaurant).getCurrentTime();
        boolean statusOfRestaurant = mockRestaurant.isRestaurantOpen();
        Assertions.assertEquals(false,statusOfRestaurant);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
//        LocalTime openingTime = LocalTime.parse("10:30:00");
//        LocalTime closingTime = LocalTime.parse("22:00:00");
//        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
//        restaurant.addToMenu("Sweet corn soup",119);
//        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
//        LocalTime openingTime = LocalTime.parse("10:30:00");
//        LocalTime closingTime = LocalTime.parse("22:00:00");
//        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
//        restaurant.addToMenu("Sweet corn soup",119);
//        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




    @Test
    public void totalItemValue_price_allItems_selectedListed() {
        List<String> restItems = new ArrayList<String>();
        restaurant.addToMenu("Salad",150);
        restItems.add("Salad");
        restItems.add("Sweet corn soup");
        double totalCost = restaurant.getTotalCostOfSelectedItems(restItems);
        Assertions.assertEquals(269,totalCost);
    }
}