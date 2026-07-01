package com.vivek.stocksentry.controller;

import com.vivek.stocksentry.dto.AlertRequest;
import com.vivek.stocksentry.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<?> createAlert(@RequestBody AlertRequest request,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(alertService.createAlert(
                userDetails.getUsername(),
                request.getSymbol(),
                request.getTargetPrice(),
                request.getConditionType()));
    }

    @GetMapping
    public ResponseEntity<?> getAlerts(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(alertService.getAlerts(userDetails.getUsername()));
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<?> deleteAlert(@PathVariable Long alertId,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        alertService.deleteAlert(userDetails.getUsername(), alertId);
        return ResponseEntity.ok("Alert deleted");
    }
}