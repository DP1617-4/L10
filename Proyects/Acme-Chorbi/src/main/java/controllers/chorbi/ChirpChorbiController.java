
package controllers.chorbi;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import services.ChorbiService;
import services.FolderService;
import controllers.AbstractController;
import domain.Chirp;
import domain.Chorbi;
import forms.ResendChirp;

@Controller
@RequestMapping("/message/chirp/chorbi")
public class ChirpChorbiController extends AbstractController {

	//Services

	@Autowired
	private ChirpService	messageService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ChorbiService	actorService;


	//Contructor

	public ChirpChorbiController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int folderId) {

		ModelAndView result;
		Collection<Chirp> messages;
		final String requestURI = "message/actor/list.do?folderId=" + folderId;

		try {
			messages = this.messageService.findAllByFolder(folderId);
			result = new ModelAndView("message/list");
			result.addObject("messages", messages);
			result.addObject("requestURI", requestURI);
		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/folder/actor/list.do");
			result.addObject("errorChirp", "message.folder.wrong");

		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final Chirp message = this.messageService.create();

		result = this.createEditModelAndView(message);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Chirp message, final BindingResult binding) {
		Chorbi principal;
		ModelAndView result;
		Chirp sent;

		if (binding.hasErrors())
			result = this.createEditModelAndView(message);
		else
			try {
				sent = this.messageService.send(message);
				principal = this.actorService.findByPrincipal();
				result = new ModelAndView("redirect:/message/actor/list.do?folderId=" + this.folderService.findSystemFolder(principal, "Outbox").getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(message, "message.commit.error");
			}

		return result;
	}

	//TODO Cuando lanza la excepci�n a d�nde lo mando?
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId) {
		ModelAndView result;

		Chirp message;

		try {
			message = this.messageService.findOne(messageId);
			this.messageService.delete(message);
			result = new ModelAndView("redirect:/message/actor/list.do?folderId=" + message.getFolder().getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
			result.addObject("errorChirp", "message.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/resend", method = RequestMethod.POST, params = "save")
	public ModelAndView resend(@RequestParam final ResendChirp resendChirp, final BindingResult binding) {
		Chorbi principal;
		ModelAndView result;
		Chirp sent;
		final Chorbi recipient;

		if (binding.hasErrors())
			result = new ModelAndView("redirect:/welcome/index.do");
		else
			try {
				recipient = this.actorService.findOne(resendChirp.getRecipientId());
				sent = this.messageService.findOne(resendChirp.getChirpId());
				sent = this.messageService.reSend(sent, recipient);
				principal = this.actorService.findByPrincipal();
				result = new ModelAndView("redirect:/message/actor/list.do?folderId=" + this.folderService.findSystemFolder(principal, "Outbox").getId());
			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:/welcome/index.do");
			}

		return result;
	}
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam final int chirpId) {
		ModelAndView result;
		final Chirp chirp = this.messageService.findOne(chirpId);
		final Chirp reply = this.messageService.reply(chirp);

		result = this.createEditModelAndView(reply);

		return result;

	}
	//TODO lo mismo que arriba

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Chirp message) {
		ModelAndView result;

		result = this.createEditModelAndView(message, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Chirp message, final String errorChirp) {
		ModelAndView result;
		Collection<Chorbi> actors;

		actors = this.actorService.findAll();

		result = new ModelAndView("message/edit");
		result.addObject("errorChirp", errorChirp);
		result.addObject("message", message);
		result.addObject("actors", actors);

		return result;
	}
}