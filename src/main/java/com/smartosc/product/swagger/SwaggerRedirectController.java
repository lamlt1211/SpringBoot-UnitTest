package com.smartosc.product.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * product
 *
 * @author Tung lam
 * @created_at 08/06/2020 - 11:03
 * @created_by Tung lam
 * @since 08/06/2020
 */
@RestController
public class SwaggerRedirectController {
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView redirect1() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
