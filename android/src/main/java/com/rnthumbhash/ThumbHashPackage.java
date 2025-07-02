package com.rnthumbhash;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThumbHashPackage extends TurboReactPackage {

    @Override
    public NativeModule getModule(String name, ReactApplicationContext context) {
        if (name.equals(ThumbHashModule.NAME)) {
            return new ThumbHashModule(context);
        }
        return null;
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        // Android 不一定支持 Map.of，使用传统写法
        final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
        moduleInfos.put(
            ThumbHashModule.NAME,
            new ReactModuleInfo(
                ThumbHashModule.NAME,
                ThumbHashModule.class.getName(),
                true,   // canOverrideExistingModule
                false,  // needsEagerInit
                true,   // hasConstants
                false,  // isCxxModule
                true    // isTurboModule
            )
        );
        return () -> moduleInfos;
    }
}
