<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/resume-list-styles.css">
    <link rel="stylesheet" href="css/style.css">
    <title>List of Resumes</title>
</head>
<body>
<style>
    <%@ include file="/css/style.css"%>
    <%@ include file="/css/resume-list-styles.css"%>
</style>
<div class="themes">
    <div class="theme-title">Theme</div>
    <div class="theme-selector">
        <form action="" method="GET">
            <label>
                <select name="theme" onchange="this.form.submit()">
                    <option value="light" selected>Light</option>
                    <option value="dark">Dark</option>
                </select>
            </label>
        </form>
    </div>
</div>
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
<div>
    <div class="table-wrapper">
        <div class="add-resume">
            <a class="no-underline-anchor" href="resume?action=add&theme=light">
                <img class="add-resume" src="images/add-person.png" alt="Add new resume">
            </a>
            <a class="text-anchor" href="resume?action=add&theme=light">
                <p class="add-resume-title">Add new</p>
            </a>
        </div>
        <div class="t-header-div">
            <table>
                <tr class="t-header">
                    <th class="name-column">Имя</th>
                    <th class="info-column">Email</th>
                    <th class="img-column">Delete</th>
                    <th class="img-column">Edit</th>
                </tr>
            </table>
        </div>
        <div class="scrollable-panel">
            <table>
                <jsp:useBean id="resumes" scope="request" type="java.util.List"/>
                <c:forEach items="${resumes}" var="resume">

                    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                    <tr class="t-body">
                        <td class="name-column">
                            <a class="contact-link" href="resume?uuid=${resume.uuid}&action=view">
                                    ${resume.fullName}
                            </a>
                        </td>
                        <td class="info-column">
                            <a class="contact-link"
                               href="mailto:${resume.getContact(ContactType.MAIL)}">${resume.getContact(ContactType.MAIL)}</a>
                        </td>
                        <td class="img-column">
                            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=delete">
                                <button name="Delete">Delete</button>
                            </a>
                        </td>
                        <td class="img-column">
                            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit">
                                <img style="margin: auto" src="images/light-edit.svg" alt="Edit">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div class="footer">
    <a class="footer-text-anchor" href="https://www.google.com">GOOGLE</a>
    <a class="footer-text-anchor" href="#">
        <button>GO UP</button>
    </a>
</div>
</body>
</html>