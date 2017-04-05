package controllers.chorbi;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.SearchTemplateService;

import controllers.AbstractController;
import domain.Chorbi;
import domain.SearchTemplate;

@Controller
@RequestMapping("/searchTemplate/chorbi")
public class SearchTemplateChorbiController extends AbstractController {

	//Services --------------------
	@Autowired
	private SearchTemplateService	searchTemplateService;
	@Autowired
	private ChorbiService	chorbiService;


	//Constructor -----------------
	public SearchTemplateChorbiController() {
		super();
	}

	//Creation -------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SearchTemplate searchTemplate = searchTemplateService.create();
		result = createEditModelAndView(searchTemplate);
		return result;
	}

	//Edition --------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		Chorbi principal = chorbiService.findByPrincipal();
		SearchTemplate searchTemplate = chorbiService.findSearchTemplateByChorbi(principal);
		ModelAndView result;
		if (searchTemplate == null) {
			result = new ModelAndView("redirect:/searchTemplate/chorbi/create.do");
		} else {
			result = createEditModelAndView(searchTemplate, null);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SearchTemplate searchTemplate, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(searchTemplate);
		} else {
			try {
				searchTemplate = searchTemplateService.reconstruct(searchTemplate, binding);
				if (binding.hasErrors()) {
					result = createEditModelAndView(searchTemplate);
				}
				if (searchTemplateService.checkCache(searchTemplate)) {
					result = new ModelAndView("redirect:/chorbi/listFound.do?searchTemplateId=" + searchTemplate.getId());
					result.addObject("message", "searchTemplate.commit.ok");
				} else {
					searchTemplateService.save(searchTemplate);
					result = new ModelAndView("redirect:/chorbi/listFound.do?chorbiId=" + searchTemplate.getId());
					result.addObject("message", "searchTemplate.commit.ok");
				}

			} catch (Throwable oops) {
				result = createEditModelAndView(searchTemplate, "searchTemplate.commit.error");
			}
		}

		return result;

	}

	//Ancillary methods -------------
	protected ModelAndView createEditModelAndView(SearchTemplate searchTemplate) {
		return createEditModelAndView(searchTemplate, null);
	}

	protected ModelAndView createEditModelAndView(SearchTemplate searchTemplate, String message) {
		ModelAndView result;

		result = new ModelAndView("searchTemplate/edit");
		result.addObject("searchTemplate", searchTemplate);
		result.addObject("message", message);

		return result;
	}

}