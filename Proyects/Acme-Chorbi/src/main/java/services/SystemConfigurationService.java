
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SystemConfigurationRepository;
import domain.Actor;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	//managed repository---------------------
	@Autowired
	private SystemConfigurationRepository	systemConfigurationRepository;

	//supporting services -------------------

	@Autowired
	private AdministratorService			adminService;

	@Autowired
	private MessageService					messageService;


	//Basic CRUD methods --------------------
	public SystemConfiguration create() {
		SystemConfiguration created;
		created = new SystemConfiguration();
		return created;
	}

	public SystemConfiguration findOne(final int systemConfigurationId) {
		this.adminService.checkAdministrator();
		SystemConfiguration retrieved;
		retrieved = this.systemConfigurationRepository.findOne(systemConfigurationId);
		return retrieved;
	}

	public Collection<SystemConfiguration> findAll() {
		Collection<SystemConfiguration> result;
		result = this.systemConfigurationRepository.findAll();
		return result;
	}

	public SystemConfiguration save(final SystemConfiguration systemConfiguration) {
		this.adminService.checkAdministrator();
		SystemConfiguration saved;
		saved = this.systemConfigurationRepository.save(systemConfiguration);
		return saved;
	}

	public void delete(final SystemConfiguration systemConfiguration) {
		this.systemConfigurationRepository.delete(systemConfiguration);
	}

	//Auxiliary methods ---------------------

	//Our other bussiness methods -----------
	public SystemConfiguration findMain() {
		final SystemConfiguration systemConfiguration = this.systemConfigurationRepository.findMain();
		return systemConfiguration;
	}

	public String getBanner() {
		final String res = this.systemConfigurationRepository.getBanner();
		return res;
	}

	public Double findAvgReceivedMessagesPerActor() {
		final Double result = this.messageService.findAvgReceivedMessagesPerActor();
		return result;
	}

	public Double findMinReceivedMessagesPerActor() {
		final Double result = this.messageService.findMinReceivedMessagesPerActor();
		return result;
	}

	public Double findMaxReceivedMessagesPerActor() {
		final Double result = this.messageService.findMaxReceivedMessagesPerActor();
		return result;
	}

	public Double findAvgSentMessagesPerActor() {
		final Double result = this.messageService.findAvgSentMessagesPerActor();
		return result;
	}

	public Double findMinSentMessagesPerActor() {
		final Double result = this.messageService.findMinSentMessagesPerActor();
		return result;
	}

	public Double findMaxSentMessagesPerActor() {
		final Double result = this.messageService.findMaxSentMessagesPerActor();
		return result;
	}

	public Collection<Actor> findActorWithMoreSentMessages() {
		final Collection<Actor> result = this.messageService.findActorWithMoreSentMessages();
		return result;
	}

	public Collection<Actor> findActorWithMoreReceivedMessages() {
		final Collection<Actor> result = this.messageService.findActorWithMoreReceivedMessages();
		return result;
	}
}
