#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ThumbHashModule, NSObject)

RCT_EXTERN_METHOD(decodeThumbHash:(NSString *)base64Hash
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(encodeThumbHash:(NSString *)base64Image
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

@end
