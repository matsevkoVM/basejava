package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.utils.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {
        String phone = "+52 998 200 0078";
        String mobile = "+380 97 432 8694";
        String homePhone = "45 109";
        String skype = "mvm.skype";
        String email = "vitt0ss.gmail.com";
        String linkedIn = "https://www.linkedin.com/in/viktor-matsevko-b6ba6519a/";
        String git = "github.com/matsevkoVM";
        String stackoverflow = "https://stackoverflow.com/users/21074079/viktor-matsevko";
        String homePage = "google.com";
        String positionName = "Главний інженєр";
        String position_description = "Розробляє всяку хуйню, а потім тею хуйнею в людей кидається";
        String personalQualities_description = "Вагонна провідниця котру всі вищезгадані митці їбуть іноді по черзі а іноді так";
        String[] achievementArray = new String[]{"*Магарич, митець", "*Омелян Косопизд, митець.", "*Харитон Уйобищенко, митець.",
                "*Назар Сивуха, митець.", "*Альфред Золупенко, митець.", "*Гаврюша Обізянов, інженер.",
                "*Степан Срака, главний інженер.", "*Шльома Гомельский, науковець.", "*Роже Городі, французський буржуазний націоналіст.",
                "*Пророк Самуїл, старий жид."};
        String[] qualificationArray = new String[]{"Qualification1", "Qualification2", "Qualification3", "Qualification4",
                "Qualification5", "Qualification6", "Qualification7", "Qualification8", "Qualification9",};
        String[] title1 = new String[]{"Захеканий мудило в цигейковій шапці"};
        String[] title2 = new String[]{"Звєзда вастока"};
        String[] title3 =new String[]{"Йобане чмо", "Хтивий дядько принца"};
        String[] titleEducation =new String[]{"Пацан ішчьо", "Помічник тракториста", "Сам господь бог"};
        String[] description1 = new String[]{"description 1"};
        String[] description2 = new String[]{"description 2"};
        String[] description3 = new String[]{"description 3", "description 4"};
        String[] descriptionEducation = new String[]{"description Education", "description Education1", "description Education2"};
        Resume r = new Resume("uuid01", "Tomas Edison");
        Link org1 = new Link("Organization1 name", "https://www.somelink1.something.else");
        Link org2 = new Link("Organization2 name", "https://www.somelink2.something.else");
        Link org3 = new Link("Organization3 name", "https://www.somelink3.something.else");
        Link orgEdu = new Link("OrganizationEducation name", "https://www.someEDUCATIONALlink.something.else");
        LocalDate[] startOrg1 = new LocalDate[]{DateUtil.of(2000, Month.SEPTEMBER)};
        LocalDate[] endOrg1 = new LocalDate[]{DateUtil.of(2002, Month.OCTOBER)};
        LocalDate[] startOrg2 = new LocalDate[]{DateUtil.of(2002, Month.DECEMBER)};
        LocalDate[] endOrg2 = new LocalDate[]{DateUtil.of(2005, Month.NOVEMBER)};
        LocalDate[] startOrg3 = new LocalDate[]{DateUtil.of(2006, Month.JANUARY), DateUtil.of(2010, Month.NOVEMBER)};
        LocalDate[] endOrg3 = new LocalDate[]{DateUtil.of(2010, Month.SEPTEMBER), DateUtil.of(2013, Month.FEBRUARY)};
        LocalDate[] startEdu = new LocalDate[]{DateUtil.of(1992, Month.JANUARY), DateUtil.of(1995, Month.DECEMBER),
                DateUtil.of(1998, Month.DECEMBER)};
        LocalDate[] endEdu = new LocalDate[]{DateUtil.of(1995, Month.NOVEMBER), DateUtil.of(1998, Month.FEBRUARY),
                DateUtil.of(2000, Month.DECEMBER)};
        Organization[] orgs = new Organization[]{new Organization(org1.getName(), org1.getUrl(), startOrg1, endOrg1, title1, description1),
        new Organization(org2.getName(), org2.getUrl(), startOrg2, endOrg2, title2, description2), new Organization(org3.getName(),
                org3.getUrl(), startOrg3, endOrg3, title3, description3)};
        Organization[] orgEducation = new Organization[] {new Organization(orgEdu.getName(), orgEdu.getUrl(), startEdu,
                endEdu,titleEducation, descriptionEducation )};
        OrganizationSection orgSection = new OrganizationSection(Arrays.asList(orgs));
        OrganizationSection educationSection = new OrganizationSection(Arrays.asList(orgEducation));
        TextSection position = new TextSection(String.format("%s%n%s", positionName, position_description));
        TextSection personalQualities = new TextSection(personalQualities_description);
        ListSection achievement = new ListSection(Arrays.asList(achievementArray));
        ListSection qualification = new ListSection(Arrays.asList(qualificationArray));

        System.out.println(r.getFullName());
        drawSeparator();
        System.out.println("CONTACTS");
        System.out.printf("%-20s %s", ContactType.PHONE + ": ", "||");
        System.out.printf("%-20s %s", ContactType.MOBILE + ": ", "||");
        System.out.println(ContactType.HOME_PHONE + ": ");
        System.out.printf("%23s", phone + "||");
        System.out.printf("%23s", mobile + "||");
        System.out.printf("%10s%n", homePhone);
        drawLine();

        System.out.printf("%-20s %s", ContactType.SKYPE + ": ", "||");
        System.out.printf("%-20s %s", ContactType.MAIL + ": ", "||");
        System.out.println(ContactType.LINKED_IN + ": ");
        System.out.printf("%23s", skype + "||");
        System.out.printf("%23s", email + "||");
        System.out.printf("%23s%n", linkedIn);
        drawLine();

        System.out.printf("%-20s %s", ContactType.GITHUB + ": ", "||");
        System.out.println(ContactType.STACKOVERFLOW + ":                                           ||");
        System.out.printf("%23s", git + "||");
        System.out.printf("%23s%n", stackoverflow + " ||");
        drawLine();

        System.out.println(ContactType.HOME_PAGE + ": ");
        System.out.printf("%10s%n", homePage);
        drawSeparator();

        System.out.println(SectionType.OBJECTIVE);
        System.out.println(position);
        drawSeparator();

        System.out.println(SectionType.PERSONAL + " лічниє качєства");
        System.out.println(personalQualities);
        drawSeparator();

        System.out.println(SectionType.ACHIEVEMENTS);
        System.out.println(achievement);
        drawSeparator();

        System.out.println(SectionType.QUALIFICATIONS);
        System.out.println(qualification);
        drawSeparator();

        System.out.println(SectionType.EXPERIENCE);
        System.out.println(orgSection);
        drawSeparator();

        System.out.println(SectionType.EDUCATION);
        System.out.println(educationSection);
        drawSeparator();
    }

    public static void drawLine() {
        System.out.println("----------------------------------------------------------------------------------");
    }

    public static void drawSeparator() {
        System.out.println("==================================================================================");
    }
}
