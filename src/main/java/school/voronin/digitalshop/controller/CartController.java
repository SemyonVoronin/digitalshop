package school.voronin.digitalshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.voronin.digitalshop.model.CartItem;
import school.voronin.digitalshop.model.User;
import school.voronin.digitalshop.service.CartService;
import school.voronin.digitalshop.service.ProductService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {

    public final CartService cartService;
    public final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model){
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("total", cartService.getTotal(session));
        return "cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable UUID productId, HttpSession session){
        cartService.removeFromCart(session, productId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session, RedirectAttributes redirectAttributes){
        List<CartItem> cart = cartService.getCart(session);

        if (cart.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Корзина пуста");
            return "redirect:/cart";
        }

        try {
            for (CartItem item : cart){
                productService.decreaseStock(item.product().id(), item.quantity());
            }
            cartService.clearCart(session);

            redirectAttributes.addFlashAttribute("success", "Заказ успешно оформлен");

        }catch (IllegalArgumentException ex){
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/cart";

    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable UUID productId, HttpSession session, RedirectAttributes redirectAttributes){
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null){
            redirectAttributes.addFlashAttribute("error", "Войдите, чтобы добавить в корзину");
            return "redirect:/login";
        }
        cartService.addToCart(session, productService.getProductById(productId));
        return "redirect:/products";
    }


}
