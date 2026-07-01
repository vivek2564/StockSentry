package com.vivek.stocksentry.controller;

import com.vivek.stocksentry.dto.WatchlistRequest;
import com.vivek.stocksentry.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping
    public ResponseEntity<?> addSymbol(@RequestBody WatchlistRequest request,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(watchlistService.addSymbol(
                userDetails.getUsername(), request.getSymbol()));
    }

    @GetMapping
    public ResponseEntity<?> getWatchlist(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(watchlistService.getWatchlist(userDetails.getUsername()));
    }

    @DeleteMapping("/{symbol}")
    public ResponseEntity<?> removeSymbol(@PathVariable String symbol,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        watchlistService.removeSymbol(userDetails.getUsername(), symbol);
        return ResponseEntity.ok("Removed " + symbol + " from watchlist");
    }
}