package lt.ramunas.alksnys.Springbackendportfolioproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping("/login")
	public String showPage(ModelMap model){		
		return "login";
	}
	
}
