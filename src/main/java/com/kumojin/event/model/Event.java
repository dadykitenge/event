package com.kumojin.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kumojin.event.constants.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 32)
    @NotBlank(message = "Please add the event name!")
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_description")
    private String eventDescription;

    @NotNull
    @DateTimeFormat(pattern = Constant.LOCAL_DATE_TIME_FORMAT)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull
    @DateTimeFormat(pattern = Constant.LOCAL_DATE_TIME_FORMAT)
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "zone_offset")
    private String zoneOffset;

}