## Minimum Requirements
- `minSdkVersion 16` 
- `AndroidX`

## Getting Started

Add following lines in your root ```build.gradle```
```
allprojects {
    repositories {
        ...
        maven { url "https://dl.bintray.com/invoidandroid12/android/" }
    }
}

```

Add following lines in your module level ```build.gradle```
```
dependencies {
    ....
    implementation 'co.invoid.android:livenesscheck:1.0.5rc2'
}

```

This library also uses some common android libraries. So if you are not already using them then make sure you add these libraries to your module level `build.gradle`
- `androidx.appcompat:appcompat:1.0.0`
- `androidx.constraintlayout:constraintlayout:1.1.3`
- `com.google.android.material:material:1.0.0`

## Initialize SDK

```
yourinitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivenessHelper.with(YourActivity.this, "YOUR_AUTH_KEY").start()
            }
        });
```

## Authorization 
To Obtain your organisation's authkey, contact us at hello@invoid.co


## Response returned from the SDK
- Selfie file path ```LivenessResponse.getSelfieFilePath()```
- Liveness api response ```LivenessResponse.getLivenessApiResponse()```

```
@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == LivenessHelper.LIVENESS_HELPER_REQ_CODE) {
        
            if(resultCode == Activity.RESULT_OK) {
                LivenessResponse livenessResponse = data.getParcelableExtra(LivenessHelper.RESULT);
                String selfiePath = livenessResponse.getSelfieFilePath();
                LivenessApiResponse livenessApiResponse = livenessApiResponse.getLivenessApiResponse();
                
           } else if(resultCode == LivenessHelper.AUTHORIZATION_RESULT) {
                int authorizationResult = data.getIntExtra(LivenessHelper.AUTHORIZATION_RESULT, -1);
                if(authorizationResult == LivenessHelper.UNAUTHORIZED) {
                    Log.d(TAG, "onActivityResult: unauthorized");
                } else {
                    Log.d(TAG, "onActivityResult: authorization error");
                }
            } else if(resultCode == Activity.RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: cancelled by user");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
```

## Release Notes

### `1.0.5rc2`
- improve liveness detection
- add selfie sanity check

### `1.0.4`
- fix camera preview getting stretched on some devices
- add support for devices using Camera1 Api on API 21+

### `1.0.3`
- fix crash on devices running Android 8.0 (API Level 26)

### `1.0.2`
- minSdk version 16

### `1.0.1`
- Minor UI changes.

### `1.0.0`
- Inital release
