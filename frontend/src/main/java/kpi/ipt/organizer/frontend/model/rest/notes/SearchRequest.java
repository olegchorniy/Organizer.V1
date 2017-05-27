package kpi.ipt.organizer.frontend.model.rest.notes;

import lombok.*;

/**
 * @author olch0615
 *         Date: 5/15/2017
 *         Time: 1:32 PM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private String query;
}