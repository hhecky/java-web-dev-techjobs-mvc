package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.*;
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

    @PostMapping("/results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs = new ArrayList<>();

        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "Jobs With All:");


        } else if (searchType.equals("positionType")) {
            ArrayList<Job> allJobs = JobData.findAll();
            for(int j = 0; j < allJobs.size(); j++) {
                Job job = allJobs.get(j);
                if(job.getPositionType().toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                    jobs.add(job);
                }
            }

           model.addAttribute("title", "Jobs With Position Type: " + searchTerm);

        } else if (searchType.equals("employer")) {
            ArrayList<Job> allJobs = JobData.findAll();
            for(int j = 0; j < allJobs.size(); j++) {
                Job job = allJobs.get(j);
                if(job.getEmployer().toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                    jobs.add(job);
                }
            }
            model.addAttribute("title", "Jobs With Employer: " + searchTerm);

        } else if (searchType.equals("coreCompetency")) {
            ArrayList<Job> allJobs = JobData.findAll();
            for(int j = 0; j < allJobs.size(); j++) {
                Job job = allJobs.get(j);
                if(job.getCoreCompetency().toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                    jobs.add(job);
                }
            }
        model.addAttribute("title", "Jobs With Skill: " + searchTerm);

        } else if (searchType.equals("location")) {
            ArrayList<Job> allJobs = JobData.findAll();
            for(int j = 0; j < allJobs.size(); j++) {
                Job job = allJobs.get(j);
                if(job.getLocation().toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                    jobs.add(job);
                }
            }
            model.addAttribute("title", "Jobs With Location: " + searchTerm);

        } else {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs With All: " + searchTerm);

        }


            model.addAttribute("jobs", jobs);

            return "search";

        }
    }

