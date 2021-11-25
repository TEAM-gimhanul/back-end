package com.backend.gimhanul.global.utils.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterRequest {

	private final List<String> text;

}
