package com.gkatzioura.springdata.jpa.controller;

        import com.gkatzioura.springdata.jpa.persistence.entity.Employee;
        import com.gkatzioura.springdata.jpa.persistence.repository.EmployeeRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

/**
 * Created by gkatzioura on 6/2/16.
 */
@RestController
public class TestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/employee")
    public List<Employee> getTest() {

        return employeeRepository.findAll();
    }

    @RequestMapping("/employee/filter")
    public List<Employee> getFiltered(String firstName,@RequestParam(defaultValue = "0") Double bonusAmount) {

        return employeeRepository.getFirstNamesLikeAndBonusBigger(firstName,bonusAmount);
    }

}
