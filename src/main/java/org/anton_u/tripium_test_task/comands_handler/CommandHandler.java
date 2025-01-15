package org.anton_u.tripium_test_task.comands_handler;

import lombok.RequiredArgsConstructor;
import org.anton_u.tripium_test_task.entity.Lecture;
import org.anton_u.tripium_test_task.service.ConsoleHandlerService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@PropertySource("classpath:console_commands.properties")
@RequiredArgsConstructor
public class CommandHandler {

    private static final int REGEX_GROUP = 1;
    private final Environment environment;
    private final ConsoleHandlerService consoleHandlerService;

    public String dispatch(String input) {

        if (input.matches(environment.getProperty("department_head"))) {
            String departmentHeadRegex = environment.getProperty("department_head");
            String queryParam = getQueryParam(input, departmentHeadRegex);
           return getHeadOfDepartment(queryParam);
        }

        return "Nothing to find";
    }


    public String getHeadOfDepartment(String departmentName) {
        Lecture head = consoleHandlerService.findHeadByName(departmentName);

        if (head != null) {
            String nameAndSurname = String.format("%s %s", head.getName(), head.getSurname());
            return String.format("Head of %s department is %s", departmentName, nameAndSurname);
        }
        return String.format("There is no data with %s", departmentName);
    }

    private String getQueryParam(String input, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.group(REGEX_GROUP);
    }
}
