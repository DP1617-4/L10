<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Chorbies Co., Inc." /> <a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.chorbi" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/administrator/list.do"><spring:message code="master.page.chorbi.list" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="systemConfiguration/administrator/edit.do"><spring:message code="master.page.system" /></a></li>
					<li><a href="systemConfiguration/administrator/dashboard.do"><spring:message code="master.page.dashboard" /></a></li>	
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="chorbi/register.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CHORBI')">
			
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.chorbi" /> 
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/chorbi/list.do"><spring:message code="master.page.chorbi.list" /></a></li>
					<li><a href="likes/chorbi/list.do"><spring:message code="master.page.likes.list" /></a></li>
					<li><a href="chorbi/chorbi/listLiking.do"><spring:message code="master.page.chorbi.liking" /></a></li>
					<li><a href="searchTemplate/chorbi/edit.do"><spring:message code="master.page.search" /></a></li>
				</ul>
			</li>
			
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.chirp" /> 
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chirp/chorbi/create.do"><spring:message code="master.page.chirp.create" /></a></li>
					<li><a href="folder/chorbi/list.do"><spring:message code="master.page.folder.list" /></a></li>
				</ul>
			</li>
			
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/chorbi/display.do"><spring:message code="master.page.chorbi.display" /></a></li>
					<li><a href="actor/chorbi/edit.do"><spring:message code="master.page.chorbi.edit" /></a></li>
					<li><a href="creditCard/chorbi/edit.do"><spring:message code="master.page.credit" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			
		</security:authorize>
	</ul>
</div>


