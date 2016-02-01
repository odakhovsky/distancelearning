package com.distancelearning.api;

import com.distancelearning.api.model.response.SimpleResponseBody;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public interface ResponseCallback<T extends SimpleResponseBody> {
    /**
     * Return data on success request
     * @param response contain request response data && result object
     */
    void onSuccess(T response);

    /**
     * Return data on failed operation
     * @param response contains status code and message
     */
    void onFailure(SimpleResponseBody response);
}
