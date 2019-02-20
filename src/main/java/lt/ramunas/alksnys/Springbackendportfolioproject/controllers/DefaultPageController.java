package lt.ramunas.alksnys.Springbackendportfolioproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class DefaultPageController {
	
	@RequestMapping(value={"/","/employee", "/projects"}, method = RequestMethod.GET)
	public String showPage(ModelMap model){	
		return "home";
	}
	
}
