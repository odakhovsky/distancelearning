//
//  Copyright (c) 2015 VK.com
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy of
//  this software and associated documentation files (the "Software"), to deal in
//  the Software without restriction, including without limitation the rights to
//  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
//  the Software, and to permit persons to whom the Software is furnished to do so,
//  subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in all
//  copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
//  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
//  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
//  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
//  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.distancelearning.api;

import android.support.annotation.Nullable;


public abstract class AccessTokenTracker {

    private boolean isTracking = false;

    /**
     * This method will be call only from main thread
     *
     * @param oldToken Token before changes
     * @param newToken Actual token after changes
     */
    public abstract void onAccessTokenChanged(@Nullable AccessToken oldToken, @Nullable AccessToken newToken);

    public boolean isTracking() {
        return isTracking;
    }

    public void startTracking() {
        DistanceApi.addVKTokenTracker(this);
        isTracking = true;
    }

    public void stopTracking() {
        DistanceApi.removeVKTokenTracker(this);
        isTracking = false;
    }
}