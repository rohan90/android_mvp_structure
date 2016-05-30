package com.rohan.poc_mvp.model.dtos;

import com.rohan.poc_mvp.model.domain.Repository;

import java.util.List;

/**
 * Created by rohan on 27/5/16.
 */
public class RepositoryListResponseDto {
    private List<Repository> data;

    public List<Repository> getData() {
        return data;
    }
}
