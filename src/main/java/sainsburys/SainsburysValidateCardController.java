package sainsburys;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card/number")
public class SainsburysValidateCardController {
    private SainsburysCardValidationService sainsburysCardValidationService;

    public SainsburysValidateCardController(SainsburysCardValidationService sainsburysCardValidationService) {
        this.sainsburysCardValidationService = sainsburysCardValidationService;
    }

    @GetMapping
    public boolean isValid(@RequestBody String cardNumber) {
        return sainsburysCardValidationService.isValid(cardNumber);
    }

}
