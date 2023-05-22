<%@ page import="com.urise.webapp.model.*" %>
<%@ page import="com.urise.webapp.utils.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/themes/light.css">
    <link rel="stylesheet" href="css/resume-edit-style.css"
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Resume <%=resume.getFullName()%>></title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
    <style>
        <%@ include file="/css/style.css"%>
        <%@ include file="/css/themes/light.css"%>
        <%@ include file="/css/resume-edit-style.css"%>
    </style>
<section class="scrollable-panel">
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="<%=resume.getUuid()%>">
        <dl>
            <dt>Name</dt>
            <dd>
                <label>
                    <input type="text" name="fullName" size="30" value="<%=resume.getFullName()%>">
                </label>
            </dd>
        </dl>
        <hr/>
        <h3>Contacts</h3>

        <%for (ContactType type : ContactType.values()) {%>
        <dl>
            <dt>
                <%=type.getTitle()%>
            </dt>
            <dd>
                <label>
                    <%if (resume.getContact(type) != null) {%>
                    <input type="text" name="<%=type.name()%>" size="30" value="<%=resume.getContact(type)%>">
                    <%} else {%>
                    <input type="text" name="<%=type.name()%>" size="30" value="<%=""%>">
                    <%}%>
                </label>
            </dd>
        </dl>
        <%}%>
        <hr/>
        <div style="margin-right: 30px">
            <%for (SectionType type : SectionType.values()) {%>
            <%Section section = resume.getSection(type);%>
            <%if (type.name().equals("OBJECTIVE")) {%>
            <h3 style="margin-left: auto"><%=type.name()%>:</h3>
            <dl>
                <dt>
                    <%=type.getTitle()%>
                </dt>
                <dd>
                    <label>
                        <%if (section != null) {%>
                        <input name="<%=type%>" size="75" type="text" value="<%=section%>"/>
                        <%} else {%>
                        <input name="<%=type%>" size="75" type="text" value="<%=""%>"/>
                        <%}%>
                    </label>
                </dd>
            </dl>
            <%} else if (type.name().equals("PERSONAL")) {%>
            <h3 style="margin-left: auto"><%=type.name()%>:</h3>
            <dl>
                <dt>
                    <%=type.getTitle()%>
                </dt>
                <dd>
                    <label>
                        <%if (section != null) {%>
                        <textarea name="<%=type%>" cols="75" rows="5"><%=section%></textarea>
                        <%} else {%>
                        <textarea name="<%=type%>" cols="75" rows="5"><%=""%></textarea>
                        <%}%>
                    </label>
                </dd>
            </dl>
            <%} else if (type.name().equals("ACHIEVEMENTS") || type.name().equals("QUALIFICATIONS")) {%>
            <h3 style="margin-left: auto"><%=type.name()%>:</h3>
            <dl>
                <dt>
                    <%=type.getTitle()%>
                </dt>
                <dd>
                    <label>
                        <%if (section != null) {%>
                        <textarea name="<%=type%>" cols="75"
                                  rows="5"><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                        <%} else {%>
                        <textarea name="<%=type%>" cols="75" rows="5"><%=String.join("\n", "")%></textarea>
                        <%}%>
                    </label>
                </dd>
            </dl>
            <%} else if (type.name().equals("EXPERIENCE") || type.name().equals("EDUCATION")) {%>
            <%List<Organization> orgList = ((OrganizationSection) section).getOrganizations();%>
            <h3 style="margin-left: auto"><%=type.name()%>:</h3>
            <%for (int i = 0; i < orgList.size(); i++) {%>
            <dl>
                <dt>Establishment's name</dt>
                <dd>
                    <label>
                        <input name="<%=type%>" size="75" type="text"
                               value="<%=orgList.get(i).getHomePage().getName() != null ? orgList.get(i).getHomePage().getName() : ""%>">
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Web site</dt>
                <dd>
                    <label>
                        <input name="<%=type%>url" size="75" type="text"
                               value="<%=orgList.get(i).getHomePage().getUrl() != null ? orgList.get(i).getHomePage().getUrl() : ""%>">
                    </label>
                </dd>
            </dl>
            <br/>
        </div>
        <div style="margin-left: auto">
            <%List<Organization.Position> positions = orgList.get(i).getPositions();%>
            <%for (Organization.Position p : positions) {%>
            <dl>
                <dt>Date of start</dt>
                <dd>
                    <label>
                        <input name="<%=type + String.valueOf(i)%>startDate" size="15" type="text"
                               value="<%=DateUtil.format(p.getStartDate())%>" placeholder="MM/yyyy">
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Date of end</dt>
                <dd>
                    <label>
                        <input name="<%=type + String.valueOf(i)%>endDate" size="15" type="text"
                               value="<%=DateUtil.format(p.getEndDate())%>" placeholder="MM/yyyy">
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Name of the Position</dt>
                <dd>
                    <label>
                        <input name="<%=type + String.valueOf(i)%>title" size="15" type="text"
                               value="<%=p.getTitle() != null? p.getTitle()  :""%>">
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Description</dt>
                <dd>
                    <label>
                                <textarea name="<%=type + String.valueOf(i)%>description" cols="75" rows="5">
                                    <%=p.getDescription() != null ? p.getDescription() : ""%>
                                </textarea>
                    </label>
                </dd>
            </dl>
            <%}%>
        </div>
        <%}%>
        <%}%>
        <%}%>
        <hr/>
        <button type="submit">Save</button>
        <input type="reset" name="Reset"value="Reset">
        <button onclick="window.history.back()">Back</button>
    </form>
    <a href="#">
        <button>GO UP</button>
    </a>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
