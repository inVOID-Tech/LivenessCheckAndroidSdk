## Minimum Requirements
- `minSdkVersion 21` 
- `AndroidX`

## Getting Started

Add following lines in your root ```build.gradle```
```
buildscript {

    allprojects {
        repositories {
            ...
            maven { url "https://dl.bintray.com/invoidandroid12/android/" }
        }
    }
}
```

Add following lines in your module level ```build.gradle```
```
android {
    ...
    compileOptions {
       sourceCompatibility = 1.8
       targetCompatibility = 1.8
    }
}
dependencies {
    ....
    implementation 'co.invoid.android:livenesscheck:1.0.0'
}
```

This library also uses some common android libraries. So if you are not already using them then make sure you add these libraries to your module level `build.gradle`
- `androidx.appcompat:appcompat:1.1.0`
- `androidx.constraintlayout:constraintlayout:1.1.3`
- `com.google.android.material:material:1.1.0`

## Initialize SDK

```
yourinitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivenessHelper.with(this@MainActivity, "YOUR_AUTH_KEY").start()
            }
        });
```

## Authorization 
To Obtain your organisation's authkey, contact us at hello@invoid.co


## Response returned from the SDK
- Selfie file path ```LivenessResponse.getSelfieFilePath()```
- Liveness response ```LivenessResponse.getSelfieFilePath()```
- Document back image file path ```photoHelperResult.getLivenessApiResponse()```

```
@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == LivenessHelper.LIVENESS_HELPER_REQ_CODE) {
        
            if(resultCode == RESULT_OK) {
                LivenessResponse livenessResponse = data.getParcelableExtra(LivenessHelper.RESULT);
       
           
           } else if(resultCode == LivenessHelper.AUTHORIZATION_RESULT) {
           
           int authorizationResult = data.getIntExtra(LivenessHelper.AUTHORIZATION_RESULT, -1);
                if(authorizationResult == LivenessHelper.UNAUTHORIZED) {
                    Log.d(TAG, "onActivityResult: unauthorized");
                } else {
                    Log.d(TAG, "onActivityResult: authorization error");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
```
