package com.example.quartzimpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService){ this.jobService = jobService; }

    @PostMapping("/run-now")
    public ResponseEntity<String> runNow(@RequestParam(defaultValue = "hi") String msg) {
        try {
            jobService.scheduleOneOffJob(msg);
            return ResponseEntity.ok("Job scheduled");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
