package com.example.steamlensbackend.steam.services;

import com.example.steamlensbackend.steam.wrappers.Meta;
import com.example.steamlensbackend.steam.wrappers.PagedResponse;

import java.util.Collection;
import java.util.List;

public class PageableService {
    public static <T>PagedResponse<List<T>> paginate(Collection<T> data, int page, int pageSize) {
        int totalPages = (int) Math.ceil((double) data.size() / pageSize);
        int offset = page * pageSize;

        List<T> content = data.stream()
                .skip(offset)
                .limit(pageSize)
                .toList();

        return PagedResponse.of(content, new Meta(page, pageSize, totalPages, data.size()));
    }
}
