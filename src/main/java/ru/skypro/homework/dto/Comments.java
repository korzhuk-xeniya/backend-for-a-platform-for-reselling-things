package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class Comments {

    private Integer count = null;
    private List<CommentDto> results = null;

//    public Comments count(Integer count) {
//        this.count = count;
//        return this;
//    }
//
//
//    /**
//     * общее количество комментариев
//     * @return count
//     **/
//    @Schema(description = "общее количество комментариев")
//
//    public Integer getCount() {
//        return count;
//    }
//
//    public void setCount(Integer count) {
//        this.count = count;
//    }
//
//    public Comments results(List<CommentDto> results) {
//        this.results = results;
//        return this;
//    }
//
//    public Comments addResultsItem(CommentDto resultsItem) {
//        if (this.results == null) {
//            this.results = new ArrayList<CommentDto>();
//        }
//        this.results.add(resultsItem);
//        return this;
//    }
//
//    /**
//     * Get results
//     * @return results
//     **/
//    @Schema(description = "")
//    public List<CommentDto> getResults() {
//        return results;
//    }
//
//    public void setResults(List<CommentDto> results) {
//        this.results = results;
//    }
//
//
//    @Override
//    public boolean equals(java.lang.Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Comments comments = (Comments) o;
//        return Objects.equals(this.count, comments.count) &&
//                Objects.equals(this.results, comments.results);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(count, results);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("class Comments {\n");
//
//        sb.append("    count: ").append(toIndentedString(count)).append("\n");
//        sb.append("    results: ").append(toIndentedString(results)).append("\n");
//        sb.append("}");
//        return sb.toString();
//    }
//
//    /**
//     * Convert the given object to string with each line indented by 4 spaces
//     * (except the first line).
//     */
//    private String toIndentedString(java.lang.Object o) {
//        if (o == null) {
//            return "null";
//        }
//        return o.toString().replace("\n", "\n    ");
//    }

}
