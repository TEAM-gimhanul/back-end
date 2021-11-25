package com.backend.gimhanul.global.utils.api.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class FilterRequest {

	private final List<String> text;

	public FilterRequest(List<String> text) {
		List<String> bufList = new ArrayList<>();

		for(String value : text) {
			if(value.getBytes().length <= 1)
				bufList.add(value.replace(value, "쩝"));
			else
				bufList.add(value);
		}

		this.text = bufList;
	}

}
