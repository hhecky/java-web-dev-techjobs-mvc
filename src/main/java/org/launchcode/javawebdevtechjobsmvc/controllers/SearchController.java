package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    //@RequestMapping(value = "jobs")
    @PostMapping("/results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "Jobs With All:");

        } else if (searchType.equals("positionType")) {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs With Position Type: " + searchTerm);

        } else if (searchType.equals("employer")) {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs With Employer: " + searchTerm);

        } else if (searchType.equals("coreCompetency")) {
        jobs = JobData.findByValue(searchTerm);
        model.addAttribute("title", "Jobs With Skill: " + searchTerm);

        } else if (searchType.equals("location")) {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs With Location: " + searchTerm);

        } else {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs With All: " + searchTerm);

        }


            model.addAttribute("jobs", jobs);

            return "list-jobs";

        }
    }

