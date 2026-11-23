package school.voronin.digitalshop.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import school.voronin.digitalshop.model.CartItem;
import school.voronin.digitalshop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    @SuppressWarnings("uncheked")
    public List<CartItem> getCart(HttpSession session){
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null){
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(HttpSession session, Product product){
        List<CartItem> cart = getCart(session);

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).product().id().equals(product.id())){
                CartItem existing = cart.get(i);
                cart.set(i, new CartItem(existing.product(), existing.quantity() + 1));
                return;
            }
        }
        cart.add(0, new CartItem(product, 1));
    }

    public void removeFromCart(HttpSession session, Long productId){
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.product().id().equals(productId));
    }

    public double getTotal(HttpSession session){
        return getCart(session).stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clearCart(HttpSession session){
        session.removeAttribute(CART_SESSION_KEY);
    }


}
