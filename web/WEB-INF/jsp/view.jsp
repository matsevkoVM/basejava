<%@ page import="com.urise.webapp.utils.HtmlUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.urise.webapp.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/themes/light.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/resume-view.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Resume <%=resume.getFullName()%>
    </title>
</head>
<body>
<div>
    <style>
        <%@ include file="/css/themes/light.css"%>
        <%@ include file="/css/style.css"%>
        <%@ include file="/css/resume-view.css"%>
    </style>
    <div class="header">
        <a class="no-underline-anchor" href="resume?theme=light">
            <div class="arrow-dot">
                <img class="arrow-dot" src="images/left_arrow.svg" alt="List of resumes">
            </div>
        </a>
        <a class="text-anchor" href="resume?theme=light">
            <span class="resumes-control-title">Manage Resume</span>
        </a>
    </div>
    <div class="scrollable-panel">
        <div class="form-wrapper">
            <div class="full-name">
                <h2>
                    <%=resume.getFullName()%>
                    <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit">
                        <img style="margin: auto" src="images/light-edit.svg" alt="Edit">
                    </a>
                </h2>
            </div>
            <div class="contacts">
                <table class="t-body">
                    <%for (Map.Entry<ContactType, String> pair : resume.getContacts().entrySet()) {%>
                    <tr>
                        <td>
                            <%=pair.getKey().getTitle()%>:
                        </td>
                        <td><%=pair.getKey().toHtml(pair.getValue())%>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div class="spacer">

            </div>
            <div class="section-wrapper">
                <table>
                    <%for (Map.Entry<SectionType, Section> pair : resume.getSections().entrySet()) {%>
                    <%SectionType type = pair.getKey();%>
                    <%Section section = pair.getValue();%>
                    <tr class="section">
                        <td colspan="2">
                            <h2>
                                <a id="<%=type.name()%>">
                                    <%=type.getTitle()%>:
                                </a>
                            </h2>
                        </td>
                    </tr>
                    <%if (type.name().equals("OBJECTIVE")) {%>
                    <tr class="position">
                        <td colspan="2">
                            <h3><%=((TextSection) section).getContent()%>
                            </h3>
                        </td>
                    </tr>
                    <%} else if (type.name().equals("PERSONAL")) {%>
                    <tr class="qualities">
                        <td colspan="2">
                            <h3><%=((TextSection) section).getContent()%>
                            </h3>
                        </td>
                    </tr>
                    <%} else if (type.name().equals("ACHIEVEMENTS") || type.name().equals("QUALIFICATIONS")) {%>
                    <tr>
                        <td colspan="2">
                            <ul>
                                <%for (String item : ((ListSection) section).getItems()) {%>
                                <li>
                                    <%=item%>
                                </li>
                                <%}%>
                            </ul>
                        </td>
                    </tr>
                    <%} else if (type.name().equals("EDUCATION") || type.name().equals("EXPERIENCE")) {%>
                    <%for (Organization org : ((OrganizationSection) section).getOrganizations()) {%>
                    <tr>
                        <td colspan="2">
                            <%if (org.getHomePage().getUrl() == null) {%>
                            <h3>
                                <%=org.getHomePage().getName()%>
                            </h3>
                            <%} else {%>
                            <h3>
                                <a href="<%=org.getHomePage().getUrl()%>">
                                    <%=org.getHomePage().getName()%>
                                </a>
                            </h3>
                            <%}%>
                        </td>
                    </tr>
                    <%for (Organization.Position position : org.getPositions()) {%>
                    <%if (position != null) {%>
                    <tr>
                        <td>
                            <%=HtmlUtil.formatDates(position)%>
                        </td>
                        <td>
                            <b><%=position.getTitle()%>
                            </b>
                            <br/>
                            <%=position.getDescription()%>
                        </td>
                    </tr>
                    <%}%>
                    <%}%>
                    <%}%>
                    <%}%>
                    <%}%>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <a class="footer-text-anchor" href="https://www.google.com">GOOGLE</a>
    <a class="footer-text-anchor" href="#">
        <button>GO UP</button>
    </a>
    <a href="resume">
        <button>Back</button>
    </a>
</div>
</body>
</html>
