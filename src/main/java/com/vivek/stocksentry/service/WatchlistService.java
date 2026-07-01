package com.vivek.stocksentry.service;

import com.vivek.stocksentry.entity.User;
import com.vivek.stocksentry.entity.WatchlistItem;
import com.vivek.stocksentry.repository.UserRepository;
import com.vivek.stocksentry.repository.WatchlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistItemRepository watchlistItemRepository;

    @Autowired
    private UserRepository userRepository;

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public WatchlistItem addSymbol(String username, String symbol) {
        User user = getUser(username);
        String upperSymbol = symbol.toUpperCase();

        if (watchlistItemRepository.existsByUserIdAndSymbol(user.getId(), upperSymbol)) {
            throw new RuntimeException(upperSymbol + " is already in your watchlist");
        }

        WatchlistItem item = WatchlistItem.builder()
                .user(user)
                .symbol(upperSymbol)
                .build();

        return watchlistItemRepository.save(item);
    }

    public List<WatchlistItem> getWatchlist(String username) {
        User user = getUser(username);
        return watchlistItemRepository.findByUserId(user.getId());
    }

    public void removeSymbol(String username, String symbol) {
        User user = getUser(username);
        WatchlistItem item = watchlistItemRepository
                .findByUserIdAndSymbol(user.getId(), symbol.toUpperCase())
                .orElseThrow(() -> new RuntimeException(symbol + " not found in watchlist"));
        watchlistItemRepository.delete(item);
    }
}