package com.wanted.crud.course.model.dto;

public class CourseReviewDTO {

    private Long courseId;
    private String title;
    private String description;
    private Long price;
    private Double avgRating;

    public CourseReviewDTO() {}

    public CourseReviewDTO (Long courseId, String title, String description, Long price, Double avgRating){
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.avgRating = avgRating;
    }

    public Long getCourseId(){
        return courseId;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Long getPrice(){
        return price;
    }

    public Double getAvgRating(){
        return  avgRating;
    }

    public void setCourseId(Long courseId){this.courseId = courseId; }
    public void setTitle(String title){this.title = title; }
    public void setDescription(String description){this.description = description; }
    public void setPrice(Long price){this.price = price; }
    public void setAvgRating(Double avgRating){this.avgRating = avgRating; }


    @Override
    public String toString() {
        // 평점이 0.0보다 크면 별을 찍어주고, 아니면 리뷰 없음 처리
        String ratingStr = (avgRating != null && avgRating > 0)
                ? "⭐ 평  점 : " + String.format("%.1f", avgRating)
                : "❌ 등록된 리뷰가 없습니다.";

        String priceStr = String.format("%,d", price);

        return "╭───────────────────────────────────────────╮\n" +
                "  📌 강좌 번호 : " + courseId + "\n" +
                "  📖 강 좌 명 : " + title + "\n" +
                "  💬 소    개 : " + description + "\n" +
                "  💰 가    격 : " + priceStr + "원\n" +
                "  " + ratingStr + "\n" +
                "╰───────────────────────────────────────────╯";
    }


}
