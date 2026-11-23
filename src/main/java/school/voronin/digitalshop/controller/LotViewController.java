package school.voronin.digitalshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.voronin.digitalshop.model.User;
import school.voronin.digitalshop.service.BidService;
import school.voronin.digitalshop.service.LotService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/lots")
public class LotViewController {

    private final LotService lotService;
    private final BidService bidService;

    public LotViewController(LotService lotService, BidService bidService) {
        this.lotService = lotService;
        this.bidService = bidService;
    }

    @GetMapping
    public String allLots(Model model) {
        model.addAttribute("lots", lotService.getAllLots());
        return "lots-all";
    }

    @GetMapping("/active")
    public String activeLots(Model model) {
        model.addAttribute("lots", lotService.getActiveLots());
        return "lots-all";
    }

    @GetMapping("/{id}")
    public String lotDetails(@PathVariable Long id, Model model) {
        model.addAttribute("lot", lotService.getLotById(id));
        model.addAttribute("bids", bidService.getBidsByLotId(id));
        return "lot-details";
    }

    @PostMapping("/{id}/bid")
    public String placeBid(
            @PathVariable Long id,
            @RequestParam BigDecimal amount,
            HttpSession session,
            RedirectAttributes redirectAttributes
            ){
        try {
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null){
                return "redirect:/login";
            }
            bidService.placeBid(id, currentUser.id(), amount);
            return "redirect:/lots/" + id;
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/lots/" + id;
        }

    }


}