package com.backend.gimhanul.global.utils.api;

import com.backend.gimhanul.global.utils.api.exception.OtherServerBadRequestException;
import com.backend.gimhanul.global.utils.api.exception.OtherServerExpiredTokenException;
import com.backend.gimhanul.global.utils.api.exception.OtherServerForbiddenException;
import com.backend.gimhanul.global.utils.api.exception.OtherServerUnauthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		if(response.status() >= 400) {
			switch (response.status()){
				case 401:
					throw OtherServerUnauthorizedException.EXCEPTION;
				case 403:
					throw OtherServerForbiddenException.EXCEPTION;
				case 419:
					throw OtherServerExpiredTokenException.EXCEPTION;
				default:
					throw OtherServerBadRequestException.EXCEPTION;
			}
		}

		return FeignException.errorStatus(methodKey, response);
	}

}
