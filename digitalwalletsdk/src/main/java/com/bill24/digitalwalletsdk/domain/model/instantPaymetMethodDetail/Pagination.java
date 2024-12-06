package com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail;

import com.google.gson.annotations.SerializedName;

public class Pagination {
    public double getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(double currentPage) {
        this.currentPage = currentPage;
    }

    public double getPageSize() {
        return pageSize;
    }

    public void setPageSize(double pageSize) {
        this.pageSize = pageSize;
    }

    public double getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(double totalPages) {
        this.totalPages = totalPages;
    }

    public double getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(double totalItems) {
        this.totalItems = totalItems;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    @SerializedName("current_page")
    private double currentPage;
    @SerializedName("page_size")
    private double pageSize;
    @SerializedName("total_page")
    private double totalPages;
    @SerializedName("total_items")
    private double totalItems;
    @SerializedName("has_next_page")
    private boolean hasNextPage;
    @SerializedName("has_previous_page")
    private boolean hasPreviousPage;
}
