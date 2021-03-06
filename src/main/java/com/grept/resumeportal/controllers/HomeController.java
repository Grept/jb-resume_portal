package com.grept.resumeportal.controllers;

import com.grept.resumeportal.models.Job;
import com.grept.resumeportal.models.UserProfile;
import com.grept.resumeportal.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home() {
        UserProfile profile1 = new UserProfile();
        profile1.setId(1);
        profile1.setDesignation("Current Designation");
        profile1.setUserName("tom");
        profile1.setFirstName("Tom");
        profile1.setLastName("Jansen");
        profile1.setTheme(1);

        Job job1 = new Job();
        job1.setCompany("Company 1");
        job1.setDesignation("Designation 1");
        job1.setId(3);
        job1.setStartDate(LocalDate.of(2019, 5, 1));
        job1.setEndDate(LocalDate.of(2020, 8, 1));

        Job job2 = new Job();
        job2.setCompany("Company 2");
        job2.setDesignation("Designation 2");
        job2.setId(4);
        job2.setStartDate(LocalDate.of(2020, 9, 1));
        job2.setEndDate(LocalDate.of(2021, 4, 1));

        profile1.setJobs(Arrays.asList(job1, job2));

        userProfileRepository.save(profile1);

        return "profile";
    }

    @GetMapping("/edit")
    public String edit() {
        return "Edit Page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);
        System.out.println(userProfile.getJobs());

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
