package com.moodrop.model.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PerfumeResponseDto {
	NoteScoreDto[] selectedNotes;
    Map<String, Integer> ratios;
}
