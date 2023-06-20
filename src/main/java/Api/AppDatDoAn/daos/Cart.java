package Api.AppDatDoAn.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {
    private List<Item> cartItems = new ArrayList<>();
    public void addItems(Item item) {
        boolean isExist = cartItems.stream()
                .filter(i -> Objects.equals(i.getProductId(),
                        item.getProductName()))
                .findFirst()
                .map(i -> {
                    i.setQuantity(i.getQuantity() +
                            item.getQuantity());
                    return true;
                })
                .orElse(false);
        if (!isExist) {
            cartItems.add(item);
        }
    }
    public void removeItems(Long ProductId) {
        cartItems.removeIf(item -> Objects.equals(item.getProductId(),
                ProductId));
    }
    public void updateItems(int ProductId, int quantity) {
        cartItems.stream()
                .filter(item -> Objects.equals(item
                        .getProductId(), ProductId))
                .forEach(item -> item.setQuantity(quantity));
    }
}
