package org.anton_u.tripium_test_task.comands_handler;

import org.anton_u.tripium_test_task.service.ConsoleHandlerService;
import org.anton_u.tripium_test_task.util.FakeDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@PropertySource("classpath:console_commands.properties")
public class ConsoleCommandHandler {

    private static final int REGEX_GROUP = 1;
    private final Environment environment;
    private final ConsoleHandlerService consoleHandlerService;

    @Autowired
    public ConsoleCommandHandler(ConsoleHandlerService consoleHandlerService, Environment environment, FakeDataGenerator fakeDataGenerator) {
        this.consoleHandlerService = consoleHandlerService;
        this.environment = environment;
    }

    public String getResponse(String input) {
        String departmentHeadRegex = environment.getProperty("department_head");
        String departmentStatisticRegex = environment.getProperty("department_statistic");
        String departmentAverageSalaryRegex = environment.getProperty("department_avg_salary");
        String departmentEmployeeCountRegex = environment.getProperty("department_employee_count");
        String lecturesGlobalSearchPattern = environment.getProperty("lectures_global_search");


        if (input.matches(departmentHeadRegex)) {
            String queryParam = getQueryParam(input, departmentHeadRegex);
            return consoleHandlerService.findHeadByName(queryParam);
        } else if (input.matches(departmentStatisticRegex)) {
            String queryParam = getQueryParam(input, departmentStatisticRegex);
            return consoleHandlerService.getDepartmentStatistic(queryParam);
        } else if (input.matches(departmentAverageSalaryRegex)) {
            String queryParam = getQueryParam(input, departmentAverageSalaryRegex);
            return consoleHandlerService.getAverageSalary(queryParam);
        } else if (input.matches(departmentEmployeeCountRegex)) {
            String queryParam = getQueryParam(input, departmentEmployeeCountRegex);
            return consoleHandlerService.getLecturesCount(queryParam);
        } else if (input.matches(lecturesGlobalSearchPattern)) {
            String queryParam = getQueryParam(input, lecturesGlobalSearchPattern);
            return consoleHandlerService.getLectureNameByPattern(queryParam);
        }

        return "Can not process your request! Try again..";
    }

    private String getQueryParam(String input, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(REGEX_GROUP);
    }

}
