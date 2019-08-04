
package com.harsukh.githubusers.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GitUsersResponseModel {
    @SerializedName("total_count")
    private Integer totalCount;

    @SerializedName("incomplete_results")
    private Boolean incompleteResults;

    @SerializedName("items")
    private List<UsersListModel> usersListModels;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<UsersListModel> getUsersListModels() {
        return usersListModels;
    }

    public void setUsersListModels(List<UsersListModel> usersListModels) {
        this.usersListModels = usersListModels;
    }

}
